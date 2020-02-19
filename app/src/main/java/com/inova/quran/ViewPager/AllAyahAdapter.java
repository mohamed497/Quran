package com.inova.quran.ViewPager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;
import com.inova.quran.ui.AyahAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-19.
 */
public class AllAyahAdapter extends RecyclerView.Adapter<AllAyahAdapter.AllAyahViewHolder> {

    private List<AyahModel> ayahModels;
    private Context context;

    @NonNull
    @Override
    public AllAyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_fragment, parent, false);
        return new AllAyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAyahViewHolder holder, int position) {

        AyahModel ayahModel = ayahModels.get(position);
        holder.allAyahText.setText(ayahModel.getText());

    }

    public void setList(List<AyahModel> ayahModels){
        this.ayahModels = ayahModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d("zxc", "SIZE : :"+ayahModels.size());
        return ayahModels.size();
    }

    public class AllAyahViewHolder extends RecyclerView.ViewHolder {
        TextView allAyahText;
        public AllAyahViewHolder(@NonNull View itemView) {
            super(itemView);
            allAyahText = itemView.findViewById(R.id.all_ayah_text);
        }
    }
}
