package com.teamutil.unhidegov;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    ViewPager2 viewPager2;
    ImageView menubar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        viewPager2 = this.findViewById(R.id.pager);
        PageAdaptor pageAdaptor = new PageAdaptor(this);
        viewPager2.setAdapter(pageAdaptor);
        menubar = this.findViewById(R.id.menubar);
        bottomNavigationView = this.findViewById(R.id.bottomnav);









       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               try {
                   if (item.getItemId()==R.id.hometab){
                       viewPager2.setCurrentItem(0,false);
                   } else if (item.getItemId()==R.id.searchtab){
                       viewPager2.setCurrentItem(1,false);
                   }  else if (item.getItemId()==R.id.notification){
                       viewPager2.setCurrentItem(2,false);
                   }  else if (item.getItemId()==R.id.user){
                       viewPager2.setCurrentItem(3,false);
                   }

               } catch (Exception e ){
                   Log.d("Fitoy", "onCreate: "+ e.getMessage());
               }

               return true;
           }
       });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0 ){
                    bottomNavigationView.setSelectedItemId(R.id.hometab);
                } else if (position == 1 ){
                    bottomNavigationView.setSelectedItemId(R.id.searchtab);
                }

            }
        });

        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),menubar);
                popupMenu.inflate(R.menu.menubar);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.logout){
                            Auth.logoutAccount();
                            Toast.makeText(Dashboard.this, "Logging out", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}