package Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ColumnInfo.INTEGER;

@Entity(indices = {@Index(value = {"UserName"}, unique = true)})
public class Customers {



    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(typeAffinity = INTEGER)
    private int CustID;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String CustName;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String UserName;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String Password;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String Gender;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String Job;
    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String Birthdate;

    @NonNull
    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(@NonNull String birthdate) {
        Birthdate = birthdate;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }

    @NonNull
    public String getCustName() {
        return CustName;
    }

    public void setCustName(@NonNull String custName) {
        CustName = custName;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public void setUserName(@NonNull String userName) {
        UserName = userName;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }

    @NonNull
    public String getGender() {
        return Gender;
    }

    public void setGender(@NonNull String gender) {
        Gender = gender;
    }

    @NonNull
    public String getJob() {
        return Job;
    }

    public void setJob(@NonNull String job) {
        Job = job;
    }
}
