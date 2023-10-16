package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.product.common.constant.HouseConstant;
import com.outmao.ebs.mall.product.domain.HouseDomain;
import com.outmao.ebs.mall.product.domain.ProductDomain;
import com.outmao.ebs.mall.product.domain.ProductSnapshotDomain;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Component
public class HouseDomainImpl extends BaseDomain implements HouseDomain {


    @Autowired
    private ProductDomain productDomain;

    @Autowired
    private ProductSnapshotDomain productSnapshotDomain;

    @Transactional
    @Override
    public Product saveHouse(HouseDTO request) {

        ProductDTO data=new ProductDTO();

        org.springframework.beans.BeanUtils.copyProperties(request,data);

        data.setAttributes(houseAttributesDTO2ProductAttributeGroupDTOList(request,request.getAttributes()));

        data.setSkus(houseSkuDTOList2ProductSkuDTOList(request.getSkus()));




        return productDomain.saveProduct(data);
    }


    private List<ProductSkuDTO> houseSkuDTOList2ProductSkuDTOList(List<HouseSkuDTO> data){
        List<ProductSkuDTO> list=new ArrayList<>();


        data.forEach(s->{

            ProductSkuDTO sku=new ProductSkuDTO();
            org.springframework.beans.BeanUtils.copyProperties(s,sku);

            sku.setValue(new ArrayList<>());

            HouseSkuPropertyDTO p=s.getValue();

            /**
             * @ApiModelProperty(name = "houseRoomNum", value = "户型室数",required = true)
             *     private int houseRoomNum;
             *     @ApiModelProperty(name = "houseHallNum", value = "户型厅数",required = true)
             *     private int houseHallNum;
             *     @ApiModelProperty(name = "houseBathNum", value = "户型卫数",required = true)
             *     private int houseBathNum;
             *     @ApiModelProperty(name = "houseArea", value = "房屋面积(平方米)")
             *     private Integer houseArea;
             *     @ApiModelProperty(name = "houseDirection", value = "朝向：南")
             *     private String houseDirection;
             *     @ApiModelProperty(name = "houseFloor", value = "楼层 房屋所在的层号")
             *     private Integer houseFloor;
             * */

            sku.getValue().add(new ProductSkuPropertyDTO("houseRoomNum","房数",new ProductSkuPropertyItemDTO(p.getHouseRoomNum()+"房")));
            sku.getValue().add(new ProductSkuPropertyDTO("houseHallNum","厅数",new ProductSkuPropertyItemDTO(p.getHouseHallNum()+"厅")));
            sku.getValue().add(new ProductSkuPropertyDTO("houseBathNum","卫数",new ProductSkuPropertyItemDTO(p.getHouseBathNum()+"卫")));

            if(p.getHouseArea()!=null)
            sku.getValue().add(new ProductSkuPropertyDTO("houseArea","面积",new ProductSkuPropertyItemDTO(p.getHouseArea()+"平方米")));

            if(StringUtil.isNotEmpty(p.getHouseDirection()))
                sku.getValue().add(new ProductSkuPropertyDTO("houseDirection","朝向",new ProductSkuPropertyItemDTO(p.getHouseDirection())));

            if(p.getHouseFloor()!=null)
                sku.getValue().add(new ProductSkuPropertyDTO("houseFloor","楼层",new ProductSkuPropertyItemDTO(p.getHouseFloor()+"层")));

            list.add(sku);

        });


        return list;
    }

