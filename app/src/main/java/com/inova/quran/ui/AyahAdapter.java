package com.inova.quran.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
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
import com.google.gson.Gson;
import com.inova.quran.R;
import com.inova.quran.pojo.AyahModel;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.tryDownload.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-18.
 */
public class AyahAdapter extends RecyclerView.Adapter<AyahAdapter.AyahViewHolder> {
    private List<AyahModel> ayahModels;
    private Context context;
    private String dirPath;
    private int downloadIdOne;
    private final String URL1 = "http://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/";
    public int flag;


    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public static final String PREFS_NAME2 = "PRODUCT_APP2";
    public static final String FAVORITES2 = "Product_Favorite2";

    public static final String PREFS_NAME3 = "PRODUCT_APP3";
    public static final String FAVORITES3 = "Product_Favorite3";

    @NonNull
    @Override
    public AyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.ayah_item, parent, false);
        return new  AyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AyahViewHolder holder, int position) {

        final AyahModel ayahModel = ayahModels.get(position);
        holder.ayahText.setText(ayahModel.getText()+" "+ ayahModel.getNumberInSurah());

        final String URL = URL1+ayahModel.getNumberInSurah();

        dirPath = Utils.getRootDirPath(context);

        holder.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                startDownloading(URL);
                                //\\//\\
                                flag = 0;
                                addAllAyah(context, ayahModel);
                                addToDownloading(context, ayahModel);
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
                                flag = 2;
                                removeFromALL(context, ayahModel);
                                removeFromDownloaded(context, ayahModel);
                                removeFromDownloading(context, ayahModel);
                            }
                        }).setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                holder.progressBarOne.setProgress((int) progressPercent);
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
                                flag = 1;
                                if (checkAllItem(ayahModel)){
                                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    addAllAyah(context, ayahModel);
                                }
                                addDownloadedAyah(context, ayahModel);
                                removeFromDownloading(context, ayahModel);
                            }

                            @Override
                            public void onError(Error error) {
                               holder.buttonOne.setText(R.string.start);
//                               Toast.makeText(context, error.getServerErrorMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, context.getString(R.string.some_error_occurred) + " " + "1", Toast.LENGTH_SHORT).show();
                                holder.textViewProgressOne.setText("");
                                holder.progressBarOne.setProgress(0);
                                downloadIdOne = 0;
                                holder.buttonCancelOne.setEnabled(false);
                                holder.progressBarOne.setIndeterminate(false);
                                holder.buttonOne.setEnabled(true);
                                flag = 2;
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
            }
        });
        holder.buttonCancelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRDownloader.cancel(downloadIdOne);
            }
        });

//        if (checkFavoriteItem(ayahModel)){
//            Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//        }else{

