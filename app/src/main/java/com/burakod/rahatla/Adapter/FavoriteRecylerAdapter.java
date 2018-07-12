package com.burakod.rahatla.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.burakod.rahatla.Favorite;
import com.burakod.rahatla.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoriteRecylerAdapter extends RecyclerView.Adapter<FavoriteRecylerAdapter.MyViewHolder> {

    ArrayList<Favorite> mDataList;
    LayoutInflater layoutInflater;

    public FavoriteRecylerAdapter() {
    }

    public FavoriteRecylerAdapter(Context context , ArrayList<Favorite> data){
        layoutInflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.favorite_item_list,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Favorite clickFavorite = mDataList.get(position);
        holder.setData(clickFavorite,position);

    }

    public void deleteItem(int position){
        mDataList.remove(position);

        // notifyDataSetChanged();  iki metoda gerek kalmaz .
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDataList.size());
    }



    public void addItem(int position,Favorite addFavorite){
        mDataList.add(position,addFavorite);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        int objectPositionValue=0;
        TextView mMusicName;
        ImageView mMusicPlay,mMusicFavorite;
        SeekBar mVolumeBar;
        MediaPlayer mediaPlayer = new MediaPlayer();


        final int maxVolume = 100;
        final int curVolume = 100;

        // Muzik player kontrol
        void musicControler(){
            String url = mDataList.get(objectPositionValue).getMusicUrl();

            if(mediaPlayer.isPlaying()) {
                mMusicPlay.setImageResource(R.drawable.icon_play);
                mediaPlayer.pause();
                notifyItemRangeChanged(objectPositionValue,mDataList.size());
            }

            else{
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    mMusicPlay.setImageResource(R.drawable.pause);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        public MyViewHolder(View itemView) {
            super(itemView);
            mMusicName = itemView.findViewById(R.id.tv_musicName);
            mMusicPlay = itemView.findViewById(R.id.iv_play);
            mMusicFavorite = itemView.findViewById(R.id.iv_favorite);
            mVolumeBar = itemView.findViewById(R.id.seek_musicVolume);
            mVolumeBar.setMax(maxVolume);
            mVolumeBar.setProgress(curVolume);

           // Pause Edebildim ama Birdaha Açılmıyor.
            mMusicPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    //curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                    mVolumeBar
                            .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onStopTrackingTouch(SeekBar arg0) {
                                    System.out.println("Test "+ curVolume);
                                    System.out.println("Test "+ maxVolume);
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar arg0) {

                                }

                                @Override
                                public void onProgressChanged(SeekBar arg0, int arg1,
                                                              boolean arg2) {
                                    float volume = (float) (1 - (Math.log(100 - arg1) / Math.log(100)));
                                    mediaPlayer.setVolume(volume,volume);


                                }
                            });

                   musicControler();


                }

            });
            mMusicFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }
                    deleteItem(objectPositionValue);
                    saveArrayList(mDataList,"Favorite");
                }
            });

        }


        public void setData(Favorite clickFavorite, int position) {

            saveArrayList(mDataList,"Favorite");
            this.mMusicName.setText(clickFavorite.getMusicName());
            this.objectPositionValue = position;

        }

        // Bu İki metod ile sırasıyla
        // Bilgileri sharedReference ile telefona kaydediyoruz.
        // Bilgiler sharedReference ile telefondan alıyoruz.
        public void saveArrayList(ArrayList<Favorite> list, String key){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(list);
            editor.putString(key, json);
            editor.apply();     // This line is IMPORTANT !!!
        }
        public ArrayList<Favorite> getArrayList(String key){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
            Gson gson = new Gson();
            String json = prefs.getString(key, null);
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            return gson.fromJson(json, type);
        }


    }


}
