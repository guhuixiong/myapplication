package com.example.lingzihui.linzihuiandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lingzihui.linzihuiandroid.R;
import com.example.lingzihui.linzihuiandroid.entity.MyUser;
import com.example.lingzihui.linzihuiandroid.utils.KeyBoardUtils;
import com.example.lingzihui.linzihuiandroid.utils.L;
import com.example.lingzihui.linzihuiandroid.utils.SPUtils;
import com.example.lingzihui.linzihuiandroid.utils.T;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.SMSSDK;

public class LoginActivity extends Activity {

    // 填写从短信SDK应用后台注册得到的APPKEY123456
    private static String APPKEY = "bd2e97285a60";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "a674e74bf5ee044aab7695bdf0135a29";
    public static final String TAG = "LoginActivity";

    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.login_but)
    Button login;
    @InjectView(R.id.tv_registered)
    TextView registered;
    @InjectView(R.id.forget_pwd)
    TextView password;
    @InjectView(R.id.et_username)
    EditText etUser;
    @InjectView(R.id.et_password)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "953e584ada055ec7250cbc57f589f308");
        SMSSDK.initSDK(this, APPKEY, APPSECRET);// 初始化短信SDK
        ButterKnife.inject(this);//执行注入
        initData();
        addAction();
    }

    private void initData() {
        title.setText("登录");
        etUser.setText((String)SPUtils.get(this,"username",""));
        etPwd.setText((String)SPUtils.get(this,"password",""));
    }

    private void addAction() {
        login.setOnClickListener(new MyClickListener());
        registered.setOnClickListener(new MyClickListener());
        password.setOnClickListener(new MyClickListener());
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_but://登录
                    login();
                    break;
                case R.id.tv_registered://注册
                    Registered registered = new Registered();
                    registered.show(LoginActivity.this);
                    break;
                case R.id.forget_pwd://忘记密码
                    break;
            }
        }
    }

    private void login() {
        KeyBoardUtils.closeKeybord(etPwd, this);
        final String username = etUser.getText().toString().trim();
        final String pwd = etPwd.getText().toString().trim();
        checkpwd(username, pwd);//检验用户名和密码是否为空！


        BmobUser.loginByAccount(this, username, pwd, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                // TODO Auto-generated method stub
                if (user != null) {
                    L.i(TAG, "用户登陆成功");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user",user);
                    SPUtils.put(LoginActivity.this, "username", username);
                    SPUtils.put(LoginActivity.this, "password", pwd);
                    startActivity(intent);
                    finish();
                }else{
                    T.show(LoginActivity.this,"用户名或密码输入错误！",1);
                    L.i(TAG, "用户名或密码输入错误！");
                }
            }
        });
    }

    private void checkpwd(String username, String pwd) {
        if("".equals(username) || username.length()==0){
            T.show(this, "用户名不能为空！", 1);
            return ;
        }

        if("".equals(pwd) || pwd.length()==0){
            T.show(this, "密码不能为空！", 1);
            return ;
        }
    }


}
