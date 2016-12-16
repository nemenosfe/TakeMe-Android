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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pes.takemelegends.R;
import com.squareup.picasso.Picasso;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RECORD_REQUEST_CODE = 101;

    private ImageButton buttonShare, mapBtn;
    private Context context;
    private ImageView eventImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(getResources().getColor(R.color.main_ambar));
        getSupportActionBar().setTitle(getResources().getString(R.string.settings));

        eventImage = (ImageView)findViewById(R.id.eventImage);
        buttonShare = (ImageButton)findViewById(R.id.buttonShare);
        mapBtn = (ImageButton) findViewById(R.id.mapBtn);


        Picasso.with(context).load("http://www.events.cat/wp-content/gallery/2015/exp-events-2015-001.jpg").into(eventImage);

        mapBtn.setOnClickListener(this);
        buttonShare.setOnClickListener(this);
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