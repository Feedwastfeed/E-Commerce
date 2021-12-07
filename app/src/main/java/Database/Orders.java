package Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Customers.class, parentColumns = "CustID", childColumns = "CustID", onDelete = CASCADE))
public class Orders {
    @PrimaryKey(autoGenerate = true)
    private int OrdID;

    private String Address;
    private String OrdDate;
    private int CustID;

    public int getOrdID() {
        return OrdID;
    }

    public void setOrdID(int ordID) {
        OrdID = ordID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOrdDate() {
        return OrdDate;
    }

    public void setOrdDate(String ordDate) {
        OrdDate = ordDate;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }
}
