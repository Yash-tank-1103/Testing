package com.example.testing;



import static com.example.testing.SignupActivity.SHARED_PREF_NAME;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {


    public DrawerLayout drawerLayout;
             ActionBarDrawerToggle actionBarDrawerToggle;
            NavigationView navigationView;
            ImageView productimg;
            TextView productname;
    private ProgressBar progressBar;
            GridView GV;



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_home);
                drawerLayout = findViewById(R.id.my_drawer_layout);
                actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
                navigationView = findViewById(R.id.home_navbar);
                drawerLayout.addDrawerListener(actionBarDrawerToggle);
                progressBar = findViewById(R.id.productdisplayprogressbarhome);
                actionBarDrawerToggle.syncState();



                // to make the Navigation drawer icon always appear on the action bar
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_account:
                                Toast.makeText(HomeActivity.this, "profle", Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.nav_logout:
                                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                finish();
                                Toast.makeText(HomeActivity.this, "log in ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity.this, MainActivity2.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_settings:
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + item.getItemId());
                        }

                        return false;
                    }
                });


                // drawer layout instance to toggle the menu icon to open
                // drawer and back button to close drawer


                // pass the Open and Close toggle for the drawer layout listener
                // to toggle the button

                progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                                            @Override
                                            public void run() {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Call < ProductListResponse.Root > call = RetrofitClient.getInstance().getProductlistApi().productlist();
                                                        call.enqueue(new Callback<ProductListResponse.Root>() {
                                                            @Override
                                                            public void onResponse(Call<ProductListResponse.Root> call, Response<ProductListResponse.Root> response) {
                                                                progressBar.setVisibility(View.GONE);
                                                                ArrayList<ProductListResponse.Futureproduct> futureProList = new ArrayList<>();
                                                                futureProList.addAll(response.body().response.futureproduct);
                                                                Log.d("4545", "onResponse: "+ futureProList.get(0).productname);
                                                                RecyclerView recyclerView=findViewById(R.id.homedisplay);
                                                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                                                                recyclerView.setLayoutManager(gridLayoutManager);
                                                                //Toast.makeText(HomeActivity.this, futureProList.size(), Toast.LENGTH_SHORT).show();
                                                                Log.d("[456]", "onResponse: "+futureProList.size());


                                                                RecycleViewAdapter recycleViewAdapter=new RecycleViewAdapter(HomeActivity.this,futureProList);
                                                                recyclerView.setAdapter(recycleViewAdapter);


                                                            }

                                                            @Override
                                                            public void onFailure(Call<ProductListResponse.Root> call, Throwable t) {

                                                                Log.d("4545", "4545==>> "+t.toString());
                                                            }
                                                        });


                                                    }
                                                });

                                            }
                                        });



            }



    class GridAdapter extends BaseAdapter
            {

                @Override
                public int getCount() {
                    return 0;
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View v=getLayoutInflater().inflate(R.layout.card_item,parent,false);
                  productimg=v.findViewById(R.id.productimg);
                  productname=v.findViewById(R.id.productname);
                    return v;
                }
            }


            // override the onOptionsItemSelected()
            // function to implement
            // the item click listener callback
            // to open and close the navigation
            // drawer when the icon is clicked
            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem item) {

                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    Log.d("9898", "onOptionsItemSelected: "+item);




                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
        }
