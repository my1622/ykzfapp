package whzl.com.ykzfapp.pro;

import java.security.acl.Group;
import java.util.Date;

/**
 * Created by 01 on 2017/6/23.
 */

public class User {
    private static final long serialVersionUID = 1L;

    /**编号*/
    private Long id;
    /**登录名*/
    private String name;
    /**密码*/
    private String password;
    /**全名*/
    private String fullName;
    /**头像*/
    private String headPhoto;
    /**电手机*/
    private String tel;
    /**固定电话*/
    private String phone;

    /**组*/
    private Group group;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIsRepairman() {
        return isRepairman;
    }

    public void setIsRepairman(int isRepairman) {
        this.isRepairman = isRepairman;
    }

    /**所属部门*/
    private Department department;
    /**QQ*/
    private String qq;
    /**邮箱*/
    private String email;
    /**出生年月*/
    private Date born;
    /**超级用户*/
    private String level;

    /**是否为维修员
     * 0 不是
     * 1 是*/
    private int isRepairman;
    /**
     * @return the id
     */
}
