package com.teamutil.unhidegov;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Project extends LinearLayout {

    TextView name,description,percenttext,datetext;
    String savedname,savedescription;
    ImageView image;
    int saveimage;

    static int NATIONAL = 1;
    static  int PROVINCIAL = 2;
    static int LOCAL = 3;

    int location;

    long date;

    int budget;

    int progress;

    String dateinput;

    String title,details,time;
    ProgressBar progressBar;
    String breakdstring;

    ArrayList<HashMap<String,Object>> breakdown = new ArrayList<>();

    public Project(Context context) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.projectlist_ui,null);
        this.addView(view);
        name = view.findViewById(R.id.itemname);
        image = view.findViewById(R.id.itemimage);
        description = view.findViewById(R.id.itemdescription);
        progressBar = view.findViewById(R.id.pb);
        percenttext = view.findViewById(R.id.percenttext);
        datetext = view.findViewById(R.id.datetext);

    }

    public void setName(String name){
        this.name.setText(name);
        this.savedname = name;
    }

    public  void setDescription( String description){
        this.description.setText(description);
        this.savedescription = description;
    }


    public void setImage (int resid){
        image.setImageResource(resid);
        this.saveimage = resid;
    }

    public  void setDate (long date ){
        this.date = date;
        long convert = date * (1000*60*60*24);
        String input = DateFormat.getDateInstance().format(convert);
        datetext.setText(input);
        this.dateinput = input;
    }

    public void setLocation(int type){
        this.location = type;
    }

    public void setTitle(String tit){
    this.title = tit;
    }

    public void setDetails (String details){
        this.details = details;
    }

    public void setTime(String time ){
        this.time = time;
    }

    public void setProgress(int progress){
        this.progress = progress;
        progressBar.setMax(100);
        progressBar.setProgress(progress);
        percenttext.setText(String.valueOf(progress)+"%");

    }


    public  void setBudget(int budget,String breakdown ){

        try {
            this.budget = budget;
            this.breakdstring = breakdown;

            String[] lines = breakdown.split("\n");
            for(int i = 0; i <lines.length;i++){
                String[] keys = lines[i].split(" - ");
                HashMap<String,Object> da = new HashMap<>();
                da.put("name",keys[0]);
                int p = Integer.valueOf(keys[1].trim());
                da.put("percent",p);
                this.breakdown.add(da);
            }
        } catch (Exception e){
            Log.d("Fitoy", "setBudget: "+ e.getMessage());
        }




    }

}
