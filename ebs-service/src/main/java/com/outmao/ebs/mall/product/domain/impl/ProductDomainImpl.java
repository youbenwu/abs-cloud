package com.outmao.ebs.mall.product.domain.impl;


import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Address;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.amap.AmapClient;
import com.outmao.ebs.common.services.amap.vo.GeocodeResult;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.bbs.common.annotation.BindingSubjectId;
import com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd;
import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.mall.product.common.constant.ProductStatus;
import com.outmao.ebs.mall.product.common.util.SaveProductHolder;
import com.outmao.ebs.mall.product.common.util.SpecAlgorithm;
import com.outmao.ebs.mall.product.dao.*;
import com.outmao.ebs.mall.product.domain.ProductDomain;
import com.outmao.ebs.mall.product.domain.conver.*;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.*;
import com.outmao.ebs.mall.product.vo.*;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.entity.QShop;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.store.domain.StoreProductDomain;
import com.outmao.ebs.mall.store.domain.StoreSkuDomain;
import com.outmao.ebs.mall.store.dto.StoreProductDTO;
import com.outmao.ebs.mall.store.entity.QStoreProduct;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.MathExpressions.*;

@Component
public class ProductDomainImpl extends BaseDomain implements ProductDomain {




    @Autowired
    private QrCodeService qrcodeService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductAttributeGroupDao productAttributeGroupDao;

    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Autowired
    private ProductPropertyDao productPropertyDao;

    @Autowired
    private ProductPropertyItemDao productPropertyItemDao;

    @Autowired
    private ProductMediaDao productMediaDao;

    @Autowired
    private ProductAddressDao productAddressDao;

    @Autowired
    private ProductSalesAddressDao productSalesAddressDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private ProductImageDao productImageDao;

    @Autowired
    private ShopDao shopDao;


    @Autowired
    private StoreProductDomain storeProductDomain;

    @Autowired
    private StoreSkuDomain storeSkuDomain;


    @Autowired
    private AmapClient amapClient;


    private ProductVOConver productVOConver=new ProductVOConver();
    private SimpleProductVOConver simpleProductVOConver=new SimpleProductVOConver();
    private ProductAttributeGroupVOConver productAttributeGroupVOConver=new ProductAttributeGroupVOConver();
    private ProductAttributeVOConver productAttributeVOConver=new ProductAttributeVOConver();
    private ProductPropertyVOConver productPropertyVOConver=new ProductPropertyVOConver();
    private ProductSkuVOConver productSkuVOConver=new ProductSkuVOConver();
    private ProductMediaVOConver productMediaVOConver=new ProductMediaVOConver();
    private ProductAddressVOConver productAddressVOConver=new ProductAddressVOConver();
    private ProductSalesAddressVOConver productSalesAddressVOConver=new ProductSalesAddressVOConver();
    private ProductImageVOConver productImageVOConver=new ProductImageVOConver();

    @Autowired
    private MiniProductSkuVOConver miniProductSkuVOConver;
    @Autowired
    private MiniProductVOConver miniProductVOConver;


