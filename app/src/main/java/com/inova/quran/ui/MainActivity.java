package com.inova.quran.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inova.quran.R;
import com.inova.quran.ViewPager.DownloadingActivity;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.tryDownload.DownloadService;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    QuranViewModel quranViewModel;
    RecyclerView recyclerView;
    QuranAdapter quranAdapter;
    Button downloadingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadingBtn = findViewById(R.id.Downloading_btn);
        downloadingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownloadingActivity.class);
                startActivity(intent);
            }
        });

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

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent(MainActivity.this, DownloadService.class);
//        startService(intent);

//        bindService(intent, )
    }
}
