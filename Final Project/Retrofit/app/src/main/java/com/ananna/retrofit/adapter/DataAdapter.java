package com.ananna.retrofit.adapter;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ananna.retrofit.R;
import com.ananna.retrofit.model.AndroidVersion;

import java.util.ArrayList;

/**
 * Created by Ananna on 28-Nov-17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private ArrayList<AndroidVersion> androidVersions;

    public DataAdapter(ArrayList<AndroidVersion> androidVersions){
        this.androidVersions =androidVersions;
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
holder.tvVer.setText("");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
 TextView tvVer;
        public ViewHolder(View itemView) {
            super(itemView);
            tvVer = itemView.findViewById(R.id.textViewVersion);
        }
    }
}
