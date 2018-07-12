package com.burakod.rahatla;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burakod.rahatla.Adapter.FavoriteRecylerAdapter;



public class FavoriteFragment extends Fragment{
    RecyclerView recyclerView;
    View mView;




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;
        init();
    }

    private void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        FavoriteRecylerAdapter favoriteRecylerAdapter = new FavoriteRecylerAdapter(mView.getContext(), FetchFavorite.favoriteArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(favoriteRecylerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new FetchFavorite().execute();
        mView = inflater.inflate(R.layout.fragment_favorite,container,false);
        recyclerView = mView.findViewById(R.id.recyler_favorite);
        return  mView;
    }
}