package java.android.quanlybanhang.CongAdapter;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.android.quanlybanhang.R;

public class Putnotification extends Application {


    public static final String CHANNEL_ID = "DongA";

    public Putnotification() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CongBui";
            String description = "abc xyz";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager!=null)
            {
                notificationManager.createNotificationChannel(channel);
            }

        }
    }
}
