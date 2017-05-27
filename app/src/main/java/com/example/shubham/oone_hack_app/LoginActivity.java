package com.example.shubham.oone_hack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);

        Button signIn=(Button)findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService=ApiClient.getApiService();
                String sendemail=email.getText().toString();
                String sendpassword=password.getText().toString();

                if(!TextUtils.isEmpty(sendemail) && !TextUtils.isEmpty(sendpassword)) {

                    Call<Token> call = apiService.getToken(new AuthBody(sendemail,sendpassword));
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            Token tok = response.body();
                            Log.i("token",tok.getToken());
                            Toast.makeText(LoginActivity.this, tok.getToken(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}
