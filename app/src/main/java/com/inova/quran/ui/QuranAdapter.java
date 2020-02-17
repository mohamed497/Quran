package com.inova.quran.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inova.quran.R;
import com.inova.quran.pojo.SurahModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.QuranViewHolder> {

    private List<SurahModel> surahModels;
    private Context context;


    @NonNull
    @Override
    public QuranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.quran_item, parent, false);
        return new QuranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuranViewHolder holder, int position) {

        SurahModel surah = surahModels.get(position);
        holder.surahText.setText(surah.getEnglishName());

    }

    @Override
    public int getItemCount() {
        return surahModels.size();
    }

    public void setList(List<SurahModel> surahModel){
        this.surahModels = surahModel;
        notifyDataSetChanged();
    }

    public class QuranViewHolder extends RecyclerView.ViewHolder {

        TextView surahText;

        public QuranViewHolder(@NonNull View itemView) {
            super(itemView);
            surahText = itemView.findViewById(R.id.quran_id);
        }
    }
}
