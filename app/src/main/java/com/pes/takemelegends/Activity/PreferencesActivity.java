package com.pes.takemelegends.Activity;

import android.app.Activity;
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
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends Activity implements View.OnClickListener {


    private List<String> allCities, selectedCities, allCategories;
    private SharedPreferencesManager sharedPreferencesManager;
    private Button saveButton, skipButton;
    private TextView selectedCitiesTextView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        selectedCities = new ArrayList<>();
        allCities = new ArrayList<>();
        allCategories = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        sharedPreferencesManager = new SharedPreferencesManager(this);

        skipButton = (Button)findViewById(R.id.button_skip);
        saveButton = (Button)findViewById(R.id.button_save);
        backButton = (ImageButton) findViewById(R.id.preferences_back_button);
        selectedCitiesTextView = (TextView)findViewById(R.id.text_selected_cities);

        saveButton.setOnClickListener(this);
        selectedCitiesTextView.setOnClickListener(this);

        if (extras.getBoolean("skip")) {
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

        allCategories.add("Música");
        allCategories.add("Conferencias");
        allCategories.add("Comedia");
        allCategories.add("Educación");
        allCategories.add("Familia");
        allCategories.add("Festivales");
        allCategories.add("Cine");
        allCategories.add("Gastronomía");
        allCategories.add("Recaudación de fondos");
        allCategories.add("Arte");
        allCategories.add("Salud");
        allCategories.add("Vacaciones");
        allCategories.add("Lectura");
        allCategories.add("Museos");
        allCategories.add("Comunidad");
        allCategories.add("Negocios");
        allCategories.add("Fiestas");
        allCategories.add("Meetups");
        allCategories.add("Actividades al aire libre");
        allCategories.add("Artes escénicas");
        allCategories.add("Animales");
        allCategories.add("Política");
        allCategories.add("Ventas");
        allCategories.add("Ciencia");
        allCategories.add("Religión");
        allCategories.add("Deporte");
        allCategories.add("Tecnología");
        allCategories.add("Otros");
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
