package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Login_Form extends AppCompatActivity {

    @Bind(R.id.login_text_newuser) TextView login_text_newuser;
    @Bind(R.id.login_button)
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);

        ButterKnife.bind(this);

        TextView textView = (TextView) findViewById(R.id.login_heading);
        TextView login_or = (TextView) findViewById(R.id.login_or);

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");

        textView.setTypeface(type);
        login_or.setTypeface(type);


        EditText password = (EditText) findViewById(R.id.login_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.login_text_newuser)
    public void newuser(){
        Intent intent=new Intent(Login_Form.this,Signup_form.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    public void login(){
        Intent intent1=new Intent(Login_Form.this,Home.class);
        startActivity(intent1);
    }
}
