package whzl.com.ykzfapp.bean;

/**
 * Created by my on 2017/10/30.
 */

public class CommunityBean extends BaseBean {

    /**
     * address :
     * dealLeaseHouse : 0
     * dealSaleHouse : 0
     * id : 245
     * infoState : 101
     * lat : 33.6105
     * lng : 119.018979
     * name : 光辉乾城
     * region : 320806
     * remarks :
     * sendLeaseHouse : 0
     * sendSaleHouse : 2
     */

    private String address;
    private int dealLeaseHouse;
    private int dealSaleHouse;
    private int id;
    private String infoState;
    private double lat;
    private double lng;
    private String name;
    private String region;
    private String remarks;
    private int sendLeaseHouse;
    private int sendSaleHouse;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDealLeaseHouse() {
        return dealLeaseHouse;
    }

    public void setDealLeaseHouse(int dealLeaseHouse) {
        this.dealLeaseHouse = dealLeaseHouse;
    }

    public int getDealSaleHouse() {
        return dealSaleHouse;
    }

    public void setDealSaleHouse(int dealSaleHouse) {
        this.dealSaleHouse = dealSaleHouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfoState() {
        return infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSendLeaseHouse() {
        return sendLeaseHouse;
    }

    public void setSendLeaseHouse(int sendLeaseHouse) {
        this.sendLeaseHouse = sendLeaseHouse;
    }

    public int getSendSaleHouse() {
        return sendSaleHouse;
    }

    public void setSendSaleHouse(int sendSaleHouse) {
        this.sendSaleHouse = sendSaleHouse;
    }
}
