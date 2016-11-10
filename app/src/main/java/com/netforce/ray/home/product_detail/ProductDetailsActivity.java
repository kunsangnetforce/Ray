package com.netforce.ray.home.product_detail;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeData;
import com.netforce.ray.home.private_offer.PrivateOffer;
import com.netforce.ray.home.ViewPagerAdapter;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.offer_adapter.OfferAdapter;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.offer_adapter.OfferData;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.question_adapter.QuestionAdapter;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.question_adapter.QuestionData;
import com.netforce.ray.home.seller_shop.SellerShopActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener
{

    Toolbar toolbar;
    protected View view;
    MaterialDialog  dailog;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    Button aks_button,private_button;
    RecyclerView offer_list,question_list;
    OfferAdapter adapter;
    ViewPagerAdapter_product_detail viewPagerAdapter_product_detail;
    QuestionAdapter questionAdapter;
    public ArrayList<OfferData> offerDatas = new ArrayList<>();
    public ArrayList<QuestionData> questionDatas = new ArrayList<>();
    public ArrayList<String>Imagedatas=new ArrayList<>();
    LinearLayoutManager layoutManager,questionlayoutManager;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView seller_profile_image,like_image;
    TextView seller_name,like_text,productTitle,product_price,product_description;

    String product_id;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            product_id = extras.getString("productid");
            setupwebservice(product_id);
            setupToolBar();

            setupviewpager();

            setuplayout();

            setupoffer_list_layout();

            setup_question_layout();
        }




    }

    private void setupwebservice(String product_id) {
        String Product_cat_url="http://netforce.biz/seeksell/products/product_details?action=details&id="+product_id;
        Log.e("Product_cat_url",Product_cat_url);
        Ion.with(this).load(Product_cat_url)

                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                String status=result.get("status").getAsString();
                if (status.contains("success"))
                {
                    JsonObject data=result.getAsJsonObject("data");
                    JsonObject Product=data.getAsJsonObject("Product");
                    String product_name=Product.get("name").getAsString();
                    String description=Product.get("description").getAsString();
                    product_description.setText(description);
                    String like=Product.get("like").getAsString();
                    String price=Product.get("price").getAsString();
                    JsonObject User=data.getAsJsonObject("User");
                    String product_user_id=User.get("id").getAsString();
                    String product_username=User.get("name").getAsString();
                    seller_name.setText(product_username);
                    if (User.get("profile_photo").isJsonNull()) {

                    } else {


                        String profile_photo = getString(R.string.profile_url)+User.get("profile_photo").getAsString();
                        Ion.with(seller_profile_image)
                                //.placeholder(R.drawable.placeholder_image)

                                .load(profile_photo);

                    }
//                    JsonObject Json_ProductImage=result.getAsJsonObject("ProductImage");
//                    String product_image=getString(R.string.imageurl)+Json_ProductImage.get("name").getAsString();
//                    Log.e("product_name",product_name);



                    JsonArray js_product_image = data.getAsJsonArray("ProductImage");
                    for(int k=0;k<js_product_image.size();k++) {
                        // JsonObject js_product_image = jsonObject.getAsJsonObject("ProductImage");
                        JsonObject jsonObject2 = (JsonObject) js_product_image.get(k);
                        if (jsonObject2.get("name").isJsonNull()) {

                        } else {
                            String ProductImage_url = getString(R.string.imageurl) + jsonObject2.get("name").getAsString();
                            Imagedatas.add(ProductImage_url);
                            System.out.println("imageurl ======================" + ProductImage_url);
                            productTitle.setText(product_name);
                            like_text.setText(like);
                            product_price.setText(price);


                            //homeDatas.add(new HomeData(ProductImage_url, name, price, id));
                        }


                    }}

                viewPagerAdapter_product_detail = new ViewPagerAdapter_product_detail(ProductDetailsActivity.this, Imagedatas);
                intro_images.setAdapter(viewPagerAdapter_product_detail);
                intro_images.setCurrentItem(0);
                setUiPageViewController();
                intro_images.setOnPageChangeListener(ProductDetailsActivity.this);


            }
        });

    }

    private void setuplayout() {


        seller_profile_image = (ImageView) findViewById(R.id.seller_profile_image);

        like_image = (ImageView) findViewById(R.id.like_image);

        seller_name = (TextView) findViewById(R.id.seller_name);
        like_text=(TextView) findViewById(R.id.like_txt);
        productTitle=(TextView)findViewById(R.id.productTitle);
        product_price=(TextView)findViewById(R.id.product_price);
        product_description=(TextView)findViewById(R.id.product_description);

        seller_profile_image.setOnClickListener(this);

        like_image.setOnClickListener(this);

        seller_name.setOnClickListener(this);


    }

    private void setup_question_layout()
    {

        setQuestionData();

        question_list = (RecyclerView) findViewById(R.id.question_list);

        questionAdapter = new QuestionAdapter(getApplicationContext(),questionDatas);

        questionlayoutManager = new LinearLayoutManager(getApplicationContext());

        question_list.setLayoutManager(questionlayoutManager);

        question_list.setAdapter(questionAdapter);

    }

    private void setQuestionData() {
        try{
            questionDatas.clear();

        }
        catch (Exception e){}

        questionDatas.add(new QuestionData("HI"));
        questionDatas.add(new QuestionData("HI"));
        questionDatas.add(new QuestionData("HI"));
    }

    private void setupoffer_list_layout()
    {

        setOfferData();

        offer_list = (RecyclerView) findViewById(R.id.offer_list);

        adapter = new OfferAdapter(getApplicationContext(), offerDatas);

        layoutManager = new LinearLayoutManager(getApplicationContext());

        offer_list.setLayoutManager(layoutManager);

        offer_list.setAdapter(adapter);

    }


    private void setOfferData()
    {

        try
        {
            offerDatas.clear();
        }
        catch (Exception ex)
        {

        }
        offerDatas.add(new OfferData("offered 8000"));
        offerDatas.add(new OfferData("offered 18000"));
        offerDatas.add(new OfferData("offered 20000"));


    }



    private void setupviewpager()
    {

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);

        aks_button = (Button) findViewById(R.id.aks_button);

        private_button = (Button) findViewById(R.id.privateofferButton);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


        collapsingToolbarLayout.setTitle("Motorcycle");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent)); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));







        aks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopUp();
            }
        });


        private_button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetailsActivity.this,PrivateOffer.class);
                startActivity(i);
            }


        });


    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    private void setUiPageViewController() {

        dotsCount = viewPagerAdapter_product_detail.getCount();
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




    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.seller_profile_image:
                Intent i = new Intent(ProductDetailsActivity.this, SellerShopActivity.class);
                startActivity(i);
                break;
            case R.id.seller_name:
                Intent s = new Intent(ProductDetailsActivity.this, SellerShopActivity.class);
                startActivity(s);
                break;
            case R.id.like_image:
                break;


        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void showPopUp()
    {

        dailog = new MaterialDialog.Builder(ProductDetailsActivity.this)
                .title("Kunsang Wangyal")
                .customView(R.layout.custom_write_comment, true).build();

        Button b = (Button) dailog.findViewById(R.id.submit);

        Button cancel = (Button) dailog.findViewById(R.id.cancel);
        // TextView textView = (TextView) dailog.findViewById(R.id.textView1);

        //   mExplosionField.explode(icon,null,0,5000);
        //addListener(dailog.findViewById(R.id.root));

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                dailog.dismiss();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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



}
