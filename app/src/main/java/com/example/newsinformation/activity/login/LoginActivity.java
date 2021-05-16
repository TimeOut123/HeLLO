package com.example.newsinformation.activity.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsinformation.MainActivity;
import com.example.newsinformation.R;
import com.example.newsinformation.po.User;
import com.example.newsinformation.service.UserService;

public class LoginActivity extends AppCompatActivity {
    private Button buttonOk;
    private Button buttonCancel;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private String userName;
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPwd;
    private String password;
    private TextView registerView;
    private TextView forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollect.addActivity(this);//将活动加入活动管理器中


        buttonOk = findViewById(R.id.buttonOK);
        buttonCancel = findViewById(R.id.buttonCancel);
        editTextUserName= findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        rememberPwd = findViewById(R.id.rememberWpd);
        registerView = findViewById(R.id.registerView);
        forget = findViewById(R.id.forget);
        pre = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pre.getBoolean("remember_pwd",false);
        if(isRemember) {
            //将账号密码都设置到文本中
            userName = pre.getString("userName","");
            password = pre.getString("password","");
            editTextUserName.setText(userName);
            editTextPassword.setText(password);
            rememberPwd.setChecked(true);
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rememberPwd.setChecked(false);
                editTextUserName.setText("");
                editTextPassword.setText("");
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName= editTextUserName.getText().toString().trim();
                password=editTextPassword.getText().toString().trim();
                User user = new User();
                user.setUname(userName);
                user.setUpassword(password);
                UserService userService = new UserService(LoginActivity.this);
                boolean isLogin = userService.findByUnameAndPassword(user);
                if (isLogin) {
                    editor = pre.edit();
                    if(rememberPwd.isChecked()){//检查复选框有没有被选中
                        editor.putBoolean("remember_pwd",true);
                        editor.putString("userName",userName);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "登录成功！！！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userName",userName);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败,用户名或密码错误！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"该功能有待实现！！！",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String name = data.getStringExtra("userName");
                    String pwd = data.getStringExtra("password");
                    editTextUserName.setText(name);
                    editTextPassword.setText(pwd);

                }
                break;
            default:
                break;
        }
    }
}