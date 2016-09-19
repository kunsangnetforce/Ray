package com.netforce.ray.sell.sellproductdetail;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforce.ray.R;
import com.netforce.ray.home.ViewPagerAdapter;
import com.netforce.ray.sell.updatesellproduct.UpdateSellActivity;

public class Sell_ProductDeatailAcrtivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener
{


    Toolbar toolbar;
    protected View view;
    MaterialDialog dailog,dailog2;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;

    private int[] mImageResources =
            {
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle
            };

    ImageView edit;
    Button buttonOpenDialog;

    String KEY_TEXTPSS = "TEXTPSS";
    static final int CUSTOM_DIALOG_ID = 0;

    ListView dialog_ListView;

    String[] listContent = {
            "Edit", "Delete", "Mark as sold everywhere"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellproduct_detail);

        setupToolBar();

        setupviewpager();
    }


    private void setupviewpager()
    {

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        edit = (ImageView) findViewById(R.id.editImage);
        edit.setOnClickListener(this);

        mAdapter = new ViewPagerAdapter(Sell_ProductDeatailAcrtivity.this, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(Sell_ProductDeatailAcrtivity.this);
        setUiPageViewController();

    }

    private void setupToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Motorcycle";
        getSupportActionBar().setTitle(teams);
    }




    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }





    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.editImage:
                showDialog(CUSTOM_DIALOG_ID);
                break;

        }
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageSelected(int position)
    {
        for (int i = 0; i < dotsCount; i++)
        {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


    }


    public void onPageScrollStateChanged(int state)
    {

    }


    private void showPopUp()
    {

        dailog = new MaterialDialog.Builder(Sell_ProductDeatailAcrtivity.this)
                .title("Kunsang Wangyal")
                .customView(R.layout.custom_write_comment, true).build();

        Button b = (Button) dailog.findViewById(R.id.submit);
        // TextView textView = (TextView) dailog.findViewById(R.id.textView1);

        //   mExplosionField.explode(icon,null,0,5000);
        //addListener(dailog.findViewById(R.id.root));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dailog.dismiss();

            }
        });
        dailog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.more:
                return true;
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
    protected Dialog onCreateDialog(int id)
    {

        Dialog dialog = null;

        switch(id)
        {
            case CUSTOM_DIALOG_ID:

                dialog = new Dialog(Sell_ProductDeatailAcrtivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_dialog_layout);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
                {


                    @Override
                    public void onCancel(DialogInterface dialog)
                    {
                        // TODO Auto-generated method stub
                        Toast.makeText(Sell_ProductDeatailAcrtivity.this,
                                "OnCancelListener",
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }});

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
                {

                    @Override
                    public void onDismiss(DialogInterface dialog)
                    {
                        // TODO Auto-generated method stub
                        //Toast.makeText(Sell_ProductDeatailAcrtivity.this, "OnDismissListener", Toast.LENGTH_LONG).show();



                    }});

                //Prepare ListView in dialog
                dialog_ListView = (ListView)dialog.findViewById(R.id.dialoglist);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContent);
                dialog_ListView.setAdapter(adapter);

                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        // TODO Auto-generated method stub
                       // Toast.makeText(Sell_ProductDeatailAcrtivity.this, parent.getItemAtPosition(position).toString() + " clicked", Toast.LENGTH_LONG).show();

                        if(parent.getItemAtPosition(position).toString().equals("Edit"))
                        {

                            Intent edit = new Intent(Sell_ProductDeatailAcrtivity.this, UpdateSellActivity.class);
                            startActivity(edit);

                        }
                        else if(parent.getItemAtPosition(position).toString().equals("Delete"))
                        {




                        }
                        else
                        {



                        }
                        dismissDialog(CUSTOM_DIALOG_ID);
                    }});

                break;
        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle bundle)
    {
        // TODO Auto-generated method stub
        super.onPrepareDialog(id, dialog, bundle);

        switch(id)
        {
            case CUSTOM_DIALOG_ID:
                //
                break;
        }
    }


}
