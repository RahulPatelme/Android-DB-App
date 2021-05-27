package nz.ac.op.cs.rahul.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import nz.ac.op.cs.rahul.finalproject.R;
import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.Tracks;

public class TracksAdapter extends RecyclerView.Adapter<TracksViewHolder> {

    private List<Tracks> TracksList;
    private Context context;
    private OnItemClickListener bonItemClickListener;

    /**
     *
     * @param bonItemClickListener
     */

    public void setonItemClickListener(OnItemClickListener bonItemClickListener) {
        this.bonItemClickListener = bonItemClickListener;
    }

    /**
     *
     * @param TracksList
     * @param context
     */

    public TracksAdapter(List<Tracks> TracksList, Context context) {
        this.TracksList = TracksList;
        this.context = context ;
    }

    /**
     *
     * @param TracksList
     */

    public void setTrackList(List<Tracks> TracksList){
        this.TracksList = TracksList;
        notifyDataSetChanged();
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public TracksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item2,
                parent,false);

        TracksViewHolder pCViewHolder = new TracksViewHolder(view);
        return pCViewHolder;
    }

    /**
     *
     * @param pCViewHolder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull TracksViewHolder pCViewHolder, int position) {

        Tracks track = TracksList.get(position);
        pCViewHolder.trackImage.setImageBitmap(DataConverter.converByteArray2Image(track.getImage()));
        pCViewHolder.nameTextView.setText(track.getTrackName());
        pCViewHolder.trackTextView.setText(track.getTrackNationality());
        pCViewHolder.detailsTextView.setText(track.getTrackDetails());

        pCViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bonItemClickListener.onItemClick(view,track,position);
            }
        });
    }
    public void setTrack(List<Tracks> tracks){
        TracksList = tracks;
        notifyDataSetChanged();
    }

    /**
     *
     * @return
     */

    @Override
    public int getItemCount() {
        if(TracksList != null){
            return TracksList.size();
        } else
            return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Tracks obj, int pos);
    }
}