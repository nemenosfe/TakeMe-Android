package com.pes.takemelegends.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EventDetailsActivity extends Activity implements View.OnClickListener {

    private static final int RECORD_REQUEST_CODE = 101;
    private EventController eventController;

    private ImageButton buttonShare, mapBtn;
    private TextView eventName, textEventDate, textEventTime, textEventAddress, textDescription;
    private float latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        buttonShare = (ImageButton)findViewById(R.id.buttonShare);
        mapBtn = (ImageButton) findViewById(R.id.mapBtn);

        mapBtn.setOnClickListener(this);
        buttonShare.setOnClickListener(this);

        eventName = (TextView) findViewById(R.id.textEventName);
        textEventDate = (TextView) findViewById(R.id.textEventDate);
        //textEventTime = (TextView) findViewById(R.id.textEventTime);
        textEventAddress = (TextView) findViewById(R.id.textEventAdress);
        textDescription = (TextView) findViewById(R.id.textDescription);

        eventController = ControllerFactory.getInstance().getEventController();
        Bundle extra = getIntent().getExtras();
        if (extra!=null) {
            eventController.getEventInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONObject event = null;
                    try {
                        event = response.getJSONObject("event");
                        //TODO: Obtenir categoria de la API
                        String category = event.isNull("categories") ? "" : event.getString("categories");
                        String title = event.isNull("title") ? "" : event.getString("title");
                        String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                        String description = event.isNull("description") ? "" : event.getString("description");
                        String venue = event.isNull("venue_name") ? "" : event.getString("venue_name");
                        String lat = event.isNull("latitude") ? "" : event.getString("latitude");
                        String lng = event.isNull("longitude") ? "" : event.getString("longitude");
                        latitude = Float.valueOf(lat);
                        longitude = Float.valueOf(lng);
                        String id = event.getString("id");
                        String image = "http://www.hutterites.org/wp-content/uploads/2012/03/placeholder.jpg";
                        if (!event.isNull("image")) {
                            JSONObject imageObject = event.getJSONObject("image");
                            if (!imageObject.isNull("medium")) image = imageObject.getJSONObject("medium").getString("url");
                            else if (!imageObject.isNull("thumb")) image = imageObject.getJSONObject("thumb").getString("url");
                        }
                        String attendances = String.valueOf(event.getInt("number_attendances"));
                        String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));

                        eventName.setText(title);
                        textEventDate.setText(startTime);
                        textDescription.setText(description);
                        textEventAddress.setText(venue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.v("reposnse","fail");
                }
            },extra.getString("id"));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonShare:
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Mira este evento que he encontrado en Take Me Legends! www.takeme-legends.com";
                String shareSub = "Take Me Legends!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));

                break;
            }
            case R.id.mapBtn:
            {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    checkPermissions();
                } else{
                    Intent intent = new Intent(EventDetailsActivity.this, MapActivity.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    startActivity(intent);
                    break;

                }
            }
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(EventDetailsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(EventDetailsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(EventDetailsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        RECORD_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(EventDetailsActivity.this, MapActivity.class);
                    startActivity(intent);
                    break;

                } else {

                    Toast toast = Toast.makeText(EventDetailsActivity.this, "DENIED", Toast.LENGTH_LONG);
                    toast.show();
                }
                return;
            }
        }
    }
}