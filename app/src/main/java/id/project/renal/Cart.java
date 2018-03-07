package id.project.renal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import id.project.renal.Common.Common;
import id.project.renal.Database.DataHelper2;
//import id.project.renal.Database.Database;
import id.project.renal.Model.Order;
import id.project.renal.Model.Request;
import id.project.renal.Model.User;
import id.project.renal.ViewHolder.CartAdapter;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button btnPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    protected Cursor cursor;
    DataHelper2 dbHelper = new DataHelper2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlace = (Button)findViewById(R.id.btn_place);




        loadList();

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAllerDialog();



            }

        });
    }


private void showAllerDialog(){
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
    alertDialog.setTitle("One more step!");
    alertDialog.setMessage("Enter your address: ");

    final EditText editAddress = new EditText(Cart.this);
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
    );

    editAddress.setLayoutParams(lp);
    alertDialog.setView(editAddress);
    alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Request request = new Request(
                    Common.currentUser.getPhone(),
                    Common.currentUser.getName(),
                    editAddress.getText().toString(),
                    txtTotalPrice.getText().toString(),
                    cart

            );

            requests.child(String.valueOf(System.currentTimeMillis()))
                    .setValue(request);

            new DataHelper2(getBaseContext());
            Toast.makeText(Cart.this,"Thank you for Order",Toast.LENGTH_SHORT).show();
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            cursor = db.rawQuery("DELETE FROM coffee ",null);
//            cursor.close();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("DELETE FROM coffee"); //delete all rows in a table
            db.close();
            Intent intent = new Intent(Cart.this, Home.class);
            startActivity(intent);
        }
    });

    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
    alertDialog.show();

}


    private void loadList(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM coffee ",null);
        ArrayList<HashMap<String, String>> coffeeList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("nameproduct")),
                        cursor.getString(cursor.getColumnIndex("quantity")),
                        cursor.getString(cursor.getColumnIndex("price")));
//                HashMap<String, String> coffee = new HashMap<>();
//                coffee.put("nama", cursor.getString(cursor.getColumnIndex("ProductName")));
//                coffee.put("harga", cursor.getString(cursor.getColumnIndex("Price")));
//                coffeeList.add(coffee);
                cart.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }
//        cart = new DataBase(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);



        int total = 0;
        for(Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
//        Locale locale = new Locale("id","INDONESIA");
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String tot = String.valueOf(total);
        txtTotalPrice.setText(tot);
    }
}
