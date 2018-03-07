package id.project.renal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

//import id.project.renal.Database.DBHelper;
import id.project.renal.Database.DataHelper2;
//import id.project.renal.Database.Database;
import id.project.renal.Model.Menu;
import id.project.renal.Model.Order;

public class MenuDetail extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference detailList;

    TextView  list_price, list_description, menu_name;
    ImageView list_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    Menu currentList;
//    DBHelper dbHelper = new DBHelper(this);
    DataHelper2 dbHelper = new DataHelper2(this);

    String listId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        database = FirebaseDatabase.getInstance();
        detailList = database.getReference("Menu");

        list_description = (TextView)findViewById(R.id.menu_des);
        list_image = (ImageView)findViewById(R.id.menu_img);
        list_price = (TextView)findViewById(R.id.price);
        menu_name = (TextView) findViewById(R.id.menu_name);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        numberButton = (ElegantNumberButton)findViewById(R.id.number_btn);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into coffee(nameproduct, quantity, price) values(" +
                        "'" + currentList.getName().toString()+"'," +
                        "'" + numberButton.getNumber().toString()+"'," +
                        "'"+ currentList.getPrice().toString() +"')");


                Intent intent = new Intent(MenuDetail.this,MenuList.class);


//                ArrayList<HashMap<String, String>> cofees = dbHelper.addNewCoffee(,"list_price","numberButton","listId");

//               new Database(getBaseContext()).addToCart(new Order(
//                   listId,
//                       currentList.getName(),
//                       numberButton.getNumber(),
//                       currentList.getPrice(),
//                       currentList.getDiscount()
//
//               ));

                Toast.makeText(MenuDetail.this,"Add to Cart", Toast.LENGTH_SHORT).show();
//                MenuDetail.ma.RefreshList();
                finish();
            }
        });


        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collaps);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpendedAppbar);



        if(getIntent()!=null)
            listId = getIntent().getStringExtra("ListId");
        if(!listId.isEmpty()){
            getDetailList(listId);
        }


    }

    private void getDetailList(String listId) {
        detailList.child(listId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentList = dataSnapshot.getValue(Menu.class);

                Picasso.with(getBaseContext()).load(currentList.getImage2())
                        .into(list_image);

                collapsingToolbarLayout.setTitle(currentList.getName());

                list_description.setText(currentList.getDescription());

                list_price.setText(currentList.getPrice());
                list_description.setText(currentList.getDescription());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
