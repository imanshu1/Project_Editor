package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Signup_form extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.signup_text_existinguser)
    TextView signup_text_existinguser;
    @Bind(R.id.signupform_button)
    Button signupform_button;
    @Bind(R.id.signup_heading)
    TextView signup_heading;
    @Bind(R.id.signup_or)
    TextView signup_or;
    @Bind(R.id.signup_password)
    EditText signup_password;
    @Bind(R.id.signup_email)
    EditText signup_email;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.signupform_error)
    TextView signupform_error;
    @Bind(R.id.sign_in_button)
    SignInButton signInButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    private GoogleSignInOptions gso;

    @OnClick(R.id.signup_text_existinguser)
    public void existing_user(){
        Intent intent=new Intent(Signup_form.this,Login_Form.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_in_button)
    public void setSignInButton(){

        signIn();

    }



    @OnClick(R.id.signupform_button)
    public void setSignupform_button() {
        String email, password;
        email = signup_email.getText().toString();
        password = signup_password.getText().toString();

        if (password.isEmpty() || email.isEmpty()) {

            signupform_error.setText("That doesn't look like a correct email address and password");
            signupform_error.setTextColor(this.getResources().getColor(R.color.red));
        } else {
            progressBar.setVisibility(View.VISIBLE);
            signupform_error.setVisibility(View.GONE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            signupform_error.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Signup_form.this, Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                signupform_error.setText("Something went wrong , please try again!");
                                signupform_error.setTextColor(Signup_form.this.getResources().getColor(R.color.red));
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

/////////////////////////////////////////
        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        signInButton.setScopes(gso.getScopeArray());
        //Initializing signinbutton
        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //////////////////////////////////////////////////////



        signup_email.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");

        signup_heading.setTypeface(type);
        signup_or.setTypeface(type);
        signup_password.setTypeface(Typeface.DEFAULT);
        signup_password.setTransformationMethod(new PasswordTransformationMethod());
    }
    /////////////////////////////////////////////////////////////////////////

    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                //AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                Log.d("GOOFG", "firebaseAuthWithGoogle:" + account.getIdToken());
            } else {
                signupform_error.setText("Something went wrong , please try again!");
                signupform_error.setTextColor(Signup_form.this.getResources().getColor(R.color.red));
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
