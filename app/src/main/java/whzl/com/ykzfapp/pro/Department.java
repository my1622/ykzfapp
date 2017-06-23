package whzl.com.ykzfapp.pro;

import java.util.Set;

/**
 * Created by 01 on 2017/6/23.
 */

public class Department {
    /** serialVersionUID*/
    private static final long serialVersionUID = 1L;
    /**编号*/
    private Long id;
    /**名称*/
    private String name;
    /**代码*/
    private String code;
    /**描述*/
    private String summary;
    /**联系电话*/
    private String tel;
    /**地址*/
    private String address;
    private Set<User> users;
    /**部门*/
    private Department department;
    /**部门*/
    private Set<Department> departments;
    /**所属行政区划*/
    private String xzqh;

    /**联系人*/
    private String lxr;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }
}
