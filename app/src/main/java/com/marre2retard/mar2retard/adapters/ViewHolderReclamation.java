package com.marre2retard.mar2retard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marre2retard.mar2retard.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gasmi on 07/01/2018.
 */

public class ViewHolderReclamation {
    @BindView(R.id.titleUser)
    TextView titleUser;

    @BindView(R.id.textViewDepart)
    TextView textViewDepart;

    @BindView(R.id.textViewGareRetard)
    TextView textViewGareRetard;

    @BindView(R.id.textViewNumTrain)
    TextView textViewNumTrain;

    @BindView(R.id.textViewDate)
    TextView textViewDate;

    @BindView(R.id.textViewHeureDepart)
    TextView textViewHeureDepart;

    @BindView(R.id.textViewHeureArrivee)
    TextView textViewHeureArrivee;

    @BindView(R.id.textViewRetard)
    TextView textViewRetard;

    @BindView(R.id.textViewCommentaire)
    TextView textViewCommentaire;

    @BindView(R.id.layoutListPhoto)
    RelativeLayout layoutListPhoto;

    @BindView(R.id.recycleViewPhoto)
    RecyclerView recycleViewPhoto;

    @BindView(R.id.buttonConfirme)
    Button buttonConfirme;

    @BindView(R.id.numberComfirmed)
    TextView numberComfirmed;

    @BindView(R.id.buttonSignaler)
    Button buttonSignaler;

    @BindView(R.id.numberSignaler)
    TextView numberSignaler;


    public ViewHolderReclamation(View view) {
        ButterKnife.bind(this, view);
    }
}
