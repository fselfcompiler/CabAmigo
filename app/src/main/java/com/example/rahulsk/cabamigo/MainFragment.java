package com.example.rahulsk.cabamigo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;
    private LoginButton loginButton;



    private FacebookCallback<LoginResult> mCallback= new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken=loginResult.getAccessToken();
            Profile profile=Profile.getCurrentProfile();
            welcome_message(profile);


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };


    public MainFragment() {

    }

    void welcome_message(Profile profile)
    {
        if(profile!=null)
        {
            mTextDetails.setText(profile.getFirstName());
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager=CallbackManager.Factory.create();

        tracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldaccessToken, AccessToken newaccessToken) {

            }
        };

        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {

            }
        };


        tracker.startTracking();
        profileTracker.startTracking();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(isUserLoggedIn()!=true)
        {
            want_to_signup();
        }
        else
        {
            Intent intent = new Intent(getActivity(), After_Login.class);
            startActivity(intent);


        }
//        Profile profile=Profile.getCurrentProfile();
//        welcome_message(profile);
//
   }

    void want_to_signup()
    {
        mTextDetails.setText("Sign Up with FaceBook");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton=(LoginButton) view.findViewById(R.id.login_button);
        mTextDetails=(TextView) view.findViewById(R.id.text_details);
        mTextDetails.setTextColor(Color.BLUE);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);
        if(isUserLoggedIn())
        {
            Intent intent=new Intent(getActivity(),After_Login.class);
            startActivity(intent);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLoggedIn() != true) {
                    want_to_signup();
                } else {

                    Intent intent = new Intent(getActivity(), After_Login.class);
                    startActivity(intent);

                }
            }
        });
        if(isUserLoggedIn()!=true)
        {
            want_to_signup();
        }
        view.setBackgroundColor(Color.BLACK);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        tracker.stopTracking();
        profileTracker.stopTracking();
    }

    boolean isUserLoggedIn()
    {
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            return true;
        }
        return false;
    }


}
