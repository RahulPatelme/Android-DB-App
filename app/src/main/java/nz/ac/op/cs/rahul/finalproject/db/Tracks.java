package nz.ac.op.cs.rahul.finalproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TracksTable")
public class Tracks {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String trackName;
    private String trackNationality;
    private String trackDetails;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;

    public int getId() {
        return id;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackNationality() {
        return trackNationality;
    }

    public String getTrackDetails() {
        return trackDetails;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrackName(String pCName) {
        this.trackName = pCName;
    }

    public void setTrackNationality(String pCGenre) {
        this.trackNationality = pCGenre;
    }

    public void setTrackDetails(String pCDetails) {
        this.trackDetails = pCDetails;
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
        return "Track{" +
                "id=" + id +
                ", TrackName='" + trackName + '\'' +
                ", TrackNationality='" + trackNationality + '\'' +
                ", TrackDetails='" + trackDetails + '\'' +
                '}';
    }
}
