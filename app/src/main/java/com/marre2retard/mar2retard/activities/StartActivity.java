package com.marre2retard.mar2retard.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.marre2retard.mar2retard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

//import butterknife.ButterKnife;

public class StartActivity extends Activity {


    /**
     * Tag pour les logs
     */
    private static final String TAG = "StartActivity";

    /**
     * longueur du chargement 1 seconde
     */
    private static final long LOADING_DURATION_MS = 2000;

    /**
     * pourcentage de fin du chargement
     */
    private static final int PROGRESS_COMPLETE = 100;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_version)
    TextView tvVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Fabric.with(this, new Crashlytics());
        // init ButterKnife
        ButterKnife.bind(this);
        String versionName = "";
        try {
            versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvVersion.setText("Version " + versionName);

        startAnimation();

    }

    /**
     * Déclenche le chargement
     */
    private void startAnimation() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", PROGRESS_COMPLETE).setDuration(LOADING_DURATION_MS);
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {


                startActivity(new Intent(getApplicationContext(), HomeActivity.class));

            }
        });
        progressAnimator.start();
    }

}
