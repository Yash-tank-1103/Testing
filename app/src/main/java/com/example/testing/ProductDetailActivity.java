package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    TextView Productname1,Productdetail1;
    ImageView productimage1;
    ViewPager viewPager;
    ArrayList<Integer> arrayList=new ArrayList<>();
    private ProgressBar progressBar;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Productdetail1=findViewById(R.id.ProductDetail1);
        Productname1=findViewById(R.id.productname1);
        productimage1=findViewById(R.id.productimage1);
        progressBar = findViewById(R.id.productdisplayprogressbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Log.d("betaa", "onCreate: "+id);
        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Call<ProductListResponse.Root> call=RetrofitClient.getInstance().getProductlistApi().productlist();

                        call.enqueue(new Callback<ProductListResponse.Root>() {
                            @Override
                            public void onResponse(Call<ProductListResponse.Root> call, Response<ProductListResponse.Root> response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.isSuccessful()) {
                                    ArrayList<ProductListResponse.Futureproduct> futureProList = new ArrayList<>();
                                    futureProList.addAll(response.body().response.futureproduct);
                                    Picasso.get().load(futureProList.get(id).images.get(0).images).into(productimage1);
                                    Productname1.setText(futureProList.get(id).productname);
                                    String plain = Html.fromHtml(futureProList.get(id).productdetails).toString();
                                    Productdetail1.setText(plain);
                                }

                            }

                            @Override
                            public void onFailure(Call<ProductListResponse.Root> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Log.d("4545", "4545==>> "+t.toString());
                            }
                        });
                    }
                });
            }
        });
    }

    public void productDetailData() {

    }

}