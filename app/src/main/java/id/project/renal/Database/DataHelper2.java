package id.project.renal.Database;


        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DataHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "coffee.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table coffee(id integer primary key, nameproduct text null, quantity text null, price text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
//        sql = "INSERT INTO coffee ( nameproduct, price) VALUES ( 'Espresso', '10.000');";
//        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}