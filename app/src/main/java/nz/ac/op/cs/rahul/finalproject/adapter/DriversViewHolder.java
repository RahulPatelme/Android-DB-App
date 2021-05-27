package nz.ac.op.cs.rahul.finalproject.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nz.ac.op.cs.rahul.finalproject.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DriversViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView DriverImage;
    TextView nameTextView;
    TextView DriverTextView;
    TextView detailsTextView;

    /**
     *
     * @param view
     */

    public DriversViewHolder(@NonNull final View view) {
        super(view);
        cardView = view.findViewById(R.id.cView);
        DriverImage = view.findViewById(R.id.driver_imageview);
        nameTextView = view.findViewById(R.id.name_textview);
        DriverTextView = view.findViewById(R.id.nationality_textview);
        detailsTextView = view.findViewById(R.id.details_textview);
    }
}
