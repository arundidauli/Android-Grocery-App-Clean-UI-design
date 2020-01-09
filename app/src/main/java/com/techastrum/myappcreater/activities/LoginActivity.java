package com.techastrum.myappcreater.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techastrum.myappcreater.R;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout ll_login, ll_register, ll_otp;
    private TextView txt_go_login, txt_go_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ll_login = findViewById(R.id.ll_login);
        ll_register = findViewById(R.id.ll_register);
        ll_otp = findViewById(R.id.ll_otp);
        txt_go_login = findViewById(R.id.txt_login_visible);
        txt_go_register = findViewById(R.id.txt_register_visible);
        unvisible();
        ll_register.setVisibility(View.VISIBLE);

        txt_go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unvisible();
                ll_login.setVisibility(View.VISIBLE);
            }
        });
        txt_go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unvisible();

                ll_register.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unvisible();

                ll_otp.setVisibility(View.VISIBLE);

            }
        });
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unvisible();

                ll_otp.setVisibility(View.VISIBLE);

            }
        });
        findViewById(R.id.btn_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });



    }

    private void unvisible() {
        ll_login.setVisibility(View.GONE);
        ll_otp.setVisibility(View.GONE);
        ll_register.setVisibility(View.GONE);
    }
}
