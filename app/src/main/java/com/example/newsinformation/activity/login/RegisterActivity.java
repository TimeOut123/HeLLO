package com.example.newsinformation.activity.login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newsinformation.R;
import com.example.newsinformation.po.User;
import com.example.newsinformation.service.UserService;

public class RegisterActivity extends AppCompatActivity {
    private EditText userNameE;
    private EditText passwordE;
    private Button register;
    private Button cancel;
    private EditText phoneE;
    private RadioGroup radioGroup;
    private RadioButton radioBoy;
    private RadioButton radioGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userNameE = findViewById(R.id.userNameE);
        passwordE = findViewById(R.id.passwordE);
        cancel = findViewById(R.id.Cancel);
        register = findViewById(R.id.buttonRegister);
        radioBoy = findViewById(R.id.radioBoy);
        radioGirl = findViewById(R.id.radioGirl);
        radioGroup = findViewById(R.id.radioGrop);
        phoneE = findViewById(R.id.userPhone);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                int usex = 1;
                user.setUname(userNameE.getText().toString());
                user.setUpassword(passwordE.getText().toString());
                if(radioBoy.isChecked()) {
                    usex = 0;
                }
                user.setUsex(usex);
                user.setUphone(phoneE.getText().toString());
                UserService userService = new UserService(RegisterActivity.this);
                if(!userService.findByUnameAndPassword(user)) {
                    if(userService.insert(user)){//新增信息结果判断
                        Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                        intent1.putExtra("userName",user.getUname());
                        intent1.putExtra("password",user.getUpassword());
//                        setResult(RESULT_OK,intent1);
                        startActivity(intent1);
                    }else {
                        Toast.makeText(RegisterActivity.this,"注册信息有误，请正确输入！！！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"该账户已存在，请重新注册！！！",Toast.LENGTH_SHORT).show();
                    userNameE.setText("");
                    passwordE.setText("");
                    radioBoy.isChecked();
                    phoneE.setText("");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameE.setText("");
                passwordE.setText("");
                radioBoy.isChecked();
                phoneE.setText("");
            }
        });



    }
}