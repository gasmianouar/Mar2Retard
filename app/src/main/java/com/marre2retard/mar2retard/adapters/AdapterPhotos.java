package com.marre2retard.mar2retard.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marre2retard.mar2retard.R;

import java.util.List;

/**
 * Created by gasmi on 07/01/2018.
 */

public class AdapterPhotos extends RecyclerView.Adapter<ViewHolderPhoto> {

    private List<Bitmap> listPhoto;
    private Context mContext;

    public AdapterPhotos(Context context, List<Bitmap> imageViewList) {
        mContext = context;
        listPhoto = imageViewList;

    }

    @Override
    public ViewHolderPhoto onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        ViewHolderPhoto holder = new ViewHolderPhoto(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderPhoto holder, int position) {

        holder.photo.setImageBitmap(listPhoto.get(position));


    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }

}

