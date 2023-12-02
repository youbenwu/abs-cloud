package com.outmao.ebs.jnet.entity.asssignment;

import com.outmao.ebs.common.dsl.DslVO;
import com.querydsl.core.Tuple;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 外发单
 * @author yeyi
 * @date 2019/9/11 15:06
 **/
@Entity
@Table(name="z_my_assignment")
@DynamicInsert
@DynamicUpdate
public class ZMyAssignment implements DslVO<QZMyAssignment> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="update_time")
	private Date updateTime;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_deleted")
	private Boolean isDeleted;	// 0未删除 1已删除

	@Column(name="user_id")
	private Long userId;	// 所属用户ID(ebs_user.id)

	@Column(name="type")
	private Byte type;	// 0大厂 1官方

	@Column(name="status")
	private Byte status;	// 0生效中 1已到期 2已满员（上限50人） 3已结束 4已取消 5待完成(包含12状态)

	@Column(name="text", length=500)
	private String text;	// 正文少于500字符

	@Column(name="img_url", length=1024)
	private String imgUrl;	// 图片url,0~9张，英文逗号分隔

	@Column(name="category_id")
	private Long categoryId;	// 品类ID(z_production_category.id)

	@Column(name="release_time")
	private Date releaseTime;	// 发布时间

	@Column(name="applicant_num")
	private Long applicantNum;	// 申请人数
	
	@Column(name="visits_num")
	private Long visitsNum;	// 访问次数

	@Column(name="validity_time")
	private Integer validityTime;	// 生效时长，单位为小时

	@Column(name="province")
	private String province;	// 省

	@Column(name="city")
	private String city;	// 市

	@Column(name="area")
	private String area;	// 区

	@Column(name="address")
	private String address;	// 不包括省市区的详细地址

	@Column(name="longitude")
	private String longitude;	// 经度

	@Column(name="latitude")
	private String latitude;	// 纬度

	@Column(name="latest_applicant_avatar", length=5120)
	private String latestApplicantAvatar;	// 最近 20 个申请人头像 url, 用英文逗号分隔

	@Column(name="selected_factory", length=2048)
	private String selectedFactory;	// 被选中的工厂名及头像的 json 数组，不超过 1024字符
	
	@Column(name="technology_json", length=256)
	private String technologyJson;	// 工艺ID与工艺名json: [{"id":1,"name":"工艺名"}] 不超过256字符
	
	/**
	 * 所属用户的类型（冗余），同 ebs_user.type: 0普通 13外发机器人
	 */
	@Column(name="user_type")
	private Integer userType;

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Boolean getIsDeleted(){
		return this.isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted){
		this.isDeleted=isDeleted;
	}
	public Long getUserId(){
		return this.userId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Byte getType(){
		return this.type;
	}
	public void setType(Byte type){
		this.type=type;
	}
	public Byte getStatus(){
		return this.status;
	}
	public void setStatus(Byte status){
		this.status=status;
	}
	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text=text;
	}
	public String getImgUrl(){
		return this.imgUrl;
	}
	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	public Long getCategoryId(){
		return this.categoryId;
	}
	public void setCategoryId(Long categoryId){
		this.categoryId=categoryId;
	}
	public Date getReleaseTime(){
		return this.releaseTime;
	}
	public void setReleaseTime(Date releaseTime){
		this.releaseTime=releaseTime;
	}
	public Long getApplicantNum(){
		return this.applicantNum;
	}
	public void setApplicantNum(Long applicantNum){
		this.applicantNum=applicantNum;
	}
	public Long getVisitsNum() {
		return visitsNum;
	}
	public void setVisitsNum(Long visitsNum) {
		this.visitsNum = visitsNum;
	}
	public Integer getValidityTime(){
		return this.validityTime;
	}
	public void setValidityTime(Integer validityTime){
		this.validityTime=validityTime;
	}
	public String getProvince(){
		return this.province;
	}
	public void setProvince(String province){
		this.province=province;
	}
	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getArea(){
		return this.area;
	}
	public void setArea(String area){
		this.area=area;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getLongitude(){
		return this.longitude;
	}
	public void setLongitude(String longitude){
		this.longitude=longitude;
	}
	public String getLatitude(){
		return this.latitude;
	}
	public void setLatitude(String latitude){
		this.latitude=latitude;
	}
	public String getLatestApplicantAvatar(){
		return this.latestApplicantAvatar;
	}
	public void setLatestApplicantAvatar(String latestApplicantAvatar){
		this.latestApplicantAvatar=latestApplicantAvatar;
	}
	public String getSelectedFactory(){
		return this.selectedFactory;
	}
	public void setSelectedFactory(String selectedFactory){
		this.selectedFactory=selectedFactory;
	}
	public String getTechnologyJson() {
		return technologyJson;
	}
	public void setTechnologyJson(String technologyJson) {
		this.technologyJson = technologyJson;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Override
	public DslVO<QZMyAssignment> fromTuple(Tuple t, QZMyAssignment e) {
		this.id=t.get(e.id);
        this.address = t.get(e.address);
        this.applicantNum = t.get(e.applicantNum);
        this.visitsNum = t.get(e.visitsNum);
        this.area = t.get(e.area);
        this.categoryId = t.get(e.categoryId);
        this.city = t.get(e.city);
        this.createTime = t.get(e.createTime);
        this.imgUrl = t.get(e.imgUrl);
        this.isDeleted = t.get(e.isDeleted);
        this.latestApplicantAvatar = t.get(e.latestApplicantAvatar);
        this.latitude = t.get(e.latitude);
        this.longitude = t.get(e.longitude);
        this.province = t.get(e.province);
        this.releaseTime = t.get(e.releaseTime);
        this.selectedFactory = t.get(e.selectedFactory);
        this.status = t.get(e.status);
        this.text = t.get(e.text);
        this.type = t.get(e.type);
        this.updateTime = t.get(e.updateTime);
        this.userId = t.get(e.userId);
        this.validityTime = t.get(e.validityTime);
        this.technologyJson = t.get(e.technologyJson);
        this.setUserType(t.get(e.userType));
        return this;
	}

}