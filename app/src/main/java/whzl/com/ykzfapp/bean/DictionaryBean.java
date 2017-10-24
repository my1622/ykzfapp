package whzl.com.ykzfapp.bean;

/**
 * Created by my on 2017/10/24.
 */

public class DictionaryBean extends BaseBean {
    public DictionaryBean(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * code : 100
     * id : 92
     * letter : orientation
     * name : 朝向
     * num : 0
     * value : 南
     */

    private String code;
    private int id;
    private String letter;
    private String name;
    private int num;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
