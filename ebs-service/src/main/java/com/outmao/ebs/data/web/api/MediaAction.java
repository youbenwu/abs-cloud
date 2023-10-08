package com.outmao.ebs.data.web.api;


import com.outmao.ebs.common.services.oss.OssService;
import com.outmao.ebs.common.services.oss.vo.OssSession;
import com.outmao.ebs.common.services.oss.vo.OssToken;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.entity.Media;
import com.outmao.ebs.data.service.MediaService;
import com.outmao.ebs.data.dto.MediaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Api(value = "data-media", tags = "数据-资源")
@RestController
@RequestMapping("/api/data/media")
public class MediaAction {


	@Autowired
    private OssService ossService;

	@Autowired
	private MediaService mediaService;


	@Value("${web.static-path}")
	private String savePath;

	@Value("${config.base-url}")
	private String baseUrl;

	@Value("${config.use-oss}")
	private boolean useOss;


	private String filePath(String path, String fileName) {
		return "media/" + path + "/" + fileName;
	}


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存媒体", notes = "保存媒体")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Media saveMedia(@RequestBody MediaDTO request){
		return mediaService.saveMedia(request);
	}


	@ApiOperation(value = "获取OSS上传TOKEN", notes = "获取OSS上传TOKEN")
	@ApiImplicitParams({

	})
	@RequestMapping(value = "/oss/session", method = RequestMethod.POST)
	public OssSession getOssSession(){
		return ossService.getOssSession();
	}


	@ApiOperation(value = "获取OSS上传TOKEN", notes = "获取OSS上传TOKEN")
	@ApiImplicitParams({

	})
	@RequestMapping(value = "/oss/token", method = RequestMethod.POST)
	public OssToken getOssToken(){
		return ossService.generateToken();
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "上传文件接口", notes = "上传文件,文件大小限制为10M")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "path", value = "业务类型", type = "path", required = true, dataType = "String"),
	})
	@RequestMapping(value = "/upload/{path}", method = RequestMethod.POST)
	public Map<String,List<String>> uploadFile(@PathVariable(value = "path") String path, HttpServletRequest request)
			throws IllegalStateException,IOException {
		if(useOss){
			return this.uploadFileOSS(path,request);
		}
		Assert.notNull(path, "type不能为空");
		Map<String,List<String>> values=new HashMap<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> names = multiRequest.getFileNames();
			while (names.hasNext()) {
				String name=names.next();
				List<MultipartFile> files = multiRequest.getFiles(name);
				List<String> paths = new ArrayList<>();
				for (MultipartFile file:files) {
					if (!file.isEmpty()) {
						String url=uploadMultipartFile(file,path,UUID.randomUUID().toString().replace("-",""));
						// 累加保存生成文件名
						paths.add(url);
					}
				}
				values.put(name,paths);
			}
		}
		return values;
	}


	@ApiOperation(value = "上传文件接口", notes = "上传文件,文件大小限制为10M")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "path", value = "业务类型", type = "path", required = true, dataType = "String"),
	})
	@RequestMapping(value = "/uploadOSS/{path}", method = RequestMethod.POST)
	public Map<String,List<String>> uploadFileOSS(@PathVariable(value = "path") String path, HttpServletRequest request)
			throws IllegalStateException ,IOException{
		Assert.notNull(path, "path");
		Map<String,List<String>> values=new HashMap<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> names = multiRequest.getFileNames();
			while (names.hasNext()) {
				String name=names.next();
				List<MultipartFile> files = multiRequest.getFiles(name);
				List<String> paths = new ArrayList<>();
				for (MultipartFile file:files) {
					if (!file.isEmpty()) {
						// 上传文件流。
						String url = uploadMultipartFileToOSS(file,path,UUID.randomUUID().toString().replace("-",""));
						// 累加保存生成文件名
						paths.add(url);
					}
				}
				values.put(name,paths);
			}
		}
		return values;
	}





	@ApiOperation(value = "上传媒体接口", notes = "上传媒体接口,文件大小限制为10M,文件名称传'file'，缩略图名称传'thumbnai'")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "path", value = "业务类型", type = "path", required = true, dataType = "String"),
	})
	@PreAuthorize("principal.id.equals(#userId)")
	@RequestMapping(value = "/uploadMedia/{path}", method = RequestMethod.POST)
	public Media uploadMedia(
			@PathVariable(value = "path") String path,
			Long userId,
			Long orgId,
			Integer width,
			Integer height,
			Double duration,
			Integer orientation,
			Long itemId,
			String itemType,
			HttpServletRequest request
	) throws IllegalStateException ,IOException{

		Assert.notNull(path, "path不能为空");

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			MultipartFile file=multiRequest.getFile("file");
			MultipartFile thumbnaiFile=multiRequest.getFile("thumbnai");

			String uuid=UUID.randomUUID().toString().replace("-","");

			String url= uploadMultipartFile(file,path,uuid);
			String thumbnaiUrl = thumbnaiFile==null||thumbnaiFile.isEmpty()?null :uploadMultipartFile(thumbnaiFile,path,uuid);

            long size=file.getSize();
			String contentType=file.getContentType();

			MediaDTO saveMediaRequest=new MediaDTO(
					null,
					uuid,
					orgId,
					userId,
					new BindingItem(itemId,itemType),
					contentType,
					url.substring(url.lastIndexOf(".")+1),
					url,
					thumbnaiUrl,
					size,
					width,
					height,
					duration,
					orientation
			);

			Media media=mediaService.saveMedia(saveMediaRequest);

			return media;

		}
		return null;
	}


    private String uploadMultipartFileToOSS(MultipartFile file, String path, String uuid)throws IOException{
		// 取得当前上传文件的文件名称
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 使用GUID重命名图片名称
		fileName = uuid + suffixName;
		// path
		String filePath = this.filePath(path, fileName);
		// 上传文件流。
		String url = ossService.putObject(filePath, file.getInputStream());
		return url;
	}


	private String uploadMultipartFile(MultipartFile file, String path, String uuid)throws IOException{
		if(useOss){
			return uploadMultipartFileToOSS(file,path,uuid);
		}
		// 取得当前上传文件的文件名称
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 使用GUID重命名图片名称
		fileName = uuid + suffixName;
		// 文件保存相对路径
		String filePath = this.filePath(path, fileName);
		// 文件最终保存全路径
		String fileNamePath = this.savePath + filePath;
		// 创建File对象
		File localFile = new File(fileNamePath);
		// 检测是否存在目录，不存在则创建
		if (!localFile.getParentFile().exists()) {
			localFile.getParentFile().mkdirs();
		}
		// 执行上传文件
		file.transferTo(localFile);
		// 累加保存生成文件名
		return baseUrl+"/"+ filePath;
	}



}
