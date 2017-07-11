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

public class Signup_form extends AppCompatActivity {

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
    @Bind(R.id.signupform_google_login)
    SignInButton signInButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    CallbackManager mCallbackManager;
    LoginButton loginButton;

    public static final int RC_SIGN_IN = 100;
    private static final String TAG="Signup_form";
    private GoogleApiClient mGoogleApiClient;

    @OnClick(R.id.signup_text_existinguser)
    public void existing_user(){
        Intent intent=new Intent(Signup_form.this,Login_Form.class);
        startActivity(intent);
    }

    @OnClick(R.id.signupform_google_login)
    public void setSignInButton(){

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.signupform_button)
    public void setSignupform_button() {
        String email, password;
        email = signup_email.getText().toString();
        password = signup_password.getText().toString();

        if (password.isEmpty() || email.isEmpty())
        {
            signupform_error.setText("That doesn't look like a correct email address and password");
            signupform_error.setTextColor(this.getResources().getColor(R.color.red));
        }
        else
        {
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

        Button signup_gmail_button=(Button)findViewById(R.id.signup_gmail_button);
        signup_gmail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignInButton();
            }
        });

        signInButton.setSize(SignInButton.SIZE_WIDE);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(Signup_form.this,Home.class));
                }
            }
        };

        ////////////////////////////////////////
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        Button signup_fb_button=(Button)findViewById(R.id.signup_fb_button);
        signup_fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
            }
        });
        loginButton = (LoginButton) findViewById(R.id.fb_signup_button);
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
                Toast.makeText(Signup_form.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        });

        /////////////////////////////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                Toast.makeText(Signup_form.this, "you got an error", Toast.LENGTH_SHORT).show();
            }
        })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signup_email.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");
        signup_heading.setTypeface(type);
        signup_or.setTypeface(type);
        signup_password.setTypeface(Typeface.DEFAULT);
        signup_password.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
        signupform_error.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
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
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {

                            signupform_error.setText("Something went wrong , please try again!");
                            signupform_error.setTextColor(Signup_form.this.getResources().getColor(R.color.red));
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        progressBar.setVisibility(View.VISIBLE);
        signupform_error.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            signupform_error.setVisibility(View.VISIBLE);
                            Intent intent=new Intent(Signup_form.this,Home.class);
                            startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            progressBar.setVisibility(View.GONE);
                            signupform_error.setVisibility(View.VISIBLE);
                            signupform_error.setText("Something went wrong , please try again!");
                            signupform_error.setTextColor(Signup_form.this.getResources().getColor(R.color.red));
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());


                        }

                        // ...
                    }
                });
    }

}
