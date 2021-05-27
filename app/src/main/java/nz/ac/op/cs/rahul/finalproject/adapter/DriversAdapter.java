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
import nz.ac.op.cs.rahul.finalproject.db.Drivers;

public class DriversAdapter extends RecyclerView.Adapter<DriversViewHolder> {

    private List<Drivers> DriversList;
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
     * @param DriversList
     * @param context
     */

    public DriversAdapter(List<Drivers> DriversList, Context context) {
        this.DriversList = DriversList;
        this.context = context ;
    }

    /**
     *
     * @param driversList
     */

    public void setDriversList(List<Drivers> driversList){
        this.DriversList = driversList;
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
    public DriversViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item,
                parent,false);

        DriversViewHolder driversViewHolder = new DriversViewHolder(view);
        return driversViewHolder;
    }

    /**
     *
     * @param driversViewHolder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull DriversViewHolder driversViewHolder, int position) {

        Drivers driver = DriversList.get(position);
        driversViewHolder.DriverImage.setImageBitmap(DataConverter.converByteArray2Image(driver.getImage()));
        driversViewHolder.nameTextView.setText(driver.getDriverName());
        driversViewHolder.DriverTextView.setText(driver.getDriverNationality());
        driversViewHolder.detailsTextView.setText(driver.getDriverDetails());

        driversViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bonItemClickListener.onItemClick(view,driver,position);
            }
        });

    }
    public void setDriver(List<Drivers> drivers){
        DriversList = drivers;
        notifyDataSetChanged();
    }

    /**
     *
     * @return
     */

    @Override
    public int getItemCount() {
        if(DriversList != null){
            return DriversList.size();
        } else
            return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Drivers obj, int pos);
    }
}