//            if (flag == 0){ // add all , add downloading
//
//                if (checkAllItem(ayahModel)){
//                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    addAllAyah(context,ayahModel);
//                }
//                if (checkDownloadingItem(ayahModel)){
//                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                }else{
//                    addToDownloading(context,ayahModel);
//                }
//            }else if (flag == 1){ // add all , add downloaded , remove downloading
//
//
//                if (checkAllItem(ayahModel)){
//                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    addAllAyah(context,ayahModel);
//                }
//                if (checkDownloadedItem(ayahModel)){
//                    Toast.makeText(context, "Ayah already downloaded ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    addDownloadedAyah(context, ayahModel);
//                }
//                removeFromDownloading(context, ayahModel);
//
//            }else { // remove from all , downloading , downloaded
//                removeFromDownloading(context, ayahModel);
//                removeFromALL(context, ayahModel);
//                removeFromDownloaded(context, ayahModel);
//            }
//        }

    }


    public void setList(List<AyahModel> ayahModels){
        this.ayahModels = ayahModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ayahModels.size();
    }

    public class AyahViewHolder extends RecyclerView.ViewHolder {

        TextView ayahText,textViewProgressOne;
        Button buttonOne,buttonCancelOne;
        ProgressBar progressBarOne;


        public AyahViewHolder(@NonNull View itemView) {
            super(itemView);
            ayahText = itemView.findViewById(R.id.ayahText);
            textViewProgressOne = itemView.findViewById(R.id.text__ViewProgressOne);
            buttonOne = itemView.findViewById(R.id.button__One);
            buttonCancelOne = itemView.findViewById(R.id.button__CancelOne);
            progressBarOne = itemView.findViewById(R.id.progress_BarOne);

        }
    }

    public void addToDownloading(Context context, AyahModel ayah) {
        List<AyahModel> ayahs = getDownloadingAyahs(context);
        if (ayahs == null)
            ayahs = new ArrayList<AyahModel>();
        ayahs.add(ayah);
//        if (flag == 0){
//            saveAllAyahs(context, ayahs);
//        }else if (flag == 1){
//
//        }else{
//
//        }
        saveFavorites(context, ayahs);
    }

    public ArrayList<AyahModel> getDownloadingAyahs(Context context) {
        SharedPreferences settings;
        List<AyahModel> ayahs;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();

            AyahModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    AyahModel[].class);
            ayahs = Arrays.asList(favoriteItems);
            ayahs = new ArrayList<AyahModel>(ayahs);

        } else
            return null;

        return (ArrayList<AyahModel>) ayahs;

    }

    public boolean checkDownloadingItem(AyahModel checkAyah) {
        boolean check = false;
        List<AyahModel> ayahs = getDownloadingAyahs(context);
        if (ayahs != null) {
            for (AyahModel ayah : ayahs) {
                if (ayah.equals(checkAyah)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public void removeFromDownloading(Context context, AyahModel ayah) {
        ArrayList<AyahModel> favorites = getDownloadingAyahs(context);
        if (favorites != null) {
            favorites.remove(ayah);
            saveFavorites(context, favorites);
        }
    }

    public void saveFavorites(Context context, List<AyahModel> ayahs) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(ayahs);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    // ALL
    public void saveAllAyahs(Context context, List<AyahModel> ayahs) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME2,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(ayahs);
        editor.putString(FAVORITES2, jsonFavorites);
        editor.commit();
    }

    public ArrayList<AyahModel> getAllAyahs(Context context) {
        SharedPreferences settings;
        List<AyahModel> ayahs;

        settings = context.getSharedPreferences(PREFS_NAME2,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES2)) {
            String jsonFavorites = settings.getString(FAVORITES2, null);
            Gson gson = new Gson();

            AyahModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    AyahModel[].class);
            ayahs = Arrays.asList(favoriteItems);
            ayahs = new ArrayList<AyahModel>(ayahs);

        } else
            return null;

        return (ArrayList<AyahModel>) ayahs;

    }

    public void addAllAyah(Context context, AyahModel ayah) {
        List<AyahModel> ayahs = getAllAyahs(context);
        if (ayahs == null)
            ayahs = new ArrayList<AyahModel>();
        ayahs.add(ayah);
        saveAllAyahs(context, ayahs);
    }

    public void removeFromALL(Context context, AyahModel ayah) {
        ArrayList<AyahModel> favorites = getAllAyahs(context);
        if (favorites != null) {
            favorites.remove(ayah);
            saveAllAyahs(context, favorites);
        }
    }
    public boolean checkAllItem(AyahModel checkAyah) {
        boolean check = false;
        List<AyahModel> ayahs = getAllAyahs(context);
        if (ayahs != null) {
            for (AyahModel ayah : ayahs) {
                if (ayah.equals(checkAyah)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    //Downloaded

    public void saveDownloadedAyahs(Context context, List<AyahModel> ayahs) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME3,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(ayahs);

        editor.putString(FAVORITES3, jsonFavorites);
        editor.commit();
    }

    public ArrayList<AyahModel> getDownloadedAyahs(Context context) {
        SharedPreferences settings;
        List<AyahModel> ayahs;

        settings = context.getSharedPreferences(PREFS_NAME3,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES3)) {
            String jsonFavorites = settings.getString(FAVORITES3, null);
            Gson gson = new Gson();

            AyahModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    AyahModel[].class);
            ayahs = Arrays.asList(favoriteItems);
            ayahs = new ArrayList<AyahModel>(ayahs);

        } else
            return null;

        return (ArrayList<AyahModel>) ayahs;

    }

    public void addDownloadedAyah(Context context, AyahModel ayah) {
        List<AyahModel> ayahs = getDownloadedAyahs(context);
        if (ayahs == null)
            ayahs = new ArrayList<AyahModel>();
        ayahs.add(ayah);
        saveDownloadedAyahs(context, ayahs);
    }

    public void removeFromDownloaded(Context context, AyahModel ayah) {
        ArrayList<AyahModel> favorites = getDownloadedAyahs(context);
        if (favorites != null) {
            favorites.remove(ayah);
            saveDownloadedAyahs(context, favorites);
        }
    }
    public boolean checkDownloadedItem(AyahModel checkAyah) {
        boolean check = false;
        List<AyahModel> ayahs = getDownloadedAyahs(context);
        if (ayahs != null) {
            for (AyahModel ayah : ayahs) {
                if (ayah.equals(checkAyah)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
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
