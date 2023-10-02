package com.outmao.ebs.mall.product.common.constant;

public class HouseConstant {


    //商品类型 0普通商品 11新楼盘 12二手房 13出租房
    //普通商品
    public static final int PRODUCT_TYPE_DEFAULT =0;

    //新房产
    public static final int PRODUCT_TYPE_HOUSE_NEW =11;
    //二手房产
    public static final int PRODUCT_TYPE_HOUSE_OLD =12;
    //出租房
    public static final int PRODUCT_TYPE_HOUSE_RENT =13;


    //检索类型
    //能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
    public static final int SEARCH_TYPE_NONE=0;
    public static final int SEARCH_TYPE_KEYWORD=1;
    public static final int SEARCH_TYPE_RANGE=2;


    //参数录入方式：0--手动录入 1--单选参数 2--多选参数
    public static final int INPUT_TYPE_EDIT=0;
    public static final int INPUT_TYPE_SELECT=1;
    public static final int INPUT_TYPE_SELECT_MUILT=2;


    //唯一属性  单选属性  复选属性
    public static final int SKU_TYPE_ONE=0;
    public static final int SKU_TYPE_SELECT=1;
    public static final int SKU_TYPE_SELECT_MULIT=2;


    //商品属性健值


    /**
     * 入住：随时
     * 租期：1年以上
     * 配备：洗衣机、空调、衣柜、电视、冰箱、热水器、床
     * */

    //入住：随时
    public static final String PROPERTY_KEY_HOUSE_IN_TIME="houseInTime";

    //租期：1年以上
    public static final String PROPERTY_KEY_HOUSE_LEASE="houseLease";

    //配备：洗衣机、空调、衣柜、电视、冰箱、热水器、床
    public static final String PROPERTY_KEY_HOUSE_Furn="houseFurn";

    //户型室数
    public static final String PROPERTY_KEY_HOUSE_ROOM_NUM="houseRoomNum";

    //户型厅数
    public static final String PROPERTY_KEY_HOUSE_HALL_NUM="houseHallNum";

    //户型卫数
    public static final String PROPERTY_KEY_HOUSE_BATH_NUM="houseBathNum";



    //地址
    public static final String PROPERTY_KEY_HOUSE_ADDRESS="address";

    //基础信息
    public static final String PROPERTY_KEY_BASE_INFO="baseInfo";

    //销售信息
    public static final String PROPERTY_KEY_SALE_INFO="saleInfo";

    //销售地址
    public static final String PROPERTY_KEY_HOUSE_SALE_ADDRESS="saleAddress";

    //房屋面积平方米 double
    public static final String PROPERTY_KEY_HOUSE_AREA="houseArea";

    //房屋用途：住宅
    public static final String PROPERTY_KEY_HOUSE_USE="houseType";

    //朝向：南
    public static final String PROPERTY_KEY_HOUSE_DIRECTION="houseDirection";

    //楼层 房屋所在的层
    public static final String PROPERTY_KEY_HOUSE_FLOOR="houseFloor";

    //电梯
    public static final String PROPERTY_KEY_HOUSE_LIFT="houseLift";

    //挂牌
    public static final String PROPERTY_KEY_HOUSE_LISTED="houseListed";

    //装修
    public static final String PROPERTY_KEY_HOUSE_DECOR="houseDecor";

    //建造年代
    public static final String PROPERTY_KEY_HOUSE_ERA="houseEra";

    //权属类别
    public static final String PROPERTY_KEY_HOUSE_OWNERSHIP="houseOwnership";

    //所属小区
    public static final String PROPERTY_KEY_HOUSE_COMMUNITY="houseCommunity";


    //建筑规划
    public static final String PROPERTY_KEY_HOUSE_PROJECT="houseProject";

    //建筑类型: 高层
    public static final String PROPERTY_KEY_HOUSE_BUILD_TYPE="houseBuildType";

    //产权年限：70年
    public static final String PROPERTY_KEY_HOUSE_PROP_YEAR="housePropYear";

    //容积率：n%
    public static final String PROPERTY_KEY_HOUSE_FAR="houseFar";
    //绿化率：n%
    public static final String PROPERTY_KEY_HOUSE_GSR="houseGsr";
    //规划用户：1876
    public static final String PROPERTY_KEY_HOUSE_HOLD_NUM="houseHoldNum";

    //楼层状况：33层
    public static final String PROPERTY_KEY_HOUSE_FLOOR_NUM="houseFloorNum";
    //占地面积：100000m²
    public static final String PROPERTY_KEY_HOUSE_FLOOR_AREA="houseFloorArea";
    //建地面积：260000m²
    public static final String PROPERTY_KEY_HOUSE_LAND_AREA="houseLandArea";
    //开发商：广州市xxxx房地产开发有限公司
    public static final String PROPERTY_KEY_HOUSE_DEV="houseDev";



    //房屋物业信息
    public static final String PROPERTY_KEY_HOUSE_PROP="houseProp";

    //物业公司
    public static final String PROPERTY_KEY_HOUSE_PROP_COMPANY="housePropCompany";
    //物业费用(元/m²/月)
    public static final String PROPERTY_KEY_HOUSE_PROP_FEE="housePropFee";
    //车位数(个)
    public static final String PROPERTY_KEY_HOUSE_PARK_NUM="houseParkNum";
    //车位比
    public static final String PROPERTY_KEY_HOUSE_PARK_THAN="housePartThan";
    //地上车位数(个)
    public static final String PROPERTY_KEY_HOUSE_PARK_G_NUM="housePartGNum";
    //地下车位数(个)
    public static final String PROPERTY_KEY_HOUSE_PARK_L_NUM="housePartLNum";
    //供暖方式：自采暖
    public static final String PROPERTY_KEY_HOUSE_HEAT_TYPE="houseHeatType";
    //供水：民水
    public static final String PROPERTY_KEY_HOUSE_WATER_TYPE="houseWaterType";
    //供电：民电
    public static final String PROPERTY_KEY_HOUSE_POWER_TYPE="housePowerType";




}
