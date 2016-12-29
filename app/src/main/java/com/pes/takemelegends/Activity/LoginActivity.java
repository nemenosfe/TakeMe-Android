package com.pes.takemelegends.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

import static android.R.attr.data;
import static com.pes.takemelegends.R.id.button;
import static com.twitter.sdk.android.core.TwitterCore.TAG;

public class LoginActivity extends Activity implements GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "3BHl3HDnSPuOEz4h8udLjZ1an";
    private static final String TWITTER_SECRET = "gCwOn4hM5xiC07KJXpONWZUw0moyz5OhP92GYFZRxveCImCtBK";
    private static final int RC_SIGN_IN = 1;
    private TwitterLoginButton loginButton;
    private Button buttonFacebook;
    private GoogleApiClient mGoogleApiClient;
    private SharedPreferencesManager sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        sharedPreferences = new SharedPreferencesManager(this);
        //mGoogleApiClient = new GoogleApiClient.Builder(this)
        //        .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
        //        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        //        .build();


        //buttonFacebook = (Button) findViewById(R.id.button_facebook);
        //buttonFacebook.setOnClickListener(this);

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);

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
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                /*TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        // Do something with the result, which provides the email address
                        //session.
                        // TODO: Remove toast and use the TwitterSession's userID
                        // with your app's user model
                        Toast.makeText(getApplicationContext(),result.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                        Toast.makeText(getApplicationContext(),exception.toString(), Toast.LENGTH_LONG).show();
                    }
                });*/
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });



        Button buttonDirecte = (Button) findViewById(R.id.buttonDirecte);
        buttonDirecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (!sharedPreferences.isFirstTime()) {
                    intent = new Intent(LoginActivity.this, PreferencesActivity.class);
                    intent.putExtra("skip", true);
                    sharedPreferences.setFirstTime(true);
                }
                else intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignInResult(result);
            }
            // Make sure that the loginButton hears the result from any
            // Activity that it triggered.
            loginButton.onActivityResult(requestCode, resultCode, data);
        }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            acct.getEmail();
            String msg = "@" + acct.getEmail() + " logged in! (#" + acct.getId() + ")";
            Intent intent = new Intent(LoginActivity.this, PreferencesActivity.class);
            intent.putExtra("skip", true);
            startActivity(intent);
            finish();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        
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

