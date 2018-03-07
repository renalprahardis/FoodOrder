package id.project.renal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.project.renal.Interface.ItemClickListener;
import id.project.renal.Model.Menu;
import id.project.renal.ViewHolder.ListViewHolder;

public class MenuList extends AppCompatActivity {

//    private GridLayoutManager layout;

    FirebaseDatabase database;
    DatabaseReference coffeeList;

    TextView txtFullName;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    MenuAdapter adapter;
    FirebaseRecyclerAdapter<Menu,ListViewHolder> adapter;

    String categoryId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        database = FirebaseDatabase.getInstance();
        coffeeList = database.getReference("Menu");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
//        layoutManager = new GridLayoutManager(this,2);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent()!=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadListCoffee(categoryId);
        }

//        adapter = new MenuAdapter(new ArrayList<Menu>(), this);


    }

    private void loadListCoffee(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Menu, ListViewHolder>(Menu.class, R.layout.listitem2, ListViewHolder.class,
                coffeeList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ListViewHolder viewHolder, Menu model, int position) {
                viewHolder.listname.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.coffeeimage);
                final Menu local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent coffeeList = new Intent(MenuList.this, MenuDetail.class);
                        coffeeList.putExtra("ListId", adapter.getRef(position).getKey());
                        startActivity(coffeeList);
//                        Toast.makeText(MenuList.this, ""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        Log.d("TAG", ""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }

}
