package com.inova.quran.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.inova.quran.R;
import com.inova.quran.pojo.SurahModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    QuranViewModel quranViewModel;
    RecyclerView recyclerView;
    QuranAdapter quranAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.quran_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        quranViewModel = ViewModelProviders.of(this).get(QuranViewModel.class);
        quranViewModel.getSurah();
        quranViewModel.surahMutableLiveData.observe(this, new Observer<List<SurahModel>>() {
            @Override
            public void onChanged(List<SurahModel> surahModels) {
                if(surahModels != null){
                    quranAdapter = new QuranAdapter();
                    quranAdapter.setList(surahModels);
                    recyclerView.setAdapter(quranAdapter);
                }

            }
        });

    }
}
