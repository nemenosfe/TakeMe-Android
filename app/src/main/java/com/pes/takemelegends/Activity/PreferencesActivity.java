package com.pes.takemelegends.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pes.takemelegends.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener {


    List<String> allCities = new ArrayList<String>();
    List<String> selectedCities = new ArrayList<String>();
    List<String> allCategories = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(getResources().getColor(R.color.main_ambar));
        //toolbar.setTitle(getResources().getString(R.string.settings));
        getSupportActionBar().setTitle(getResources().getString(R.string.settings));

        Button buttonSkip= (Button)findViewById(R.id.button_skip);
        buttonSkip.setOnClickListener(this);

        Button buttonSave = (Button)findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);

        TextView textViewSelectedCities = (TextView)findViewById(R.id.text_selected_cities);
        textViewSelectedCities.setOnClickListener(this);

        allCities.add("Barcelona");
        allCities.add("Madrid");
        allCities.add("Valencia");

        allCategories.add("Footbol");
        allCategories.add("Basquet");
        allCategories.add("Todos los deportes");
        allCategories.add("Conciertos");
        allCategories.add("Pintura");
        allCategories.add("Teatro");
        allCategories.add("Cine");
        allCategories.add("Televisión");
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.preferencesTimetableFrom: {
//
//                TextView txtFrom = (TextView) findViewById(R.id.preferencesTimetableFrom);
//
//                SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
//                Date date = null;
//                try {
//                    date = sdf.parse((String) txtFrom.getText());
//                } catch (ParseException e) {
//                }
//
//                Calendar mcurrentTime = Calendar.getInstance();
//                mcurrentTime.setTime(date);
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//                TimePickerDialog mTimePicker;
//                mTimePicker = new TimePickerDialog(PreferencesActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        TextView txtFrom = (TextView) findViewById(R.id.preferencesTimetableFrom);
//                        txtFrom.setText(selectedHour + ":" + selectedMinute);
//                    }
//                }, hour, minute, true);//Yes 24 hour time
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
//
//                break;
//            }
//
//            case R.id.preferencesTimetableTo: {
//
//                TextView txtTo = (TextView) findViewById(R.id.preferencesTimetableTo);
//
//
//                SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
//                Date date = null;
//                try {
//                    date = sdf.parse((String) txtTo.getText());
//                } catch (ParseException e) {
//                }
//
//                Calendar mcurrentTime = Calendar.getInstance();
//                mcurrentTime.setTime(date);
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//                TimePickerDialog mTimePicker;
//                mTimePicker = new TimePickerDialog(PreferencesActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        TextView txtTo = (TextView) findViewById(R.id.preferencesTimetableTo);
//                        txtTo.setText(selectedHour + ":" + selectedMinute);
//                    }
//                }, hour, minute, true);//Yes 24 hour time
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
//                break;
//            }

            case R.id.text_selected_cities: {

                final CharSequence[] dialogList = allCities.toArray(new CharSequence[allCities.size()]);
                final android.app.AlertDialog.Builder builderDialog = new android.app.AlertDialog.Builder(PreferencesActivity.this);
                builderDialog.setTitle("Select Item");
                int count = dialogList.length;
                boolean[] is_checked = new boolean[count];

                // Select the cities that were previously selected
                for (int i = 0; i < allCities.size(); i++){
                    if (selectedCities.contains(allCities.get(i))){
                        is_checked[i] = true;
                    }
                    else {
                        is_checked[i] = false;
                    }
                }

                // Creating multiple selection by using setMutliChoiceItem method
                builderDialog.setMultiChoiceItems(dialogList, is_checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) { }
                        });

                builderDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // select the view containing all the cities
                                ListView list = ((android.app.AlertDialog) dialog).getListView();

                                // Empty the selected cities
                                selectedCities = new ArrayList<String>();

                                // iterate throught the cities
                                for (int i = 0; i < list.getChildCount(); i++)
                                {
                                    // if the CheckedTextView city is selected is added to selectedCities
                                    CheckedTextView checkedTextView = (CheckedTextView)list.getChildAt(i);
                                    if (checkedTextView.isChecked()) {
                                        selectedCities.add((String) checkedTextView.getText());
                                    }
                                }

                                // Display the selected cities or "All Cities" message if none is selected
                                TextView text_selected_cities = (TextView) findViewById(R.id.text_selected_cities);
                                String text = "";

                                if (selectedCities.size() == 0){
                                    Resources resources = getResources();
                                    text = resources.getString(R.string.pteferences_all_cities);
                                }
                                else {
                                    for (int i = 0; i < selectedCities.size(); i++) {
                                        text += selectedCities.get(i);
                                        if (i < selectedCities.size() - 1) {
                                            text += ", ";
                                        }
                                    }
                                }

                                text_selected_cities.setText(text);

                            }
                        });

                builderDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //((TextView) findViewById(R.id.text)).setText("Click here to open Dialog");
                            }
                        });
                android.app.AlertDialog alert = builderDialog.create();
                alert.show();
                break;
            }

            case R.id.button_skip: {
                Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }

            case R.id.button_save: {
                Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}