    @Transactional
    @BindingSubjectId
    @Override
    public Product saveProduct(ProductDTO request) {

        if(request.getShopId()==null){
            Long shopId=QF.select(QShop.shop.id).from(QShop.shop).fetchFirst();
            request.setShopId(shopId);
        }

        if(StringUtil.isEmpty(request.getTitle())){
            throw new BusinessException("标题不能为空");
        }

        if(request.getImages()==null||request.getImages().isEmpty()) {
            throw new BusinessException("最少上传一张图片");
        }

        if(StringUtil.isEmpty(request.getImage())){
            for(ProductImageDTO t:request.getImages()) {
                if (t.getType() == 0) {
                    request.setImage(t.getUrl());
                    break;
                }
            }
        }

        if(StringUtil.isEmpty(request.getImage())){
            throw new BusinessException("商品图片不能为空");
        }

        Product product=request.getId()==null?null:productDao.findByIdForUpdate(request.getId());

        Shop shop=shopDao.getOne(product!=null?product.getShopId():request.getShopId());

        if(product==null){
            product=new Product();
            product.setCreateTime(new Date());
            product.setOrgId(shop.getOrgId());
            product.setUserId(shop.getUserId());
            product.setMerchantId(shop.getMerchantId());
            product.setShopId(request.getShopId());
        }

        security.hasPermission(product.getOrgId(),null);

        product.setLetter(StringUtil.toPinyin(request.getTitle()));

        BeanUtils.copyProperties(request,product,"shopId","attributes","properties","skus","images","medias");

        product.setUpdateTime(new Date());

        productDao.save(product);

        if(product.getUrl()==null){
            String url=config.getBaseUrl()+"/product?id="+product.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            product.setUrl(url);
            product.setQrCode(qrCode);
        }

        saveProductAddress(product,request.getAddress());

        saveProductSalesAddress(product,request.getSalesAddress());

        saveProductAttributeGroupList(product,request.getAttributes());

        SaveProductHolder holder=new SaveProductHolder();
        holder.request=request;
        holder.product=product;

        //保存商品SKU属性
        saveProductPropertyList(holder);
        //保存商品SKU列表
        saveProductSkuList(holder);
        //保存商品图片列表
        saveProductImageList(holder);
        //保存商品相册列表
        saveProductMediaList(holder);

        //商品关联到门店
        if(request.getStoreId()!=null){
            storeProductDomain.saveStoreProduct(new StoreProductDTO(request.getStoreId(),product.getId()));
        }

        //设置keyword
        product.setKeyword(getKeyword(product));

        //更新库存
        if(shop.isUseStoreStock()) {
            storeSkuDomain.updateStore(product);
        }

        return product;
    }




    private void saveProductPropertyList(SaveProductHolder holder){

        Product product=holder.product;
        List<ProductPropertyDTO> data=holder.request.getProperties();

        if(data==null||data.isEmpty()){
            return;
        }

        check(data);

        List<ProductProperty> properties=new ArrayList<>(data.size());

        int sort=0;
        for(ProductPropertyDTO dto:data){
            ProductProperty p=new ProductProperty();
            p.setSort(sort++);
            p.setProductId(product.getId());
            BeanUtils.copyProperties(dto,p);
            properties.add(p);
        }

        productPropertyDao.saveAll(properties);
        Collection<Long> ids=properties.stream().map(t->t.getId()).collect(Collectors.toList());
        productPropertyItemDao.deleteAllByProductIdAndPropertyIdNotIn(product.getId(),ids);
        productPropertyDao.deleteAllByProductIdAndIdNotIn(product.getId(),ids);

        Map<Long,List<ProductPropertyItem>> itemsMap=new HashMap<>();

        for(int i=0;i<properties.size();i++){
            List<ProductPropertyItem> items=saveProductPropertyItemList(properties.get(i),data.get(i).getItems());
            itemsMap.put(properties.get(i).getId(),items);
        }

        holder.properties=properties;
        holder.propertyItemsMap=itemsMap;

    }


    private List<ProductPropertyItem> saveProductPropertyItemList(ProductProperty property,List<ProductPropertyItemDTO> data){

        List<ProductPropertyItem> list=new ArrayList<>(data.size());
        for(ProductPropertyItemDTO dto:data){
            ProductPropertyItem item=new ProductPropertyItem();
            item.setProductId(property.getProductId());
            item.setPropertyId(property.getId());
            item.setKey(property.getKey());
            BeanUtils.copyProperties(dto,item);
            list.add(item);
        }

        productPropertyItemDao.saveAll(list);
        productPropertyItemDao.deleteAllByPropertyIdAndIdNotIn(property.getId(),list.stream().map(t->t.getId()).collect(Collectors.toList()));

        String value= JsonUtil.toJson(list);
        property.setValue(value);

        return list;

    }


