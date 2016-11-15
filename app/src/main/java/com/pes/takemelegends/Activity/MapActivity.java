package com.pes.takemelegends.Activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.pes.takemelegends.Fragment.MapFragment;
import com.pes.takemelegends.R;

public class MapActivity extends Activity {

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

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (map == null) map = new MapFragment();
        transaction.replace(R.id.mapContainer, map, "map");
        transaction.addToBackStack("eventsFeed");
        transaction.commit();
    }
}
