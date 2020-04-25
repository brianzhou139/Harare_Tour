package com.edufree.harare.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edufree.harare.R;
import com.edufree.harare.adapters.eventAdapter;
import com.edufree.harare.models.Event;
import com.edufree.harare.models.dataManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {
    private ArrayList<Event> myEventList;
    private RecyclerView mRecycler;
    private eventAdapter mAdapter;
    private dataManager data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_events, container, false);
        data=new dataManager(getActivity());
        myEventList= data.getEvents();

        mRecycler=(RecyclerView)mView.findViewById(R.id.event_rv_id);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter=new eventAdapter(myEventList, getContext());
        mRecycler.setAdapter(mAdapter);

        return mView;
    }

}
