package whzl.com.ykzfapp.net;

import whzl.com.ykzfapp.Config;

/**
 * Created by 01 on 2017/6/23.
 */

public class ListHouse {
    public ListHouse(String name, String region, String salePrice,
                     String bedRooms, String area, String category,
                     String buildingYear, String tags,
                     String businessDistrict, String community_id,
                     final SuccessCallback successCallback, final FailCallback failCallback) {

        new NetConnection(Config.SERVER_URL, HttpMethod.GET, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                successCallback.onSuccess(result);
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {

            }
        }, Config.ACTION_LIST_HOUSE,Config.KEY_NAME,name,
                Config.KEY_REGION, region,
                Config.KEY_SALEPRICE, salePrice,
                Config.KEY_BEDROOMS, bedRooms,
                Config.KEY_AREA, area,
                Config.KEY_CATEGORY, category,
                Config.KEY_BUILDINGYEAR, buildingYear,
                Config.KEY_TAGS, tags,
                Config.KEY_BUSINESSDISTRICT, businessDistrict,
                Config.KEY_COMMUNITY_ID, community_id);

    }

    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailCallback {
        void onFail();
    }
}
