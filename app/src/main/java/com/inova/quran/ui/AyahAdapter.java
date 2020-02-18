package com.inova.quran.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;
import com.inova.quran.pojo.SurahModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-18.
 */
public class AyahAdapter extends RecyclerView.Adapter<AyahAdapter.AyahViewHolder> {
    private List<AyahModel> ayahModels;
    private Context context;

    @NonNull
    @Override
    public AyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.ayah_item, parent, false);
        return new  AyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyahViewHolder holder, int position) {

        AyahModel ayahModel = ayahModels.get(position);
        holder.ayahText.setText(ayahModel.getText()+" "+ ayahModel.getNumberInSurah());

    }

    public void setList(List<AyahModel> ayahModels){
        this.ayahModels = ayahModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ayahModels.size();
    }

    public class AyahViewHolder extends RecyclerView.ViewHolder {

        TextView ayahText;

        public AyahViewHolder(@NonNull View itemView) {
            super(itemView);
            ayahText = itemView.findViewById(R.id.ayahText);
        }
    }
}
