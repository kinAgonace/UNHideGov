package com.teamutil.unhidegov;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProjectManageState extends LinearLayout {

    TextView name,description;
    String savedname,savedescription;
    ImageView image;
    int saveimage;

    static int NATIONAL = 1;
    static  int PROVINCIAL = 2;
    static int LOCAL = 3;

    int location;

    public ProjectManageState(Context context) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.projectlist_ui,null);
        this.addView(view);
        name = view.findViewById(R.id.itemname);
        image = view.findViewById(R.id.itemimage);
        description = view.findViewById(R.id.itemdescription);
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

    public void setLocation(int type){
        this.location = type;
    }



}
