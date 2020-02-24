package com.inova.quran.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
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
import com.inova.quran.AyahActivity;
import com.inova.quran.R;
import com.inova.quran.ViewPager.DownloadingActivity;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.tryDownload.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.QuranViewHolder> {

    private List<SurahModel> surahModels;
    private Context context;
    private final String URL1 = "http://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/";
    private String dirPath;
    private int downloadIdOne;
    AyahAdapter ayahAdapter = new AyahAdapter();
    int i;

    @NonNull
    @Override
    public QuranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.quran_item, parent, false);
        return new QuranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuranViewHolder holder, int position) {

        final SurahModel surah = surahModels.get(position);

//        dirPath = Utils.getRootDirPath(context);

//        final String URL = URL1+surah.ayahs.get(0).getNumberInSurah();
        dirPath = Utils.getRootDirPath(context);
        holder.ayahText.setText(surah.getName());

//        final int finalI = i;
//        holder.buttonOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
//                    PRDownloader.pause(downloadIdOne);
//                    return;
//                }
//                holder.buttonOne.setEnabled(false);
//                holder.progressBarOne.setIndeterminate(true);
//                holder.progressBarOne.getIndeterminateDrawable().setColorFilter(
//                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
//
//                if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
//                    PRDownloader.resume(downloadIdOne);
//                    return;
//                }
//
//                downloadIdOne = PRDownloader.download(URL, dirPath, "Quran")
//                        .build()
//                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
//                            @Override
//                            public void onStartOrResume() {  // flag = 0
//                                holder.progressBarOne.setIndeterminate(false);
//                                holder.buttonOne.setEnabled(true);
//                                holder.buttonOne.setText(R.string.pause);
//                                holder.buttonCancelOne.setEnabled(true);
//                                //\\//\\
//                                startDownloading(URL);
//                                //\\//\\
//
//                                ayahAdapter.addAllAyah(context, surah.ayahs.get(0));
//                                ayahAdapter. addToDownloading(context, surah.ayahs.get(0));
//                            }
//                        }).setOnPauseListener(new OnPauseListener() {
//                            @Override
//                            public void onPause() {
//                                holder.buttonOne.setText(R.string.resume);
//
//                            }
//                        }).setOnCancelListener(new OnCancelListener() {
//                            @Override
//                            public void onCancel() {  // flag = 2
//                                holder.buttonOne.setText(R.string.start);
//                                holder.buttonCancelOne.setEnabled(false);
//                                holder.progressBarOne.setProgress(0);
//                                holder.textViewProgressOne.setText("");
//                                downloadIdOne = 0;
//                                holder.progressBarOne.setIndeterminate(false);
//                                //\\//\\
////                                    flag = 2;
//                                ayahAdapter.removeFromALL(context, surah.ayahs.get(0));
//                                ayahAdapter.removeFromDownloaded(context, surah.ayahs.get(0));
//                                ayahAdapter.removeFromDownloading(context, surah.ayahs.get(0));
//                            }
//                        }).setOnProgressListener(new OnProgressListener() {
//                            @Override
//                            public void onProgress(Progress progress) {
//                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
//                                holder.progressBarOne.setProgress((int) progressPercent);
//                                holder.textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
//                                holder.progressBarOne.setIndeterminate(false);
//                            }
//                        }).start(new OnDownloadListener() {
//                            @Override
//                            public void onDownloadComplete() {     //completed flag = 1
//                                holder.buttonOne.setEnabled(false);
//                                holder.buttonCancelOne.setEnabled(false);
//                                holder.buttonOne.setText(R.string.completed);
//                                //\\//\\
////                                    flag = 1;
////                                if (ayahAdapter.checkAllItem(surah.ayahs.get(0))){
//////                                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
////                                }
////                                else{
//                                    ayahAdapter.addAllAyah(context, surah.ayahs.get(0));
////                                }
//                                ayahAdapter.addDownloadedAyah(context, surah.ayahs.get(0));
//                                ayahAdapter.removeFromDownloading(context, surah.ayahs.get(0));
//                            }
//
//                            @Override
//                            public void onError(Error error) {
//                                holder.buttonOne.setText(R.string.start);
////                               Toast.makeText(context, error.getServerErrorMessage(), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(context, context.getString(R.string.some_error_occurred) + " " + "1", Toast.LENGTH_SHORT).show();
//                                holder.textViewProgressOne.setText("");
//                                holder.progressBarOne.setProgress(0);
//                                downloadIdOne = 0;
//                                holder.buttonCancelOne.setEnabled(false);
//                                holder.progressBarOne.setIndeterminate(false);
//                                holder.buttonOne.setEnabled(true);
//                            }
//                        });
////                if (flag == 0){ // add all , add downloading
////
////                    if (checkAllItem(ayahModel)){
////                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
////                    }
////                    else {
////                        addAllAyah(context,ayahModel);
////                    }
////                    if (checkDownloadingItem(ayahModel)){
////                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
////                    }else{
////                        addToDownloading(context,ayahModel);
////                    }
////                }else if (flag == 1){ // add all , add downloaded , remove downloading
////
////                    if (checkAllItem(ayahModel)){
////                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
////                    }
////                    else {
////                        addAllAyah(context,ayahModel);
////                    }
////                    if (checkDownloadedItem(ayahModel)){
////                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
////                    }
////                    else {
////                        addDownloadedAyah(context, ayahModel);
////                    }
////                    removeFromDownloading(context, ayahModel);
////
////                }else { // remove from all , downloading , downloaded
////                    removeFromDownloading(context, ayahModel);
////                    removeFromALL(context, ayahModel);
////                    removeFromDownloaded(context, ayahModel);
////                }
//            }
//        });
//        holder.buttonCancelOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PRDownloader.cancel(downloadIdOne);
//            }
//        });



            holder.buttonOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (i = 0; i<surah.ayahs.size()-1; i++){
//                        Log.d("zxc", surah.ayahs.)
                        downloadIdOne = 0;
                        final String URL = URL1+surah.ayahs.get(i).getNumberInSurah();
                    if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
                        PRDownloader.pause(downloadIdOne);
                        return;
                    }
                    holder.buttonOne.setEnabled(false);
                    holder.progressBarOne.setIndeterminate(true);
                    holder.progressBarOne.getIndeterminateDrawable().setColorFilter(
                            Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                    if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
                        PRDownloader.resume(downloadIdOne);
                        return;
                    }

                    downloadIdOne = PRDownloader.download(URL, dirPath, "Quran")
                            .build()
                            .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                @Override
                                public void onStartOrResume() {  // flag = 0
                                    holder.progressBarOne.setIndeterminate(false);
                                    holder.buttonOne.setEnabled(true);
                                    holder.buttonOne.setText(R.string.pause);
                                    holder.buttonCancelOne.setEnabled(true);
                                    //\\//\\
//                                    startDownloading(URL);
                                    //\\//\\

                                    ayahAdapter.addAllAyah(context, surah.ayahs.get(i));
                                    ayahAdapter.addToDownloading(context, surah.ayahs.get(i));
                                }
                            }).setOnPauseListener(new OnPauseListener() {
                                @Override
                                public void onPause() {
                                    holder.buttonOne.setText(R.string.resume);

                                }
                            }).setOnCancelListener(new OnCancelListener() {
                                @Override
                                public void onCancel() {  // flag = 2
                                    holder.buttonOne.setText(R.string.start);
                                    holder.buttonCancelOne.setEnabled(false);
                                    holder.progressBarOne.setProgress(0);
                                    holder.textViewProgressOne.setText("");
                                    downloadIdOne = 0;
                                    holder.progressBarOne.setIndeterminate(false);
                                    //\\//\\
//                                    flag = 2;
                                    ayahAdapter.removeFromALL(context, surah.ayahs.get(i));
                                    ayahAdapter.removeFromDownloaded(context, surah.ayahs.get(i));
                                    ayahAdapter.removeFromDownloading(context, surah.ayahs.get(i));
                                }
                            }).setOnProgressListener(new OnProgressListener() {
                                @Override
                                public void onProgress(Progress progress) {

                                    //progress loading line
                                    long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                    holder.progressBarOne.setProgress((int) progressPercent);

                                    // to get file size
                                    holder.textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                    holder.progressBarOne.setIndeterminate(false);
                                }
                            }).start(new OnDownloadListener() {
                                @Override
                                public void onDownloadComplete() {     //completed flag = 1
                                    holder.buttonOne.setEnabled(false);
                                    holder.buttonCancelOne.setEnabled(false);
                                    holder.buttonOne.setText(R.string.completed);
                                    //\\//\\
//                                    flag = 1;
//                                if (ayahAdapter.checkAllItem(surah.ayahs.get(0))){
////                                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
                                    ayahAdapter.addAllAyah(context, surah.ayahs.get(i));
//                                }
                                    ayahAdapter.addDownloadedAyah(context, surah.ayahs.get(i));
                                    ayahAdapter.removeFromDownloading(context, surah.ayahs.get(i));
                                }

                                @Override
                                public void onError(Error error) {
                                    holder.buttonOne.setText(R.string.start);
//                               Toast.makeText(context, error.getServerErrorMessage(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(context, context.getString(R.string.some_error_occurred) + " " + "1", Toast.LENGTH_SHORT).show();
                                    error.getServerErrorMessage();
                                    holder.textViewProgressOne.setText("");
                                    holder.progressBarOne.setProgress(0);
                                    downloadIdOne = 0;
                                    holder.buttonCancelOne.setEnabled(false);
                                    holder.progressBarOne.setIndeterminate(false);
                                    holder.buttonOne.setEnabled(true);
                                }
                            });
