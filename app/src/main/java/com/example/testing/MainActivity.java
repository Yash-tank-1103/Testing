package com.example.testing;

import static com.example.testing.SignupActivity.KEY_EMAIL;
import static com.example.testing.SignupActivity.SHARED_PREF_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREF_NAME="mydata";
    public static final String KEY_EMAIL="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.apply();

        String data = sharedPreferences.getString(KEY_EMAIL,"");
        if(data.equals(""))
        {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            Log.d("key==>>", data.toString());
        }else {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);

        }


    }
}