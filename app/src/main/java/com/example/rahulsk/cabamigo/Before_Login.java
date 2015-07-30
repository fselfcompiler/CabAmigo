package com.example.rahulsk.cabamigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
 * Created by rahul.sk on 30/07/15.
 */
public class Before_Login extends Activity {

    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;
    private LoginButton loginButton;

//    private FacebookCallback<LoginResult> mCallback= new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//            AccessToken accessToken=loginResult.getAccessToken();
//            Profile profile=Profile.getCurrentProfile();
//            welcome_message(profile);
//
//
//        }
//
//        @Override
//        public void onCancel() {
//
//        }
//
//        @Override
//        public void onError(FacebookException e) {
//
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.before_login);
        mTextDetails=(TextView)findViewById(R.id.text_details);
        loginButton=(LoginButton) findViewById(R.id.login_button);
//      //  mCallbackManager=CallbackManager.Factory.create();
//
//        tracker=new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldaccessToken, AccessToken newaccessToken) {
//
//            }
//        };
//
//        profileTracker=new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {
//
//            }
//        };
//
//
//        tracker.startTracking();
//        profileTracker.startTracking();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
//
//    void welcome_message(Profile profile)
//    {
//        if(profile!=null)
//        {
//            mTextDetails.setText(profile.getFirstName());
//        }
//
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        tracker.stopTracking();
//        profileTracker.stopTracking();
//    }
//
//    boolean isUserLoggedIn()
//    {
//        if(AccessToken.getCurrentAccessToken()!=null)
//        {
//            return true;
//        }
//        return false;
//    }
}
