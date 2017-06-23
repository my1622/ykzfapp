package whzl.com.ykzfapp.pro;
import java.util.Date;

/**
 * Created by 01 on 2017/6/23.
 */

public class Community {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * √	编号 	唯一标识
     */
    private int id;
    /**
     * √	所在区域	数据字典	清江浦区，
     */
    private String region;

    /**
     * √	小区名称
     */
    private String name;
    /**
     * √	小区地址
     */
    private String address;

    /**
     * √	定位	经度
     */
    private Double lng;
    /**
     * √	定位	纬度
     */
    private Double lat;
    /**
     * 补充说明
     */
    private String remarks;

    /**
     * √	信息状态	数据字典	有效，过期，无效
     */
    private String infoState;
//	/** 	√	审核状态	数据字典	待审核，审核通过，审核未通过*/
//	private String		auditStatus;
//	/** 		审核人*/
//	private String		auditBy;
//	/** 		审核时间*/
//	private Date		auditOn;
//	/** 		审核理由*/
//	private String		auditInfo;

    /**
     * √	发布人
     */
    private String createdBy;
    /**
     * √	发布时间
     */
    private Date createdOn;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 修改时间
     */
    private Date updatedOn;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInfoState() {
        return infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
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
}