    private List<ProductSkuDTO> getProductSkuDTOList(Product product,
                                                     List<ProductProperty> properties,
                                                     Map<Long,List<ProductPropertyItem>> itemsMap,
                                                     List<ProductSkuDTO> data){

        if(properties==null||properties.isEmpty()) {
            if(data==null||data.isEmpty()){
                throw new BusinessException("最少包含一个SKU");
            }
            Collection<String> names=new HashSet<>();
            data.forEach(t->{
                if(StringUtil.isEmpty(t.getName())){
                    throw new BusinessException("SKU名称不能为空");
                }
                names.add(t.getName());
            });
            if(names.size()!=data.size()){
                throw new BusinessException("SKU名称重复");
            }
            return data;
        }

        List<String>[] specs=new List[properties.size()];
        for(int i=0;i<properties.size();i++){
            ProductProperty p=properties.get(i);
            specs[i]=itemsMap.get(p.getId()).stream().map(t->t.getValue()).collect(Collectors.toList());
        }

        SpecAlgorithm<String> specAlgorithm=new SpecAlgorithm<String>();
        List<List<String>> skuNames=specAlgorithm.specesPlan(specs);

        List<ProductSkuDTO> list=new ArrayList<>(skuNames.size());

        for(List<String> names:skuNames){
            StringBuilder sb = new StringBuilder();
            for(String str: names){//拼接组合
                sb.append(str+" " );
            }
            ProductSkuDTO skuDTO=new ProductSkuDTO();
            skuDTO.setName(sb.toString().trim());
            //System.out.println(skuDTO.getName());
            list.add(skuDTO);
        }

        List<String> names=list.stream().map(t->t.getName()).collect(Collectors.toList());

        for (ProductSkuDTO dto : data) {
            if(!names.contains(dto.getName())){
                throw new BusinessException("SKU名称不匹配");
            }
        }

        Map<String,ProductSkuDTO> dataMap=data.stream().collect(Collectors.toMap(t->t.getName(),t->t));

        for (ProductSkuDTO sku:list){
            ProductSkuDTO dto=dataMap.get(sku.getName());
            if(dto!=null) {
                BeanUtils.copyProperties(dto, sku);
            }
        }

        return list;

    }


    private void saveProductSkuList(SaveProductHolder holder){

        List<ProductSkuDTO> data=getProductSkuDTOList(holder.product,holder.properties,holder.propertyItemsMap,holder.request.getSkus());


        List<ProductSku> list=new ArrayList<>(data.size());

        for(ProductSkuDTO dto:data){
            ProductSku sku=new ProductSku();
            sku.setKey(UUID.randomUUID().toString());
            sku.setProduct(holder.product);
            if(dto.getValue()!=null) {
                sku.setValue(JsonUtil.toJson(dto.getValue()));
            }
            BeanUtils.copyProperties(dto,sku);
            list.add(sku);
            if(dto.getImages()!=null) {
                dto.getImages().forEach(t -> t.setSkuKey(sku.getKey()));
            }

        }

        productSkuDao.saveAll(list);
        productSkuDao.deleteAllByProductIdAndIdNotIn(holder.product.getId(),list.stream().map(t->t.getId()).collect(Collectors.toList()));


        double minPrice=0;
        double maxPrice=0;
        double minUnitPrice=0;
        double maxUnitPrice=0;
        int stock=0;
        for(ProductSku sku:list){
            stock+=sku.getStock();
            if(minPrice==0||sku.getPrice()<minPrice)
                minPrice=sku.getPrice();
            if(sku.getPrice()>maxPrice)
                maxPrice=sku.getPrice();
            if(sku.getUnitPrice()!=null){
                if(minUnitPrice==0||sku.getUnitPrice()<minUnitPrice)
                    minUnitPrice=sku.getUnitPrice();
            }
            if(sku.getUnitPrice()!=null&&sku.getUnitPrice()>maxUnitPrice)
                maxUnitPrice=sku.getUnitPrice();
        }

        holder.product.setStock(stock);
        holder.product.setPrice(minPrice);
        holder.product.setMaxPrice(maxPrice);
        holder.product.setUnitPrice(minUnitPrice);

        holder.product.setSkus(list);

    }



