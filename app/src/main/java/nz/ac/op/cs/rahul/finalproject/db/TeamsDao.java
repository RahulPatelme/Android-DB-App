package nz.ac.op.cs.rahul.finalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamsDao {
    @Query("SELECT * FROM TeamsTable")
    LiveData<List<Teams>> getAllTeams();

    @Query("SELECT * FROM TeamsTable WHERE id = :id")
    LiveData<Teams> get_teams(int id);

    @Insert
    void insert(Teams teams);

    @Update
    void update(Teams team);

    @Delete
    void delete(Teams team);

    @Query("DELETE FROM TeamsTable")
    void deleteAllTeams();
}
