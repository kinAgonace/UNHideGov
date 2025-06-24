package com.teamutil.unhidegov;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Manageprojects extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        refreshList(0,"");
    }

    LinearLayout projectlist,addnew;
    EditText search ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manageprojects);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        projectlist = this.findViewById(R.id.recyclelist);
        addnew = this.findViewById(R.id.addnew);
        search = this.findViewById(R.id.Search);


        refreshList(0,"");

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), NewProject.class);
                startActivity(intent);
            }
        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshList(0,search.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }




    public void refreshList(int sortLocation,String input){
        projectlist.removeAllViews();



        for (int i = 0; i < AppData.projects.size();i++){

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.projecteditstate,null);
            final TextView name = view.findViewById(R.id.itemname);
            final ImageView image = view.findViewById(R.id.itemimage);
            final TextView description = view.findViewById(R.id.itemdescription);
            final  Button recycle = view.findViewById(R.id.recycle);
            name.setText(AppData.projects.get(i).savedname);
            description.setText(AppData.projects.get(i).savedescription);
            image.setImageResource(AppData.projects.get(i).saveimage);

            final String namestring = AppData.projects.get(i).savedname;
            final String descriptionstring = AppData.projects.get(i).savedescription;
            final int imageres = AppData.projects.get(i).saveimage;
            final Button edit = view.findViewById(R.id.edit);
            final String breakstring = AppData.projects.get(i).breakdstring;
            final int budget = AppData.projects.get(i).budget;
            final String title = AppData.projects.get(i).title;
            final String details = AppData.projects.get(i).details;;
            final  String time = AppData.projects.get(i).time;
            final int position = i;
            final long date = AppData.projects.get(i).date;
            final  int  progress = AppData.projects.get(i).progress;


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), EditView.class);
                    intent.putExtra("name",namestring);
                    intent.putExtra("description",descriptionstring);
                    intent.putExtra("image",imageres);
                    intent.putExtra("date",date);
                    intent.putExtra("breakstring",breakstring);
                    intent.putExtra("budget",budget);
                    intent.putExtra("title",title);
                    intent.putExtra("details",details);
                    intent.putExtra("time",time);
                    intent.putExtra("position",position);
                    intent.putExtra("progress",progress);

                    startActivity(intent);
                }
            });

            recycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Project recycled = AppData.projects.get(position);
                    AppData.projects.remove(position);
                    Admin.recycled.add(recycled);
                    refreshList(0,"");
                    Admin.saveData();
                }
            });

            if (input.isBlank()){
                if (sortLocation == 0){
                    projectlist.addView(view);
                } else {
                    if ( AppData.projects.get(i).location == sortLocation){
                        projectlist.addView(view);
                    }
                }
            } else {
                if (namestring.toLowerCase().contains(input)){
                    projectlist.addView(view);
                } else if (descriptionstring.toLowerCase().contains(input)) {
                    projectlist.addView(view);
                }
            }


        }



    }
}