package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.api.RequestPlaceHolder;
import com.example.project.api.RetrofitBuilder;
import com.example.project.pojos.Login;
import com.google.android.material.button.MaterialButton;

import java.util.IllegalFormatCodePointException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login1Activity extends AppCompatActivity {

    public EditText username, password;
    public MaterialButton loginbtn;

    public RetrofitBuilder retrofitBuilder;
    public RequestPlaceHolder requestPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         username = findViewById(R.id.username);
         password = findViewById(R.id.password);
         loginbtn = findViewById(R.id.loginbtn);

         retrofitBuilder = new RetrofitBuilder();
         requestPlaceHolder = retrofitBuilder.getRetrofit().create(RequestPlaceHolder.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText() != null && password.getText() !=null) {
                    Call<Login> loginCall = requestPlaceHolder.login(new Login(null, username.getText().toString(), null, null, password.getText().toString()));

                    loginCall.enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            if (!response.isSuccessful()) {
                                if (response.code() == 404) {
                                    Toast.makeText(Login1Activity.this, "User not found", Toast.LENGTH_SHORT).show();
                                    Log.e("Loging Err", response.message());
                                }else {
                                    Toast.makeText(Login1Activity.this, "Theres an error upon logging in the api", Toast.LENGTH_SHORT).show();
                                    Log.e("Loging Err", response.message());
                                }
                            }else {
                                if (response.code() == 200) {
                                    Toast.makeText(Login1Activity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            Toast.makeText(Login1Activity.this, "Theres an error upon logging in the api", Toast.LENGTH_SHORT).show();
                            Log.e("Logging Error", t.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(Login1Activity.this, "Please supply all the fields to the login!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}