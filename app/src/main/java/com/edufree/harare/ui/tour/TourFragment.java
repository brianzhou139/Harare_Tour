package com.edufree.harare.ui.tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.edufree.harare.R;
import com.edufree.harare.fragments.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TourFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tour, container, false);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager)root.findViewById(R.id.viewpager);
        tabLayout = (TabLayout)root.findViewById(R.id.sliding_tabs);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(),getContext());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

}