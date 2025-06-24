package com.teamutil.unhidegov;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class recycledlist extends AppCompatActivity {


    LinearLayout recyclelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycledlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclelist = this.findViewById(R.id.recyclelist);

        refreshList();

    }


    public void refreshList (){
        recyclelist.removeAllViews();

        for (Project project : Admin.recycled){
            recyclelist.addView(project);
            final Project pr = project;
            project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppData.projects.add(pr);
                    Admin.recycled.remove(pr);
                    Admin.saveData();
                    refreshList();
                    Toast.makeText(getApplicationContext(),"Restored",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}