package com.teamutil.unhidegov;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class NewProject extends AppCompatActivity {

    EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8;
    ImageView image;
    Button submitBUtton,selectdate;
    SeekBar seekbar ;
    long start;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_project);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = this.findViewById(R.id.edit1);
        edit2 = this.findViewById(R.id.edit2);
        edit3 = this.findViewById(R.id.edit3);
        edit4 = this.findViewById(R.id.edit4);
        edit5 = this.findViewById(R.id.edit5);
        edit6 = this.findViewById(R.id.edit6);

        edit8 = this.findViewById(R.id.edit8);
        image = this.findViewById(R.id.image);
        submitBUtton = this.findViewById(R.id.submitbutton);
        selectdate = this.findViewById(R.id.selectdate);
        seekbar = this.findViewById(R.id.seekBar);
        back = this.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectdate();
            }
        });

        submitBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameinput = edit1.getText().toString();
                String descriptioninput = edit2.getText().toString();
                String title = edit3.getText().toString();
                String details = edit4.getText().toString();
                String time = edit5.getText().toString();
                int budget = Integer.valueOf(edit6.getText().toString());
                String breakdowninput = edit8.getText().toString();

                Project project = new Project(getApplicationContext());
                project.setName(nameinput);
                project.setDescription(descriptioninput);
                project.setImage(R.drawable.kibuloy);
                project.setLocation(Project.PROVINCIAL);
                project.setDate(start);
                project.setTitle(title);
                project.setDetails(details);
                project.setTime(time);
                project.setProgress(seekbar.getProgress());
                project.setBudget(budget,breakdowninput);
                AppData.projects.add(project);
                finish();


            }
        });


    }


    public void selectdate (){
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date Range").build();
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                start = selection / (1000 * 60 * 60 * 24);
            }
        });

        datePicker.show(getSupportFragmentManager(),"Selector" );

    }
}
