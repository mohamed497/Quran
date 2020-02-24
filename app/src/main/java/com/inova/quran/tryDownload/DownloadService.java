package com.inova.quran.tryDownload;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

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
import com.inova.quran.room.AyahRoomModel;
import com.inova.quran.room.RoomViewModel;
import com.inova.quran.ui.MainActivity;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

@RequiresApi(api = Build.VERSION_CODES.O)

public class DownloadService extends LifecycleService {

    boolean state;
    int progress;
    String size;
    RoomViewModel roomViewModel;
    String dirPath;
    int downloadIdOne;



    public DownloadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        roomViewModel.getmAllAyahs().observe(this, new Observer<List<AyahRoomModel>>() {
            @Override
            public void onChanged(List<AyahRoomModel> ayahRoomModels) {
                Log.d("service_log", "LIFE_CYCLE_SERVICE"+ayahRoomModels.get(0).text);
            }
        });

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        // ?
        super.onBind(intent);
        dirPath = Utils.getRootDirPath(getBaseContext());
        downloadIdOne = 0;
        if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.pause(downloadIdOne);
//            return;
        }

        if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
            PRDownloader.resume(downloadIdOne);
//            return;
        }

        downloadIdOne = PRDownloader.download("URL", dirPath, "Quran")
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                }).setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                }).setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                }).setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                    }
                }).start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {

//                        startForeground(0, notification);
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
//        return START_NOT_STICKY;


        throw new UnsupportedOperationException("Not yet implemented");
    }


//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        return super.onStartCommand(intent, flags, startId);
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent =
//                PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//
//        Notification notification = new Notification.Builder(this,"channel_ID")
//                .setContentTitle(getText(R.string.start))
//                .setContentTitle(getText(R.string.resume))
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentIntent(pendingIntent)
//                .setTicker(getText(R.string.start))
//                .build();
//
//        startForeground(1, notification);
//        return START_STICKY;
//    }
}
