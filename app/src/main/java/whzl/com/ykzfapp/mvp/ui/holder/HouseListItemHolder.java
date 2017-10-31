package whzl.com.ykzfapp.mvp.ui.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseListBean;

/**
 * Created by Administrator on 2017/7/5.
 */

public class HouseListItemHolder extends BaseHolder<HouseListBean> {

    @BindView(R.id.first_pic)
    ImageView firstPic;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_info)
    TextView textInfo;
    @BindView(R.id.text_tag1)
    TextView textTag1;
    @BindView(R.id.text_tag2)
    TextView textTag2;
    @BindView(R.id.text_sale_price)
    TextView textSalePrice;
    @BindView(R.id.wan_textview)
    TextView wanTextview;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.btn_modify)
    Button btnModify;
    @BindView(R.id.btn_info_state_update)
    Button btnDelete;
    @BindView(R.id.btn_updata)
    Button btnUpdata;


    public HouseListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(HouseListBean data, int position) {
        textTitle.setText(data.getTitle());
       /* tvAuthor.setText(data.who);
        tvDesc.setText(data.desc);
        if (data.type.equals(CategoryType.ANDROID_STR)){
            ivImage.setImageResource(R.mipmap.icon_android);
        }else  if (data.type.equals(CategoryType.IOS_STR)){
            ivImage.setImageResource(R.mipmap.icon_apple);
        }else  if (data.type.equals(CategoryType.QIAN_STR)){
            ivImage.setImageResource(R.mipmap.html);
        }*/
    }

}
