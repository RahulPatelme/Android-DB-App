package nz.ac.op.cs.rahul.finalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DriversDao {
    @Query("SELECT * FROM DriversTable")
    LiveData<List<Drivers>> getAllDrivers();

    @Query("SELECT * FROM DriversTable WHERE id = :id")
    LiveData<Drivers> get_driver(int id);

    @Insert
    void insert(Drivers drivers);

    @Update
    void update(Drivers driver);

    @Delete
    void delete(Drivers driver);

    @Query("DELETE FROM DriversTable")
    void deleteAllDrivers();
}
