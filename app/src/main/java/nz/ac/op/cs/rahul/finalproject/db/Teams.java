package nz.ac.op.cs.rahul.finalproject.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TeamsTable")
public class Teams {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String teamName;
    private String teamNationality;
    private String teamDetails;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamNationality() {
        return teamNationality;
    }

    public String getTeamDetails() {
        return teamDetails;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamNationality(String teamNationality) {
        this.teamNationality = teamNationality;
    }

    public void setTeamDetails(String teamDetails) {
        this.teamDetails = teamDetails;
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
        return "Team{" +
                "id=" + id +
                ", TeamName='" + teamName + '\'' +
                ", TeamNationality='" + teamNationality + '\'' +
                ", TeamDetails='" + teamDetails + '\'' +
                '}';
    }
}
