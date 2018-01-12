package com.marre2retard.mar2retard.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marre2retard.mar2retard.R;
import com.marre2retard.mar2retard.models.Voyageur;
import com.marre2retard.mar2retard.tools.Singleton;
import com.marre2retard.mar2retard.tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasmi on 07/01/2018.
 */

public class AdapterReclamations extends ArrayAdapter<Voyageur> {
    List<Voyageur> listReclamation;

    public AdapterReclamations(@NonNull Context context, int resource, List<Voyageur> voyageurs) {
        super(context, resource, voyageurs);
        listReclamation = voyageurs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final ViewHolderReclamation holder;
        if (view != null) {
            holder = (ViewHolderReclamation) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_reclamation, parent, false);
            holder = new ViewHolderReclamation(view);
            view.setTag(holder);
        }

        Voyageur voyageur = getItem(position);

        holder.textViewDate.setText(voyageur.getDatePublication());
        holder.textViewDepart.setText(voyageur.getTrain().getDepurtStation());
        holder.textViewGareRetard.setText(voyageur.getTrain().getArrivalStation());
        holder.textViewHeureDepart.setText(voyageur.getTrain().getDepartTime());
        holder.textViewHeureArrivee.setText(voyageur.getTrain().getArrivalTime());
        holder.textViewNumTrain.setText(voyageur.getTrain().getTrainNumber());
        holder.textViewRetard.setText(voyageur.getRetard());
        holder.titleUser.setText(voyageur.getPsoeudo());


        holder.buttonConfirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to like a not like publicaltion
                int like = Singleton.getInstance().getLike();
                like++;

                holder.numberComfirmed.setText("" + like);
                Singleton.getInstance().setLike(like);
            }
        });


        holder.buttonSignaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int notLike = Singleton.getInstance().getDoNotLike();
                notLike++;
                holder.numberSignaler.setText("" + notLike);
                Singleton.getInstance().setDoNotLike(notLike);
            }
        });


        if (voyageur.getCommentaire().isEmpty()) {
            holder.textViewCommentaire.setVisibility(View.GONE);
        } else {
            holder.textViewCommentaire.setVisibility(View.VISIBLE);
            holder.textViewCommentaire.setText(voyageur.getCommentaire());
        }

        if (voyageur.getListPhotoBase64() == null) {
            holder.layoutListPhoto.setVisibility(View.GONE);
        } else {
            holder.layoutListPhoto.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
            holder.recycleViewPhoto.setLayoutManager(layoutManager);
            holder.recycleViewPhoto.setHasFixedSize(true);
            List<Bitmap> listBitmapPhotos = new ArrayList<>();
            for (String photoString : voyageur.getListPhotoBase64()) {
                Bitmap bitmap = Utils.convert(photoString);
                listBitmapPhotos.add(bitmap);
            }
            holder.recycleViewPhoto.setAdapter(new AdapterPhotos(view.getContext(), listBitmapPhotos));

        }


        return view;
    }

    @Override
    public int getCount() {
        return listReclamation.size();
    }
}
