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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.UserController;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PreferencesActivity extends Activity implements View.OnClickListener {


    private List<String> allCities, selectedCities, allPreferences, selectedPreferences;
    private Button saveButton, skipButton;
    private TextView selectedCitiesTextView, selectedPreferencesTextView;
    private ImageButton backButton;
    private ListView selectedPreferencesList, selectedCitiesList;
    private UserController userController;
    private Boolean isFirstTime = true;
    private Map<String,String> mapCategories;
    private SharedPreferencesManager shared;

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
        selectedPreferencesList = (ListView) findViewById(R.id.preferences_list);
        selectedCitiesList = (ListView) findViewById(R.id.cities_list);

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
        
//        shared = new SharedPreferencesManager(this);
//        if (shared.getHasPreferences()) {
//            isFirstTime = false;
//        }
//        else {
//            isFirstTime = true;
//        }

        allCities.add("Barcelona");
        allCities.add("Madrid");
        allCities.add("Valencia");
        allCities.add("Sevilla");
        allCities.add("Zaragoza");
        allCities.add("Murcia");
        allCities.add("Palma de Mallorca");
        allCities.add("Palma de Gran Canaria");
        allCities.add("Bilbao");

        mapCategories = new HashMap<>();
        mapCategories.put("music", getResources().getString(R.string.preferences_music));
        mapCategories.put("conference", getResources().getString(R.string.preferences_conference));
        mapCategories.put("comedy", getResources().getString(R.string.preferences_comedy));
        mapCategories.put("learning_education", getResources().getString(R.string.preferences_learning_education));
        mapCategories.put("family_fun_kids", getResources().getString(R.string.preferences_family_fun_kids));
        mapCategories.put("festivals_parades", getResources().getString(R.string.preferences_festivals_parades));
        mapCategories.put("movies_film", getResources().getString(R.string.preferences_movies_film));
        mapCategories.put("food", getResources().getString(R.string.preferences_food));
        mapCategories.put("fundraisers", getResources().getString(R.string.preferences_fundraisers));
        mapCategories.put("art", getResources().getString(R.string.preferences_art));
        mapCategories.put("support", getResources().getString(R.string.preferences_support));
        mapCategories.put("holiday", getResources().getString(R.string.preferences_holiday));
        mapCategories.put("books", getResources().getString(R.string.preferences_books));
        mapCategories.put("attractions", getResources().getString(R.string.preferences_attractions));
        mapCategories.put("community", getResources().getString(R.string.preferences_community));
        mapCategories.put("business", getResources().getString(R.string.preferences_business));
        mapCategories.put("singles_social", getResources().getString(R.string.preferences_singles_social));
        mapCategories.put("schools_alumni", getResources().getString(R.string.preferences_schools_alumni));
        mapCategories.put("outdoors_recreation", getResources().getString(R.string.preferences_outdoors_recreation));
        mapCategories.put("performing_arts", getResources().getString(R.string.preferences_performing_arts));
        mapCategories.put("animals", getResources().getString(R.string.preferences_animals));
        mapCategories.put("politics_activism", getResources().getString(R.string.preferences_politics_activism));
        mapCategories.put("sales", getResources().getString(R.string.preferences_sales));
        mapCategories.put("science", getResources().getString(R.string.preferences_science));
        mapCategories.put("religion_spirituality", getResources().getString(R.string.preferences_religion_spiritualism));
        mapCategories.put("sports", getResources().getString(R.string.preferences_sports));
        mapCategories.put("technology", getResources().getString(R.string.preferences_technology));
        mapCategories.put("others", getResources().getString(R.string.preferences_others));

        for ( Map.Entry<String, String> entry : mapCategories.entrySet() ) {
            String value = entry.getValue();
            allPreferences.add(value);
        }

        userController = ControllerFactory.getInstance().getUserController();
        userController.getPreferences(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                String strCategories = "";
                String strLocations = "";
                try {
                    if (response.getJSONObject("preferences").getString("categories") != null) {
                        strCategories = response.getJSONObject("preferences").getString("categories");
                    }
                    if (response.getJSONObject("preferences").getString("locations") != null) {
                        strLocations = response.getJSONObject("preferences").getString("locations");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                List<String> categories = Arrays.asList(strCategories.split("\\|\\|"));
                List<String> locations = Arrays.asList(strLocations.split("\\|\\|"));

                for (int i=0; i< categories.size(); i++) {
                    String key = categories.get(i);
                    String value = mapCategories.get(key);
                    if (value != null) {
                        selectedPreferences.add(value);
                    }
                }

                for (int i=0; i< locations.size(); i++) {
                    String value = locations.get(i);
                    if (value != null) {
                        selectedCities.add(value);
                    }
                }

                selectedPreferencesList.setAdapter(new ArrayAdapter<>(PreferencesActivity.this, android.R.layout.simple_list_item_1, selectedPreferences));
                setListViewHeightBasedOnChildren(selectedPreferencesList);

                selectedCitiesList.setAdapter(new ArrayAdapter<>(PreferencesActivity.this, android.R.layout.simple_list_item_1, selectedCities));
                setListViewHeightBasedOnChildren(selectedCitiesList);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "getPreferences failed: ", Toast.LENGTH_SHORT).show();
            }
        },getApplicationContext());
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    private void goToMainActivity() {
        Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
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

                                selectedCitiesList.setAdapter(new ArrayAdapter<>(PreferencesActivity.this, android.R.layout.simple_list_item_1, selectedCities));
                                setListViewHeightBasedOnChildren(selectedCitiesList);

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
                                selectedPreferencesList.setAdapter(new ArrayAdapter<>(PreferencesActivity.this, android.R.layout.simple_list_item_1, selectedPreferences));
                                setListViewHeightBasedOnChildren(selectedPreferencesList);

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

                // prepare the cities in a string like:   aa||bbbb||asagfdsg
                String cities = "";
                for(int i = 0; i < selectedCities.size(); i++) {
                    if (cities.equals("")) {
                        cities += selectedCities.get(i);
                    }
                    else {
                        cities += "||";
                        cities += selectedCities.get(i);
                    }
                }

                // prepare the categories in a string like:   aa||bbbb||asagfdsg
                String categories = "";
                for(int i = 0; i < selectedPreferences.size(); i++) {

                    String key = (String) getKeyFromValue(mapCategories,selectedPreferences.get(i));

                    if (categories.equals("")) {
                        categories += key;
                    }
                    else {
                        categories += "||";
                        categories += key;
                    }
                }

                // Create the string preferences to send it to the controller:   "{'loc..':'aa||sad||adf', 'categories':'asd||an||ad'}"
                JSONObject json = new JSONObject();
                try {
                    json.put("categories", categories);
                    json.put("locations", cities);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_SHORT).show();

                // POST or PUT
                if (isFirstTime) {
                    userController.postPreferences(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            //shared.setHasPreferences(true);
                            goToMainActivity();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Toast.makeText(getApplicationContext(), "postPreferences failed", Toast.LENGTH_SHORT).show();
                            goToMainActivity();
                        }
                    },getApplicationContext(), categories, cities);
                }
                else {
                    userController.putPreferences(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            goToMainActivity();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Toast.makeText(getApplicationContext(), "putPreferences failed", Toast.LENGTH_SHORT).show();
                            goToMainActivity();
                        }
                    },getApplicationContext(), categories, cities);
                }

                break;
            }

            case R.id.preferences_back_button: {
                finish();
            }
        }
    }
}