    @Transactional
    @Override
    public Product setProductStock(SetProductStockDTO request) {
        Product product=productDao.findByIdForUpdate(request.getId());
        Shop shop=shopDao.getOne(product.getShopId());

        if(shop.isUseStoreStock()){
            throw new BusinessException("请使用仓库管理库存");
        }

        QProductSku qs=QProductSku.productSku;

        QF.update(qs).set(qs.stock,request.getStock()).where(qs.product.id.eq(request.getId())).execute();

        long stock=QF.select(qs.stock.sum()).from(qs).where(qs.product.id.eq(request.getId())).fetchOne();

        product.setStock(stock);

        productDao.save(product);

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {

        productAttributeDao.deleteAllByProductId(id);
        productAttributeGroupDao.deleteAllByProductId(id);
        productPropertyItemDao.deleteAllByProductId(id);
        productPropertyDao.deleteAllByProductId(id);
        productSkuDao.deleteAllByProductId(id);
        productAddressDao.deleteByProductId(id);
        productSalesAddressDao.deleteByProductId(id);
        productImageDao.deleteAllByProductId(id);
        productMediaDao.deleteAllByProductId(id);
        productDao.deleteById(id);

    }

    private String getKeyword(Product m){
        StringBuffer s=new StringBuffer();
        if(m.getTitle()!=null){
            s.append(" "+m.getTitle());
        }
        if(m.getSubtitle()!=null){
            s.append(" "+m.getSubtitle());
        }
        if(m.getDescription()!=null){
            s.append(" "+m.getDescription());
        }
        if(m.getDetails()!=null){
            s.append(" "+m.getDetails());
        }

        List<String> stores=storeProductDomain.getStoreTitleListByProductId(m.getId());

        if(stores!=null&&stores.size()>0){
            stores.forEach(t->{
                s.append(" "+t);
            });
        }

        return s.toString();
    }

    private void saveProductImageList(SaveProductHolder holder){

        List<ProductImageDTO> data=holder.request.getImages();

        //SKU中的图片也一起保存
        if(holder.request.getSkus()!=null){
            holder.request.getSkus().forEach(sku->{
                if(sku.getImages()!=null){
                    holder.request.getImages().addAll(sku.getImages());
                }
            });
        }

        List<ProductImage> list=new ArrayList<>(data.size());
        data.forEach(t->{
            ProductImage m=new ProductImage();
            m.setProduct(holder.product);
            m.setCreateTime(new Date());
            BeanUtils.copyProperties(t,m);
            list.add(m);
        });
        productImageDao.saveAll(list);
        productImageDao.deleteAllByProductIdAndIdNotIn(holder.product.getId(),list.stream().map(t->t.getId()).collect(Collectors.toList()));

    }

    private void saveProductAddress(Product product,ProductAddressDTO data){
        if(data==null)
            return;
        ProductAddress address=product.getAddress()==null?null:product.getAddress();

        if(address==null){
            address=new ProductAddress();
            address.setCreateTime(new Date());
            address.setProduct(product);
        }

        BeanUtils.copyProperties(data,address,"id");
        address.setUpdateTime(new Date());

        if(address.getLatitude()==null||address.getLongitude()==null){
            geocodeAddr(address);
        }

        productAddressDao.save(address);
        product.setAddress(address);

    }

    private void saveProductSalesAddress(Product product,ProductAddressDTO data){
        if(data==null)
            return;
        ProductSalesAddress address=product.getSalesAddress()==null?null:product.getSalesAddress();

        if(address==null){
            address=new ProductSalesAddress();
            address.setCreateTime(new Date());
            address.setProduct(product);
        }

        BeanUtils.copyProperties(data,address,"id");

        address.setUpdateTime(new Date());

        if(address.getLatitude()==null||address.getLongitude()==null){
            geocodeAddr(address);
        }

        productSalesAddressDao.save(address);

        product.setSalesAddress(address);

    }


    private void geocodeAddr(Address address){
        String addr=address.toFullAddress();
        if(addr.trim().length()>0) {
            GeocodeResult gr = amapClient.geocode(addr);
            if (gr.isSuccess()) {
                String[] loc = gr.getGeocodes().get(0).getLocation().split(",");
                address.setLatitude(Double.parseDouble(loc[1]));
                address.setLongitude(Double.parseDouble(loc[0]));
            }
        }
    }


    private void saveProductMediaList(SaveProductHolder holder){

        List<ProductMediaDTO> data=holder.request.getMedias();

        if(data==null||data.isEmpty())
            return;

        List<ProductMedia> list=new ArrayList<>(data.size());
        data.forEach(t->{
            ProductMedia m=new ProductMedia();
            m.setProduct(holder.product);
            m.setCreateTime(new Date());
            BeanUtils.copyProperties(t,m);
            list.add(m);
        });
        productMediaDao.saveAll(list);
        productMediaDao.deleteAllByProductIdAndIdNotIn(holder.product.getId(),list.stream().map(t->t.getId()).collect(Collectors.toList()));
    }



    private void check(List<ProductPropertyDTO> data){
        Collection<String> names=new HashSet<>();
        data.forEach(t->{
            if(t.getItems()==null&&StringUtil.isNotEmpty(t.getValue())){
                t.setItems((List<ProductPropertyItemDTO>)JsonUtil.fromJson(t.getValue(),List.class,ProductPropertyItemDTO.class));
            }
            if(StringUtil.isEmpty(t.getName())){
                throw new BusinessException("属性名称不能为空");
            }
            names.add(t.getName());
            if(t.getItems()==null||t.getItems().isEmpty()){
                throw new BusinessException("属性值不能为空");
            }
            Collection<String> vs=new HashSet<>();
            t.getItems().forEach(v->{
                if(StringUtil.isEmpty(v.getValue())){
                    throw new BusinessException("属性值不能为空");
                }
                vs.add(v.getValue());
            });
            if(vs.size()!=t.getItems().size()){
                throw new BusinessException("属性值重复");
            }
        });
        if(names.size()!=data.size()){
            throw new BusinessException("属性名称重复");
        }
    }





    private List<ProductPropertyDTO> getProductPropertyDTOList(List<ProductSkuDTO> data){

        if(data==null
                ||data.isEmpty()
                ||data.get(0).getValue()==null)
            return null;


        List<ProductPropertyDTO> list=new ArrayList<>(data.get(0).getValue().size());
        for(int i=0;i<list.size();i++){
            ProductPropertyDTO p=new ProductPropertyDTO();
            p.setItems(new ArrayList<>());
            for (ProductSkuDTO sku:data){
                ProductSkuPropertyDTO item= sku.getValue().get(i);
                BeanUtils.copyProperties(item,p);
                ProductPropertyItemDTO value=p.getItem(item.getValue().getValue());
                if(value==null){
                    value=new ProductPropertyItemDTO();
                    BeanUtils.copyProperties(item.getValue(),value);
                    p.getItems().add(value);
                }
            }
        }

        return list;
    }


    private void saveProductAttributeGroupList(Product product, List<ProductAttributeGroupDTO> data){

        if(data==null||data.isEmpty()){
            return;
        }


        List<ProductAttributeGroup> list=new ArrayList<>(data.size());

        for(ProductAttributeGroupDTO dto:data){
            ProductAttributeGroup group= new ProductAttributeGroup();
            group.setProductId(product.getId());
            BeanUtils.copyProperties(dto,group);
            list.add(group);
        }

        productAttributeGroupDao.saveAll(list);

        Collection<Long> ids=list.stream().map(t->t.getId()).collect(Collectors.toList());

        productAttributeDao.deleteAllByProductIdAndGroupIdNotIn(product.getId(),ids);
        productAttributeGroupDao.deleteAllByProductIdAndIdNotIn(product.getId(),ids);


//        for(int i=0;i<list.size();i++){
//            saveProductAttributeList(list.get(i),data.get(i).getAttributes());
//        }


        List<ProductAttribute> attributes=new ArrayList<>(data.size());
        for(int i=0;i<list.size();i++){
            ProductAttributeGroup group=list.get(i);
            List<ProductAttributeDTO> attrs=data.get(i).getAttributes();
            for(ProductAttributeDTO dto:attrs){
                ProductAttribute attr= new ProductAttribute();
                attr.setProductId(group.getProductId());
                attr.setGroupId(group.getId());
                BeanUtils.copyProperties(dto,attr);
                attributes.add(attr);
            }
        }

        productAttributeDao.saveAll(attributes);

        productAttributeDao.deleteAllByProductIdAndIdNotIn(product.getId(),attributes.stream().map(t->t.getId()).collect(Collectors.toList()));


    }



    private void saveProductAttributeList(ProductAttributeGroup group, List<ProductAttributeDTO> data){

        if(data==null||data.isEmpty())
            return;

        List<ProductAttribute> list=new ArrayList<>(data.size());

        for(ProductAttributeDTO dto:data){
            ProductAttribute attr= new ProductAttribute();
            attr.setProductId(group.getProductId());
            attr.setGroupId(group.getId());
            BeanUtils.copyProperties(dto,attr);
            list.add(attr);
        }

        productAttributeDao.saveAll(list);

        Collection<Long> ids=list.stream().map(t->t.getId()).collect(Collectors.toList());

        productAttributeDao.deleteAllByGroupIdAndIdNotIn(group.getId(),ids);


    }



    @Transactional
    @Override
    public Product setProductStatus(SetProductStatusDTO request) {

        Product product=productDao.getOne(request.getId());

        security.hasPermission(product.getOrgId(),null);

        product.setStatus(request.getStatus());

        productDao.save(product);

        return product;
    }

    @Transactional
    @Override
    public Product setProductAuditStatus(SetProductAuditStatusDTO request) {
        Product product=productDao.getOne(request.getId());

        security.hasPermission(product.getOrgId(),null);

        product.setAuditStatus(request.getAuditStatus());

        productDao.save(product);

        return product;
    }

    @SubjectBrowsesAdd
    @SubjectItemFilter
    @Override
    public ProductVO getProductVOById(Long id) {


        QProduct e=QProduct.product;

        ProductVO vo=queryOne(e,e.id.eq(id),productVOConver);

        if(vo==null)
            return null;

        List<ProductImageVO> images=getProductImageVOList(id);
        vo.setImages(images.stream().filter(t->t.getSkuKey()==null).collect(Collectors.toList()));
        vo.setImages(getProductImageVOList(id));
        vo.setMedias(getProductMediaVOList(id));
        vo.setAttributes(getProductAttributeGroupVOList(id));
        vo.setProperties(getProductPropertyVOList(id));
        vo.setSkus(getProductSkuVOList(id));

        vo.getSkus().forEach(t->{
            t.setImages(images.stream().filter(i->t.getKey().equals(i.getSkuKey())).collect(Collectors.toList()));
        });

        if(vo.getAddressId()!=null) {
            vo.setAddress(getProductAddressVO(vo.getAddressId()));
        }

        if(vo.getSalesAddressId()!=null){
            vo.setSalesAddress(getProductSalesAddressVO(vo.getSalesAddressId()));
        }

        return vo;

    }

    @Override
    public ProductVO getProductVO(Long id, Long skuId) {
        QProduct e=QProduct.product;

        ProductVO vo=queryOne(e,e.id.eq(id),productVOConver);

        if(vo==null)
            return null;


        List<ProductImageVO> images=getProductImageVOList(id);
        vo.setImages(images.stream().filter(t->t.getSkuKey()==null).collect(Collectors.toList()));
        vo.setMedias(getProductMediaVOList(id));
        vo.setAttributes(getProductAttributeGroupVOList(id));

        if(skuId!=null){
            vo.setSkus(new ArrayList<>(1));
            vo.getSkus().add(getProductSkuVOById(skuId));
            vo.getSkus().forEach(t->{
                t.setImages(images.stream().filter(i->t.getKey().equals(i.getSkuKey())).collect(Collectors.toList()));
            });
        }

        if(vo.getAddressId()!=null) {
            vo.setAddress(getProductAddressVO(vo.getAddressId()));
        }

        if(vo.getSalesAddressId()!=null){
            vo.setSalesAddress(getProductSalesAddressVO(vo.getSalesAddressId()));
        }

        return vo;
    }

    private List<ProductImageVO> getProductImageVOList(Long productId){
        QProductImage e=QProductImage.productImage;
        List<ProductImageVO> list=queryList(e,e.product.id.eq(productId),productImageVOConver);
        return list;
    }

    private ProductAddressVO getProductAddressVO(Long id){
        QProductAddress e=QProductAddress.productAddress;

        return queryOne(e,e.id.eq(id),productAddressVOConver);
    }

    private ProductAddressVO getProductSalesAddressVO(Long id){
        QProductSalesAddress e=QProductSalesAddress.productSalesAddress;

        return queryOne(e,e.id.eq(id),productSalesAddressVOConver);
    }

    private List<ProductMediaVO> getProductMediaVOList(Long productId){
        QProductMedia e=QProductMedia.productMedia;
        List<ProductMediaVO> list=queryList(e,e.product.id.eq(productId),productMediaVOConver);
        return list;

    }

    private List<ProductSkuVO> getProductSkuVOList(Long productId){
        QProductSku e=QProductSku.productSku;
        List<ProductSkuVO> list=queryList(e,e.product.id.eq(productId),productSkuVOConver);
        return list;
    }

    private ProductSkuVO getProductSkuVOById(Long id){
        QProductSku e=QProductSku.productSku;
        ProductSkuVO vo=queryOne(e,e.id.eq(id),productSkuVOConver);
        return vo;
    }

    private List<ProductPropertyVO> getProductPropertyVOList(Long productId){
        QProductProperty e=QProductProperty.productProperty;
        List<ProductPropertyVO> list=queryList(e,e.productId.eq(productId),e.sort.asc(),productPropertyVOConver);
        return list;
    }


    private List<ProductAttributeGroupVO> getProductAttributeGroupVOList(Long productId){
        QProductAttributeGroup e=QProductAttributeGroup.productAttributeGroup;

        List<ProductAttributeGroupVO> list=queryList(e,e.productId.eq(productId),productAttributeGroupVOConver);

        Map<Long,ProductAttributeGroupVO> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));



