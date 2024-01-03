package com.outmao.ebs.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@Data
@Embeddable
@MappedSuperclass
public class Address implements Serializable {

	@ApiModelProperty(name = "latitude", value = "纬度")
	private Double latitude;// 纬度

	@ApiModelProperty(name = "longitude", value = "经度")
	private Double longitude;

	@ApiModelProperty(name = "province", value = "省份")
	private String province;

	@ApiModelProperty(name = "city", value = "城市")
	private String city;

	@ApiModelProperty(name = "district", value = "区")
	private String district;

	@ApiModelProperty(name = "street", value = "街道")
	private String street;

	@ApiModelProperty(name = "details", value = "street之后的详细地址")
	private String details;

	//zipCode
	@ApiModelProperty(name = "zipCode", value = "邮政编码")
	private String zipCode;

	@ApiModelProperty(name = "subway", value = "地铁站")
	private String subway;

	@ApiModelProperty(name = "fullAddress", value = "完整地址")
	private String fullAddress;

	public String toFullAddress(){
		if(fullAddress!=null&&fullAddress.length()>0)
			return fullAddress;
		StringBuffer sb=new StringBuffer();
		if(!StringUtils.isEmpty(province)){
			sb.append(province);
		}
		if(!StringUtils.isEmpty(city)&&!city.equals("市辖区")){
			sb.append(city);
		}
		if(!StringUtils.isEmpty(district)){
			sb.append(district);
		}
		if(!StringUtils.isEmpty(street)){
			sb.append(street);
		}
		if(!StringUtils.isEmpty(details)){
			sb.append(details);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return toFullAddress();
	}

	public String toShortAddress(){
		StringBuffer sb=new StringBuffer();
		if(!StringUtils.isEmpty(province)){
			sb.append(province);
		}
		if(!StringUtils.isEmpty(city)&&!city.equals("市辖区")){
			sb.append(city);
		}
		if(!StringUtils.isEmpty(district)){
			sb.append(district);
		}
		return sb.toString();
	}


}
