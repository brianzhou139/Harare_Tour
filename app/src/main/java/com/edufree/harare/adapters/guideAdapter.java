package com.edufree.harare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.edufree.harare.R;
import com.edufree.harare.models.Guide;

import java.util.ArrayList;

public class guideAdapter extends ArrayAdapter<Guide> {
    private Context mContext;
    private ArrayList<Guide> guideList = new ArrayList<>();

    public guideAdapter(@NonNull Context context,ArrayList<Guide> guideList) {
        super(context, 0,guideList);
        mContext = context;
        this.guideList = guideList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.guide_list_item,parent,false);


        Guide currentGuide=guideList.get(position);
        ImageView gPhoto = (ImageView)listItem.findViewById(R.id.image_id);
        gPhoto.setImageResource(currentGuide.getGuide_foto());
        TextView name = (TextView) listItem.findViewById(R.id.guide_name_id);
        name.setText(currentGuide.getGuide_name());

        return listItem;
    }
}
