package com.marre2retard.mar2retard.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.marre2retard.mar2retard.R;
import com.marre2retard.mar2retard.adapters.AdapterReclamations;
import com.marre2retard.mar2retard.models.Voyageur;
import com.marre2retard.mar2retard.tools.Singleton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gasmi on 01/01/2018.
 */

public class ListReclamationActivity extends Activity {

    //recycleView photos
    @BindView(R.id.listReclamations)
    ListView listViewReclamation;

    //recycleView photos
    @BindView(R.id.searchView)
    SearchView searchView;


    List<Voyageur> listReclamation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list_reclamations);
        // init ButterKnife
        ButterKnife.bind(this);

        listReclamation.add(Singleton.getInstance().getVoyageur());
        AdapterReclamations adapterReclamations = new AdapterReclamations(this, R.layout.item_reclamation, listReclamation);
        listViewReclamation.setAdapter(adapterReclamations);
    }

    @OnClick(R.id.exit)
    void Excit() {
        finish();
    }

    @OnClick(R.id.refresh)
    void Refresh() {

    }
}
