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

/**
 * 邻友界面
 */
public class TwoFragment extends Fragment {

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
        conventView = inflater.inflate(R.layout.fragment_two, null);
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
        mLv = (ListView) activity.findViewById(R.id.two_lv);
    }

    private void initData() {
        list.add(new ItemData("新朋友",R.mipmap.new_friend_icon));
        list.add(new ItemData("群聊",R.mipmap.group_chat_icon));
        list.add(new ItemData("关注",R.mipmap.guanzhu_icon));
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
