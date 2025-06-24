package com.teamutil.unhidegov;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Admin admin = new Admin(this);
        Auth auth = new Auth(getApplicationContext());
        Auth.setListeners(new Auth.AuthListeners() {
            @Override
            public void onAccountCreated() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Dashboard.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLogoutSuccess() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLoginSuccess() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




        AppData appdata = new AppData(this);

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();

                if (Auth.data.getString("currentUser","").trim().equals("")){
                    intent.setClass(getApplicationContext(), privacypolicy.class);
                } else {
                    intent.setClass(getApplicationContext(), Dashboard.class);
                }

                startActivity(intent);
                finish();
            }
        };

        timer.schedule(timerTask,3000);
    }
}