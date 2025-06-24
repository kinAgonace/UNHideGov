package com.teamutil.unhidegov;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class reportlist extends AppCompatActivity {

    LinearLayout reportlistt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reportlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reportlistt = this.findViewById(R.id.recyclelist);
        refrshList();
    }


    public  void refrshList (){
        reportlistt.removeAllViews();

        for (int i = 0;i < Admin.reports.size() ; i++){
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.reportui,null);
            final TextView name = view.findViewById(R.id.nam);
            final  TextView project = view.findViewById(R.id.proj);
            final TextView message = view.findViewById(R.id.message);
            name.setText(Admin.reports.get(i).get("name").toString());
            project.setText(Admin.reports.get(i).get("project").toString());
            message.setText(Admin.reports.get(i).get("message").toString());

            reportlistt.addView(view);
        }

        Log.d("fitoy", "refrshList: "+Admin.reports);
    }
}