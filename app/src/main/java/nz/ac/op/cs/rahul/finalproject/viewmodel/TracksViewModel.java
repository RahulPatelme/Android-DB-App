package nz.ac.op.cs.rahul.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import nz.ac.op.cs.rahul.finalproject.db.Tracks;

public class TracksViewModel extends AndroidViewModel {


    private TracksRepository trackRepository;
    private final LiveData<List<Tracks>> currentTracks;

    public TracksViewModel(@NonNull Application application) {
        super(application);
        trackRepository = new TracksRepository(application);
        currentTracks = trackRepository.get_alltracks();
    }
    public LiveData<List<Tracks>> getCurrenttracks(){
        return currentTracks;
    }


    public void save_track(Tracks track){

        trackRepository.save_track(track);
    }
    public void update_track(Tracks track){
        trackRepository.update_track(track);
    }
    public void delete_track(Tracks track){

        trackRepository.delete_track(track);
    }
    public void delete_all(){

        trackRepository.delete_alltrack();
    }
}
