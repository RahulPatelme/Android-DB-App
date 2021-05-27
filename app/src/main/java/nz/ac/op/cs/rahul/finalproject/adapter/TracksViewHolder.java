package nz.ac.op.cs.rahul.finalproject.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nz.ac.op.cs.rahul.finalproject.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TracksViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView trackImage;
    TextView nameTextView;
    TextView trackTextView;
    TextView detailsTextView;

    /**
     *
     * @param view
     */

    public TracksViewHolder(@NonNull final View view) {
        super(view);
        cardView = view.findViewById(R.id.cView2);
        trackImage = view.findViewById(R.id.track_imageview);
        nameTextView = view.findViewById(R.id.name_textview);
        trackTextView = view.findViewById(R.id.nationality_textview);
        detailsTextView = view.findViewById(R.id.details_textview);
    }
}
