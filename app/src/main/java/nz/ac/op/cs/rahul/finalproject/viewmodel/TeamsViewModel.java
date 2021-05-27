package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.Teams;

public class TeamsViewModel extends AndroidViewModel {

    private TeamsRepository teamsRepository;
    private final LiveData<List<Teams>> currentTeams;

    public TeamsViewModel(@NonNull Application application) {
        super(application);
        teamsRepository = new TeamsRepository(application);
        currentTeams = teamsRepository.get_allteams();
    }
    public LiveData<List<Teams>> getCurrentteams(){
        return currentTeams;
    }


    public void save_team(Teams team){
        teamsRepository.save_team(team);
    }

    public void update_team(Teams team){
        teamsRepository.update_team(team);
    }
    public void delete_team(Teams team){

        teamsRepository.delete_team(team);
    }
    public void delete_all(){

        teamsRepository.delete_allteam();
    }
}