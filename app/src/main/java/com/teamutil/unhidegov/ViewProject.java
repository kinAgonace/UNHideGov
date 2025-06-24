package com.teamutil.unhidegov;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewProject extends AppCompatActivity {

    TextView name,description;
    ImageView send,image;
    EditText commentinput;
    LinearLayout commentList;
    SharedPreferences comments;
    SharedPreferences.Editor editor;
    Button report;

    LinearLayout breakdownlist;
    TextView budgetText,time,titletext,details;

    ArrayList<HashMap<String,Object>> breakdown = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_project);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = this.findViewById(R.id.projectname);
        description = this.findViewById(R.id.projectdescription);
        send = this.findViewById(R.id.send);
        commentinput = this.findViewById(R.id.commentinput);
        image = this.findViewById(R.id.image);
        commentList = this.findViewById(R.id.commentList);
        report = this.findViewById(R.id.report);
        breakdownlist = this.findViewById(R.id.breakdownlist);
        budgetText = this.findViewById(R.id.budgettext);
        time = this.findViewById(R.id.time);
        titletext = this.findViewById(R.id.titletext);
        details = this.findViewById(R.id.details);


        breakdown =Cache.arraymap;





        comments = getSharedPreferences("data1", Context.MODE_PRIVATE);
        editor = comments.edit();

        refreshcomments();


        listBreakdown();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = commentinput.getText().toString();
                String output = "";
                if (comments.contains(getIntent().getStringExtra("name"))){
                    output = comments.getString(getIntent().getStringExtra("name"),"").toString()+"&&"+Auth.getUserName()+","+commentinput.getText().toString();
                    editor.putString(getIntent().getStringExtra("name"),output);
                    editor.apply();
                } else {
                    editor.putString(getIntent().getStringExtra("name"),Auth.getUserName()+","+commentinput.getText().toString());
                    editor.apply();
                }

               refreshcomments();
                commentinput.setText("");
            }
        });

        name.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("description"));
        image.setImageResource(getIntent().getIntExtra("image",0));
        budgetText.setText("₱"+NumberFormat.getInstance().format(getIntent().getIntExtra("budget",0)));
        titletext.setText(getIntent().getStringExtra("title"));
        time.setText(getIntent().getStringExtra("time"));
        details.setText(getIntent().getStringExtra("details"));

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Report.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("description",getIntent().getStringExtra("description"));
                intent.putExtra("image",getIntent().getIntExtra("image",0));
                startActivity(intent);
            }
        });




    }

    public void listBreakdown (){
        try {
            breakdownlist.removeAllViews();
            for (int i = 0; i < breakdown.size(); i++){
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.breakdownui,null);
                final TextView nametext = view.findViewById(R.id.nametext);
                final TextView percent = view.findViewById(R.id.percent);
                final ProgressBar progressBar = view.findViewById(R.id.progressbar);
                nametext.setText(breakdown.get(i).get("name").toString());
                progressBar.setMax(100);
                progressBar.setProgress((int) breakdown.get(i).get("percent"));
                percent.setText((int) breakdown.get(i).get("percent")+"%");
                breakdownlist.addView(view);
            }


        } catch (Exception e){
            Log.d("Fitoy", "listBreakdown: "+e.getMessage()+breakdown);
        }


    }

    public void refreshcomments (){

        try {
            if (! getIntent().getStringExtra("name").trim().equals("")){
                String input =comments.getString(getIntent().getStringExtra("name"),"");
                String[] objects = input.split("&&");
                commentList.removeAllViews();
                for (int position = 0; position< objects.length; position++){
                    //String[] keys = objects[position].split(",");
                    String comment = objects[position];
                    String[] keys = comment.split(",");

                    LayoutInflater layoutInflater = getLayoutInflater();
                    View view = layoutInflater.inflate(R.layout.commentui,null);
                    final TextView textcomment = view.findViewById(R.id.comment);
                    final  TextView textname = view.findViewById(R.id.name);
                    final  ImageView thumbup = view.findViewById(R.id.thumbsup);

                    final String com = keys[1];
                    final String name = keys[0];
                    final int fposition = position;
                    thumbup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


                    textcomment.setText(keys[1]);
                    textname.setText("Anonymous");
                    Log.d("hay nako", "refreshcomments: " + comments.getString(getIntent().getStringExtra("name"),""));
                    if(!comment.trim().equals("")){
                        commentList.addView(view);
                    }

                }
            }
        } catch (Exception e) {

        }

    }
}