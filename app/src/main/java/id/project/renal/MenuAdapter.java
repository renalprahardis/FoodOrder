package id.project.renal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.project.renal.Model.Menu;

/**
 * Created by ASUS on 03/03/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>  {
        private ArrayList<Menu> menuList;
        Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView esp;
            public ViewHolder(View v) {
                super(v);
                esp = (ImageView) v.findViewById(R.id.esp);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MenuAdapter(ArrayList<Menu> myDataset, Context context) {
            menuList = myDataset;
            this.context = context;
        }

        public void addMenu(Menu menuBaru){
            menuList.add(menuBaru);
            notifyDataSetChanged();
        }

        public void changeMenu(int position, Menu menuBaru){
            menuList.set(position, menuBaru);
            notifyDataSetChanged();
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            Picasso.with(context)
                    .load(menuList.get(position).getImage())
                    .into(holder.esp);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return menuList.size();
        }
}
