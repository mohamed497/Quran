package com.inova.quran.tryDownload;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
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
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

@RequiresApi(api = Build.VERSION_CODES.O)

public class DownloadService extends LifecycleService {

    boolean state;
    boolean check;
    int progress;
    String size;
    RoomViewModel roomViewModel;
    String dirPath;
    int downloadIdOne;

    private IBinder mBinder = new MyBinder();

    public static final String CHANNEL_ID = "ForegroundServiceChannel";


    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        roomViewModel = new RoomViewModel(getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        roomViewModel.getmAllAyahs().observe(this, new Observer<List<AyahRoomModel>>() {
            @Override
            public void onChanged(List<AyahRoomModel> ayahRoomModels) {
                if(ayahRoomModels != null &&  ayahRoomModels.size() >0){
                    Log.d("service_log", "LIFE_CYCLE_SERVICE"+ayahRoomModels.get(0).text);
                    ayahRoomModels.get(0).state = true;

//                    roomViewModel.update();
                }
            }
        });


        // check (state && check)

        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Downloading...")
                .setContentText("Ayah Text")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        return START_NOT_STICKY;
//        return super.onStartCommand(intent, flags, startId);

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

//        throw new UnsupportedOperationException("Not yet implemented");
        Log.v("zxc", "in onBind");

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("zxc", "in onUnbind");
        stopForeground(true);
        return true;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        stopForeground(true);
//    }

    //    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        return super.onStartCommand(intent, flags, startId);
//
//        super.onStartCommand(intent, flags, startId);
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent =
//                PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//
//        Notification notification = new Notification.Builder(this, "channel_ID")
//                .setContentTitle(getText(R.string.download))
//                .setContentText(getText(R.string.download))
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentIntent(pendingIntent)
//                .setTicker(getText(R.string.start))
//                .build();
//
//        startForeground(1, notification);
//        return START_STICKY;
//    }

    public class MyBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
