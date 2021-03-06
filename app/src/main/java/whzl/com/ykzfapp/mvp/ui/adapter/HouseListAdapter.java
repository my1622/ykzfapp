package whzl.com.ykzfapp.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.model.api.Api;
import whzl.com.ykzfapp.mvp.ui.widget.GlideTool;

/**
 * Created by Administrator on 2017/7/4.
 */

public class HouseListAdapter extends BaseQuickAdapter<HouseListBean, BaseViewHolder> {


    private Builder mBuilder;


    public static class Builder {

        private int layoutId = R.layout.templete_todo_house_cardview;
        public HouseListAdapter build() {
            return new HouseListAdapter(this);
        }
        Context mContext;
        public Builder layout(int resId) {
            this.layoutId = resId;

            return this;
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }
    }

    private HouseListAdapter(Builder builder) {
        super(builder.layoutId);
        this.mBuilder = builder;

        openLoadAnimation();
    }

    public static Builder builder() {

        return new Builder();
    }


    @Override
    protected void convert(BaseViewHolder helper, HouseListBean item) {
        ImageView mAvater=helper.getView(R.id.first_pic);

        GlideTool
                .showImage(Api.APP_DOMAIN+item.getFirstPic()
                        ,mAvater,null);
        if (item.getTitle()!=null) {
            helper.setText(R.id.text_title, item.getTitle());
        }
        String info="";
        if (item.getCommunityName()!=null){
            info+= item.getCommunityName()+ " | ";
        }

        info+= item.getBedRooms() + "室" + item.getLivingRooms() + "厅"
                + item.getCookRooms() + "厨" + item.getBathRooms() + "卫" + " | " + item.getArea() + "m²";
        helper.setText(R.id.text_info,info );
        if (item.getTagsArray()!=null) {
            helper.setText(R.id.text_tag1, item.getTagsArray().get(0));
            helper.setVisible(R.id.text_tag1, true);

        }else{
            helper.setVisible(R.id.text_tag1, false);
            helper.setVisible(R.id.text_tag2, false);
        }
        if (item.getTagsArray()!=null&&item.getTagsArray().size()>1){

            helper.setText(R.id.text_tag2, item.getTagsArray().get(1));
            helper.setVisible(R.id.text_tag2, true);
        }else{

            helper.setVisible(R.id.text_tag2, false);
        }

        helper.setText(R.id.text_sale_price, Float.valueOf(item.getSalePrice())+"");
        helper.addOnClickListener(R.id.btn_modify);
       /* helper.setText(R.id.text_title, item.getHousetitle()+item.getCommName()+"-"+item.getRentalMode());
        helper.setText(R.id.text_rent, item.getRent());
        helper.setText(R.id.text_audit_status, getStatus(item.getAuditstatus()));
        helper.setText(R.id.text_purpose, getPurpose(item.getPurpose()));
        helper.setVisible(R.id.text_rent_name, false);
        //Logger.d(item.getHousetitle()+item.getLeaseStatus());

        if (item.getAuditstatus().equals(mDictionary.getAuthstatus().get(1).getValue())){

            helper.setVisible(R.id.btn_delete, false);
            helper.setVisible(btn_updata, true);
            helper.setText(btn_updata, "重新发布");
        }else{
            helper.setVisible(R.id.btn_delete, true);
            helper.setVisible(btn_updata, false);
        }*/

   /*     helper.addOnClickListener(R.id.btn_delete);
        helper.addOnClickListener(R.id.btn_modify);
        helper.addOnClickListener(btn_updata);*/


    }

}
