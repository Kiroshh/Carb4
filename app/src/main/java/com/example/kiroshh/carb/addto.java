package com.example.kiroshh.carb;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class addto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private Button mpicDate;
    private Button mpicTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    static  final int Dialog_ID=0;
    private String mSpinnerLabel = "";
    private Button mok;
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto);
        mDisplayDate = (TextView) findViewById(R.id.datepic);
        mpicDate=(Button)findViewById(R.id.picdate);
        mpicTime=(Button)findViewById(R.id.pictime);
        mDisplayTime=(TextView) findViewById(R.id.timepic);
        mok=(Button)findViewById(R.id.adbtn_ok);


        Spinner spinner = (Spinner) findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }



        mok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply=""   ;

                reply+=(mDisplayTime.getText().toString());
                reply+=(mDisplayDate.getText().toString());
                reply+=mSpinnerLabel;

                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_REPLY, reply);
                setResult(RESULT_OK, replyIntent);
                Log.v(TAG,reply);
                finish();

            }
        });

        mpicDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        addto.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mpicTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calc = Calendar.getInstance();
                int hour = calc.get(Calendar.HOUR_OF_DAY);
                int minute = calc.get(Calendar.MINUTE);
                TimePickerDialog dialogg=new TimePickerDialog(addto.this,android.R.style.Theme_Holo_Dialog_MinWidth,mTimeSetListener,hour,minute,false);
                dialogg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogg.show();
            }
        });

        mTimeSetListener=new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time=hourOfDay+":"+minute;
                mDisplayTime.setText(time);

            }
        };

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: ");
    }
}