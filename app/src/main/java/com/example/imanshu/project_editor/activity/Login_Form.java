package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Login_Form extends AppCompatActivity {

    @Bind(R.id.login_text_newuser)
    TextView login_text_newuser;
    @Bind(R.id.login_button)
    Button login_button;
    @Bind(R.id.login_heading)
    TextView login_heading;
    @Bind(R.id.login_or)
    TextView login_or;
    @Bind(R.id.loginform_email)
    EditText loginform_email;
    @Bind(R.id.loginform_password)
    EditText loginform_password;
    @Bind(R.id.loginform_error)
    TextView loginform_error;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @OnClick(R.id.login_text_newuser)
    public void newuser(){
        Intent intent=new Intent(Login_Form.this,Signup_form.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    public void login(){
        String password,email;
        email=loginform_email.getText().toString();
        password=loginform_password.getText().toString();

        if (password.isEmpty() || email.isEmpty()){
            loginform_error.setVisibility(View.VISIBLE);
            loginform_error.setText("That doesn't look like a valid email address and password");
            loginform_error.setTextColor(this.getResources().getColor(R.color.red));
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.GONE);
                        Intent intent=new Intent(Login_Form.this,Home.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        loginform_error.setVisibility(View.VISIBLE);
                        loginform_error.setText(e.getMessage());
                        loginform_error.setTextColor(Login_Form.this.getResources().getColor(R.color.red));
                    }
                });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");

        login_heading.setTypeface(type);
        login_or.setTypeface(type);
        loginform_password.setTypeface(Typeface.DEFAULT);
        loginform_password.setTransformationMethod(new PasswordTransformationMethod());
    }
}