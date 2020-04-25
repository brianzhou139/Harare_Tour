package com.edufree.harare.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edufree.harare.R;
import com.edufree.harare.TourGuideActivity;
import com.edufree.harare.adapters.guideAdapter;
import com.edufree.harare.models.Guide;
import com.edufree.harare.models.dataManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {
    private ArrayList<Guide> myGuideList;
    private ListView mListView;
    private guideAdapter adapter;
    private dataManager data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_guide, container, false);
        data=new dataManager(getActivity());
        myGuideList= data.getGuides();

        mListView=(ListView)mView.findViewById(R.id.guide_listView);
        adapter=new guideAdapter(getContext(),myGuideList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent viewGuideIntent=new Intent(getActivity(), TourGuideActivity.class);
                viewGuideIntent.putExtra(dataManager.PASS_GUIDE_KEY,position);
                startActivity(viewGuideIntent);
            }
        });

        return mView;
    }

}
