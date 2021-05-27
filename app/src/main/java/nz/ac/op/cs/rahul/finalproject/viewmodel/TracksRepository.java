package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.ItemDB;
import nz.ac.op.cs.rahul.finalproject.db.Tracks;
import nz.ac.op.cs.rahul.finalproject.db.TracksDao;

public class TracksRepository {

    private ItemDB itemDB;
    private TracksDao trackDao;
    private LiveData<List<Tracks>> mAllTracks;
    Context context;
    private static final String TAG = "TrackRepository";

    public TracksRepository(Context context) {

        itemDB = ItemDB.getDBInstance(context);
        trackDao = itemDB.getTrackDao();
        mAllTracks = trackDao.getAllTracks();
    }
    public LiveData<List<Tracks>> get_alltracks(){
        return mAllTracks;
    }


    public void save_track(Tracks track) {

        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    trackDao.insert(track);
                    Log.d(TAG, "run : saved track name => " + track.getTrackName());
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to save track name=> " + track.getTrackName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });

    }

    public LiveData<Tracks> get_pC(int id) {
        return trackDao.get_track(id);
    }

    public void update_track(Tracks track) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    trackDao.update(track);
                    Log.d(TAG, "run : update => " + track.getTrackName() + "information");
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to update track name => " + track.getTrackName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_track(Tracks track) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    trackDao.delete(track);
                    Log.d(TAG, "run : successfully delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete track name => " +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_alltrack() {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    trackDao.deleteAllTracks();
                    Log.d(TAG, "run : delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete track name => "  +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
}

