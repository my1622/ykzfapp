package whzl.com.ykzfapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;

import java.util.ArrayList;
import java.util.List;

import whzl.com.ykzfapp.bean.DictionaryBean;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    List<DictionaryBean> address;
    List<DictionaryBean> salePrice;
    List<DictionaryBean> bedRooms;
    List<DictionaryBean> area;

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener
    , List<DictionaryBean> address, List<DictionaryBean> salePrice, List<DictionaryBean> bedRooms
    , List<DictionaryBean>  area) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.address = address;
        this.salePrice = salePrice;
        this.bedRooms = bedRooms;
        this.area = area;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView1();
                break;
            case 1:
                view = createSingleListView2();
                break;
            case 2:
                view = createSingleListView3();
                break;
            case 3:
//                 view = createSingleListViewHouseType();
                view = createSingleListView4();
                break;
        }

        return view;
    }
    private View createSingleListView1() {
        SingleListView<DictionaryBean> singleListView = new SingleListView<DictionaryBean>(mContext)
                .adapter(new SimpleTextAdapter<DictionaryBean>(null, mContext) {
                    @Override
                    public String provideText(DictionaryBean string) {
                        return string.getValue();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(item -> {
                    FilterUrl.instance().singleListPosition = item.getValue();
                    FilterUrl.instance().position = 0;
                    FilterUrl.instance().positionTitle = item.getValue();
                    FilterUrl.instance().positionId = item.getCode();
                    onFilterDone();
                });
        List<DictionaryBean> list1 = new ArrayList<>();
        for (DictionaryBean bean:address){
            list1.add(new DictionaryBean(bean.getCode(),bean.getValue()));
        }
        singleListView.setList(list1, -1);
        return singleListView;
    }

    private View createSingleListView2() {
        SingleListView<DictionaryBean> singleListView = new SingleListView<DictionaryBean>(mContext)
                .adapter(new SimpleTextAdapter<DictionaryBean>(null, mContext) {
                    @Override
                    public String provideText(DictionaryBean string) {
                        return string.getValue();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(item -> {
                    FilterUrl.instance().singleListPosition = item.getValue();
                    FilterUrl.instance().position = 1;
                    FilterUrl.instance().positionTitle = item.getValue();
                    FilterUrl.instance().positionId = item.getCode();
                    onFilterDone();
                });
        List<DictionaryBean> list1 = new ArrayList<>();
        for (DictionaryBean bean:salePrice){
            list1.add(new DictionaryBean(bean.getCode(),bean.getValue()));
        }
        singleListView.setList(list1, -1);
        return singleListView;
    }

    private View createSingleListView3() {
        SingleListView<DictionaryBean> singleListView = new SingleListView<DictionaryBean>(mContext)
                .adapter(new SimpleTextAdapter<DictionaryBean>(null, mContext) {
                    @Override
                    public String provideText(DictionaryBean string) {
                        return string.getValue();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(item -> {
                    FilterUrl.instance().singleListPosition = item.getValue();
                    FilterUrl.instance().position = 2;
                    FilterUrl.instance().positionTitle = item.getValue();
                    FilterUrl.instance().positionId = item.getCode();
                    onFilterDone();
                });
        List<DictionaryBean> list1 = new ArrayList<>();
        for (DictionaryBean bean:bedRooms){
            list1.add(new DictionaryBean(bean.getCode(),bean.getValue()));
        }
        singleListView.setList(list1, -1);
        return singleListView;
    }

    private View createSingleListView4() {
        SingleListView<DictionaryBean> singleListView = new SingleListView<DictionaryBean>(mContext)
                .adapter(new SimpleTextAdapter<DictionaryBean>(null, mContext) {
                    @Override
                    public String provideText(DictionaryBean string) {
                        return string.getValue();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(item -> {
                    FilterUrl.instance().singleListPosition = item.getValue();
                    FilterUrl.instance().position = 3;
                    FilterUrl.instance().positionTitle = item.getValue();
                    FilterUrl.instance().positionId = item.getCode();
                    onFilterDone();
                });
        List<DictionaryBean> list1 = new ArrayList<>();
        for (DictionaryBean bean:area){
            list1.add(new DictionaryBean(bean.getCode(),bean.getValue()));
        }
        singleListView.setList(list1, -1);
        return singleListView;
    }
/*
    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add("" + i);
        }
        singleListView.setList(list, -1);

        return singleListView;
    }*/

    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }

}
