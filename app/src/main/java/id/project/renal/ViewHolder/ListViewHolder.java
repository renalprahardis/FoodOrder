package id.project.renal.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.project.renal.Interface.ItemClickListener;
import id.project.renal.R;

/**
 * Created by ASUS on 22/02/2018.
 */

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView listname ;
    public ImageView coffeeimage;

    private ItemClickListener itemClickListener;

    public ListViewHolder(View itemView){
        super(itemView);

        coffeeimage = (ImageView)itemView.findViewById(R.id.coffee_image);
        listname = (TextView) itemView.findViewById(R.id.menu_name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
