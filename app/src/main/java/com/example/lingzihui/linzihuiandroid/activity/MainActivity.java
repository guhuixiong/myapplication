package com.example.lingzihui.linzihuiandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingzihui.linzihuiandroid.MyApplication;
import com.example.lingzihui.linzihuiandroid.R;
import com.example.lingzihui.linzihuiandroid.entity.MyUser;
import com.example.lingzihui.linzihuiandroid.fragment.FourFragment;
import com.example.lingzihui.linzihuiandroid.fragment.OneFragment;
import com.example.lingzihui.linzihuiandroid.fragment.ThreeFragment;
import com.example.lingzihui.linzihuiandroid.fragment.TwoFragment;
import com.example.lingzihui.linzihuiandroid.utils.L;
import com.example.lingzihui.linzihuiandroid.widget.CommonRadioGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.Bmob;
/** 主界面*/
public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";
    private long exitTime = 0;
    private Fragment oneFragment, twoFragment, threeFragment, fourFragment;
    private MyUser user;

    @InjectView(R.id.main_rg)
    CommonRadioGroup rg;
    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.main_fl_content)
    FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);//执行注入
        initData();
        addAction();
    }

    private void initData() {
        showFragment();
        user = (MyUser) getIntent().getSerializableExtra("user");
        L.i(TAG,"Username=="+user.getUsername());
    }

    private void addAction() {
        rg.setOnCheckedChangeListener(new CommonRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CommonRadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.main_rb1:
                        title.setText("邻信");
                        hideAllFragment(transaction);
                        if (oneFragment == null) {
                            oneFragment = new OneFragment();
                            Log.i(TAG,"new oneFragment()");
                            transaction.add(layout.getId(), oneFragment);
                        } else {
                            transaction.show(oneFragment);
                        }
                        break;
                    case R.id.main_rb2:
                        title.setText("邻友");
                        hideAllFragment(transaction);
                        if (twoFragment == null) {
                            twoFragment = new TwoFragment();
                            Log.i(TAG,"new twoFragment()");
                            transaction.add(layout.getId(), twoFragment);
                        } else {
                            transaction.show(twoFragment);
                        }

                        break;
                    case R.id.main_rb3:
                        title.setText("邻近");
                        hideAllFragment(transaction);
                        if (threeFragment == null) {
                            threeFragment = new ThreeFragment();
                            Log.i(TAG,"new threeFragment()");
                            transaction.add(layout.getId(), threeFragment);
                        } else {
                            transaction.show(threeFragment);
                        }
                        break;
                    case R.id.main_rb4:
                        title.setText("我");
                        hideAllFragment(transaction);
                        if (fourFragment == null) {
                            fourFragment = new FourFragment();
                            Log.i(TAG,"new FourFragment()");
                            transaction.add(layout.getId(), fourFragment);
                        } else {
                            transaction.show(fourFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void showFragment() {
        title.setText("邻信");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (oneFragment == null) {
            oneFragment = new OneFragment();
            Log.i(TAG,"new FourFragment()");
            transaction.add(layout.getId(), oneFragment);
        } else {
            transaction.show(oneFragment);
        }
        transaction.commit();
    }

    /**
     * 隐藏全部显示着的fragment
     *
     * @param transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (oneFragment != null && !oneFragment.isHidden()) {
            transaction.hide(oneFragment);
            Log.i(TAG, "hide oneFragment()");
        }
        if (twoFragment != null && !twoFragment.isHidden()) {
            transaction.hide(twoFragment);
            Log.i(TAG, "hide twoFragment()");
        }
        if (threeFragment != null && !threeFragment.isHidden()) {
            transaction.hide(threeFragment);
            Log.i(TAG, "hide threeFragment()");
        }
        if (fourFragment != null && !fourFragment.isHidden()) {
            transaction.hide(fourFragment);
            Log.i(TAG, "hide fourFragment()");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                exitFunction();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exitFunction() {
        MyApplication.getInstance().exit();
        finish();
    }
}
