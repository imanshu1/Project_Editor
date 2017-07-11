package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imanshu.project_editor.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

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
    @Bind(R.id.loginform_google_login)
    SignInButton signInButton;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    LoginButton loginButton;
    CallbackManager mCallbackManager;

    //Signin constant to check the activity result
    public static final int RC_SIGN_IN = 100;
    private static final String TAG="Signup_form";

    private GoogleApiClient mGoogleApiClient;
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

    @OnClick(R.id.loginform_google_login)
    public void setLogin_button(){

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Button gmail=(Button)findViewById(R.id.gmail);
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin_button();
            }
        });

        signInButton.setSize(SignInButton.SIZE_WIDE);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(Login_Form.this,Home.class));
                }
            }
        };

        ////////////////////////////////////////
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        Button fb=(Button)findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
            }
        });



        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        //loginButton.setBackgroundResource(R.drawable.facebook_btn);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                //Toast.makeText(Login_Form.this, "on success", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                //Toast.makeText(Login_Form.this, "on CANCRL", Toast.LENGTH_SHORT).show();
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(Login_Form.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        });

        /////////////////////////////////////////
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(Login_Form.this, "you got an error", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        //////////////////////////////////////////////////////

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");

        login_heading.setTypeface(type);
        login_or.setTypeface(type);
        loginform_password.setTypeface(Typeface.DEFAULT);
        loginform_password.setTransformationMethod(new PasswordTransformationMethod());
    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        progressBar.setVisibility(View.VISIBLE);
        loginform_error.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            loginform_error.setVisibility(View.VISIBLE);
                            Intent intent=new Intent(Login_Form.this,Home.class);
                            startActivity(intent);
                           // Toast.makeText(Login_Form.this, "on cmplete", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            progressBar.setVisibility(View.GONE);
                            loginform_error.setVisibility(View.VISIBLE);
                            loginform_error.setText("Something went wrong , please try again!");
                            loginform_error.setTextColor(Login_Form.this.getResources().getColor(R.color.red));
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        progressBar.setVisibility(View.VISIBLE);
        loginform_error.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            loginform_error.setVisibility(View.VISIBLE);

                            Intent intent=new Intent(Login_Form.this,Home.class);
                            startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            progressBar.setVisibility(View.GONE);
                            loginform_error.setVisibility(View.VISIBLE);
                            loginform_error.setText("Something went wrong , please try again!");
                            loginform_error.setTextColor(Login_Form.this.getResources().getColor(R.color.red));
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());


                        }

                        // ...
                    }
                });
    }

}