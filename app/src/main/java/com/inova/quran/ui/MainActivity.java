package com.inova.quran.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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

    DownloadService mDownloadService;

    boolean mServiceBound = false;


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

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.MyBinder myBinder = (DownloadService.MyBinder) service;
            mDownloadService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;

        }
    };

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent(MainActivity.this, DownloadService.class);
//        startService(intent);

//        bindService(intent, )

        Intent intent = new Intent(MainActivity.this, DownloadService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }
}