        QProductAttribute q=QProductAttribute.productAttribute;

        List<ProductAttributeVO> attrs=queryList(q,q.productId.eq(productId),productAttributeVOConver);


        attrs.forEach(t->{
            ProductAttributeGroupVO g=listMap.get(t.getGroupId());
            g.getAttributes().add(t);
        });


        return list;
    }

    @Override
    public Page<ProductVO> getProductVOPage(GetProductListDTO request, Pageable pageable) {

        QProduct e=QProduct.product;

        Predicate p=null;

        if(request.getCategoryId()!=null){
            p=e.categoryId.eq(request.getCategoryId()).and(p);
        }

        if(request.getSCategoryId()!=null){
            p=e.sCategoryId.eq(request.getSCategoryId()).and(p);
        }

        if(request.getShopId()!=null){
            p=e.shopId.eq(request.getShopId()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        //价格区间
        if(request.getPriceBetween()!=null){
            p=e.price.between(Double.parseDouble(request.getPriceBetween().getFrom().toString()),Double.parseDouble(request.getPriceBetween().getTo().toString())).and(p);
        }


        if(request.getMarketTimeBetween()!=null){
            p=e.marketTime.between((Date) request.getMarketTimeBetween().getFrom(),(Date) request.getMarketTimeBetween().getTo()).and(p);
        }

        if(request.getAuditStatus()!=null){
            p=e.auditStatus.eq(request.getAuditStatus()).and(p);
        }else{
            if(!SecurityUtil.isAdminApi()) {
                p = e.auditStatus.eq(Status.NORMAL.getStatus()).and(p);
            }
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }else{
            if(!SecurityUtil.isAdminApi()) {
                p = e.status.eq(ProductStatus.ON_SELL.getStatus()).and(p);
            }
        }


        NumberExpression<Double> distance=null;
        if(request.getNear()!=null){
            distance = acos(sin(radians(Expressions.constant(request.getNear().getLatitude())))
                    .multiply(sin(radians(e.address.latitude)))
                    .add(cos(radians(Expressions.constant(request.getNear().getLatitude())))
                            .multiply(cos(radians(e.address.latitude)))
                            .multiply(cos(radians(Expressions.constant(request.getNear().getLongitude()))
                                    .subtract(radians(e.address.longitude)))))).multiply(6371);

        }


        Page<ProductVO> page;

        if(request.getStoreId()!=null){
            QStoreProduct q=QStoreProduct.storeProduct;
            JPAQuery query=QF.select(e).from(e,q).where(e.id.eq(q.product.id).and(q.store.id.eq(request.getStoreId())).and(p));
            page=queryPage(e,query,productVOConver,pageable,distance==null?null:distance.asc());
        }else{
            page=queryPage(e,p,productVOConver,pageable,distance==null?null:distance.asc());
        }

        if(request.getAttrs()!=null&&request.getAttrs().length>0){
            if(!page.getContent().isEmpty())
                setProductAttributeVOList(page.getContent(),request.getAttrs());
        }

        return page;
    }


    private void setProductAttributeVOList(List<ProductVO> data,String[] attrs){

        Map<Long,ProductVO> dataMap=data.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        QProductAttribute e=QProductAttribute.productAttribute;

        List<ProductAttributeVO> list=queryList(e,e.productId.in(dataMap.keySet()).and(e.key.in(attrs)),productAttributeVOConver);

        if(list.isEmpty())
            return;

        for(ProductAttributeVO a:list){
            ProductVO p=dataMap.get(a.getProductId());
            if(p.getAttributes()==null){
                p.setAttributes(new ArrayList<>());
                p.getAttributes().add(new ProductAttributeGroupVO());
                p.getAttributes().get(0).setAttributes(new ArrayList<>());
            }
            p.getAttributes().get(0).getAttributes().add(a);
        }

    }


    @Override
    public List<ProductVO> getProductVOListByIdIn(Collection<Long> idIn) {
        return getProductVOListByIdIn(idIn,null);
    }

    @Override
    public List<ProductVO> getProductVOListByIdIn(Collection<Long> idIn,String[] attrs) {

        QProduct e=QProduct.product;

        List<ProductVO> list=queryList(e,e.id.in(idIn),productVOConver);

        if(attrs!=null){
            if(!list.isEmpty())
                setProductAttributeVOList(list,attrs);
        }

        return list;
    }



    @Override
    public long getProductCount() {
        return productDao.count();
    }


    @Override
    public List<MiniProductVO> getMiniProductVOListByIdIn(Collection<Long> idIn) {
        QProduct e=QProduct.product;
        List<MiniProductVO> list=queryList(e,e.id.in(idIn),miniProductVOConver);
        return list;
    }

    @Override
    public List<MiniProductSkuVO> getMiniProductSkuVOListByIdIn(Collection<Long> idIn) {
        QProductSku e=QProductSku.productSku;
        List<MiniProductSkuVO> list=queryList(e,e.id.in(idIn),miniProductSkuVOConver);
        return list;
    }



}


