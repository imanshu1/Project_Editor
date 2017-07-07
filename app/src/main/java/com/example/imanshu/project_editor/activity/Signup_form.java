package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Signup_form extends AppCompatActivity {

    @Bind(R.id.signup_text_existinguser) TextView signup_text_existinguser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        ButterKnife.bind(this);


        EditText email = (EditText) findViewById(R.id.signup_email);
        email.setMaxLines(1);
        email.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        TextView textView = (TextView) findViewById(R.id.signup_heading);
        TextView signup_or = (TextView) findViewById(R.id.signup_or);

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");

        textView.setTypeface(type);
        signup_or.setTypeface(type);

        EditText password = (EditText) findViewById(R.id.signup_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.signup_text_existinguser)
    public void existing_user(){
        Intent intent=new Intent(Signup_form.this,Login_Form.class);
        startActivity(intent);
    }
}
