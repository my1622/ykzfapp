package whzl.com.ykzfapp.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.CityBean;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private OnButtonClickListener mListener;


    public CityAdapter( List<CityBean> mDatas,OnButtonClickListener listerner) {

        this.mDatas = mDatas;
        this.mListener=listerner;

    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public CityAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent,false));

    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(v ->{
                    if (mListener!=null){
                        mListener.onButtonClick(v,cityBean.getId(),cityBean.getCity());
                    }

        }

        );
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            content = itemView.findViewById(R.id.content);
        }
    }

    public interface OnButtonClickListener{
        void onButtonClick(View view,int id,String name);
    }
}
