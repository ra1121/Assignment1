package com.app.dinosaurs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.app.dinosaurs.R;
import com.app.dinosaurs.model.Dinosaur;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DinosaursAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Dinosaur> dinosaurArrayList;

    private Context context;

    public DinosaursAdapter(ArrayList<Dinosaur> dinosaurArrayList, Context context) {
        this.dinosaurArrayList = dinosaurArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        Dinosaur dinosaur = dinosaurArrayList.get(position);
        holder.txtDinosaurName.setText(dinosaur.getName());
        Glide.with(context).load(dinosaur.getUrl())
                .transition(withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imgDinosaur);


    }

    @Override
    public int getItemCount() {
        return dinosaurArrayList == null ? 0 : dinosaurArrayList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDinosaurName;
        private ImageView imgDinosaur;

        private ViewHolder(View view) {
            super(view);
            imgDinosaur = view.findViewById(R.id.imgDinosaur);
            txtDinosaurName = view.findViewById(R.id.txtDinosaurName);

        }


    }
}
