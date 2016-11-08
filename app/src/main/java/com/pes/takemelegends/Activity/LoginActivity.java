package com.pes.takemelegends.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pes.takemelegends.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

import static android.R.attr.data;
import static com.pes.takemelegends.R.id.button;

public class LoginActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "3BHl3HDnSPuOEz4h8udLjZ1an";
    private static final String TWITTER_SECRET = "gCwOn4hM5xiC07KJXpONWZUw0moyz5OhP92GYFZRxveCImCtBK";
    private TwitterLoginButton loginButton;
    private Button buttonFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);


        buttonFacebook = (Button) findViewById(R.id.button_facebook);
        //buttonFacebook.setOnClickListener(this);

        Button buttonGoogle = (Button) findViewById(R.id.button_google);
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PreferencesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //buttonGoogle.setOnClickListener(this);

        Button buttonTwitter = (Button) findViewById(R.id.twitter_login_button);
        //buttonTwitter.setOnClickListener(this);



        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                //session.
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Make sure that the loginButton hears the result from any
            // Activity that it triggered.
            loginButton.onActivityResult(requestCode, resultCode, data);
        }


    }

    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.button_facebook:
            {
                Intent intent = new Intent(LoginActivity.this, PreferencesActivity.class);
                startActivity(intent);
                finish();
                break;
            }

            case R.id.button_google: {
                break;
            }

            case R.id.twitter_login_button: {
                break;
            }
        }

        Context context = this;
        new AlertDialog.Builder(context)
                .setTitle("Login")
                .setMessage("Are you sure you want to login?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)

        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        LoginActivity.this.startActivity(myIntent);
        
                .show();*/

