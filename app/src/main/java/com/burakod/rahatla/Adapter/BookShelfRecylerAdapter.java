package com.burakod.rahatla.Adapter;


import android.support.v4.app.Fragment ;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.burakod.rahatla.BookShelf;
import com.burakod.rahatla.DetailsFragment;
import com.burakod.rahatla.DownloadImageFromInternet;
import com.burakod.rahatla.FetchDetails;
import com.burakod.rahatla.HomeActivity;
import com.burakod.rahatla.R;

import java.util.ArrayList;

public class BookShelfRecylerAdapter extends RecyclerView.Adapter<BookShelfRecylerAdapter.MyViewHolder>{

    ArrayList<BookShelf> mDataList;
    LayoutInflater layoutInflater;


    public BookShelfRecylerAdapter(Context context , ArrayList<BookShelf> data){
        layoutInflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @NonNull
    @Override
    public BookShelfRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.bookshelf_item_list,parent,false);
        BookShelfRecylerAdapter.MyViewHolder holder = new BookShelfRecylerAdapter.MyViewHolder(v);
        return  holder;
    }


    @Override
    public void onBindViewHolder(@NonNull BookShelfRecylerAdapter.MyViewHolder holder, int position) {
        BookShelf clickBookShelf = mDataList.get(position);
        holder.setData(clickBookShelf,position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        int objectPositionValue = 0;
        TextView mBookShelfName;
        ImageView mBookShelfImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            mBookShelfName = itemView.findViewById(R.id.tv_bookshelf);
            mBookShelfImage = itemView.findViewById(R.id.iv_bookshelf);

            mBookShelfImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String bookShelfName = mDataList.get(objectPositionValue).getBookShelfName();

                    String url = "http://burakocak.net/bookshelf/"+bookShelfName+".html";
                    FetchDetails.url =url;
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new DetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, myFragment).addToBackStack(null).commit();
                    ((HomeActivity)activity).showProgress();



                }
            });


        }

        public void setData(BookShelf clickBookShelf, int position) {

            this.mBookShelfName.setText(clickBookShelf.getBookShelfName());
            this.objectPositionValue = position;
            String pathToFile = mDataList.get(position).getBookShelfImage();
            new DownloadImageFromInternet(mBookShelfImage).execute(pathToFile);


        }

    }
}
