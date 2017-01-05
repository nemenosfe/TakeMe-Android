package com.pes.takemelegends.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.pes.takemelegends.R;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends Activity implements View.OnClickListener {


    private List<String> allCities, selectedCities, allPreferences, selectedPreferences;
    private Button saveButton, skipButton;
    private TextView selectedCitiesTextView, selectedPreferencesTextView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        selectedCities = new ArrayList<>();
        selectedPreferences = new ArrayList<>();
        allCities = new ArrayList<>();
        allPreferences = new ArrayList<>();

        skipButton = (Button)findViewById(R.id.button_skip);
        saveButton = (Button)findViewById(R.id.button_save);
        backButton = (ImageButton) findViewById(R.id.preferences_back_button);
        selectedCitiesTextView = (TextView)findViewById(R.id.text_selected_cities);
        selectedPreferencesTextView = (TextView) findViewById(R.id.selected_preferences);

        saveButton.setOnClickListener(this);
        selectedCitiesTextView.setOnClickListener(this);
        selectedPreferencesTextView.setOnClickListener(this);

        if (getIntent().getExtras().getBoolean("skip")) {
            skipButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.INVISIBLE);
            skipButton.setOnClickListener(this);
        }
        else {
            skipButton.setVisibility(View.GONE);
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(this);
        }

        allCities.add("Barcelona");
        allCities.add("Madrid");
        allCities.add("Valencia");
        allCities.add("Sevilla");
        allCities.add("Zaragoza");
        allCities.add("Murcia");
        allCities.add("Palma de Mallorca");
        allCities.add("Palma de Gran Canaria");
        allCities.add("Bilbao");

        allPreferences.add(getResources().getString(R.string.preferences_music));
        allPreferences.add(getResources().getString(R.string.preferences_conference));
        allPreferences.add(getResources().getString(R.string.preferences_comedy));
        allPreferences.add(getResources().getString(R.string.preferences_learning_education));
        allPreferences.add(getResources().getString(R.string.preferences_family_fun_kids));
        allPreferences.add(getResources().getString(R.string.preferences_festivals_parades));
        allPreferences.add(getResources().getString(R.string.preferences_movies_film));
        allPreferences.add(getResources().getString(R.string.preferences_food));
        allPreferences.add(getResources().getString(R.string.preferences_fundraisers));
        allPreferences.add(getResources().getString(R.string.preferences_art));
        allPreferences.add(getResources().getString(R.string.preferences_support));
        allPreferences.add(getResources().getString(R.string.preferences_holiday));
        allPreferences.add(getResources().getString(R.string.preferences_books));
        allPreferences.add(getResources().getString(R.string.preferences_attractions));
        allPreferences.add(getResources().getString(R.string.preferences_community));
        allPreferences.add(getResources().getString(R.string.preferences_business));
        allPreferences.add(getResources().getString(R.string.preferences_singles_social));
        allPreferences.add(getResources().getString(R.string.preferences_schools_alumni));
        allPreferences.add(getResources().getString(R.string.preferences_outdoors_recreation));
        allPreferences.add(getResources().getString(R.string.preferences_performing_arts));
        allPreferences.add(getResources().getString(R.string.preferences_animals));
        allPreferences.add(getResources().getString(R.string.preferences_politics_activism));
        allPreferences.add(getResources().getString(R.string.preferences_sales));
        allPreferences.add(getResources().getString(R.string.preferences_science));
        allPreferences.add(getResources().getString(R.string.preferences_religion_spiritualism));
        allPreferences.add(getResources().getString(R.string.preferences_sports));
        allPreferences.add(getResources().getString(R.string.preferences_technology));
        allPreferences.add(getResources().getString(R.string.preferences_others));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                                    text = resources.getString(R.string.preferences_all_cities);
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

            case R.id.selected_preferences: {
                final CharSequence[] dialogList = allPreferences.toArray(new CharSequence[allPreferences.size()]);
                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(PreferencesActivity.this);
                builderDialog.setTitle("Select Item");
                int count = dialogList.length;
                boolean[] is_checked = new boolean[count];

                for (int i = 0; i < allPreferences.size(); i++){
                    if (selectedPreferences.contains(allPreferences.get(i))){
                        is_checked[i] = true;
                    }
                    else {
                        is_checked[i] = false;
                    }
                }

                builderDialog.setMultiChoiceItems(dialogList, is_checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) { }
                        });

                builderDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ListView list = ((AlertDialog) dialog).getListView();

                                selectedPreferences = new ArrayList<>();

                                for (int i = 0; i < list.getChildCount(); i++)
                                {
                                    CheckedTextView checkedTextView = (CheckedTextView)list.getChildAt(i);
                                    if (checkedTextView.isChecked()) {
                                        selectedPreferences.add((String) checkedTextView.getText());
                                    }
                                }

                                TextView text_selected_preference = (TextView) findViewById(R.id.selected_preferences);
                                String text = "";

                                if (selectedPreferences.size() == 0){
                                    Resources resources = getResources();
                                    text = resources.getString(R.string.text_all_preferences);
                                }
                                else {
                                    for (int i = 0; i < selectedPreferences.size(); i++) {
                                        text += selectedPreferences.get(i);
                                        if (i < selectedPreferences.size() - 1) {
                                            text += ", ";
                                        }
                                    }
                                }
                                text_selected_preference.setText(text);

                            }
                        });

                builderDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog alert = builderDialog.create();
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

            case R.id.preferences_back_button: {
                finish();
            }
        }
    }
}
