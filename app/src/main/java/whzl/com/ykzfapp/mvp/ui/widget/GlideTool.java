package whzl.com.ykzfapp.mvp.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.app.AppApplication;


/**
 * Created by Jaxson 2017/5/12
 */
public class GlideTool {
    public static void showImage(String imgUrl, ImageView imageView, Activity activity) {

        Context tmpContext = null;
        if (null != activity){
            tmpContext = activity;
        }else {
            tmpContext = AppApplication.getAppContext();
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.house_list_test)
                .error(R.drawable.house_list_test)
                .dontAnimate();//使用.placeholder()方法在某些情况下会导致图片显示的时候出现图片变形的情况
        Glide.with(tmpContext).setDefaultRequestOptions(options).load(imgUrl).into(imageView);
    }

    /**
     * 显示圆形图像
     */
    public static void showCircleImage(String imgUrl, ImageView imageView, Activity activity) {
        Context tmpContext = null;
        if (null != activity){
            tmpContext = activity;
        }else {
            tmpContext = AppApplication.getAppContext();
        }

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.glide_img_placeholder).centerCrop()

                .transform(new CircleTransform(tmpContext))
                .placeholder(R.drawable.glide_img_placeholder)
                .error(R.drawable.alternativepicture2x)
                .dontAnimate()
                .circleCrop();//使用.placeholder()方法在某些情况下会导致图片显示的时候出现图片变形的情况
        Glide.with(tmpContext).setDefaultRequestOptions(options).load(imgUrl).into(imageView);
    }
    public static void cleanGlideDiskCache(){
        Context  tmpContext = null;
        tmpContext = AppApplication.getAppContext();
        Glide.get(tmpContext).clearMemory();
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                // This method must be called on a background thread.
                Context  tmpContext = null;
                tmpContext = AppApplication.getAppContext();

                Glide.get(tmpContext).clearDiskCache();
                return null;
            }
        }.execute();

    }


    /*public static void showCircleCornerImage(String imgUrl, ImageView imageView, Activity activity, int rote){
        Context tmpContext = null;
        if (null != activity){
            tmpContext = activity;
        }else {
            tmpContext = XzLeaseApplication.getInstance();
        }
        Glide.with(tmpContext).load(imgUrl).centerCrop()
                .transform(new GlideRoundTransform(tmpContext,rote))
                .placeholder(R.drawable.glide_img_placeholder)
                .error(R.drawable.alternativepicture2x)
                .dontAnimate()//使用.placeholder()方法在某些情况下会导致图片显示的时候出现图片变形的情况
                .crossFade()
                .into(imageView);
    }
*/

}
