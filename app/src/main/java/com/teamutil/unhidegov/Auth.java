package com.teamutil.unhidegov;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Auth {

    static SharedPreferences data;
    static SharedPreferences.Editor editor;
    static ArrayList<HashMap<String,Object>> accounts = new ArrayList<>();
    static  Context context ;

    static AuthListeners listeners;




    public static void setListeners(AuthListeners authListeners){
        listeners = authListeners;
    }

    public Auth (Context context){
        this.context = context;
        data = context.getSharedPreferences("auth",Context.MODE_PRIVATE);
        editor = data.edit();
        String rawaccountData = data.getString("accounts","");


        if (!rawaccountData.equals("")){
            try {
                String[] decodeAccounts = rawaccountData.split("&&");

                for (int i = 0; i < decodeAccounts.length ; i++){
                    String[] splitPassword = decodeAccounts[i].split(",");
                    HashMap<String,Object> hash = new HashMap<>();
                    hash.put("name",splitPassword[0]);
                    hash.put("number",splitPassword[1]);
                    hash.put("pin",splitPassword[2]);
                    accounts.add(hash);
                }

            } catch ( Exception e ){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    public static  void logoutAccount (){
        editor.remove("currentUser");
        editor.apply();
        listeners.onLogoutSuccess();

    }
    public static void createAccount(String name , String number , String pin){


        try {
            if (data.getString("accounts", "").trim().equals("")) {
                String output = name + "," + number + "," + pin;
                editor.putString("accounts", output);
                editor.putString("currentUser", output);
                editor.apply();
                Log.d("gwapoo ko aa", "createAccount: " +data.getString("accounts", ""));
                Log.d("gwapoo ko aa", name);
                Toast.makeText(context, "Your Account has been created", Toast.LENGTH_SHORT).show();
                listeners.onAccountCreated();
            } else {
                if (data.getString("accounts", "").contains(number)) {
                    Toast.makeText(context, "Account already exist!", Toast.LENGTH_SHORT).show();
                    Log.d("gwapoo ko a", "createAccount: " +data.getString("accounts", ""));
                    Log.d("gwapoo ko a", name);

                } else {
                    String input = data.getString("accounts", "");
                    String output = input + "&&" + name + "," + number + "," + pin;
                    editor.putString("currentUser", name + "," + number + "," + pin);
                    editor.putString("accounts", output);
                    editor.apply();
                    Log.d("gwapoo ko b", "createAccount: " +data.getString("accounts", ""));
                    Log.d("gwapoo ko b", name);
                    Toast.makeText(context, "Your Account has been created", Toast.LENGTH_SHORT).show();
                    listeners.onAccountCreated();

                }



            }
            Log.d("gwapoo ko", "createAccount: " +data.getString("accounts", ""));
            Log.d("gwapoo ko", name);
        } catch (Exception e ){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();


        }
    }

    public static void loginAccount (String number, String pin){

        try {
            if (!data.getString("accounts", "").isEmpty()) {
                String[] object = data.getString("accounts", "").split("&&");
                for (int a = 0; a < object.length; a++) {
                    String[] keys = object[a].split(",");
                    String strname = keys[0];
                    String strnumber = keys[1].trim();
                    String strpin = keys[2].trim();

                    if (number.equals(strnumber) && strpin.equals(pin) ) {
                        editor.putString("currentUser", strname + "," + number + "," + pin);
                        editor.apply();
                        listeners.onLoginSuccess();
                        return;
                    }

                    Toast.makeText(context, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                    Log.d("fitoy","inAccount:1 "+keys+":"+object+"lol"+keys[2]);
                }
            } else {
                Toast.makeText(context, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                Log.d("fitoy","inAccount: "+"bruh");


            }
        } catch (Exception e ){
            Log.d("fitoy", "loginAccount: " +e.getMessage());
        }
    }


    public  static  String getUserName(){
        String input = data.getString("currentUser","");
        String[] keys = input.split(",");


        return keys[0];
    }public  static  String getUserNumber(){
        String input = data.getString("currentUser","");
        String[] keys = input.split(",");


        return keys[1];
    }public  static  String getUserPIN(){
        String input = data.getString("currentUser","");
        String[] keys = input.split(",");


        return keys[2];
    }


    interface AuthListeners {
        public void onAccountCreated();
        public  void onLogoutSuccess();

        public void onLoginSuccess();
    }

}
