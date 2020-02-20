package com.inova.quran.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;
import com.inova.quran.ui.AyahAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-18.
 */
public class DownloadedFragment extends Fragment {

    private AyahAdapter ayahAdapter;
    private List<AyahModel> ayahModels;
    private List<AyahModel> ayahlList;
    DownloadedAyahAdapter downloadedAyahAdapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloaded, container, false);

        ayahlList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.downloaded_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ayahAdapter = new AyahAdapter();

//        ayahModels = ayahAdapter.getDownloadedAyahs(getContext());
        ayahModels = ayahAdapter.getDownloadedAyahs(getContext());
        if (ayahModels != null){
            Log.d("zxc", "NAME OF Downloaded AYAH : "+ayahModels.get(0).getText());
            for (int i= 0 ; i<ayahModels.size(); i++){
                if (ayahModels.get(i) != null){
                    String name = ayahModels.get(i).getText();
                    ayahlList.add(new AyahModel(name));
                    downloadedAyahAdapter = new DownloadedAyahAdapter();
                    downloadedAyahAdapter.setList(ayahlList);

                    recyclerView.setAdapter(downloadedAyahAdapter);
                }
            }
        }


        return view;
    }
}
