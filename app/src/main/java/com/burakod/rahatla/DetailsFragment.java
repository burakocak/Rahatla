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

public class DetailsFragment extends Fragment {

    RecyclerView recyclerView;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_details,container,false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();



    }

    private void init() {
        new FetchDetails().execute();
        recyclerView = mView.findViewById(R.id.recyler_details);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DetailsRecylerAdapter detailsRecylerAdapter = new DetailsRecylerAdapter(mView.getContext(),FetchDetails.detailsArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(detailsRecylerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
