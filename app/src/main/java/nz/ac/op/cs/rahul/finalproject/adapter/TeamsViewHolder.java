package nz.ac.op.cs.rahul.finalproject.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nz.ac.op.cs.rahul.finalproject.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TeamsViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView teamImage;
    TextView nameTextView;
    TextView teamTextView;
    TextView detailsTextView;

    /**
     *
     * @param view
     */

    public TeamsViewHolder(@NonNull final View view) {
        super(view);
        cardView = view.findViewById(R.id.cView1);
        teamImage = view.findViewById(R.id.team_imageview);
        nameTextView = view.findViewById(R.id.name_textview);
        teamTextView = view.findViewById(R.id.nationality_textview);
        detailsTextView = view.findViewById(R.id.details_textview);
    }
}
