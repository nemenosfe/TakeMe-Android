package com.pes.takemelegends.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RECORD_REQUEST_CODE = 101;
    private EventController eventController;

    private ImageButton buttonShare, mapBtn, backButton;
    private Button asistire;
    private TextView eventName, textEventDate, textEventAddress, textDescription, textTakes;
    private float latitude, longitude;
    private String address;
    private Context context;
    private ImageView eventImage;
    private String event_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        context = getApplicationContext();

        eventImage = (ImageView)findViewById(R.id.eventImage);
        buttonShare = (ImageButton)findViewById(R.id.buttonShare);
        asistire = (Button)findViewById(R.id.buttonAsistire);
        mapBtn = (ImageButton) findViewById(R.id.mapBtn);

        backButton = (ImageButton) findViewById(R.id.event_details_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Picasso.with(context).load("http://www.events.cat/wp-content/gallery/2015/exp-events-2015-001.jpg").into(eventImage);

        mapBtn.setOnClickListener(this);
        buttonShare.setOnClickListener(this);
        asistire.setOnClickListener(this);

        eventName = (TextView) findViewById(R.id.textEventName);
        textEventDate = (TextView) findViewById(R.id.textEventDate);
        textEventAddress = (TextView) findViewById(R.id.textEventAdress);
        textDescription = (TextView) findViewById(R.id.textDescription);
        textTakes = (TextView) findViewById(R.id.textTakes);

        eventController = ControllerFactory.getInstance().getEventController();
        Bundle extra = getIntent().getExtras();
        if (extra!=null) {
            event_id = extra.getString("id");
            eventController.getEventInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONObject event;
                    try {
                        event = response.getJSONObject("event");
                        String title = event.isNull("title") ? "" : event.getString("title");
                        String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                        String description = event.isNull("description") ? "" : event.getString("description");
                        String venue = event.isNull("venue_name") ? "" : event.getString("venue_name");
                        String lat = event.isNull("latitude") ? "" : event.getString("latitude");
                        String lng = event.isNull("longitude") ? "" : event.getString("longitude");
                        String attendance = event.isNull("wanted_attendance") ? "0" : event.getString("wanted_attendance");
                        address = event.isNull("address") ? "" : event.getString("address");
                        latitude = Float.valueOf(lat);
                        longitude = Float.valueOf(lng);
                        String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));

                        eventName.setText(title);
                        textEventDate.setText(startTime);
                        textDescription.setText(description);
                        textEventAddress.setText(venue);
                        textTakes.setText(takes + "\ntakes");
                        if (attendance.equals("1")) {
                            disableAttendance();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.v("reponse","fail");
                }
            }, event_id);
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
                    intent.putExtra("address", address);
                    startActivity(intent);
                    break;

                }
            }
            case R.id.buttonAsistire:
                eventController.postAsistire(new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Toast.makeText(EventDetailsActivity.this, getString(R.string.success_asistire), Toast.LENGTH_SHORT).show();
                        disableAttendance();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(EventDetailsActivity.this, getString(R.string.failure_asistire), Toast.LENGTH_SHORT).show();
                    }
                },this, event_id);
                break;
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

    private void disableAttendance() {
        asistire.setClickable(false);
        asistire.setAlpha(0.25f);
    }
}