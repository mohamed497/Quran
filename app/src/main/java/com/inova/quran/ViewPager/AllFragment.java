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
public class AllFragment extends Fragment {

    RecyclerView recyclerView;
    AllAyahAdapter allAyahAdapter;
    private AyahAdapter ayahAdapter;
    private List<AyahModel> ayahModels;
    private List<AyahModel> ayalList;
    DownloadedAyahAdapter downloadedAyahAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);

        ayalList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.all_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        ayahAdapter = new AyahAdapter();
        ayahAdapter.getAllAyahs(getContext());

        ayahModels = ayahAdapter.getAllAyahs(getContext());
        if (ayahAdapter.getAllAyahs(getContext()) != null){
            Log.d("zxc", "NAME OF AYAH : "+ayahModels.get(0).getText());
            for (int i= 0 ; i<ayahModels.size(); i++){
                if (ayahModels.get(i) != null){
                    String name = ayahModels.get(i).getText();
                    ayalList.add(new AyahModel(name));
//                    allAyahAdapter = new AllAyahAdapter();
//                    allAyahAdapter.setList(ayalList);
                    downloadedAyahAdapter = new DownloadedAyahAdapter();
                    downloadedAyahAdapter.setList(ayalList);
                    recyclerView.setAdapter(downloadedAyahAdapter);
                }
            }
        }

        return view;
    }
}
