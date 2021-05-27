package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.Drivers;

public class DriversViewModel extends AndroidViewModel {

    private DriversRepository driversRepository;
    private final LiveData<List<Drivers>> currentDrivers;

    public DriversViewModel(@NonNull Application application) {
        super(application);
        driversRepository = new DriversRepository(application);
        currentDrivers = driversRepository.get_alldrivers();
    }
    public LiveData<List<Drivers>> getCurrentdrivers(){
        return currentDrivers;
    }

    /**
     *
     * @param driver
     */

    public void save_driver(Drivers driver){

        driversRepository.save_driver(driver);
    }
    public void update_driver(Drivers driver){
        driversRepository.update_driver(driver);
    }
    public void delete_driver(Drivers driver){

        driversRepository.delete_driver(driver);
    }
    public void delete_all(){

        driversRepository.delete_alldriver();
    }
}
