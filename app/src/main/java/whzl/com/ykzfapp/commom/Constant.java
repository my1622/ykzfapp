package whzl.com.ykzfapp.commom;


import java.util.List;

import whzl.com.ykzfapp.bean.DictionaryBean;

/**
 *
 * @Description:${TODO} (保存一些需要的静态常量信息）
 * Created by my on 2017/7/15.
 */

public class Constant {


    public static final String TRANSITION_ANIMATION_NEWS_PHOTOS = "transition_animation_news_photos";
    public static List<DictionaryBean> DICTIONARY=null;

    public static String getOrientatioan(String s) {
        for (DictionaryBean b:DICTIONARY
                ) {
            if (b.getLetter().equals("orientation") && b.getCode().equals(s)){
                return b.getValue();
            }

        }

        return "";
    }

    public static String getFitmentType(String s) {
        for (DictionaryBean b:DICTIONARY
                ) {
            if (b.getLetter().equals("fitmentType") && b.getCode().equals(s)){
                return b.getValue();
            }

        }

        return "";
    }

    public static String getRegion(String s) {
        for (DictionaryBean b:DICTIONARY
                ) {
            if (b.getLetter().equals("region") && b.getCode().equals(s)){
                return b.getValue();
            }

        }

        return "";
    }

}
