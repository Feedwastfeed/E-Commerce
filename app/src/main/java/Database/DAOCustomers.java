package Database;

import androidx.room.*;

@Dao
public interface DAOCustomers {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Customers... customers);

    @Insert
    void insertCustomer(Customers customers);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Customers... customer);

    @Update
    void updateCustomer(Customers customer);

    @Delete
    void deleteAll(Customers... customers);

    @Delete
    void deleteCustomer(Customers customer);

    @Query("SELECT count(*) FROM Customers where UserName = :email")
    public int getEmail(String email);

    @Query("SELECT count(*) FROM Customers where UserName = :email and Password = :pass")
    public int validEmail(String email , String pass);

    @Query("UPDATE Customers SET Password = :pass WHERE Username = :email")
    public void updatepass(String email , String pass);

}
