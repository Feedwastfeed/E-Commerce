package Database;

import androidx.room.*;

@Dao
public interface DAOOrderDatelis {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(OrderDetails... orderDetails);

    @Insert
    void insertOrderDetails(OrderDetails orderDetails);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(OrderDetails... orderDetails);

    @Update
    void updateOrderDetails(OrderDetails orderDetails);

    @Delete
    void deleteAll(OrderDetails... orderDetails);

    @Delete
    void deleteOrderDetails(OrderDetails orderDetails);
}