    private List<ProductAttributeGroupDTO> houseAttributesDTO2ProductAttributeGroupDTOList(HouseDTO house,HouseAttributesDTO data){

        List<ProductAttributeGroupDTO> list=new ArrayList<>();

        ProductAttributeGroupDTO g=new ProductAttributeGroupDTO();
        g.setKey(HouseConstant.PROPERTY_KEY_BASE_INFO);
        g.setName("基础信息");
        list.add(g);

        List<ProductAttributeDTO> attrs=new ArrayList<>();
        g.setAttributes(attrs);


        if(house.getAddress()!=null) {
            String area=(house.getAddress().getDistrict()!=null?house.getAddress().getDistrict():"")+(house.getAddress().getStreet()!=null?house.getAddress().getStreet():"");
            attrs.add(new ProductAttributeDTO(null, "houseDistrict", "地区", area,null));
        }
        if(house.getSkus()!=null&&house.getSkus().size()>0) {
            if(house.getType()==1){
                int min=0;
                int max=0;
                for(HouseSkuDTO sku:house.getSkus()){
                    if(sku.getValue().getHouseArea()!=null){
                        if(sku.getValue().getHouseArea()<min||min==0){
                            min=sku.getValue().getHouseArea();
                        }
                        if(sku.getValue().getHouseArea()>max){
                            max=sku.getValue().getHouseArea();
                        }
                    }
                }
                String area=String.format("建面%d-%dm²",min,max);
                attrs.add(new ProductAttributeDTO(null, "houseArea", "房屋面积",area ,null));
            }else {
                HouseSkuDTO sku=house.getSkus().get(0);
                if(sku.getValue().getHouseArea()!=null) {
                    String area = String.format("%dm²", sku.getValue().getHouseArea());
                    attrs.add(new ProductAttributeDTO(null, "houseArea", "房屋面积", area,null));
                }
            }
        }
        if(house.getSkus()!=null&&house.getSkus().size()>0)
            attrs.add(new ProductAttributeDTO(null,"doorModel","户型",house.getSkus().get(0).getName(),null));



        /**
         *
         *
         * 租房信息
         *
         *
         * */
        if(StringUtil.isNotEmpty(data.getHouseInTime()))
            attrs.add(new ProductAttributeDTO(null,"houseInTime","入住",data.getHouseInTime(),null));

        if(StringUtil.isNotEmpty(data.getHouseLease()))
            attrs.add(new ProductAttributeDTO(null,"houseLease","租期",data.getHouseLease(),null));


        if(StringUtil.isNotEmpty(data.getHouseFurn()))
            attrs.add(new ProductAttributeDTO(null,"houseFurn","配备",data.getHouseFurn(),null));


        if(data.getDeposit()!=null)
            attrs.add(new ProductAttributeDTO(null,"deposit","押金",data.getDeposit().toString(),"元"));


        if(data.getRentPay()!=null)
            attrs.add(new ProductAttributeDTO(null,"rentPay","租金付款方式",data.getRentPay().toString(),null));

        if(data.getServiceFee()!=null)
            attrs.add(new ProductAttributeDTO(null,"serviceFee","服务费",data.getServiceFee().toString(),"元"));

        if(data.getAgencyFee()!=null)
            attrs.add(new ProductAttributeDTO(null,"agencyFee","中介费",data.getAgencyFee().toString(),"元"));


        /**
         *
         *
         * 二手房信息
         *
         *
         * */



        if(StringUtil.isNotEmpty(data.getHouseLift()))
            attrs.add(new ProductAttributeDTO(null,"houseLift","电梯",data.getHouseLift(),null));

        if(StringUtil.isNotEmpty(data.getHouseDecor()))
            attrs.add(new ProductAttributeDTO(null,"houseDecor","装修",data.getHouseDecor(),null));
        if(StringUtil.isNotEmpty(data.getHouseEra()))

        if(StringUtil.isNotEmpty(data.getHouseEra()))
            attrs.add(new ProductAttributeDTO(null,"houseEra","建造年代",data.getHouseEra(),null));

        if(StringUtil.isNotEmpty(data.getHouseUse()))
            attrs.add(new ProductAttributeDTO(null,"houseUse","用途",data.getHouseEra(),null));

        if(StringUtil.isNotEmpty(data.getHouseCommunity()))
            attrs.add(new ProductAttributeDTO(null,"houseCommunity","所属小区",data.getHouseCommunity(),data.getHouseCommunityId().toString()));


//        if(StringUtil.isNotEmpty(data.getHouseListed()))
//            attrs.canAdd(new ProductAttributeDTO(null,"houseListed","挂牌",data.getHouseListed()));
//        attrs.canAdd(new ProductAttributeDTO(null,"houseEra","建造年代",data.getHouseEra()));
//        if(StringUtil.isNotEmpty(data.getHouseOwnership()))
//            attrs.canAdd(new ProductAttributeDTO(null,"houseOwnership","权属类别",data.getHouseOwnership()));

        g=new ProductAttributeGroupDTO();
        g.setKey("houseProject");
        g.setName("建筑规划");
        list.add(g);

        attrs=new ArrayList<>();
        g.setAttributes(attrs);

        if(StringUtil.isNotEmpty(data.getHouseType()))
            attrs.add(new ProductAttributeDTO(null,"houseType","建筑类型",data.getHouseType(),null));

        if(data.getHousePropYear()!=null)
            attrs.add(new ProductAttributeDTO(null,"housePropYear","产权年限",data.getHousePropYear()+"年",data.getHousePropYear().toString()));

        if(data.getHouseFar()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseFar","容积率",data.getHouseFar()+"%",data.getHouseFar().toString()));

        if(data.getHouseGsr()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseGsr","绿化率",data.getHouseGsr()+"%",data.getHouseGsr().toString()));

        if(data.getHouseHoldNum()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseHoldNum","规划用户",data.getHouseHoldNum()+"",data.getHouseHoldNum().toString()));

        if(data.getHouseFloorNum()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseFloorNum","楼层状况",data.getHouseFloorNum()+"层",data.getHouseFloorNum().toString()));

        if(data.getHouseFloorArea()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseFloorArea","占地面积",data.getHouseFloorArea()+"m²",data.getHouseFloorArea().toString()));

        if(data.getHouseLandArea()!=null)
            attrs.add(new ProductAttributeDTO(null,"houseLandArea","建地面积",data.getHouseLandArea()+"m²",data.getHouseLandArea().toString()));

        if(StringUtil.isNotEmpty(data.getHouseDev()))
            attrs.add(new ProductAttributeDTO(null,"houseDev","开发商",data.getHouseDev(),data.getHouseDevId()!=null?data.getHouseDevId().toString():null));



        g=new ProductAttributeGroupDTO();
        g.setKey("props");
        g.setName("物业信息");
        list.add(g);

        attrs=new ArrayList<>();
        g.setAttributes(attrs);

        if(StringUtil.isNotEmpty(data.getPropCompany()))
            attrs.add(new ProductAttributeDTO(null,"propCompany","物业公司",data.getPropCompany(),null));

        if(data.getPropFee()!=null)
            attrs.add(new ProductAttributeDTO(null,"propFee","物业费用",data.getPropFee().toString(),"元/m²/月"));

        if(data.getPropPartThan()!=null)
            attrs.add(new ProductAttributeDTO(null,"propPartThan","车位比",data.getPropPartThan(),null));

        if(data.getPropParkNum()!=null)
            attrs.add(new ProductAttributeDTO(null,"propParkNum","车位数",data.getPropParkNum().toString(),"个"));

        if(data.getPropPartGNum()!=null)
            attrs.add(new ProductAttributeDTO(null,"propPartGNum","地上车位数",data.getPropPartGNum().toString(),"个"));

        if(data.getPropPartLNum()!=null)
            attrs.add(new ProductAttributeDTO(null,"propPartLNum","地下车位数",data.getPropPartLNum().toString(),"个"));

        if(StringUtil.isNotEmpty(data.getPropHeatType()))
            attrs.add(new ProductAttributeDTO(null,"propHeatType","供暖",data.getPropHeatType(),null));

        if(StringUtil.isNotEmpty(data.getPropWaterType()))
            attrs.add(new ProductAttributeDTO(null,"propWaterType","供水",data.getPropWaterType(),null));

        if(StringUtil.isNotEmpty(data.getPropPowerType()))
            attrs.add(new ProductAttributeDTO(null,"propPowerType","供电",data.getPropPowerType(),null));

        return list;

    }


    @Override
    public HouseVO getHouseVOById(Long id) {

        ProductVO p=productDomain.getProductVOById(id);

        return productVO2HouseVO(p);
    }

    @Override
    public Page<HouseVO> getHouseVOPage(GetHouseListDTO request, Pageable pageable) {

        GetProductListDTO params=new GetProductListDTO();
        org.springframework.beans.BeanUtils.copyProperties(request,params);
        params.setAttrs(new String[]{"houseDistrict","houseArea","doorModel","houseCommunity"});

        Page<ProductVO> page=productDomain.getProductVOPage(params,pageable);

        List<HouseVO> content=new ArrayList<>();
        page.getContent().forEach(t->{
            content.add(productVO2HouseVO(t));
        });

        Page<HouseVO> page1=new PageImpl(content,page.getPageable(),page.getTotalElements());

        return page1;
    }




    @Override
    public List<HouseVO> getHouseVOListByIdIn(Collection<Long> idIn) {
        List<ProductVO> list=productDomain.getProductVOListByIdIn(idIn,new String[]{"houseDistrict","houseArea","doorModel","houseCommunity"});
        List<HouseVO> content=new ArrayList<>();
        list.forEach(t->{
            content.add(productVO2HouseVO(t));
        });
        return content;
    }



    private HouseVO productVO2HouseVO(ProductVO p){
        HouseVO h=new HouseVO();

        org.springframework.beans.BeanUtils.copyProperties(p,h);

        h.setAttributes(productAttributeGroupVOList2HouseAttributesVO(p.getAttributes()));
        h.setSkus(productSkuVOList2HouseSkuVOList(p.getSkus()));

        return h;
    }


    private List<HouseSkuVO> productSkuVOList2HouseSkuVOList(List<ProductSkuVO> data){

        List<HouseSkuVO> skus=new ArrayList<>();

        if(data!=null)
        data.forEach(t->{
            HouseSkuVO vo=new HouseSkuVO();
            org.springframework.beans.BeanUtils.copyProperties(t,vo);
            String value=t.getValue();
            Collection<ProductSkuPropertyDTO> ps= JsonUtil.fromJson(value,List.class,ProductSkuPropertyDTO.class);
            Map<String,Object> psMap=new HashMap<>();
            ps.forEach(p->{
                psMap.put(p.getKey(),p.getValue().getValue());
            });
            HouseSkuPropertyVO vop=new HouseSkuPropertyVO();
            try {
                BeanUtils.populate(vop,psMap);
            }catch (Exception e){
                e.printStackTrace();
            }
            vo.setValue(vop);
            skus.add(vo);
        });

        return skus;
    }


    private HouseAttributesVO productAttributeGroupVOList2HouseAttributesVO(List<ProductAttributeGroupVO> data){
        HouseAttributesVO attrs=new HouseAttributesVO();
        Map<String,Object> dataMap=new HashMap<>();
        if(data!=null)
        data.forEach(t->{
            t.getAttributes().forEach(a->{
                if(a.getKey().equals(HouseConstant.PROPERTY_KEY_HOUSE_COMMUNITY)){
                    if(StringUtil.isNotEmpty(a.getSuffix()))
                    attrs.setHouseCommunityId(Long.parseLong(a.getSuffix()));
                    dataMap.put(a.getKey(),a.getValue());
                }else if(a.getKey().equals("houseDev")){
                    attrs.setHouseDevId(a.getSuffix());
                    dataMap.put(a.getKey(),a.getValue());
                }else {
                    dataMap.put(a.getKey(), a.getValue()+(a.getSuffix()==null?"":a.getSuffix()));
                }
            });
        });
        try {
            BeanUtils.populate(attrs,dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return attrs;
    }


    @Override
    public List<HouseVO> getHouseSnapshotVOListByIdIn(Collection<Long> idIn) {
        List<ProductVO> list=productSnapshotDomain.getProductSnapshotVOListByIdIn(idIn);

        List<HouseVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            HouseVO vo=productVO2HouseVO(t);
            vos.add(vo);
        });

        return vos;
    }
}
