package nz.ac.op.cs.rahul.finalproject.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static nz.ac.op.cs.rahul.finalproject.DriversMainActivity.DB_Name;

@Database(entities = {Drivers.class,Teams.class,Tracks.class},version = 2,exportSchema = false)
public abstract class ItemDB extends RoomDatabase {

    private static ItemDB bInstance;
    public abstract DriversDao getDriverDao();
    public abstract TeamsDao getTeamDao();
    public abstract TracksDao getTrackDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     *
     * @param context
     * @return
     */

    public static synchronized ItemDB getDBInstance(Context context){
        if(bInstance == null ){
            bInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ItemDB.class,
                    DB_Name
            )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return bInstance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
