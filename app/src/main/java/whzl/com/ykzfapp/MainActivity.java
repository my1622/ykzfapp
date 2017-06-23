package whzl.com.ykzfapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import whzl.com.ykzfapp.net.ListHouse;
import whzl.com.ykzfapp.pro.House;

public class MainActivity extends AppCompatActivity {
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button) findViewById(R.id.btnsearch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ListHouse("", "","","","","","","","","1", new ListHouse.SuccessCallback() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject json = new JSONObject(result);
                            if (json!=null){
                                House house=new House();
                                JSONArray houseJson=json.optJSONArray("obj");
                                Log.d("tag",houseJson.toString());

                                /*JSONObject json = new JSONObject(soapObj);
                                if (json != null) {
                                    Goods goods = new Goods();
                                    goods.setGoodsNo(json.optString("productId"));// ��Ʒid
                                    goods.setGoodsName(json.optString("productName"));
                                    goods.setBrandName(json.optString("brandName"));
                                    goods.setMarketprice(json.optString("marketprice"));
                                    goods.setWeight(json.optString("weight"));
                                    goods.setProduct_brief(json.optString("brief"));
                                    goods.setGoodSn(json.optString("goodsSn"));
                                    goods.setProductSn(json.optString("productSn"));
                                    goods.setIntro(json.optString("intro"));

                                    goods.setGoodsPrice(json.optString("price"));
                                    goods.setGoods_score(json.optString("score"));
                                    goods.setProductId(json.optString("id"));// ��Ʒid

                                    JSONArray imageArray = json.optJSONArray("prductImagelist");
                                    if (imageArray != null) {
                                        List<FocusImage> imgList = new ArrayList<FocusImage>();
                                        for (int i = 0; i < imageArray.length(); i++) {
                                            JSONObject imgJson = imageArray.optJSONObject(i);
                                            if (imgJson != null) {
                                                FocusImage image = new FocusImage();
                                                image.img = imgJson.optString("smallProductImagePath");
                                                image.bigImage = imgJson
                                                        .optString("bigProductImagePath");
                                                imgList.add(image);
                                            }

                                        }
                                        goods.setImgList(imgList);
                                    }
                                    JSONObject mapJson = json.optJSONObject("map");
                                    if (mapJson != null) {
                                        Map<String, List<String>> map = new HashMap<String, List<String>>();
                                        Iterator<String> iterator = mapJson.keys();
                                        while (iterator.hasNext()) {
                                            String key = iterator.next();
                                            JSONArray arrJson = mapJson.optJSONArray(key);

                                            if (arrJson != null) {
                                                List<String> valueList = new ArrayList<String>();
                                                for (int j = 0; j < arrJson.length(); j++) {
                                                    String value = arrJson.optString(j);
                                                    if (!TextUtils.isEmpty(value)) {
                                                        valueList.add(value);
                                                        map.put(key, valueList);

                                                    }

                                                }

                                            }

                                        }
                                        goods.setMap(map);
                                    }

                                    response.setGoods(goods);
                                    response.setStoreNum(json.optInt("store"));

                                }*/

                            }
                        }catch (JSONException e){
                            Log.e(getApplicationContext().getPackageName(),"JSONException:"+e.toString());
                        }


                    }
                }, new ListHouse.FailCallback() {
                    @Override
                    public void onFail() {

                    }
                });

            }
        });


    }
}
