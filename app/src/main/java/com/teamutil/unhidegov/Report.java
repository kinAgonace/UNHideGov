package com.teamutil.unhidegov;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class Report extends AppCompatActivity {

    LinearLayout item ;
    Button submitreport;
    EditText reportinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        item = this.findViewById(R.id.item);
        submitreport = this.findViewById(R.id.submitreport);
        reportinput = this.findViewById(R.id.reportinput);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.projectlist_ui,null);
        final TextView itemname = view.findViewById(R.id.itemname);
        final TextView itemdescription = view.findViewById(R.id.itemdescription);
        final ImageView itemimage = view.findViewById(R.id.itemimage);
        final LinearLayout feedback = view.findViewById(R.id.feedback);
        feedback.setVisibility(View.GONE);
        itemimage.setImageResource(getIntent().getIntExtra("image",0));
        itemname.setText(getIntent().getStringExtra("name"));
        itemdescription.setText(getIntent().getStringExtra("description"));

        item.addView(view);


        submitreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("message",reportinput.getText().toString());
                    hashMap.put("name",Auth.getUserName());
                    hashMap.put("number",Auth.getUserNumber());
                    hashMap.put("project",getIntent().getStringExtra("name"));
                    Admin.reports.add(hashMap);
                    Admin.saveData();
                    Toast.makeText(getApplicationContext(),"Report sent",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });



    }
}