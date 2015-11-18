package com.example.lingzihui.linzihuiandroid.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingzihui.linzihuiandroid.R;
import com.example.lingzihui.linzihuiandroid.utils.KeyBoardUtils;
import com.example.lingzihui.linzihuiandroid.utils.T;
import com.mob.tools.FakeActivity;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by guhuixiong on 2015/11/17.
 * 短信注册页面
 */
public class RegisteredPwd extends FakeActivity{

    public static final String TAG = "Registered";
    private TextView mTvBack,title;
    private Button registered_but;
    private EditText pwd1,pwd2,plaseCode;
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void show(Context context) {
        super.show(context, null);
    }

    public void onCreate() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.registered, null);
        if(layout != null) {
            activity.setContentView(layout);
            mTvBack = (TextView) activity.findViewById(R.id.back_tv);
            title = (TextView) activity.findViewById(R.id.title_tv);
            registered_but = (Button) activity.findViewById(R.id.registered_but);
            pwd1 = (EditText) activity.findViewById(R.id.et_pwd1);
            pwd2 = (EditText) activity.findViewById(R.id.et_pwd2);
            plaseCode = (EditText) activity.findViewById(R.id.et_plase_code);

            mTvBack.setVisibility(View.VISIBLE);
            mTvBack.setText("<注册");
//            title.setText("注册");
            mTvBack.setOnClickListener(new MyClickListener());
            registered_but.setOnClickListener(new MyClickListener());
        }
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_tv://返回
                    finish();
                    break;
                case R.id.registered_but://注册用户
                    registered();
                    break;
            }
        }
    }

    private void registered() {
        String pass1 = pwd1.getText().toString().trim();
        String pass2 = pwd2.getText().toString().trim();
        String code = plaseCode.getText().toString().trim();
        checkpwd(pass1,pass2);//检验密码是否正确

        BmobUser bu = new BmobUser();
        bu.setUsername(phone);
        bu.setPassword(pass1);
        bu.setMobilePhoneNumber(phone);
        //注意：不能用save方法进行注册
        bu.signUp(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                T.show(activity, "注册成功!", 1);
                finish();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                T.show(activity, "注册失败:" + msg,1);
            }
        });

        /*if(code.equals("") || code.length()==0){//邀请码为空！

        }else{

        }*/

    }

    private void checkpwd(String pass1, String pass2) {
        if(pass1.equals("") || pass1.length()==0){
            Toast.makeText(activity, "密码不能为空！", Toast.LENGTH_LONG).show();
            return ;
        }
        if(pass2.equals("") || pass2.length()==0){
            Toast.makeText(activity, "确认密码不能为空！", Toast.LENGTH_LONG).show();
            return ;
        }
        if(!pass1.equals(pass2)){
            Toast.makeText(activity, "两次密码不一致！", Toast.LENGTH_LONG).show();
            return ;
        }
    }


}
