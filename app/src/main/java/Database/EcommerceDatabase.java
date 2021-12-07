package Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Categories.class , Customers.class , OrderDetails.class , Orders.class , Products.class} ,
            exportSchema = false , version = 1)
public abstract class EcommerceDatabase extends RoomDatabase {
    private static final String DB_Name = "Ecommerce_DB";
    private static EcommerceDatabase inst;
    private static final Object LOCK = new Object();
    public static EcommerceDatabase getInstance(Context context){
        if(inst == null){
            synchronized (LOCK){
                inst = Room.databaseBuilder(context.getApplicationContext() , EcommerceDatabase.class , DB_Name)
                        .allowMainThreadQueries().build();
            }
        }
        return inst;
    }
    public abstract DAOCategories categories_dao();
    public abstract DAOCustomers customers_dao();
    public abstract DAOOrderDatelis orderdetails_dao();
    public abstract DAOOrders orders_dao();
    public abstract DAOProducts products_dao();
}
