package nz.ac.op.cs.rahul.finalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TracksDao {
    @Query("SELECT * FROM TracksTable")
    LiveData<List<Tracks>> getAllTracks();

    @Query("SELECT * FROM TracksTable WHERE id = :id")
    LiveData<Tracks> get_track(int id);

    @Insert
    void insert(Tracks tracks);

    @Update
    void update(Tracks track);

    @Delete
    void delete(Tracks track);

    @Query("DELETE FROM TracksTable")
    void deleteAllTracks();
}