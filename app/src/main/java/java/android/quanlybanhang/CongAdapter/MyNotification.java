package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import java.android.quanlybanhang.Activity.DangkyKhachHang;

public class MyNotification extends NotificationListenerService {
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // We can read notification while posted.
        for (StatusBarNotification sbm : MyNotification.this.getActiveNotifications()) {
            String title = sbm.getNotification().extras.getString("android.title");
            String text = sbm.getNotification().extras.getString("android.text");
            String package_name = sbm.getPackageName();
//            Toast.makeText(context, title +"abc",Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, text+"abc",Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, package_name+"abcxyz",Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "abc",Toast.LENGTH_SHORT).show();

            if(text!=null && text.equals("alo"))
            {
                Intent intent=new Intent(getApplicationContext(), DangkyKhachHang.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
}
