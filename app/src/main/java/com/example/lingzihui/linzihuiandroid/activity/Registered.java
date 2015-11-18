package com.example.lingzihui.linzihuiandroid.activity;

import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
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
import com.example.lingzihui.linzihuiandroid.utils.L;
import com.example.lingzihui.linzihuiandroid.utils.T;
import com.mob.tools.FakeActivity;

import java.util.HashMap;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import cn.smssdk.gui.layout.RegisterPageLayout;
import cn.smssdk.gui.layout.Res;

import static com.mob.tools.utils.R.getStringRes;

/**
 * Created by guhuixiong on 2015/11/17.
 * 短信注册页面
 */
public class Registered  extends FakeActivity{

    public static final String TAG = "Registered";
    private TextView mTvBack,title,verification;
    private Button next_but;
    private EditText etPhoneNum,etCode;
    private OnSendMessageHandler osmHandler;

    public void show(Context context) {
        super.show(context, null);
    }

    public void onCreate() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.activity_registered, null);
        if(layout != null) {
            activity.setContentView(layout);
            mTvBack = (TextView) activity.findViewById(R.id.back_tv);
            title = (TextView) activity.findViewById(R.id.title_tv);
            verification = (TextView) activity.findViewById(R.id.send_verification);
            next_but = (Button) activity.findViewById(R.id.next_but);
            etPhoneNum = (EditText) activity.findViewById(R.id.et_phone);
            etCode = (EditText) activity.findViewById(R.id.et_code);

            mTvBack.setVisibility(View.VISIBLE);
            mTvBack.setText("<登录");
            title.setText("注册");
            mTvBack.setOnClickListener(new MyClickListener());
            verification.setOnClickListener(new MyClickListener());
            next_but.setOnClickListener(new MyClickListener());
        }
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_tv://返回
                    finish();
                    break;
                case R.id.send_verification://发送验证码
                    sendVerification();
                    break;
                case R.id.next_but://下一步
                    next();
                    break;
            }
        }
    }

    private void next() {
        final String phone = etPhoneNum.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        BmobSMS.verifySmsCode(activity, phone, code, new VerifySMSCodeListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//短信验证码已验证成功
                    L.i(TAG, "验证通过");
                    T.show(activity, "验证通过", 1);
                    finish();
                    RegisteredPwd rp = new RegisteredPwd();
                    rp.setPhone(phone);
                    rp.show(activity);
                } else {
                    T.show(activity, "验证失败", 1);
                    Log.i(TAG, "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                }
            }
        });
    }



    private void sendVerification() {
        //隐藏软键盘
        KeyBoardUtils.closeKeybord(etPhoneNum, activity);
        // 请求发送短信验证码
        String phone = etPhoneNum.getText().toString().trim().replaceAll("\\s*", "");
//        SMSSDK.getVerificationCode("86", phone.trim(), osmHandler);
        BmobSMS.requestSMSCode(activity, phone, "模板名称", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    L.i(TAG, "验证码发送成功");
                    T.show(activity, "验证码发送成功", 1);
                }
            }
        });
    }

}
