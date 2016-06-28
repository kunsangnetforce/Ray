package com.netforce.ray.sell;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.netforce.ray.R;
import com.netforce.ray.general.UserSessionManager;
import com.netforce.ray.profile.sellings.offer_list.OfferAdapter;
import com.netforce.ray.profile.userprofile.SettingActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TAKE_PHOTO_CODE = 108;
    private static final String IMAGE_DIRECTORY_NAME = "ray";
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int PICK_IMAGE = 109;
    private static final int IMAGE = 0;
    private static final int CLICK = 1;
    private final LayoutInflater inflater;
    private List<SellData> itemList;
    private Context context;
    private OfferAdapter adapter;
    private Uri fileUri;
    public static String filePath;
    private MaterialDialog dialog;

    public SellAdapter(Context context, List<SellData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return CLICK;
        } else {
            return IMAGE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == IMAGE) {
            View view = inflater.inflate(R.layout.row_image, parent, false);
            SellHolderImage viewHolder = new SellHolderImage(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_click, parent, false);
            SellHolderClick viewHolder = new SellHolderClick(view);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case CLICK:
                SellHolderClick sellHolderClick = (SellHolderClick) holder;
                sellHolderClick.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showEditPicPopup();
                    }
                });
                break;
            case IMAGE:
                SellHolderImage sellHolderImage = (SellHolderImage) holder;
                if (position < (itemList.size())) {
                    try {
                        Log.i("result path", itemList.get(position).path);
                        sellHolderImage.imageView.setImageBitmap(decodeUri(Uri.fromFile(new File(itemList.get(position).path))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }

    private void takePictureIntent() {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String name = userSessionManager.getName();
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        ((AppCompatActivity) context).startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private void showEditPicPopup() {
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

    public Uri getOutputMediaFileUri(int type) {
        Log.i("result called", "called");
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        Log.i("result called1", "called");
        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        try {
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.i("result failed", "could not create path");
                    return null;
                } else {
                    Log.i("result pass", mediaStorageDir.getAbsolutePath());
                }

            } else {
                Log.i("result called3", mediaStorageDir.getAbsolutePath());
            }
        } catch (Exception ex) {
            Log.i("result ex", ex.toString());
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

    private void pickPictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((AppCompatActivity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                context.getContentResolver().openInputStream(selectedImage), null, o);

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
                context.getContentResolver().openInputStream(selectedImage), null, o2);
    }

}
