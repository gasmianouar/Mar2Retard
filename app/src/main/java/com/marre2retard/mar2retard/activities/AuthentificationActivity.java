package com.marre2retard.mar2retard.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.marre2retard.mar2retard.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

import static com.facebook.internal.FacebookDialogFragment.TAG;

/**
 * Created by gasmi on 10/12/2017.
 */

public class AuthentificationActivity extends Activity {

    public static final int RC_TWITTER_LOGIN = 2;
    // [END declare_auth]
    CallbackManager callbackManager;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private TwitterLoginButton mLoginButtonTwitter;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();


        TwitterAuthConfig authConfig = new TwitterAuthConfig("0NbSH6E9BqvyecP7YVlKxAYf9", "8ocuRgDmjRayfFEfTnbKlHhzhLfhPu7NaiXi7Zhiy6H65PeMjA");
        Fabric.with(this, new Twitter(authConfig));
        super.setContentView(R.layout.activity_authentification);
        // init ButterKnife
        ButterKnife.bind(this);

        // Manually configure Firebase Options
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:481002095374:android:0380cb247f1cc5fd") // Required for Analytics.
                .setApiKey("AIzaSyD2XpuZuiv4IjHfr-yhbYy1P7EFliVHExA") // Required for Auth.
                .setDatabaseUrl("https://mar2retard.firebaseio.com") // Required for RTDB.
                .build();


        // Initialize with secondary app.
        FirebaseApp.initializeApp(this /* Context */, options);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();


        LoginButton loginButton = findViewById(R.id.login_with_facebook);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());

                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }
            }
        };

        // [END initialize_auth]

        mLoginButtonTwitter = findViewById(R.id.twitter_login_button);
        RelativeLayout layoutTwitter = (RelativeLayout) findViewById(R.id.layoutTwitter);

        // Set up an AuthStateListener that responds to changes in the user's sign-in state//
       /*    mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Retrieve the user’s account data, using the getCurrentUser method//
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // If the user signs in, then display the following message//
                    Log.d(TAG, "onAuthStateChanged" + user.getUid());
                }
            }
        };

     mLoginButtonTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "twitterLogin:success" + result);
                handleTwitterSession(result.data);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w(TAG, "twitterLogin:failure", exception);

            }
        });*/


    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AuthentificationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //Exchange the OAuth access token and OAuth secret for a Firebase credential//
    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        //If the call to signInWithCredential succeeds, then get the user’s account data//
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential" + task.isSuccessful());

                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
        // Check if user is signed in (non-null) and update UI accordingly.


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        } else {
            Log.d("zz", "----------------");
        }

        // Pass the activity result to the Twitter login button.
        //      mLoginButtonTwitter.onActivityResult(requestCode, resultCode, data);

    }


}
