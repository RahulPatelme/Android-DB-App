package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.ItemDB;
import nz.ac.op.cs.rahul.finalproject.db.Teams;
import nz.ac.op.cs.rahul.finalproject.db.TeamsDao;

public class TeamsRepository {

    private ItemDB itemDB;
    private TeamsDao teamDao;
    private LiveData<List<Teams>> mAllTeams;
    Context context;
    private static final String TAG = "TeamRepository";

    /**
     *
     * @param context
     */

    public TeamsRepository(Context context) {

        itemDB = ItemDB.getDBInstance(context);
        teamDao = itemDB.getTeamDao();
        mAllTeams = teamDao.getAllTeams();
    }
    public LiveData<List<Teams>> get_allteams(){
        return mAllTeams;
    }

    /**
     *
     * @param team
     */

    public void save_team(Teams team) {

        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    teamDao.insert(team);
                    Log.d(TAG, "run : saved team name => " + team.getTeamName());
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to save team name => " + team.getTeamName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    /**
     *
     * @param id
     * @return
     */

    public LiveData<Teams> get_team(int id) {
        return teamDao.get_teams(id);
    }

    /**
     *
     * @param team
     */

    public void update_team(Teams team) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    teamDao.update(team);
                    Log.d(TAG, "run : update => " + team.getTeamName() + "information");
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to update team name => " + team.getTeamName() +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    /**
     *
     * @param team
     */

    public void delete_team(Teams team) {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    teamDao.delete(team);
                    Log.d(TAG, "run : successfully delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete team name => " +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }

    public void delete_allteam() {
        ItemDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    teamDao.deleteAllTeams();
                    Log.d(TAG, "run : delete => " );
                } catch (Exception e) {
                    Log.d(TAG, "run: failed to delete team name => "  +
                            " because: " + e.getMessage() + "");
                }
            }
        });
    }
}