package com.teamutil.unhidegov;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout projectlist,addnew;
    EditText search ;


    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =  inflater.inflate(R.layout.fragment_search, container, false);
        projectlist = root.findViewById(R.id.recyclelist);
        addnew = root.findViewById(R.id.addnew);
        search = root.findViewById(R.id.Search);


        refreshList(0,"");


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


        return root;
    }

    public void refreshList(int sortLocation,String input){
        projectlist.removeAllViews();



        for (int i = 0; i < AppData.projects.size();i++){

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.projectlist_ui,null);
            final TextView name = view.findViewById(R.id.itemname);
            final ImageView image = view.findViewById(R.id.itemimage);
            final TextView description = view.findViewById(R.id.itemdescription);
            final ProgressBar progressBar = view.findViewById(R.id.pb);
            final TextView percenttext = view.findViewById(R.id.percenttext);
            final TextView datetext = view.findViewById(R.id.datetext);
            name.setText(AppData.projects.get(i).savedname);
            description.setText(AppData.projects.get(i).savedescription);
            image.setImageResource(AppData.projects.get(i).saveimage);
            progressBar.setMax(100);
            progressBar.setProgress((int) AppData.projects.get(i).progress);
            percenttext.setText(String.valueOf(AppData.projects.get(i).progress)+"%");
            datetext.setText(AppData.projects.get(i).dateinput);


            final String namestring = AppData.projects.get(i).savedname;
            final String descriptionstring = AppData.projects.get(i).savedescription;
            final String dateinput = AppData.projects.get(i).dateinput;
            final int imageres = AppData.projects.get(i).saveimage;

            final  int progress = AppData.projects.get(i).progress;
            final  long date = AppData.projects.get(i).date;

            final ArrayList<HashMap<String,Object>> breakdown =   AppData.projects.get(i).breakdown;
            final int budget =  AppData.projects.get(i).budget;
            final String title =  AppData.projects.get(i).title;
            final String details =  AppData.projects.get(i).details;;
            final  String time =  AppData.projects.get(i).time;





            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), ViewProject.class);
                    intent.putExtra("name",namestring);
                    intent.putExtra("description",descriptionstring);
                    intent.putExtra("image",imageres);

                    intent.putExtra("budget",budget);
                    intent.putExtra("title",title);
                    intent.putExtra("details",details);
                    intent.putExtra("time",time);
                    Cache.arraymap = breakdown;
                    startActivity(intent);
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
                } else if (dateinput.toLowerCase().contains(input)) {
                    projectlist.addView(view);
                }
            }


        }



    }
}