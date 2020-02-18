package com.inova.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.inova.quran.pojo.AyahModel;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.ui.AyahAdapter;

import java.util.ArrayList;
import java.util.List;

public class AyahActivity extends AppCompatActivity {

    SurahModel surahModel;
//    TextView ayahText;
    List<AyahModel> ayahModels;
    RecyclerView recyclerView;
    AyahAdapter ayahAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayah);

        recyclerView = findViewById(R.id.ayah_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


//        ayahText =findViewById(R.id.Ayah_id);
        ayahModels = new ArrayList<>();
        Intent intent = getIntent();
        if(intent != null){
           String x = intent.getStringExtra("TEXT");
           surahModel = intent.getParcelableExtra("SURAH");
            Log.d("zxc", "AYAH == "+x);
            Log.d("zxc", "AYAH == "+surahModel.getEnglishName());

            for (int i =0 ; i< surahModel.ayahs.size(); i++){
                ayahModels.add(new AyahModel(surahModel.ayahs.get(i).getText()));
//                ayahText.setText(ayahModels.);
            }

        }

        ayahAdapter = new AyahAdapter();
        ayahAdapter.setList(surahModel.ayahs);
        recyclerView.setAdapter(ayahAdapter);
    }
}
