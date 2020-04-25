package com.edufree.harare.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.edufree.harare.HomeActivity;
import com.edufree.harare.R;
import com.edufree.harare.models.Event;
import com.edufree.harare.models.dataManager;

import java.util.ArrayList;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.eventViewHolder> {

    private ArrayList<Event> mEventList;
    private Context mContext;

    public eventAdapter(ArrayList<Event> eventList, Context context) {
        mEventList = eventList;
        mContext = context;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(mContext).inflate(R.layout.event_list_item,parent,false);
        return new eventViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final eventViewHolder holder, int position) {
        final Event currentEvent=mEventList.get(position);

        holder.eventName.setText(currentEvent.getEvent_name());
        holder.eventDate.setText(currentEvent.getEvent_date());
        holder.eventFoto.setImageResource(currentEvent.getEvent_foto());
        holder.eventDetail.setText(currentEvent.getEvent_description());
        holder.eventFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mDetailView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class eventViewHolder extends RecyclerView.ViewHolder {
        private TextView eventName,eventDate,eventDetail;
        private ImageView eventFoto;
        private LinearLayout mDetailView;

        public eventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName=(TextView)itemView.findViewById(R.id.event_name_id);
            eventFoto=(ImageView)itemView.findViewById(R.id.event_image_id);
            eventDate=(TextView)itemView.findViewById(R.id.event_date);
            eventDetail=(TextView)itemView.findViewById(R.id.event_detail_id);
            mDetailView=(LinearLayout)itemView.findViewById(R.id.event_detail_linearView);
        }
    }

}
