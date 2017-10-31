package whzl.com.ykzfapp.bean;

/**
 * Created by my on 2017/10/31.
 */

public class FollowListBean {
    /**
     * content : 管理员修改房源信息。
     * createdBy : 管理员
     * createdOn : 2017-09-08 17:08:03
     * followUpDate : 2017-09-08 17:08:03
     * followUpUser : 管理员
     * followUpUserDId : 1
     * followUpUserDName : 软件公司
     * followUpUserGId : 1
     * followUpUserGName : 软件公司
     * houseId : 854
     * id : 1464
     */

    private String content;
    private String createdBy;
    private String createdOn;
    private String followUpDate;
    private String followUpUser;
    private String followUpUserDId;
    private String followUpUserDName;
    private String followUpUserGId;
    private String followUpUserGName;
    private String houseId;
    private int id;

    public FollowListBean(String number, String followUpUser, String content, String followUpDate) {
        this.followUpUser=followUpUser;
        this.content=content;
        this.followUpDate=followUpDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getFollowUpUser() {
        return followUpUser;
    }

    public void setFollowUpUser(String followUpUser) {
        this.followUpUser = followUpUser;
    }

    public String getFollowUpUserDId() {
        return followUpUserDId;
    }

    public void setFollowUpUserDId(String followUpUserDId) {
        this.followUpUserDId = followUpUserDId;
    }

    public String getFollowUpUserDName() {
        return followUpUserDName;
    }

    public void setFollowUpUserDName(String followUpUserDName) {
        this.followUpUserDName = followUpUserDName;
    }

    public String getFollowUpUserGId() {
        return followUpUserGId;
    }

    public void setFollowUpUserGId(String followUpUserGId) {
        this.followUpUserGId = followUpUserGId;
    }

    public String getFollowUpUserGName() {
        return followUpUserGName;
    }

    public void setFollowUpUserGName(String followUpUserGName) {
        this.followUpUserGName = followUpUserGName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
