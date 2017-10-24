package whzl.com.ykzfapp.bean;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class SimpleBean {
    public SimpleBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String key;
    public String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
