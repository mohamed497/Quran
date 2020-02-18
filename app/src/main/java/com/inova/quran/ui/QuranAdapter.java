package com.inova.quran.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inova.quran.AyahActivity;
import com.inova.quran.R;
import com.inova.quran.ViewPager.DownloadingActivity;
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

        final SurahModel surah = surahModels.get(position);
        holder.surahText.setText(surah.getName());

        holder.surahText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AyahActivity.class);
                for (int i= 0; i<surah.ayahs.size(); i++){
                    intent.putExtra("TEXT", surah.ayahs.get(i).getText());
                    intent.putExtra("SURAH", surah);
                    Log.d("zxc", "ALL : "+surah.ayahs.get(i).getText());
                }
                context.startActivity(intent);
            }
        });

        holder.quarnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DownloadingActivity.class);
                intent.putExtra("TRY", surah);
                context.startActivity(intent);
            }
        });

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
        Button quarnBtn;
        TextView surahText;

        public QuranViewHolder(@NonNull View itemView) {
            super(itemView);
            quarnBtn = itemView.findViewById(R.id.btn_download);
            surahText = itemView.findViewById(R.id.quran_id);
        }
    }
}
