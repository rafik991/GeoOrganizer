package pl.rafik.geoorganizer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import com.dropbox.sync.android.DbxException;
import pl.rafik.geoorganizer.model.entity.TaskOpenHelper;
import pl.rafik.geoorganizer.services.localisation.MyBestLocation;
import pl.rafik.geoorganizer.services.nofication.NotificationHelper;
import pl.rafik.geoorganizer.services.proximity.SchedulerFactory;
import pl.rafik.geoorganizer.services.proximity.ProximityUtil;

/**
 * rafik991@gmail.com
 * 1/10/14
 */
public class LocalisationUpdatesReceiver extends BroadcastReceiver {
    private ProximityUtil proximityUtil;
    private SchedulerFactory schedulerFactory;
    private MyBestLocation myBestLocation = new MyBestLocation();
    private SharedPreferences sharedPreferences;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        schedulerFactory = new SchedulerFactory(context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        MyBestLocation.LocationResult locationResult = new MyBestLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                try {
                    if (location != null) {
                        ProximityUtil proximityUtil1 = new ProximityUtil(location, context);
                        proximityUtil1.updateLastClosestData();
                        schedulerFactory.setUpScheduledService(proximityUtil1.getCurrentUpdateTime());
                        if (proximityUtil1.shouldFireNotification())
                            handleNotification(context, intent);
                    } else
                        throw new Exception("Sth is wrong");
                } catch (DbxException e) {
                    Log.e("LOCALISATION_UPDATES_RECEIVER", "Dbx have fault! Broadcast receiver is shutdown!");
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.e("LOCALISATION_UPDATES_RECEIVER", "Localisation wasn't found! Broadcast receiver is shutdown!");
                    e.printStackTrace();
                }
            }
        };
        myBestLocation.getLocation(context, locationResult);
    }

    private void handleNotification(Context context, Intent intent) throws DbxException {
        NotificationHelper notificationHandler = new NotificationHelper();
        Bundle bundle = intent.getExtras();
        if (proximityUtil.isEntering())
            bundle.putString(TaskOpenHelper.ID, sharedPreferences.getString(proximityUtil.LAST_MIN_DIST_TASK_ID, ""));
        notificationHandler.runNotification(context, intent);

    }
}
