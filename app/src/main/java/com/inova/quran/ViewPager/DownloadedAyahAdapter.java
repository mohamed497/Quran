package com.inova.quran.ViewPager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-19.
 */
public class DownloadedAyahAdapter extends RecyclerView.Adapter<DownloadedAyahAdapter.DownloadedAyahViewHolder> {

    private List<AyahModel> ayahModels;
    private Context context;


    @NonNull
    @Override
    public DownloadedAyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_downloaded_fragment, parent, false);
        return new DownloadedAyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadedAyahViewHolder holder, int position) {
        AyahModel ayahModel = ayahModels.get(position);
        holder.downloadAyah.setText(ayahModel.getText());
//        holder.progressBar.tint
    }

    @Override
    public int getItemCount() {
        Log.d("zxc", "DOWNLOADED SIZE : :"+ayahModels.size());
        return ayahModels.size();
    }

    public void setList(List<AyahModel> ayahModels){
        this.ayahModels = ayahModels;
        notifyDataSetChanged();
    }

    public class DownloadedAyahViewHolder extends RecyclerView.ViewHolder {
        TextView downloadAyah;
//        ProgressBar progressBar;
        public DownloadedAyahViewHolder(@NonNull View itemView) {
            super(itemView);
//            progressBar = itemView.findViewById(R.id.downloaded_progressBar);
            downloadAyah = itemView.findViewById(R.id.downloaded_ayah);

        }
    }
}
