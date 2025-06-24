package com.teamutil.unhidegov;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity implements Auth.AuthListeners {


    Button proceed;
    EditText e1,e2,e3;
    TextView reg,log;
    boolean login = true;
    CardView namelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        namelayout = this.findViewById(R.id.namelayout);
        log = this.findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = true;
                namelayout.setVisibility(View.GONE);
                log.setBackgroundResource(R.drawable.buttonstroke);
                reg.setBackgroundColor(Color.parseColor("#00000000"));

            }
        });
        reg = this.findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = false;
                namelayout.setVisibility(View.VISIBLE);
                reg.setBackgroundResource(R.drawable.buttonstroke);
                log.setBackgroundColor(Color.parseColor("#00000000"));
            }
        });




        proceed = this.findViewById(R.id.proceed);
        e1 = this.findViewById(R.id.e1);
        e2 = this.findViewById(R.id.e2);
        e3 = this.findViewById(R.id.e3);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login){
                    Auth.loginAccount(e2.getText().toString(),String.valueOf(e3.getText()));
                } else {
                    Auth.createAccount(e1.getText().toString(),e2.getText().toString(),String.valueOf(e3.getText()));
//
                }

            }
        });
    }

    @Override
    public void onAccountCreated() {

    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onLoginSuccess() {

    }
}