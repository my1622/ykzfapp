package whzl.com.ykzfapp.pro;
import java.util.Date;

/**
 * Created by 01 on 2017/6/23.
 */

public class House {
    /** serialVersionUID*/
    private static final long serialVersionUID = 1L;
    /**		√	编号 	唯一标识*/
    private int 		id;
    /** 	√	售房标题		*/
    private String		title;
    /** 	√	房屋所在区域	数据字典	清江浦区，*/
    private String		region;

    /** 小区 */
    private Community 	community;

    /** 	√	定位	经度*/
    private String		lng;
    /** 	√	定位	纬度*/
    private String		lat;
    /** 	√	楼号		*/
    private String		buildNo;
    /** 	√	单元号*/
    private String		unitNo;
    /** 	√	房间号*/
    private String		roomNo;
    /** 	√	户型-几室	户型*/
    private int		bedRooms;
    /** 	√	户型-几厅	*/
    private int		livingRooms;
    /** 	√	户型-几厨	*/
    private int		cookRooms;
    /** 	√	户型-几卫	*/
    private int		bathRooms;
    /** 	√	单价	默认单位：元/平方米*/
    private float		unitPrice;
    /** 	√	售价	默认单位：万元	*/
    private float		salePrice;
    /** 	√	首图展示	提取房源图片第一张*/
    private String 		firstPic;
    /** 	√	建筑类型	数据字典	电梯，楼梯，其他*/
    private String		buildingType;
    /** 	√	建筑年代*/
    private String		year;
    /** 	√	建筑面积	*/
    private float		area;
    /** 	√	房源信息 数据字典 房东急售等 */
    private String		category;
    /** 	√	产权年限	*/
    private int		yearLimit;
    /** 	√	房屋类型	数据字典	住宅，商铺，别墅,其他*/
    private String		houseType;
    /** 	√	当前盘别	数据字典	私盘，其他*/
    private String		building;
    /** 	√	所在楼层	楼层	*/
    private int		locatedFloor;
    /** 	√	总楼层数*/
    private int		totalFloors;
    /** 	√	朝向	数据字典	朝南，朝北，南北通透，其他*/
    private String		orientation;
    /** 	√	装修类型	数据字典	简装修，精装修，其他*/
    private String		fitmentType;
    /** 		标签	数据字典(多个)	简装修，车库，学区房，急售*/
    private String		tags;

    /** 	√	房主姓名*/
    private String		contact;
    /** 	√	房主联系电话	*/
    private String		phone;
    /** 		房主补充说明	*/
    private String		remarks;

    /** 		浏览次数	*/
    private int		viewTimes;

    /** 	√	信息状态	数据字典	101有效  102无效  103已成交   104待完善  */
    private String		infoState;
    /** 	√	信息状态变更时间*/
    private Date 	infoStateOn;
    /** 	√	信息状态变更备注*/
    private String 	infoStateInfo;
    /** 	√	信息状态变更人	*/
    private User 	infoStateUser;

    /** 	√	创建人	*/
    private String		createdBy;
    /** 	√	创建时间	*/
    private Date		createdOn;
    /** 		修改人	*/
    private String		updatedBy;
    /** 		修改时间	*/
    private Date		updatedOn;

    /** 	√	数据来源：1中介网页添加，2用户微信添加，3用户APP添加*/
    private String 		sourceType;
    /** 	√	商业区数据字典，新亚、万达等*/
    private String		businessDistrict;

    //2017-0622-新增属性-------------------------------------

    /** 		服务费	*/
    private String serviceCharge;
    /** 		省中介费	*/
    private String agencyFee;

    /** 	√	经纪人信息	*/
    private User agentUser;

    /** 	验房状态	数据字典	101待验房   102已验房通过   103验房未通过*/
    private String checkStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public int getLivingRooms() {
        return livingRooms;
    }

    public void setLivingRooms(int livingRooms) {
        this.livingRooms = livingRooms;
    }

    public int getCookRooms() {
        return cookRooms;
    }

    public void setCookRooms(int cookRooms) {
        this.cookRooms = cookRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(int yearLimit) {
        this.yearLimit = yearLimit;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getLocatedFloor() {
        return locatedFloor;
    }

    public void setLocatedFloor(int locatedFloor) {
        this.locatedFloor = locatedFloor;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getFitmentType() {
        return fitmentType;
    }

    public void setFitmentType(String fitmentType) {
        this.fitmentType = fitmentType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getInfoState() {
        return infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    public Date getInfoStateOn() {
        return infoStateOn;
    }

    public void setInfoStateOn(Date infoStateOn) {
        this.infoStateOn = infoStateOn;
    }

    public String getInfoStateInfo() {
        return infoStateInfo;
    }

    public void setInfoStateInfo(String infoStateInfo) {
        this.infoStateInfo = infoStateInfo;
    }

    public User getInfoStateUser() {
        return infoStateUser;
    }

    public void setInfoStateUser(User infoStateUser) {
        this.infoStateUser = infoStateUser;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(String agencyFee) {
        this.agencyFee = agencyFee;
    }

    public User getAgentUser() {
        return agentUser;
    }

    public void setAgentUser(User agentUser) {
        this.agentUser = agentUser;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCheckOn() {
        return checkOn;
    }

    public void setCheckOn(Date checkOn) {
        this.checkOn = checkOn;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public User getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(User checkUser) {
        this.checkUser = checkUser;
    }

    public String getReleasedStatus() {
        return releasedStatus;
    }

    public void setReleasedStatus(String releasedStatus) {
        this.releasedStatus = releasedStatus;
    }

    public User getReleasedUser() {
        return releasedUser;
    }

    public void setReleasedUser(User releasedUser) {
        this.releasedUser = releasedUser;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public String getReleasedInfo() {
        return releasedInfo;
    }

    public void setReleasedInfo(String releasedInfo) {
        this.releasedInfo = releasedInfo;
    }

    /** 	√	验房时间*/
    private Date checkOn;
    /** 	√	验房备注*/
    private String checkInfo;
    /** 	√	验房顾问信息	*/
    private User checkUser;

    /** 	发布状态	数据字典	101待发布    102已发布   103再次发布   104取消发布*/
    private String releasedStatus;
    /** 	√	发布人	*/
    private User releasedUser;
    /** 	√	发布时间	*/
    private Date releasedOn;
    /** 	√	发布备注	*/
    private String releasedInfo;
}
