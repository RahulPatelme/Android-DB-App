package nz.ac.op.cs.rahul.finalproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DriversTable")
public class Drivers {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String driverName;
    private String driverNationality;
    private String driverDetails;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;

    /**
     *
     * @return
     */

    public int getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverNationality() {
        return driverNationality;
    }

    public String getDriverDetails() {
        return driverDetails;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverNationality(String driverNationality) {
        this.driverNationality = driverNationality;
    }

    public void setDriverDetails(String driverDetails) {
        this.driverDetails = driverDetails;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", DriverName='" + driverName + '\'' +
                ", DriverNationality='" + driverNationality + '\'' +
                ", DriverDetails='" + driverDetails + '\'' +
                '}';
    }
}