package com.example.testing;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText Loginemail,Loginpass;
    Button Login;
    String email,pass;
    public static final String SHARED_PREF_NAME="mydata";
    public static final String KEY_EMAIL="name";
    public static final String KEY_PASS="pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Loginemail=findViewById(R.id.loginemail);
        Loginpass=findViewById(R.id.loginpass);
        Login=findViewById(R.id.signup);
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Loginemail.getText().toString();
                pass = Loginpass.getText().toString();
                if (pass.equals("") && email.equals("")) {
                    Toast.makeText(LoginActivity.this, "input requried", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals("")) {
                        Toast.makeText(LoginActivity.this, "pass requried", Toast.LENGTH_SHORT).show();
                    }
                    if (email.equals("")) {
                        Toast.makeText(LoginActivity.this, "email requried", Toast.LENGTH_SHORT).show();
                    }
                }


                if (!pass.equals("") && !email.equals("")) {
                    Call<LoginResponse.Root> call=RetrofitClient.getInstance().getloginApi().login(email,pass);
                    call.enqueue(new Callback<LoginResponse.Root>() {
                        @Override
                        public void onResponse(Call<LoginResponse.Root> call, Response<LoginResponse.Root> response) {

                            if(response.isSuccessful()) {

                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString(KEY_EMAIL, email);
                                editor.putString(KEY_PASS, pass);
                                editor.apply();
                                Log.d("123456", "onResponse: "+response.body().response.message.toString());
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse.Root> call, Throwable t) {
                            Log.d("2323", "onFailure: "+t);
                        }


                    });

                }
            }


        });




    }
}