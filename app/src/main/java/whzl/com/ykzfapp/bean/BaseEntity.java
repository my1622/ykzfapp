package whzl.com.ykzfapp.bean;

/**
 *
 * @Description:${TODO} (接受数据的基类，网路请求成功时返回“0000”，错误时返回对应的代码及错误的描述）
 * Created by my on 2017/7/4.
 */

public class BaseEntity<T> extends BaseBean{


    /**
     * message : 登录成功
     * obj : {"deptId":1,"deptName":"软件公司","email":"584115629@qq.com","fullName":"管理员","groupId":1,"groupName":"软件公司","id":1,"isRepairman":0,"level":"3","name":"admin","password":"cf3f8e01c896a97c0220621f983a822c","phone":"","qq":"","tel":""}
     * success : true
     */

    private String message;
    private T obj;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
