package com.netforce.ray.sell;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.netforce.ray.R;

public class VideoActivity extends AppCompatActivity
{

     Cursor videoCursor;
    int videoColumnIndex;
    ListView videolist;
    int count;
    String thumbPath;

    String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,MediaStore.Video.Thumbnails.VIDEO_ID };
    /** Called when the activity is first created. */



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initialization();
    }

    private void initialization()
    {
        System.gc();

        String[] videoProjection = { MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,MediaStore.Video.Media.SIZE };

        videoCursor =  getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoProjection, null, null, null);

        count = videoCursor.getCount();
        videolist = (ListView) findViewById(R.id.PhoneVideoList);

        videolist.setAdapter(new VideoListAdapter(this.getApplicationContext()));
        videolist.setOnItemClickListener(videogridlistener);
    }

    private AdapterView.OnItemClickListener videogridlistener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position,long id)
        {
            System.gc();
            videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            videoCursor.moveToPosition(position);
            String filename = videoCursor.getString(videoColumnIndex);
            Log.i("FileName: ", filename);
//Intent intent = new Intent(VideoActivity.this, ViewVideo.class);
//intent.putExtra("videofilename", filename);
//startActivity(intent);
        }};

    public class VideoListAdapter extends BaseAdapter
    {
        private Context vContext;
        int layoutResourceId;

        public VideoListAdapter(Context c)
        {
            vContext = c;
        }

        public int getCount()
        {
            return videoCursor.getCount();
        }

        public Object getItem(int position)
        {

            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View listItemRow = null;
            listItemRow = LayoutInflater.from(vContext).inflate(R.layout.row_video_list, parent, false);

            TextView txtTitle = (TextView)listItemRow.findViewById(R.id.txtTitle);

            TextView txtSize = (TextView)listItemRow.findViewById(R.id.txtSize);

            ImageView thumbImage = (ImageView)listItemRow.findViewById(R.id.imgIcon);

            videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);

            videoCursor.moveToPosition(position);

            txtTitle.setText(videoCursor.getString(videoColumnIndex));

            videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

            videoCursor.moveToPosition(position);

            txtSize.setText(" Size(KB):" + videoCursor.getString(videoColumnIndex));

            thumbPath = videoCursor.getString(videoCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
            Log.i("ThumbPath: ",thumbPath);

            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(thumbPath, MediaStore.Video.Thumbnails.MINI_KIND);
            System.out.println("ThumbPath----------------"+ thumbPath);

            videoCursor.moveToPosition(position);

            thumbImage.setImageBitmap(thumb);

            return listItemRow;



        }

    }

}