package com.marre2retard.mar2retard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.marre2retard.mar2retard.R;

/**
 * Created by gasmi on 07/01/2018.
 */

public class ViewHolderPhoto extends RecyclerView.ViewHolder {
    public ImageView photo;

    public ViewHolderPhoto(View itemView) {
        super(itemView);
        photo = (ImageView) itemView.findViewById(R.id.photoItem);
    }
}
