//package id.project.renal.Database;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "Coffee.db";
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, 2);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
//        db.execSQL(
//                "CREATE TABLE Coffee (" +
//                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "ProductId TEXT UNIQUE, " +
//                        "ProductName TEXT, " +
//                        "Quantity TEXT, " +
//                        "Price TEXT, " +
//                        "Discount TEXT) "
//        );
//        Log.d("db", "onCreate: token data inserted");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public int getCoffeeCount() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        int cnt = (int) DatabaseUtils.queryNumEntries(db, "Coffee");
//        db.close();
//        return cnt;
//    }
//
////    public int cekSameDeviceId(String deviceId) {
////        String countQuery = "SELECT deviceId FROM pet WHERE deviceId = '" + deviceId + "'";
////        SQLiteDatabase db = this.getReadableDatabase();
////        Cursor cursor = db.rawQuery(countQuery, null);
////        int cnt = cursor.getCount();
////        cursor.close();
////        Log.d("sameDevice", "cekSameDeviceId '" + deviceId + "' -> " + cnt);
////        return cnt;
////    }
////
////    @Override
////    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
////        // TODO Auto-generated method stub
////        db.execSQL("DROP TABLE IF EXISTS pet");
////        onCreate(db);
////    }
////
////    @Override
////    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//////        super.onDowngrade(db, oldVersion, newVersion);
////        db.execSQL("DROP TABLE IF EXISTS pet");
////        onCreate(db);
////    }
//
//    public void closeDB() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        SQLiteDatabase db1 = this.getWritableDatabase();
//        if (db.isOpen()) {
//            db.close();
//        }
//        if (db1.isOpen()) {
//            db1.close();
//        }
//    }
//
//
//
//    public long addNewCoffee(String productId, String productName, String quantity, String price) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("ProductId", productId);
//        contentValues.put("ProductName", productName);
//        contentValues.put("Quantity", quantity);
//        contentValues.put("Price", price);
////        contentValues.put("Discount", discount);
//        return db.insert("Coffee", null, contentValues);
//    }
//
//
//    public HashMap<String, String> getCoffeeDatas(String id) {
//        String selectQuery = "SELECT * FROM Coffee WHERE  = '" + id + "'";
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        HashMap<String, String> map = new HashMap<String, String>();
//        if (cursor.moveToFirst()) {
//            map.put("ID", cursor.getString(cursor.getColumnIndex("ID")));
//            map.put("ProductId", cursor.getString(cursor.getColumnIndex("ProductId")));
//            map.put("ProductName", cursor.getString(cursor.getColumnIndex("ProductName")));
//            map.put("Quantity", cursor.getString(cursor.getColumnIndex("Quantity")));
//            map.put("Price", cursor.getString(cursor.getColumnIndex("Price")));
//            map.put("Discount", cursor.getString(cursor.getColumnIndex("Discount")));
//            cursor.close();
//            closeDB();
//            return map;
//        } else {
//            closeDB();
//            return null;
//        }
//    }
//
//    public ArrayList<HashMap<String, String>> getCoffees() {
//        String selectQuery = "SELECT * FROM Coffee ORDER BY ProductName";
//        SQLiteDatabase database = this.getReadableDatabase();
////        int cnt = (int) DatabaseUtils.queryNumEntries(database, "Coffee");
//        Cursor cursor = database.rawQuery(selectQuery, null);
////        int[] ids = new int[cnt];
//
//        ArrayList<HashMap<String, String>> coffeeList = new ArrayList<>();
//
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> coffee = new HashMap<>();
//                coffee.put("nama", cursor.getString(cursor.getColumnIndex("ProductName")));
//                coffee.put("harga", cursor.getString(cursor.getColumnIndex("Price")));
//                coffeeList.add(coffee);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//        closeDB();
//        return coffeeList;
//    }
//
//    public String getCoffeeName(int id) {
//        ArrayList<HashMap<String, String>> akunList;
//        akunList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT ProductName, ID FROM Coffee WHERE ID = " + id + " ORDER BY ID ASC";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("data", cursor.getString(0));
//                akunList.add(map);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return akunList.get(0).get("data");
//    }
//
//    public String getCoffeePrice(int id) {
//        ArrayList<HashMap<String, String>> akunList;
//        akunList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT ProductPrice, ID FROM Coffee WHERE ID = " + id;
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("data", cursor.getString(0));
//                akunList.add(map);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return akunList.get(0).get("data");
//    }
//
//
//
//    public String getPetDevId(int id) {
//        String selectQuery = "SELECT id, deviceId FROM pet WHERE id = " + id;
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        String picLoc = "";
//
//        if (cursor.moveToFirst()) {
//            picLoc = cursor.getString(cursor.getColumnIndex("deviceId"));
//            cursor.close();
//        }
//        closeDB();
//        return picLoc;
//    }
//
//    public String getAmountFood(int id) {
//        String selectQuery = "SELECT id, foodsize FROM pet WHERE id = " + id;
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        String picLoc = "";
//
//        if (cursor.moveToFirst()) {
//            picLoc = cursor.getString(cursor.getColumnIndex("foodsize"));
//            cursor.close();
//        }
//        closeDB();
//        return picLoc;
//    }
//
//
//    public int deleteSetting(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("pet", "id = " + id, null);
//    }
//}
//
