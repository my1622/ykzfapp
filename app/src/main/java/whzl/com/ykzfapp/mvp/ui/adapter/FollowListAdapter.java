package whzl.com.ykzfapp.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.FollowListBean;

/**
 * Created by Administrator on 2017/7/4.
 */

public class FollowListAdapter extends BaseQuickAdapter<FollowListBean, BaseViewHolder> {

    private Builder mBuilder;

    public static class Builder {
        private int layoutId = R.layout.template_nav_icon;
        public FollowListAdapter build() {
            return new FollowListAdapter(this);
        }
        public Builder layout(int resId) {
            this.layoutId = resId;

            return this;
        }
    }

    private FollowListAdapter(Builder builder) {
        super(builder.layoutId);
        this.mBuilder = builder;

        openLoadAnimation();
    }

    public static Builder builder() {

        return new Builder();
    }


    @Override
    protected void convert(BaseViewHolder helper, FollowListBean item) {


        helper.setText(R.id.tv_content,item.getContent() );
        helper.setText(R.id.tv_followUpUser,item.getFollowUpUser() );
        String[] date = item.getFollowUpDate().split(" ");
        helper.setText(R.id.tv_follow_update,date[0] );
        helper.setText(R.id.tv_number,"1");
        if (helper.getPosition()==0){
            helper.setText(R.id.tv_number,"序号");
        }else{
            helper.setText(R.id.tv_number,helper.getPosition()+"");
        }


    }

}
