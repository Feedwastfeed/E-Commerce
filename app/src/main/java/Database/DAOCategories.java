package Database;

import androidx.room.*;

import java.util.List;

@Dao
public interface DAOCategories {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Categories... categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(Categories categories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Categories... categories);

    @Update
    void updateCategories(Categories categories);

    @Delete
    void deleteAll(Categories... categories);

    @Delete
    void deleteCategories(Categories categories);

    @Query("SELECT CatName FROM Categories")
    public List<String> select_catList();

    @Query("SELECT CatID FROM Categories WHERE CatName = :name")
    public int select_catid(String name);
}
