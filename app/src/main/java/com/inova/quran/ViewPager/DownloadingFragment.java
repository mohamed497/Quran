package com.inova.quran.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;
import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;
import com.inova.quran.tryDownload.Utils;
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
public class DownloadingFragment extends Fragment {

//    private Button buttonOne,buttonCancelOne;
//    private TextView textViewProgressOne,downloadingSurah;
//    private ProgressBar progressBarOne;
//    private int downloadIdOne;
//    private static String dirPath;
//    private final String URL1 = "http://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/319";



    private AyahAdapter ayahAdapter;
    private List<AyahModel> ayahModels;
    private List<AyahModel> ayahlList;
    DownloadedAyahAdapter downloadedAyahAdapter;
    RecyclerView recyclerView;
    AllAyahAdapter allAyahAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_downloading, container, false);

        ayahlList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.downloading_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ayahAdapter = new AyahAdapter();
        ayahModels = ayahAdapter.getDownloadingAyahs(getContext());
//        if (ayahModels != null){
//            Log.d("zxc", "NAME OF downloading AYAH : "+ayahModels.get(0).getText());
//        }

        if (ayahModels != null && ayahModels.size()>0){
            Log.d("zxc", "NAME OF Downloading AYAH : "+ayahModels.get(0).getText());
            for (int i= 0 ; i<ayahModels.size(); i++){
                if (ayahModels.get(i) != null && ayahAdapter.flag == 0){
                    String name = ayahModels.get(i).getText();
                    ayahlList.add(new AyahModel(name));
//                    downloadedAyahAdapter = new DownloadedAyahAdapter();
//                    downloadedAyahAdapter.setList(ayahlList);
//                    recyclerView.setAdapter(downloadedAyahAdapter);
                    allAyahAdapter = new AllAyahAdapter();
                    allAyahAdapter.setList(ayahlList);
                    recyclerView.setAdapter(allAyahAdapter);
                }
                else{
                    ayahAdapter.removeFromDownloading(getContext(),ayahModels.get(i));
                }
            }
        }




        return view;
    }

//    private void onClickListenerOne() {
//        buttonOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
//                    PRDownloader.pause(downloadIdOne);
//                    return;
//                }
//
//                buttonOne.setEnabled(false);
//                progressBarOne.setIndeterminate(true);
//                progressBarOne.getIndeterminateDrawable().setColorFilter(
//                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
//
//                if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
//                    PRDownloader.resume(downloadIdOne);
//                    return;
//                }
//
//                downloadIdOne = PRDownloader.download(URL1, dirPath, "Quran")
//                        .build()
//                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
//                            @Override
//                            public void onStartOrResume() {
//                                progressBarOne.setIndeterminate(false);
//                                buttonOne.setEnabled(true);
//                                buttonOne.setText(R.string.pause);
//                                buttonCancelOne.setEnabled(true);
////                                startDownloading(URL1);
//                            }
//                        })
//                        .setOnPauseListener(new OnPauseListener() {
//                            @Override
//                            public void onPause() {
//                                buttonOne.setText(R.string.resume);
//
//                            }
//                        })
//                        .setOnCancelListener(new OnCancelListener() {
//                            @Override
//                            public void onCancel() {
//                                buttonOne.setText(R.string.start);
//                                buttonCancelOne.setEnabled(false);
//                                progressBarOne.setProgress(0);
//                                textViewProgressOne.setText("");
//                                downloadIdOne = 0;
//                                progressBarOne.setIndeterminate(false);
//                            }
//                        }).setOnProgressListener(new OnProgressListener() {
//                            @Override
//                            public void onProgress(Progress progress) {
//                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
//                                progressBarOne.setProgress((int) progressPercent);
//                                textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
//                                progressBarOne.setIndeterminate(false);
//                            }
//                        })
//                        .start(new OnDownloadListener() {
//                            @Override
//                            public void onDownloadComplete() { // Completed!
//                                buttonOne.setEnabled(false);
//                                buttonCancelOne.setEnabled(false);
//                                buttonOne.setText(R.string.completed);
//                            }
//
//                            @Override
//                            public void onError(Error error) {
//                                buttonOne.setText(R.string.start);
//                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "1", Toast.LENGTH_SHORT).show();
//                                textViewProgressOne.setText("");
//                                progressBarOne.setProgress(0);
//                                downloadIdOne = 0;
//                                buttonCancelOne.setEnabled(false);
//                                progressBarOne.setIndeterminate(false);
//                                buttonOne.setEnabled(true);
//                            }
//
//                        });
//            }
//        });
//        buttonCancelOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PRDownloader.cancel(downloadIdOne);
//            }
//        });
//
//    }
}
