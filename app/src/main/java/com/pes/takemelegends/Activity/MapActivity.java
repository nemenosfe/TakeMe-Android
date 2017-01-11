package com.pes.takemelegends.Activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pes.takemelegends.Fragment.MapFragment;
import com.pes.takemelegends.R;

public class MapActivity extends FragmentActivity {

    private MapFragment map;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        backBtn= (ImageButton) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                finish();
            }
        });

        Bundle b = getIntent().getExtras();
        float latitude = b.getFloat("latitude");
        float longitude = b.getFloat("longitude");
        String address = b.getString("address");

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(address);
        title.setEllipsize(TextUtils.TruncateAt.END);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (map == null) map = new MapFragment(latitude, longitude, address);
        transaction.replace(R.id.mapContainer, map, "map");
        transaction.commit();
    }
}
