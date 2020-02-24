package com.inova.quran.ViewPager;
import android.content.Context;
import android.graphics.Color;
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
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-19.
 */
public class AllAyahAdapter extends RecyclerView.Adapter<AllAyahAdapter.AllAyahViewHolder> {

    private List<AyahModel> ayahModels;
    private Context context;
    private String dirPath;
    private int downloadIdOne;

    private final String URL1 = "http://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/";

    AyahAdapter ayahAdapter = new AyahAdapter();
    public int flag = 0;


    @NonNull
    @Override
    public AllAyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_fragment, parent, false);
        return new AllAyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllAyahViewHolder holder, int position) {

        final AyahModel ayahModel = ayahModels.get(position);
        holder.allAyahText.setText(ayahModel.getText());

        dirPath = Utils.getRootDirPath(context);


        final String URL = URL1+ayahModel.getNumberInSurah();

        holder.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Status.RUNNING == PRDownloader.getStatus(ayahAdapter.downloadIdOne)) {
                    PRDownloader.pause(ayahAdapter.downloadIdOne);
                    return;
                }
                holder.buttonOne.setEnabled(false);
                holder.progressBarOne.setIndeterminate(true);
                holder.progressBarOne.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(ayahAdapter.downloadIdOne)) {
                    PRDownloader.resume(ayahAdapter.downloadIdOne);
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
                                ayahAdapter.removeFromDownloading(context, ayahModel);
                                //\\//\\
//                                startDownloading(URL);
                                //\\//\\
//                                flag = 0;
//                                addAllAyah(context, ayahModel);
//                                addToDownloading(context, ayahModel);
                            }
                        }).setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                holder.buttonOne.setText(R.string.resume);
                            }
                        }).setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                holder.buttonOne.setText(R.string.start);
                                holder.buttonCancelOne.setEnabled(false);
                                holder.progressBarOne.setProgress(0);
                                holder.textViewProgressOne.setText("");
                                downloadIdOne = 0;
                                holder.progressBarOne.setIndeterminate(false);
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
                            public void onDownloadComplete() {
                                holder.buttonOne.setEnabled(false);
                                holder.buttonCancelOne.setEnabled(false);
                                holder.buttonOne.setText(R.string.completed);
                                ayahAdapter.addDownloadedAyah(context, ayahModel);
                                ayahAdapter.removeFromDownloading(context, ayahModel);

//                                ArrayList<AyahModel> favorites = ayahAdapter.getDownloadingAyahs(context);
//                                if (favorites != null) {
//                                    favorites.remove(ayahModel);
//                                    ayahAdapter.saveFavorites(context, favorites);
//                                }
                                flag = 1;
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

                            }
                        });
            }
        });

    }

    public void setList(List<AyahModel> ayahModels){
        this.ayahModels = ayahModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d("zxc", "SIZE : : "+ayahModels.size());
        return ayahModels.size();
    }

    public class AllAyahViewHolder extends RecyclerView.ViewHolder {
        TextView allAyahText,textViewProgressOne;
        Button buttonOne,buttonCancelOne;
        ProgressBar progressBarOne;

        public AllAyahViewHolder(@NonNull View itemView) {
            super(itemView);

            allAyahText = itemView.findViewById(R.id.all_ayah_text);
            textViewProgressOne = itemView.findViewById(R.id.textViewProgressOneDownloading);
            buttonOne = itemView.findViewById(R.id.buttonOneDownloading);
            buttonCancelOne = itemView.findViewById(R.id.buttonCancelDownloading);
            progressBarOne = itemView.findViewById(R.id.progressBarDownloading);
        }
    }
}
