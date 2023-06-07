package com.example.testing;




import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    EditText signupemail,signuppass;
    Button signup;
    TextView warning;
    String email,pass;
    public static final String SHARED_PREF_NAME="mydata";
        public static final String KEY_EMAIL="name";
    public static final String KEY_PASS="pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupemail=findViewById(R.id.signupemail);
        warning=findViewById(R.id.warning);
        signuppass=findViewById(R.id.signuppass);
        signup=findViewById(R.id.signup);
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = signupemail.getText().toString();
                pass = signuppass.getText().toString();
                if (pass.equals("") && email.equals("")) {
                    Toast.makeText(SignupActivity.this, "input requried", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals("")) {
                        Toast.makeText(SignupActivity.this, "pass requried", Toast.LENGTH_SHORT).show();
                    }
                    if (email.equals("")) {
                        Toast.makeText(SignupActivity.this, "email requried", Toast.LENGTH_SHORT).show();
                    }
                }


                if (!pass.equals("") && !email.equals("")) {

                    Call<RegisterResponse.Root> call = RetrofitClient.getInstance().getsignupApi().register(email, pass);
                    call.enqueue(new Callback<RegisterResponse.Root>() {
                        @Override
                        public void onResponse(Call<RegisterResponse.Root> call, Response<RegisterResponse.Root> response) {

                            if (response.isSuccessful()) {
                                if (response.body().response.status.toString().equals("201")) {
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString(KEY_EMAIL, email);
                                    editor.putString(KEY_PASS, pass);
                                    editor.apply();
                                    Toast.makeText(SignupActivity.this, response.body().response.message.toString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                } else if (response.body().response.message.toString() == "The email address is already used.")

                                {


                                }
                                else {}
                        }
                    }

                     @Override
                     public void onFailure(Call<RegisterResponse.Root> call, Throwable t) {



                     }
                 });

                }
            }


        });




    }
}