//                if (flag == 0){ // add all , add downloading
//
//                    if (checkAllItem(ayahModel)){
//                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        addAllAyah(context,ayahModel);
//                    }
//                    if (checkDownloadingItem(ayahModel)){
//                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                    }else{
//                        addToDownloading(context,ayahModel);
//                    }
//                }else if (flag == 1){ // add all , add downloaded , remove downloading
//
//                    if (checkAllItem(ayahModel)){
//                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        addAllAyah(context,ayahModel);
//                    }
//                    if (checkDownloadedItem(ayahModel)){
//                        Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        addDownloadedAyah(context, ayahModel);
//                    }
//                    removeFromDownloading(context, ayahModel);
//
//                }else { // remove from all , downloading , downloaded
//                    removeFromDownloading(context, ayahModel);
//                    removeFromALL(context, ayahModel);
//                    removeFromDownloaded(context, ayahModel);
//                }
                }}
            });
            holder.buttonCancelOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PRDownloader.cancel(downloadIdOne);
                }
            });

//        }
//        holder.surahText.setText(surah.getName());
//
        holder.ayahText.setOnClickListener(new View.OnClickListener() {
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

//        holder.quarnBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(context, DownloadingActivity.class);
////                intent.putExtra("TRY", surah);
////                context.startActivity(intent);
//                for (int i= 0; i<surah.ayahs.size(); i++){
//                    startDownloading(URL1+surah.ayahs.get(i).getNumberInSurah());
//                    Log.d("zxc", "ALL : "+surah.ayahs.get(i).getText());
//                }
//            }
//        });

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
//        Button quarnBtn;
//        TextView surahText;
        TextView ayahText,textViewProgressOne;
        Button buttonOne,buttonCancelOne;
        ProgressBar progressBarOne;

        public QuranViewHolder(@NonNull View itemView) {
            super(itemView);
//            quarnBtn = itemView.findViewById(R.id.btn_download);
//            surahText = itemView.findViewById(R.id.quran_id);
            ayahText = itemView.findViewById(R.id.ayahText_);
            textViewProgressOne = itemView.findViewById(R.id.textViewProgressOne_);
            buttonOne = itemView.findViewById(R.id.buttonOne_);
            buttonCancelOne = itemView.findViewById(R.id.buttonCancelOne_);
            progressBarOne = itemView.findViewById(R.id.progressBarOne_);
        }
    }

    private void startDownloading(String url){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading File...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+System.currentTimeMillis());

        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
