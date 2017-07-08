package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Login extends AppCompatActivity {

@Bind(R.id.btn_login)
    Button button;

    @Bind(R.id.btn_signup)
    Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);




        TextView textView = (TextView) findViewById(R.id.main_heading);
        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");
        textView.setTypeface(type);


    }

    @OnClick(R.id.btn_login)
    public void login(){
        Intent i=new Intent(Login.this,Login_Form.class);
        startActivity(i);
    }
    @OnClick(R.id.btn_signup)
    public void setSignup(){
        Intent j=new Intent(Login.this,Signup_form.class);
        startActivity(j);
    }
}