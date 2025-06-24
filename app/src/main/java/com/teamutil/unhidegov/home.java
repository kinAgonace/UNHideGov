package com.teamutil.unhidegov;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout projectlist,tabmenu,choosemonth,selectrange;
    ImageView monthDrop;

    TextView monthText;
    LinearLayout minimizetop,top;
    ArrayList<HashMap<String,Object>> menu = new ArrayList<>();
    int selection = 0;

    ImageView minimizeicon;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    boolean minimize = false;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        projectlist = root.findViewById(R.id.recyclelist);
        tabmenu = root.findViewById(R.id.tabmenu);
        choosemonth = root.findViewById(R.id.selectmonth);
        monthDrop = root.findViewById(R.id.monthdrop);
        monthText = root.findViewById(R.id.monthname);
        selectrange = root.findViewById(R.id.selectrange);

        top = root.findViewById(R.id.top);


        initMenu();
        refreshTab();
        refreshList(0);





        choosemonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(),monthDrop);
                popupMenu.inflate(R.menu.monthmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        monthText.setText(menuItem.getTitle());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });


        selectrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectdate();
            }
        });
        return root;
    }

    public void refreshList(int sortLocation){
        projectlist.removeAllViews();
            for (Project project : AppData.projects){
                final String name = project.savedname;
                final String description =  project.savedescription ;
                final int imageres = project.saveimage;
                final ArrayList<HashMap<String,Object>> breakdown =  project.breakdown;
                final int budget = project.budget;
                final String title = project.title;
                final String details = project.details;;
                final  String time = project.time;


                project.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(getContext(), ViewProject.class);
                        intent.putExtra("name",name);
                        intent.putExtra("description",description);
                        intent.putExtra("image",imageres);
                        intent.putExtra("budget",budget);
                        intent.putExtra("title",title);
                        intent.putExtra("details",details);
                        intent.putExtra("time",time);
                        Cache.arraymap = breakdown;
                        startActivity(intent);
                    }
                });
                if (sortLocation == 0){
                    projectlist.addView(project);
                } else {
                    if (project.location == sortLocation){
                        projectlist.addView(project);
                    }
                }

            }
    }


    public void refreshTab(){
        tabmenu.removeAllViews();
        int i = 0;
        for (HashMap<String,Object> hash : menu ) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.tabviewicon,null);
            final TextView textname = view.findViewById(R.id.tabname);
            textname.setText(hash.get("name").toString());
            final ImageView tabicon = view.findViewById(R.id.tabicon);
            tabicon.setImageResource((int)hash.get("image"));
            final LinearLayout indicator = view.findViewById(R.id.indicator);



            if (i != selection){
                indicator.setVisibility(View.GONE);
                final int position = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selection = position;
                       refreshList(position);
                       refreshTab();
                    }
                });
            } else {
                indicator.setVisibility(View.VISIBLE);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }

            tabmenu.addView(view);
            i++;
        }
    }


    public  void initMenu (){

        {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("image",R.drawable.baseline_density_small_24);
            hashMap.put("name","All");
            menu.add(hashMap);
        }{
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("image",R.drawable.national);
            hashMap.put("name","National");
            menu.add(hashMap);

        }{
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("image",R.drawable.gps);
            hashMap.put("name","Provincial");
            menu.add(hashMap);

        }{
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("image",R.drawable.local);
            hashMap.put("name","Local");
            menu.add(hashMap);

        }

    }



    public void animateView (View view ,int duration, int value){
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(duration);
        objectAnimator.setTarget(view);
        objectAnimator.setPropertyName("Alpha");
        objectAnimator.setIntValues(value);
        objectAnimator.start();
    }



    public void selectdate (){
        MaterialDatePicker<Pair<Long,Long>> datePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select Date Range").build();
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {

                long start = selection.first/(1000*60*60*24);
                long end = selection.second/(1000*60*60*24);

                projectlist.removeAllViews();
                for (Project project : AppData.projects){
                    final String name = project.savedname;
                    final String description =  project.savedescription ;
                    final int imageres = project.saveimage;

                    final ArrayList<HashMap<String,Object>> breakdown =  project.breakdown;
                    final int budget = project.budget;
                    final String title = project.title;
                    final String details = project.details;;
                    final  String time = project.time;
                    Cache.arraymap = breakdown;

                    long date = project.date;
                    project.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(getContext(), ViewProject.class);
                            intent.putExtra("name",name);
                            intent.putExtra("description",description);
                            intent.putExtra("image",imageres);

                            Cache.arraymap = breakdown;
                            intent.putExtra("budget",budget);
                            intent.putExtra("title",title);
                            intent.putExtra("details",details);
                            intent.putExtra("time",time);

                            startActivity(intent);
                        }
                    });

                    if (date > start && date < end){
                        projectlist.addView(project);
                    }


                }



                Log.d("Fitoy", String.valueOf(start));
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy", Locale.getDefault());

                Toast.makeText(getContext().getApplicationContext(),dateFormat.format(start),Toast.LENGTH_SHORT).show();

            }
        });

        datePicker.show(getParentFragmentManager(),"Selector" );

    }
}