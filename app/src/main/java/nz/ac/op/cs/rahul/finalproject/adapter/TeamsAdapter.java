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
import nz.ac.op.cs.rahul.finalproject.db.Teams;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsViewHolder> {

    private List<Teams> TeamsList;
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
     * @param TeamsList
     * @param context
     */

    public TeamsAdapter(List<Teams> TeamsList, Context context) {
        this.TeamsList = TeamsList;
        this.context = context ;
    }

    /**
     *
     * @param teamsList
     */

    public void setTeamsList(List<Teams> teamsList){
        this.TeamsList = teamsList;
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
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item1,
                parent,false);

        TeamsViewHolder teamsViewHolder = new TeamsViewHolder(view);
        return teamsViewHolder;
    }

    /**
     *
     * @param teamsViewHolder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder teamsViewHolder, int position) {

        Teams team = TeamsList.get(position);
        teamsViewHolder.teamImage.setImageBitmap(DataConverter.converByteArray2Image(team.getImage()));
        teamsViewHolder.nameTextView.setText(team.getTeamName());
        teamsViewHolder.teamTextView.setText(team.getTeamNationality());
        teamsViewHolder.detailsTextView.setText(team.getTeamDetails());

        teamsViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bonItemClickListener.onItemClick(view,team,position);
            }
        });

    }
    public void setTeam(List<Teams> teams){
        TeamsList = teams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(TeamsList != null){
            return TeamsList.size();
        } else
            return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Teams obj, int pos);
    }
}
