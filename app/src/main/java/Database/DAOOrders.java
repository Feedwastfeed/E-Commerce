package Database;

import androidx.room.*;

@Dao
public interface DAOOrders {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Orders... orders);

    @Insert
    void insertOrders(Orders orders);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Orders... orders);

    @Update
    void updateOrders(Orders orders);

    @Delete
    void deleteAll(Orders... orders);

    @Delete
    void deleteOrders(Orders orders);
}
