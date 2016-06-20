package com.netforce.ray.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.general.UserSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewBack;
    private ArrayList<String> permissions = new ArrayList<String>();
    ;
    private Button buttonCustomFB;
    private LoginButton buttonFacebook;
    private Profile profile;
    private CallbackManager mCallbackManager;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        gethash();
        permissions.add("email");
        permissions.add("user_birthday");
        buttonCustomFB = (Button) findViewById(R.id.buttonCustomFB);
        buttonFacebook = (LoginButton) findViewById(R.id.login_button);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(this);
        buttonCustomFB.setOnClickListener(this);
        profile = Profile.getCurrentProfile();
        if (profile != null) {
            buttonCustomFB.setText(getResources().getString(R.string.log_out));

        } else {
            buttonCustomFB.setText(getResources().getString(R.string.continue_with_facebook));
        }
        mCallbackManager = CallbackManager.Factory.create();
        buttonFacebook.setReadPermissions(permissions);
        buttonFacebook.registerCallback(mCallbackManager, mCallBack);
    }

    private void gethash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.netforce.ray",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(final LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        public String home;

                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.i("facebook", response.toString());
                            // Application code
                            if (object != null) {
                                //parameters.putString("fields", "id,name,email,gender, birthday,picture ");
                                AccessToken accessToken = loginResult.getAccessToken();
                                Profile profile = Profile.getCurrentProfile();
                                String fbName;
                                try {
                                    fbName = object.getString("name");
                                } catch (JSONException e) {
                                    fbName = "";
                                    e.printStackTrace();
                                }
                                String fbId;
                                try {
                                    fbId = object.getString("id");
                                } catch (JSONException e) {
                                    fbId = "";
                                    e.printStackTrace();
                                }
                                String fbEmail;
                                try {
                                    fbEmail = object.getString("email");
                                } catch (JSONException e) {
                                    fbEmail = "";
                                    e.printStackTrace();
                                }
                                String fbBirthday;
                                try {
                                    fbBirthday = object.getString("birthday");
                                } catch (JSONException e) {
                                    fbBirthday = "";
                                    e.printStackTrace();
                                }
                                String fbGender;
                                try {
                                    fbGender = object.getString("gender");
                                } catch (JSONException e) {
                                    fbGender = "";
                                    e.printStackTrace();
                                }

                                String fbToken = accessToken.getToken();
                                String imageURL = "https://graph.facebook.com/" + fbId + "/picture?type=large";
                                UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
                                userSessionManager.setToken(fbToken);
                                userSessionManager.setFBID(fbId);
                                userSessionManager.setName(fbName);
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                            }


                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.i("LoginActivity", error.toString());
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewBack:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.buttonCustomFB:
                buttonFacebook.performClick();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,
                resultCode, data);

    }

}
