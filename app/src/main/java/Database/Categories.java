package Database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"CatName"},
        unique = true)})
public class Categories {

    @PrimaryKey(autoGenerate = true)
    private int CatID;
    private String CatName;

    public int getCatID() {
        return CatID;
    }

    public String getCatName() {
        return CatName;
    }
    public void setCatID(int catID) {
        CatID = catID;
    }
    public void setCatName(String catName) {
        CatName = catName;
    }
}
