package com.netforce.ray.profile.userprofile;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.general.UserSessionManager;
import com.netforce.ray.profile.UserProfile;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements View.OnClickListener {


    private static final String TAG = "RAY_SCHPOCK";
    private ImageView imageViewFacebook, imageViewBG, imageViewTwitter;
    TextView textViewName;
    CircleImageView imageViewDP;
    UserSessionManager userSessionManager;
    private Context context;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(((AppCompatActivity) context).getApplication());
        userSessionManager = new UserSessionManager(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView textViewLogout = (TextView) view.findViewById(R.id.textViewLogout);
        textViewLogout.setOnClickListener(this);
        imageViewFacebook = (ImageView) view.findViewById(R.id.imageViewFacebook);
        imageViewTwitter = (ImageView) view.findViewById(R.id.imageViewTwitter);
        imageViewBG = (ImageView) view.findViewById(R.id.imageViewBG);
        imageViewDP = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        setupView();
        imageViewFacebook.setOnClickListener(this);
        imageViewTwitter.setOnClickListener(this);
        view.findViewById(R.id.imageViewWhatsup).setOnClickListener(this);
        return view;

    }

    private void setupView() {
        if (userSessionManager.getToken().length() > 0) {
            textViewName.setText(userSessionManager.getName());
            String imageURL = "https://graph.facebook.com/" + userSessionManager.getFBID() + "/picture?type=large";
            Picasso.with(context)
                    .load(imageURL)
                    .placeholder(R.drawable.pro_pic)
                    .error(R.drawable.pro_pic)
                    .into(imageViewDP);
            Picasso.with(context)
                    .load(imageURL)
                    .placeholder(R.drawable.pro_pic)
                    .error(R.drawable.pro_pic)
                    .into(imageViewBG);

        }
    }

    private void facebookAppInvite() {
        String appLinkUrl, previewImageUrl;

        appLinkUrl = "https://fb.me/1649446431944567";
        // previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";

        if (AppInviteDialog.canShow()) {
            AppInviteContent content = new AppInviteContent.Builder()
                    .setApplinkUrl(appLinkUrl)
                    // .setPreviewImageUrl(previewImageUrl)
                    .build();
            AppInviteDialog.show(this, content);
        }
    }

    public void twitterAppInvite() {
        String tweetUrl = String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                urlEncode("Tweet text"),
                urlEncode("https://www.google.fi/"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

// Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }
        startActivity(intent);
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf(TAG, "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewFacebook:
                facebookAppInvite();
                break;
            case R.id.imageViewTwitter:
                twitterAppInvite();
                break;
            case R.id.imageViewWhatsup:
                whatsappAppInvite();
                break;
            case R.id.textViewLogout:
                UserSessionManager userSessionManager = new UserSessionManager(getActivity());
                userSessionManager.setToken("");
                userSessionManager.setFBID("");
                userSessionManager.setEmail("");

                try {
                    LoginManager.getInstance().logOut();
                } catch (Exception ex) {

                }
                Intent intent = new Intent(getActivity(), Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
        }
    }

    private void whatsappAppInvite() {
        PackageManager pm = context.getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }
}
