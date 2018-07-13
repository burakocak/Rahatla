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

import com.burakod.rahatla.Adapter.BookShelfRecylerAdapter;


public class BookShelfFragment extends Fragment{

    RecyclerView recyclerView;
    View mView;

    public static BookShelfFragment newInstance() {
        BookShelfFragment fragment = new BookShelfFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new FetchBookShelf().execute();
        recyclerView = mView.findViewById(R.id.recyler_bookshelf);
        this.mView = view;
        init();
    }

    private void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        BookShelfRecylerAdapter bookShelfRecylerAdapter = new BookShelfRecylerAdapter(mView.getContext(),FetchBookShelf.bookShelfArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bookShelfRecylerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new FetchBookShelf().execute();
        mView = inflater.inflate(R.layout.fragment_bookshelf,container,false);
        return  mView;



    }
}