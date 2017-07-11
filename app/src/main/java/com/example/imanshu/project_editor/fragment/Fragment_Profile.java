package com.example.imanshu.project_editor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.activity.Home;
import com.example.imanshu.project_editor.activity.Login;
import com.example.imanshu.project_editor.view.RoundedImageView;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_Profile extends Fragment {

    RoundedImageView home_roundedimage;
    Button profile_btn_logout;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myInflatedView = inflater.inflate(R.layout.fragment_fragment__profile, container,false);

        profile_btn_logout=(Button)myInflatedView.findViewById(R.id.profile_btn_logout);

        mAuth = FirebaseAuth.getInstance();

       // return inflater.inflate(R.layout.fragment_fragment__profile, container, false);
        home_roundedimage=(RoundedImageView)myInflatedView.findViewById(R.id.home_roundedimage);
        FirebaseUser user=mAuth.getCurrentUser();

       // Uri imguri=Uri.parse(user.getPhotoUrl().toString());

        home_roundedimage.setImageResource(R.drawable.im);

        profile_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home act=(Home)getActivity();
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent signout=new Intent(act,Login.class);
                signout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                signout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signout);
            }
        });
        return myInflatedView;
    }
}
