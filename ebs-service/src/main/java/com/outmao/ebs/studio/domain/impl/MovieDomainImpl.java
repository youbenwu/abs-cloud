package com.outmao.ebs.studio.domain.impl;

import com.outmao.ebs.bbs.common.annotation.BindingSubjectId;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.mall.product.dto.ProductDTO;
import com.outmao.ebs.mall.product.dto.ProductSkuDTO;
import com.outmao.ebs.mall.product.dto.SaveProductSkuDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.service.ProductCategoryService;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shop.service.ShopService;
import com.outmao.ebs.studio.dao.*;
import com.outmao.ebs.studio.domain.MovieDomain;
import com.outmao.ebs.studio.domain.conver.MovieEpisodeVOConver;
import com.outmao.ebs.studio.domain.conver.MovieVOConver;
import com.outmao.ebs.studio.domain.conver.VideoVOConver;
import com.outmao.ebs.studio.dto.*;
import com.outmao.ebs.studio.entity.*;
import com.outmao.ebs.studio.vo.MovieEpisodeVO;
import com.outmao.ebs.studio.vo.MovieVO;
import com.outmao.ebs.studio.vo.VideoVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MovieDomainImpl extends BaseDomain implements MovieDomain {


    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieEpisodeDao movieEpisodeDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private UserMovieDao userMovieDao;

    @Autowired
    private UserMovieEpisodeDao userMovieEpisodeDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ShopService shopService;

    private MovieVOConver movieVOConver=new MovieVOConver();

    private MovieEpisodeVOConver movieEpisodeVOConver=new MovieEpisodeVOConver();

    private VideoVOConver videoVOConver=new VideoVOConver();

    @Transactional
    @BindingSubjectId
    @Override
    public Movie saveMovie(MovieDTO request) {
        Movie movie=request.getId()==null?null:movieDao.getOne(request.getId());

        if(movie==null){
            movie=new Movie();
            movie.setCreateTime(new Date());
        }

        movie.setUpdateTime(new Date());

        BeanUtils.copyProperties(request,movie);

        movie.setKeyword(getKeyword(movie));

        saveProduct(movie);

        movieDao.save(movie);


        return movie;
    }


    private void saveProduct(Movie movie){
        if(movie.getProductId()==null){
            Shop shop=shopService.getShopByOrgId(movie.getOrgId());
            ProductCategory category=productCategoryService.getProductCategoryByCode(ProductType.MOVIE.name());
            ProductDTO productDTO=new ProductDTO();
            productDTO.setTitle(movie.getName());
            productDTO.setCategoryId(category.getId());
            productDTO.setShopId(shop.getId());
            ProductSkuDTO skuDTO=new ProductSkuDTO();
            skuDTO.setSkuNo("0");
            skuDTO.setName("全集");
            skuDTO.setPrice(movie.getPrice());
            skuDTO.setStock(Long.MAX_VALUE);
            productDTO.setSkus(Arrays.asList(skuDTO));
            Product product=productService.saveProduct(productDTO);
            movie.setProductId(product.getId());
        }else{
            SaveProductSkuDTO skuDTO=new SaveProductSkuDTO();
            skuDTO.setProductId(movie.getProductId());
            skuDTO.setSkuNo("0");
            skuDTO.setName("全集");
            skuDTO.setPrice(movie.getPrice());
            skuDTO.setStock(Long.MAX_VALUE);
            productService.saveProductSku(skuDTO);
        }

    }

    private String getKeyword(Movie data){
        StringBuffer s=new StringBuffer();
        s.append(data.getName());
        if(data.getIntro()!=null){
            s.append(" "+data.getIntro());
        }
        return s.toString();
    }

    @Transactional
    @Override
    public void deleteMovieById(Long id) {
        Movie movie=movieDao.getOne(id);
        productService.deleteProductById(movie.getProductId());
        userMovieDao.deleteAllByMovieId(id);
        userMovieEpisodeDao.deleteAllByMovieId(id);
        videoDao.deleteAllByMovieId(id);
        movieEpisodeDao.deleteAllByMovieId(id);
        movieDao.delete(movie);
    }

    @Transactional
    @Override
    public void deleteAllByOrgId(Long orgId) {
        Collection<Long> productIds=movieDao.findAllProducttIdByOrgId(orgId);
        if(productIds.size()>0){
            productService.deleteProductList(productIds);
        }
        userMovieDao.deleteAllByOrgId(orgId);
        userMovieEpisodeDao.deleteAllByOrgId(orgId);
        videoDao.deleteAllByOrgId(orgId);
        movieEpisodeDao.deleteAllByOrgId(orgId);
        movieDao.deleteAllByOrgId(orgId);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieDao.findById(id).orElse(null);
    }

    @Override
    public MovieEpisode getMovieEpisodeById(Long id) {
        return movieEpisodeDao.findById(id).orElse(null);
    }

    @Override
    public MovieVO getMovieVOById(Long id) {
        QMovie e=QMovie.movie;
        MovieVO vo=queryOne(e,e.id.eq(id),movieVOConver);
        if(vo!=null){
            vo.setEpisodes(getMovieEpisodeVOListByMovieId(id));
        }
        return vo;
    }

    @Override
    public Page<MovieVO> getMovieVOPage(GetMovieListDTO request, Pageable pageable) {
        QMovie e=QMovie.movie;

        Predicate p=null;
        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getCategoryId()!=null){
            p=e.categoryId.eq(request.getCategoryId()).and(p);
        }

        if(request.getFeeType()!=null){
           p=e.feeType.eq(request.getFeeType()).and(p);
        }

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getFeeUserId()!=null){
            Collection<Long> idIn=userMovieDao.findAllMovieIdByUserId(request.getFeeUserId());
            Collection<Long> idIn2=userMovieEpisodeDao.findAllMovieIdByUserId(request.getFeeUserId());
            idIn.addAll(idIn2);
            p=e.id.in(idIn).and(p);
        }

        Page<MovieVO> page= queryPage(e,p,movieVOConver,pageable);

        setEpisode(page.getContent());

        return page;
    }

    private void setEpisode(List<MovieVO> list){
        if(list.isEmpty())
            return;
        Map<Long,MovieVO> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        List<MovieEpisodeVO> episodes=getMovieEpisodeVOListByMovieIdInAndFirst(listMap.keySet());
        for (MovieEpisodeVO e:episodes){
            MovieVO vo=listMap.get(e.getMovieId());
            if(vo.getEpisodes()==null){
                vo.setEpisodes(new ArrayList<>());
            }
            vo.getEpisodes().add(e);
        }
    }

    @Transactional
    @BindingSubjectId
    @Override
    public MovieEpisode saveMovieEpisode(MovieEpisodeDTO request) {
        MovieEpisode episode=request.getId()==null?null:movieEpisodeDao.getOne(request.getId());
        if(episode==null){
            if(movieEpisodeDao.existsByMovieIdAndIndex(request.getMovieId(),request.getIndex())){
                throw new BusinessException("集号重复");
            }
            episode=new MovieEpisode();
            episode.setCreateTime(new Date());
            Movie movie=movieDao.getOne(request.getMovieId());
            episode.setOrgId(movie.getOrgId());
        }

        BeanUtils.copyProperties(request,episode);
        episode.setUpdateTime(new Date());

        saveProduct(episode);

        movieEpisodeDao.save(episode);

        return episode;
    }

    private void saveProduct(MovieEpisode episode){
        Movie movie=movieDao.getOne(episode.getMovieId());
        SaveProductSkuDTO skuDTO=new SaveProductSkuDTO();
        skuDTO.setProductId(movie.getProductId());
        skuDTO.setSkuNo(episode.getIndex()+"");
        skuDTO.setName("第"+episode.getIndex()+"集");
        skuDTO.setPrice(episode.getPrice());
        skuDTO.setStock(Long.MAX_VALUE);
        productService.saveProductSku(skuDTO);
    }


    @Transactional
    @Override
    public void deleteMovieEpisodeById(Long id) {
        userMovieEpisodeDao.deleteAllByEpisodeId(id);
        videoDao.deleteAllByEpisodeId(id);
        movieEpisodeDao.deleteById(id);
    }

    @Override
    public MovieEpisodeVO getMovieEpisodeVOById(Long id) {
        QMovieEpisode e=QMovieEpisode.movieEpisode;

        MovieEpisodeVO vo=queryOne(e,e.id.eq(id),movieEpisodeVOConver);

        if(vo!=null){
            vo.setVideos(getVideoVOList(new GetVideoListDTO(null,null,id)));
        }

        return vo;
    }

    @Override
    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieId(Long movieId) {
        QMovieEpisode e=QMovieEpisode.movieEpisode;
        List<MovieEpisodeVO> list=queryList(e,e.movieId.eq(movieId),movieEpisodeVOConver);
        setVideos(list);
        return list;
    }

    @Override
    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieIdIn(Collection<Long> movieIdIn) {
        QMovieEpisode e=QMovieEpisode.movieEpisode;
        List<MovieEpisodeVO> list=queryList(e,e.movieId.in(movieIdIn),movieEpisodeVOConver);
        setVideos(list);
        return list;
    }

    public List<MovieEpisodeVO> getMovieEpisodeVOListByMovieIdInAndFirst(Collection<Long> movieIdIn) {
        QMovieEpisode e=QMovieEpisode.movieEpisode;
        List<MovieEpisodeVO> list=queryList(e,e.movieId.in(movieIdIn).and(e.index.eq(1)),movieEpisodeVOConver);
        setVideos(list);
        return list;
    }

    private void setVideos(List<MovieEpisodeVO> list){
        Map<Long,MovieEpisodeVO> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        List<VideoVO> videos=getVideoVOListByEpisodeIdIn(listMap.keySet());
        for (VideoVO v:videos){
            MovieEpisodeVO vo=listMap.get(v.getEpisodeId());
            if(vo.getVideos()==null){
                vo.setVideos(new ArrayList<>());
            }
            vo.getVideos().add(v);
        }
    }

    @Transactional
    @Override
    public Video saveVideo(VideoDTO request) {
        Video video=request.getId()==null?null:videoDao.getOne(request.getId());
        if(video==null){
            video=new Video();
            video.setCreateTime(new Date());
            MovieEpisode episode=movieEpisodeDao.getOne(request.getEpisodeId());
            video.setOrgId(episode.getOrgId());
            video.setMovieId(episode.getMovieId());
            video.setEpisodeId(episode.getId());
        }
        BeanUtils.copyProperties(request,video);
        video.setUpdateTime(new Date());
        videoDao.save(video);
        return video;
    }

    @Transactional
    @Override
    public void deleteVideoById(Long id) {
        videoDao.deleteById(id);
    }

    @Override
    public List<VideoVO> getVideoVOListByMovieIdIn(Collection<Long> movieIdIn) {
        QVideo e=QVideo.video;
        return queryList(e,e.movieId.in(movieIdIn),videoVOConver);
    }

    @Override
    public List<VideoVO> getVideoVOListByEpisodeIdIn(Collection<Long> episodeIdIn) {
        QVideo e=QVideo.video;
        return queryList(e,e.episodeId.in(episodeIdIn),videoVOConver);
    }

    private Predicate getPredicate(GetVideoListDTO request){
        QVideo e=QVideo.video;
        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId()).and(p);
        }

        if(request.getMovieId()!=null){
            p=e.movieId.eq(request.getMovieId()).and(p);
        }

        if(request.getEpisodeId()!=null){
            p=e.episodeId.eq(request.getEpisodeId()).and(p);
        }

        return p;
    }

    @Override
    public List<VideoVO> getVideoVOList(GetVideoListDTO request) {
        QVideo e=QVideo.video;
        Predicate p=getPredicate(request);

        return queryList(e,p,videoVOConver);
    }

    @Override
    public Page<VideoVO> getVideoVOPage(GetVideoListDTO request, Pageable pageable) {
        QVideo e=QVideo.video;
        Predicate p=getPredicate(request);
        return queryPage(e,p,videoVOConver,pageable);
    }

    @Transactional
    @Override
    public UserMovie saveUserMovie(Long userId, Long movieId) {
        UserMovie movie=new UserMovie();
        movie.setOrgId(movieDao.getOne(movieId).getOrgId());
        movie.setMovieId(movieId);
        movie.setUserId(userId);
        userMovieDao.save(movie);
        return movie;
    }

    @Transactional
    @Override
    public UserMovieEpisode saveUserMovieEpisode(Long userId, Long episodeId) {
        UserMovieEpisode episode=new UserMovieEpisode();
        episode.setUserId(userId);
        MovieEpisode me=movieEpisodeDao.getOne(episodeId);
        episode.setMovieId(me.getMovieId());
        episode.setOrgId(me.getOrgId());
        episode.setEpisodeId(episodeId);
        userMovieEpisodeDao.save(episode);
        return episode;
    }


    @Override
    public UserMovieEpisode getUserMovieEpisode(Long userId, Long episodeId) {
        return userMovieEpisodeDao.findByUserIdAndEpisodeId(userId,episodeId);
    }


}
