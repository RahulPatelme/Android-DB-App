package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.Drivers;
import nz.ac.op.cs.rahul.finalproject.db.DriversDao;
import nz.ac.op.cs.rahul.finalproject.db.ItemDB;

public class DriversRepository {

    private ItemDB itemDB;
    private DriversDao driverDao;
    private LiveData<List<Drivers>> mAllDrivers;
    Context context;
    private static final String TAG = "DriverRepository";

    /**
     *
     * @param context
     */

    public DriversRepository(Context context) {

        itemDB = ItemDB.getDBInstance(context);
        driverDao = itemDB.getDriverDao();
        mAllDrivers = driverDao.getAllDrivers();
    }
    public LiveData<List<Drivers>> get_alldrivers(){
        return mAllDrivers;
    }

    /**
     *
     * @param driver
     */

    public void save_driver(Drivers driver) {

        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    driverDao.insert(driver);
                    Log.d(TAG, "run : saved driver name => " + driver.getDriverName());
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to save driver name => " + driver.getDriverName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public LiveData<Drivers> get_driver(int id) {
        return driverDao.get_driver(id);
    }

    /**
     *
     * @param driver
     */

    public void update_driver(Drivers driver) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    driverDao.update(driver);
                    Log.d(TAG, "run : update => " + driver.getDriverName() + "information");
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to update driver => " + driver.getDriverName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    /**
     *
     * @param driver
     */

    public void delete_driver(Drivers driver) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    driverDao.delete(driver);
                    Log.d(TAG, "run : successfully delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete driver => " +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_alldriver() {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    driverDao.deleteAllDrivers();
                    Log.d(TAG, "run : delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete drivers => "  +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
}
