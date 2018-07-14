package com.burakod.rahatla.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.burakod.rahatla.Details;
import com.burakod.rahatla.Favorite;
import com.burakod.rahatla.R;

import java.util.ArrayList;

public class DetailsRecylerAdapter extends RecyclerView.Adapter<DetailsRecylerAdapter.MyViewHolder> {

    ArrayList<Details> mDataList;
    LayoutInflater layoutInflater;
    public DetailsRecylerAdapter(Context context , ArrayList<Details> data){
        layoutInflater = LayoutInflater.from(context);
        this.mDataList = data;
    }
    public DetailsRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.category_details_item_list,parent,false);
        DetailsRecylerAdapter.MyViewHolder holder = new DetailsRecylerAdapter.MyViewHolder(v);
        return  holder;
    }
    public void onBindViewHolder(@NonNull DetailsRecylerAdapter.MyViewHolder holder, int position) {
        Details clickDetails = mDataList.get(position);
        holder.setData(clickDetails,position);
    }
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        int objectPositionValue=0;
        TextView mMusicName,mBookShelfName;
        ImageView mMusicLogo,mAddFavorite;

        public MyViewHolder(View itemView) {
            super(itemView);
            mMusicName = itemView.findViewById(R.id.tv_detailsName);
            mBookShelfName = itemView.findViewById(R.id.tv_detailsBookShelf);
            mMusicLogo = itemView.findViewById(R.id.iv_musicLogo);
            mAddFavorite = itemView.findViewById(R.id.iv_details_favorite);
            mAddFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddFavorite(objectPositionValue);


                }


            });


        }


        private void AddFavorite(int position) {


            mDataList.remove(position);
            mAddFavorite.setImageResource(R.drawable.favorite);

            // notifyDataSetChanged();  iki metoda gerek kalmaz .
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mDataList.size());
        }

        public void setData(Details details ,int position){
            this.mMusicName.setText(details.getMusicName());
            this.mBookShelfName.setText(details.getBookShelfName());
            this.objectPositionValue = position;

        }

    }
}

