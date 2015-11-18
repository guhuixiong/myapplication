package com.example.lingzihui.linzihuiandroid.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lingzihui.linzihuiandroid.R;
import com.example.lingzihui.linzihuiandroid.adapter.CommonAdapter;
import com.example.lingzihui.linzihuiandroid.adapter.ViewHolder;
import com.example.lingzihui.linzihuiandroid.entity.ItemData;

import java.util.ArrayList;
import java.util.List;


public class FourFragment extends Fragment {

    private Activity activity;
    private View conventView;
    private ListView mLv;
    private List<ItemData> list = new ArrayList<ItemData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        conventView = inflater.inflate(R.layout.fragment_four, null);
        return conventView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        findView();
        initData();
        addAction();

    }


    private void findView() {
        mLv = (ListView) activity.findViewById(R.id.four_lv);
    }

    private void initData() {
        list.add(new ItemData("相册",R.mipmap.life_circle_icon));
        list.add(new ItemData("设置",R.mipmap.lingjin_activity_icon));
        mLv.setAdapter(new CommonAdapter<ItemData>(activity.getApplicationContext(),list,R.layout.lv_item) {
            @Override
            public void convert(ViewHolder helper, ItemData item) {
                helper.setImageResource(R.id.iv_icon,item.getIcon());
                helper.setText(R.id.tv_item_text,item.getText());
            }
        });
    }

    private void addAction() {

    }
}
