package com.marre2retard.mar2retard.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.marre2retard.mar2retard.R;
import com.marre2retard.mar2retard.adapters.AdapterPhotos;
import com.marre2retard.mar2retard.api.SNCFService;
import com.marre2retard.mar2retard.models.Train;
import com.marre2retard.mar2retard.models.Voyageur;
import com.marre2retard.mar2retard.tools.Singleton;
import com.marre2retard.mar2retard.tools.TextChangedListener;
import com.marre2retard.mar2retard.tools.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;


/**
 * Created by gasmi on 24/12/2017.
 */

public class HomeActivity extends Activity implements TextChangedListener, AdapterView.OnItemSelectedListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    // train number
    @BindView(R.id.numero_train)
    TextView numeroTrain;
    //Depart station
    @BindView(R.id.gare_depart)
    AutoCompleteTextView gareDepart;
    // arrival station
    @BindView(R.id.gare_arrivee)
    AutoCompleteTextView gareArrivee;
    //Depart Hour
    @BindView(R.id.heure_depart_spinner)
    Spinner heureDepartSpinner;
    @BindView(R.id.textViewHeureDepart)
    TextView heureDepart;
    // Arrival Hour
    @BindView(R.id.heure_arrivee)
    TextView heureArrivee;
    // retard en heure
    @BindView(R.id.retardHour)
    EditText retardHour;
    // retard en heure
    @BindView(R.id.retardMinutes)
    EditText retardMinutes;
    //Commentaire
    @BindView(R.id.commentaire)
    EditText commentaire;
    // list of all train
    List<Train> listOfAllTrain;
    //list train times
    List<String> listHoraire;
    // list voyageurs
    List<Voyageur> listVoyageurs = new ArrayList<>();
    // list photo string base 64
    List<String> listPhotoBase64;
    //relativelayout list images
    @BindView(R.id.layoutListPhoto)
    RelativeLayout layoutListPhoto;
    List<Bitmap> listBitmapPhotos;
    //recycleView photos
    @BindView(R.id.recycleViewPhoto)
    RecyclerView recycleViewPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home);
        // init ButterKnife
        ButterKnife.bind(this);

        List<String> listOfStation = new ArrayList<>();
        listOfAllTrain = new ArrayList<>();
        listHoraire = new ArrayList<>();

        listOfStation.add("Nantes");
        listOfStation.add("Angers");
        listOfStation.add("Paris");
        listOfStation.add("Marseille");
        listOfStation.add("Lyon");
        listOfStation.add("Limoges");
        listOfStation.add("Nice");
        listOfStation.add("Creteil");
        listOfAllTrain.add(new Train("1971", "Nantes", "Angers", "7:47", "8:27"));
        listOfAllTrain.add(new Train("0071", "Nantes", "Paris", "8:47", "10:27"));
        listOfAllTrain.add(new Train("02341", "Nantes", "Marseille", "9:47", "12:27"));
        listOfAllTrain.add(new Train("092851", "Nantes", "Lyon", "11:47", "17:27"));
        listOfAllTrain.add(new Train("00743", "Nantes", "Limoges", "10:47", "18:27"));


        ArrayAdapter<String> adapterStation = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfStation);
        gareDepart.setAdapter(adapterStation);
        gareArrivee.setAdapter(adapterStation);

        heureDepartSpinner.setOnItemSelectedListener(this);
        // add listenner to edit text
        gareDepart.addTextChangedListener(this);
        gareArrivee.addTextChangedListener(this);
        retardMinutes.addTextChangedListener(this);
        retardHour.addTextChangedListener(this);

        appelAsynchrone();

    }


    private void appelAsynchrone() {

        SNCFService githubService = new RestAdapter.Builder()
                .setEndpoint(SNCFService.ENDPOINT)
                .setLog(new AndroidLog("retrofit"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(SNCFService.class);


        githubService.listTrainAsync("commercial_modes", new Callback<List<Train>>() {

            @Override
            public void success(List<Train> trains, retrofit.client.Response response) {
                afficherRepos(trains);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error ", error.getMessage());
            }
        });
    }


    public void afficherRepos(List<Train> repos) {
        Toast.makeText(this, "nombre de dépots : " + repos.size(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (listBitmapPhotos == null) {
                listBitmapPhotos = new ArrayList<>();
            }
            if (listPhotoBase64 == null) {
                listPhotoBase64 = new ArrayList<>();
            }
            listBitmapPhotos.add(imageBitmap);
            listPhotoBase64.add(Utils.convert(imageBitmap));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recycleViewPhoto.setLayoutManager(layoutManager);
            recycleViewPhoto.setHasFixedSize(true);
            recycleViewPhoto.setAdapter(new AdapterPhotos(this, listBitmapPhotos));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listBitmapPhotos == null) {
            layoutListPhoto.setVisibility(View.GONE);
        } else {
            layoutListPhoto.setVisibility(View.VISIBLE);
        }
    }

    // to take photo
    @OnClick(R.id.buttonCemera)
    void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // to shared marre
    @OnClick(R.id.buttonMarre)
    void toSharedMarre() {
        // add controle on time minutes < 60 min and hour < 24 h

     /*   if(Integer.parseInt(retardHour.getText().toString()) >= 24 ){
            retardHour.setError("h >= 24");
        }

        if(Integer.parseInt(retardMinutes.getText().toString()) >= 60){
            retardHour.setError("h >= 60");
        }*/
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        Voyageur voyageur = new Voyageur("Anouar", new Train(numeroTrain.getText().toString(), gareDepart.getText().toString(),
                gareArrivee.getText().toString(), heureDepart.getText().toString(),
                heureArrivee.getText().toString()),
                retardHour.getText().toString() + ":h" + retardMinutes.getText().toString() + " :min",
                commentaire.getText().toString(), date, listPhotoBase64);

        Singleton.getInstance().setVoyageur(voyageur);
        startActivity(new Intent(this, ListReclamationActivity.class));

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void afterTextChanged(Editable s) {


        if (gareDepart.getText().toString().length() > 3 && gareArrivee.getText().toString().length() > 3) {
            listHoraire = Utils.getListHoraireForTrainFromDepartAndArrival(listOfAllTrain, gareDepart.getText().toString(), gareArrivee.getText().toString());
            heureDepartSpinner.setVisibility(View.VISIBLE);
        }
        // Creating adapter for spinner
        ArrayAdapter<String> adapterTimes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listHoraire);

        // Drop down layout style - list view with radio button
        adapterTimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        heureDepartSpinner.setAdapter(adapterTimes);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        numeroTrain.setText(Utils.getTrainNumberFromList(listOfAllTrain, gareDepart.getText().toString(),
                gareArrivee.getText().toString(), adapterView.getItemAtPosition(i).toString()));
        heureDepart.setText(adapterView.getItemAtPosition(i).toString());
        heureArrivee.setText(Utils.getHourArrivaltWithNumberTrain(listOfAllTrain, numeroTrain.getText().toString()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
