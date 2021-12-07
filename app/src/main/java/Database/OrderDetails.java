package Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"OrdID", "ProID"},
        foreignKeys = {@ForeignKey(entity = Orders.class, parentColumns = "OrdID", childColumns = "OrdID", onDelete = CASCADE),
                @ForeignKey(entity = Products.class, parentColumns = "ProID", childColumns = "ProID", onDelete = CASCADE)}
)
public class OrderDetails {
    private int OrdID;
    private int ProID;
    private int Quantity;

    public int getOrdID() {
        return OrdID;
    }

    public void setOrdID(int ordID) {
        OrdID = ordID;
    }

    public int getProID() {
        return ProID;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
