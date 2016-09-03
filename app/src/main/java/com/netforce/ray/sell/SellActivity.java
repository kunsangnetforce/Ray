package com.netforce.ray.sell;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.netforce.ray.R;
import com.netforce.ray.general.UserSessionManager;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SellActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final int REQUEST_TAKE_GALLERY_VIDEO =0 ;
    private RecyclerView recyclerView;
    private SellAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;
    private static final int TAKE_PHOTO_CODE = 108;
    private static final String IMAGE_DIRECTORY_NAME = "ray";
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int PICK_IMAGE = 109;
    protected static ArrayList<SellData> sellDatas = new ArrayList<>();
    private Toolbar toolbar;
    private MaterialBetterSpinner category;
    private MaterialBetterSpinner currency;
    private ShareDialog shareDialog;
    private CallbackManager callbackManager;
    Button sort_button,anr_button;
    private MaterialDialog dialog;
    ImageView camera_click,video_click;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this;
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(((AppCompatActivity) context).getApplication());

        setContentView(R.layout.activity_sell);

        findViewById(R.id.buttonSell).setOnClickListener(this);

        sort_button = (Button)  findViewById(R.id.sortbutton);

        anr_button = (Button)  findViewById(R.id.inr_button);
        anr_button.setOnClickListener(this);

        camera_click= (ImageView) findViewById(R.id.camera_choose);
        camera_click.setOnClickListener(this);

        video_click = (ImageView) findViewById(R.id.video_choose);
        video_click.setOnClickListener(this);

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder (context, sort_button);


        droppyBuilder.addMenuItem(new DroppyMenuItem("Fashion and Accessories"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Home and Garden"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Electronics"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Movies, Books and Musics"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Baby and Child"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Sport,Leisure and Games"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Cars and Motors"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Services"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("Other"));

        /*for (int i = 0; i < countries.size(); i++) {
            droppyBuilder.addMenuItem(new DroppyMenuItem(countries.get(i)));
        }*/




        droppyBuilder.setOnClick(new DroppyClickCallbackInterface()
        {
            @Override
            public void call(View v, int id)
            {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
        droppyBuilder.build();


        setupToolBar("Sell");
        setupRecyclerView();
        setupDropDown();
        shareDialog = new ShareDialog(this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new

                FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {}

                    @Override
                    public void onCancel() {}

                    @Override
                    public void onError(FacebookException error) {}
                });

    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        setupData();
        adapter = new SellAdapter(context, sellDatas);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true); // THIS ALSO SETS setStackFromBottom to true
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData()
    {
        sellDatas.add(new SellData("path"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    Log.i("result picture", "clicked");
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getApplicationContext(), photo);

                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File(getRealPathFromURI(tempUri));

                    sellDatas.add(new SellData(finalFile.getAbsolutePath()));
                    Log.i("result filepath1", finalFile.getAbsolutePath());
                   // imageViewDP.setImageURI(Uri.parse(finalFile.getAbsolutePath()));
                    adapter.notifyDataSetChanged();
                }
                break;
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    sellDatas.add(new SellData(filePath));
                    Log.i("result filepath1", filePath);
                  //  imageViewDP.setImageURI(Uri.parse(filePath));
                    adapter.notifyDataSetChanged();
                }
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

    public Uri getImageUri(Context inContext, Bitmap inImage)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri)
    {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void setupDropDown()
    {

       DroppyMenuPopup.Builder droppyBuilder2 = new DroppyMenuPopup.Builder(context, anr_button);

        droppyBuilder2.addMenuItem(new DroppyMenuItem("EUR"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("USD"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("GBP"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("CHF"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("NOK"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("SEK"));
        droppyBuilder2.addMenuItem(new DroppyMenuItem("INR"));


        droppyBuilder2.setOnClick(new DroppyClickCallbackInterface()
        {
            @Override
            public void call(View v, int id)
            {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
        droppyBuilder2.build();

        /*ArrayList<String> categoriesName = new ArrayList<>();
        categoriesName.add("Fashion and Accessories");
        categoriesName.add("Home and Garden");
        categoriesName.add("Electronics");
        categoriesName.add("Movies, Books and Musics");
        categoriesName.add("Baby and Child");
        categoriesName.add("Sport,Leisure and Games");
        categoriesName.add("Cars and Motors");
        categoriesName.add("Services");
        categoriesName.add("Other");
        MyCustomAdapter adapter1 = new MyCustomAdapter(this, R.layout.spinner_text_layout, categoriesName);
        category = (MaterialBetterSpinner) findViewById(R.id.category);
        category.setAdapter(adapter1);
        category.setHint(getResources().getString(R.string.choose_category));
       */


      /*  ArrayList<String> curruncy = new ArrayList<>();
        curruncy.add("EUR");
        curruncy.add("USD");
        curruncy.add("GBP");
        curruncy.add("CHF");
        curruncy.add("NOK");
        curruncy.add("SEK");
        curruncy.add("INR");
        MyCustomAdapter adapter2 = new MyCustomAdapter(this, R.layout.spinner_text_layout, curruncy);
        currency = (MaterialBetterSpinner) findViewById(R.id.currency);
        currency.setAdapter(adapter2);
        currency.setHint(getResources().getString(R.string.currency));*/

    }

    @Override
    protected void onDestroy()
    {
        sellDatas.clear();
        super.onDestroy();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.buttonSell:
                showMessage("Clicked");
                shareContent();
                break;
            case R.id.camera_choose:

                showEditPicPopup();
            break;

            case R.id.video_choose:

                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
                break;
        }
    }

    private void showEditPicPopup()
    {
        boolean wrapInScrollView = true;
        dialog = new MaterialDialog.Builder(context)
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
        dialog.findViewById(R.id.linearLayoutGalary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPictureIntent();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.linearLayoutPicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
                dialog.dismiss();
            }
        });

    }


    private void pickPictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((AppCompatActivity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void takePictureIntent() {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String name = userSessionManager.getName();
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        ((AppCompatActivity) context).startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private void showMessage(String clicked)
    {
        Toast.makeText(SellActivity.this, clicked, Toast.LENGTH_SHORT).show();
    }

    private void shareContent()
    {
        if (ShareDialog.canShow(ShareLinkContent.class))
        {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription("The 'Hello Facebook' sample  showcases simple Facebook integration")

                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();

            shareDialog.show(linkContent);


        }
    }
}
