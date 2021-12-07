package Database;

import androidx.room.*;

import java.util.List;

@Dao
public interface DAOProducts {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Products... products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(Products products);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Products... products);

    @Update
    void updateProducts(Products products);

    @Delete
    void deleteAll(Products... products);

    @Delete
    void deleteProducts(Products products);

    @Query("SELECT * FROM products ")
    public List<Products> select_product();

    @Query("SELECT * FROM products WHERE CatID = :id")
    public List<Products> select_product_id(int id);

    @Query("SELECT * FROM products WHERE ProName = :name")
    public Products select_product_by_name(String name);
}
