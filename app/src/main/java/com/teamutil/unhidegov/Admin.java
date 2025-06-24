package com.teamutil.unhidegov;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin {
    static ArrayList<HashMap<String,Object>> reports = new ArrayList<>();
    static ArrayList<Project> recycled = new ArrayList<>();
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    Context context;

    public Admin(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("Admin",Context.MODE_PRIVATE);
        editor = preferences.edit();
        restoreData();

    }



    public static  void sendReport(String message, String from ){

    }

    public  static  void saveData (){
       String output = "";
        for (int i = 0; i < reports.size(); i++){
            String input = reports.get(i).get("name").toString()+",,"+reports.get(i).get("number").toString()+",,"+reports.get(i).get("message").toString()+",,"+reports.get(i).get("project");
        if (output.isEmpty()){
            output = input;
        }else {
            output += "&&"+input;
        }

        }
        editor.putString("reports",output);
        editor.apply();
    }

    public  static void restoreData(){
        try {
            String input = preferences.getString("reports", "");
            String[] lines = input.split("&&");
            for (int i = 0; i < lines.length; i++) {
                String[] keys = lines[i].split(",,");
                String name = keys[0];
                String number = keys[1];
                String messages = keys[2];

                HashMap<String, Object> hashdata = new HashMap<>();
                hashdata.put("name", name);
                hashdata.put("number", number);
                hashdata.put("message", messages);
                hashdata.put("project",keys[3]);
                reports.add(hashdata);
            }
        } catch (Exception e ){
            Log.d("fitoy", "restoreData: "+e.getMessage());
        }

    }
}
