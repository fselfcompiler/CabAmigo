package com.example.rahulsk.cabamigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by rahul.sk on 30/07/15.
 */
public class After_Login extends Activity {
    private LoginButton logoutbutton;
    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;

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

    void welcome_message(Profile profile)
    {
        if(profile!=null)
        {
            mTextDetails.setText("You Are Logged In "+profile.getFirstName());
        }

    }

    public After_Login() {
        super();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.after_login);
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

        mTextDetails=(TextView)findViewById(R.id.text_details);
        logoutbutton=(LoginButton)findViewById(R.id.login_button);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();

                Intent intent=new Intent(getApplicationContext(),Before_Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Profile profile=Profile.getCurrentProfile();
        welcome_message(profile);
        if(isUserLoggedIn()!=true)
        {
            Intent intent=new Intent(getApplicationContext(),MainFragment.class);
            startActivity(intent);
            finish();
        }
    }

    boolean isUserLoggedIn()
    {
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
