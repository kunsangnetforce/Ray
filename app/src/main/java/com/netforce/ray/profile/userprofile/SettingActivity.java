package com.netforce.ray.profile.userprofile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.netforce.ray.R;
import com.netforce.ray.general.UserSessionManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TAKE_PHOTO_CODE = 108;
    private static final String IMAGE_DIRECTORY_NAME = "ray";
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int PICK_IMAGE = 109;
    private Toolbar toolbar;
    ImageView imageViewEmailNotification, imageViewNewsLetter, imageViewImmediate, imageViewNotificationSound, imageViewDP;
    boolean booleanEmailNotification = true, booleanNewsLetter = true, booleanImmediate = false, booleanNotificationSound = true;
    private Uri fileUri;
    private String filePath;
    TextView textViewEmail;
    private MaterialDialog dialog;
    private Context context;
    private UserSessionManager userSessionManager;
    MaterialEditText editTextEmail;
    private MaterialDialog dialogEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        userSessionManager = new UserSessionManager(context);
        setupToolBar("Settings");
        initView();
        getPermission();
    }

    private void initView() {
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        imageViewDP = (ImageView) findViewById(R.id.imageViewDP);
        imageViewEmailNotification = (ImageView) findViewById(R.id.imageViewEmailNotification);
        imageViewImmediate = (ImageView) findViewById(R.id.imageViewImmediate);
        imageViewNewsLetter = (ImageView) findViewById(R.id.imageViewNewsLetter);
        imageViewNotificationSound = (ImageView) findViewById(R.id.imageViewNotificationSound);
        imageViewEmailNotification.setOnClickListener(this);
        imageViewNewsLetter.setOnClickListener(this);
        imageViewImmediate.setOnClickListener(this);
        imageViewNotificationSound.setOnClickListener(this);
        findViewById(R.id.linearLayoutDP).setOnClickListener(this);
        findViewById(R.id.linearLayoutEmail).setOnClickListener(this);
        setChecked(imageViewEmailNotification, booleanEmailNotification);
        setChecked(imageViewNewsLetter, booleanNewsLetter);
        setChecked(imageViewImmediate, booleanImmediate);
        setChecked(imageViewNotificationSound, booleanNotificationSound);
        setDP();
    }

    private void setDP() {
        String imageURL = "https://graph.facebook.com/" + userSessionManager.getFBID() + "/picture?type=large";
        Picasso.with(context)
                .load(imageURL)
                .placeholder(R.drawable.pro_pic)
                .error(R.drawable.pro_pic)
                .into(imageViewDP);

    }

    private void setChecked(ImageView imageViewEmailNotification, boolean booleanEmailNotification) {
        if (booleanEmailNotification) {
            imageViewEmailNotification.setImageResource(R.drawable.ic_check_primary);
        } else {
            imageViewEmailNotification.setImageResource(R.drawable.ic_blank_circle);
        }
    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewEmailNotification:
                booleanEmailNotification = !booleanEmailNotification;
                setChecked(imageViewEmailNotification, booleanEmailNotification);
                break;
            case R.id.imageViewNewsLetter:
                booleanNewsLetter = !booleanNewsLetter;
                setChecked(imageViewNewsLetter, booleanNewsLetter);
                break;
            case R.id.imageViewImmediate:
                booleanImmediate = !booleanImmediate;
                setChecked(imageViewImmediate, booleanImmediate);
                break;
            case R.id.imageViewNotificationSound:
                booleanNotificationSound = !booleanNotificationSound;
                setChecked(imageViewNotificationSound, booleanNotificationSound);
                break;
            case R.id.linearLayoutDP:
                showEditPicPopup();
                break;
            case R.id.linearLayoutPicture:
                takePictureIntent();
                dialog.dismiss();
                break;
            case R.id.linearLayoutGalary:
                pickPictureIntent();
                dialog.dismiss();
                break;
            case R.id.linearLayoutEmail:
                changeEmailPopUp();
                break;
        }
    }

    private void changeEmailPopUp() {
        boolean wrapInScrollView = true;
        dialogEmail = new MaterialDialog.Builder(this)
                .title(R.string.editemail)
                .customView(R.layout.change_email, wrapInScrollView)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        textViewEmail.setText(editTextEmail.getText());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .show();
        editTextEmail = (MaterialEditText) dialogEmail.findViewById(R.id.editTextEmail);
    }

    private void pickPictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void takePictureIntent() {
        UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
        String name = userSessionManager.getName();
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        filePath = fileUri.getPath();

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private void showEditPicPopup() {
        boolean wrapInScrollView = true;
        dialog = new MaterialDialog.Builder(this)
                .title(R.string.editpic)
                .customView(R.layout.editpic, wrapInScrollView)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        dialog.findViewById(R.id.linearLayoutGalary).setOnClickListener(SettingActivity.this);
        dialog.findViewById(R.id.linearLayoutPicture).setOnClickListener(SettingActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    Log.i("result picture", "clicked");
                    imageViewDP.setImageURI(fileUri);
                }
                break;
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        imageViewDP.setImageBitmap(decodeUri(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Log.i("result picture", "picked");
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                getContentResolver().openInputStream(selectedImage), null, o2);
    }

    private void getPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permission = {
                    "android.permission.CAMERA",
                    "android.permission.WRITE_EXTERNAL_STORAGE"
            };

            ActivityCompat.requestPermissions(this,
                    permission, 1);


        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            } else {
                Log.d(IMAGE_DIRECTORY_NAME, mediaStorageDir.getAbsolutePath());
            }

        } else {
            Log.d(IMAGE_DIRECTORY_NAME, mediaStorageDir.getAbsolutePath());
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile = null;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
            Log.i("result imagepath", mediaFile.getAbsolutePath());
        } else {
        }

        return mediaFile;
    }
}
