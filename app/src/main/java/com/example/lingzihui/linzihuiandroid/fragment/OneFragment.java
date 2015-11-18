package com.example.lingzihui.linzihuiandroid.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lingzihui.linzihuiandroid.R;


public class OneFragment extends Fragment {

    private Activity activity;
    private View conventView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        conventView = inflater.inflate(R.layout.fragment_one, null);
        return conventView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        /*findView();
        initData();
        addAction();*/

    }

}
