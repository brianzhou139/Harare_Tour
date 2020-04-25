package com.edufree.harare.models;
import android.content.Context;
import android.content.res.Resources;
import android.widget.ArrayAdapter;
import com.edufree.harare.R;
import com.google.android.gms.maps.model.LatLng;
import java.net.ConnectException;
import java.util.ArrayList;

public class dataManager {
    public static final String PASS_EVENT_KEY="com.edufree.harare.models_event";
    public static final String PASS_TOUR_GUIDE_KEY="com.edufree.harare.models_tourguide";
    public static final String PASS_GUIDE_KEY="com.edufree.harare.models_guide";
    public Context mContext;
    public dataManager(){}

    public dataManager(Context context) {
        mContext = context;
    }

    public ArrayList<Guide> getGuides(){
        ArrayList<Guide> mGuides=new ArrayList<>();
        Resources value=mContext.getResources();
        mGuides.add(new Guide(value.getString(R.string.guide_1),R.drawable.ic_guide_hotel));
        mGuides.add(new Guide(value.getString(R.string.guide_2),R.drawable.ic_guide_food));
        mGuides.add(new Guide(value.getString(R.string.guide_3),R.drawable.ic_guide_arts));
        mGuides.add(new Guide(value.getString(R.string.guide_4),R.drawable.ic_guide_transport));
        mGuides.add(new Guide(value.getString(R.string.guide_5),R.drawable.ic_guide_shop));
        mGuides.add(new Guide(value.getString(R.string.guide_6),R.drawable.ic_guide_outdoor));
        return  mGuides;
    };

    public  ArrayList<Event> getEvents(){
        ArrayList<Event> mEvents=new ArrayList<>();
        Resources value=mContext.getResources();
        Event v1=new Event();
        v1.setEvent_name(value.getString(R.string.v1_name));
        v1.setEvent_description(value.getString(R.string.v1_detail));
        v1.setEvent_address(value.getString(R.string.v1_address));
        v1.setEvent_date(value.getString(R.string.v1_date));
        v1.setEvent_foto(R.drawable.event_1);

        Event v2=new Event();
        v2.setEvent_name(value.getString(R.string.v2_name));
        v2.setEvent_description(value.getString(R.string.v2_detail));
        v2.setEvent_address(value.getString(R.string.v2_address));
        v2.setEvent_date(value.getString(R.string.v2_date));
        v2.setEvent_foto(R.drawable.event_2);

        Event v3=new Event();
        v3.setEvent_name(value.getString(R.string.v3_name));
        v3.setEvent_description(value.getString(R.string.v3_detail));
        v3.setEvent_address(value.getString(R.string.v3_address));
        v3.setEvent_date(value.getString(R.string.v3_date));
        v3.setEvent_foto(R.drawable.event_3);

        Event v4=new Event();
        v4.setEvent_name(value.getString(R.string.v4_name));
        v4.setEvent_description(value.getString(R.string.v4_detail));
        v4.setEvent_address(value.getString(R.string.v4_address));
        v4.setEvent_date(value.getString(R.string.v4_date));
        v4.setEvent_foto(R.drawable.event_4);

        mEvents.add(v1);
        mEvents.add(v2);
        mEvents.add(v3);
        mEvents.add(v4);
        return mEvents;
    }

    public ArrayList<tourGuide> getTourGuides(){
        ArrayList<tourGuide> tourGuides=new ArrayList<>();
        Resources value=mContext.getResources();
        tourGuide t1=new tourGuide();
        t1.setTourName(value.getString(R.string.n1_hotel));
        t1.setTourCategory(value.getString(R.string.hotel));
        t1.setTourDetails(value.getString(R.string.t1_detail));
        t1.setTourAddress(value.getString(R.string.t1_address));
        t1.setTourPhone(value.getString(R.string.t1_phone));
        t1.setTourImage(R.drawable.hotel_1);

        tourGuide t2=new tourGuide();
        t2.setTourName(value.getString(R.string.t2_name));
        t2.setTourCategory(value.getString(R.string.hotel));
        t2.setTourDetails(value.getString(R.string.t2_detail));
        t2.setTourAddress(value.getString(R.string.t2_address));
        t2.setTourPhone(value.getString(R.string.t2_phone));
        t2.setTourImage(R.drawable.hotel_2);

        tourGuide t3=new tourGuide();
        t3.setTourName(value.getString(R.string.t3_name));
        t3.setTourCategory(value.getString(R.string.hotel));
        t3.setTourDetails(value.getString(R.string.t3_detail));
        t3.setTourAddress(value.getString(R.string.t3_address));
        t3.setTourPhone(value.getString(R.string.t3_phone));
        t3.setTourImage(R.drawable.hotel_3);

        tourGuide t4=new tourGuide();
        t4.setTourName(value.getString(R.string.t4_name));
        t4.setTourCategory(value.getString(R.string.hotel));
        t4.setTourDetails(value.getString(R.string.t4_detail));
        t4.setTourAddress(value.getString(R.string.t4_address));
        t4.setTourPhone(value.getString(R.string.t4_phone));
        t4.setTourImage(R.drawable.hotel_4);

        tourGuide t5=new tourGuide();
        t5.setTourName(value.getString(R.string.t5_name));
        t5.setTourCategory(value.getString(R.string.hotel));
        t5.setTourDetails(value.getString(R.string.t5_deatil));
        t5.setTourAddress(value.getString(R.string.t5_address));
        t5.setTourPhone(value.getString(R.string.t5_phone));
        t5.setTourImage(R.drawable.hotel_5);

        tourGuide t6=new tourGuide();
        t6.setTourName(value.getString(R.string.t6_name));
        t6.setTourCategory(value.getString(R.string.hotel));
        t6.setTourDetails(value.getString(R.string.t6_detail));
        t6.setTourAddress(value.getString(R.string.t6_details));
        t6.setTourPhone(value.getString(R.string.t6_phone));
        t6.setTourImage(R.drawable.hotel_6);

        tourGuide t7=new tourGuide();
        t7.setTourName(value.getString(R.string.t7_name));
        t7.setTourCategory(value.getString(R.string.food));
        t7.setTourDetails(value.getString(R.string.t7_detail));
        t7.setTourAddress(value.getString(R.string.t7_address));
        t7.setTourPhone(value.getString(R.string.t7_phone));
        t7.setTourImage(R.drawable.food_1);

        tourGuide t8=new tourGuide();
        t8.setTourName(value.getString(R.string.t8_name));
        t8.setTourCategory(value.getString(R.string.food));
        t8.setTourDetails("");
        t8.setTourAddress(value.getString(R.string.t8_address));
        t8.setTourPhone(value.getString(R.string.t8_phone));
        t8.setTourImage(R.drawable.food_2);

        tourGuide t9=new tourGuide();
        t9.setTourName(value.getString(R.string.t9_name));
        t9.setTourCategory(value.getString(R.string.food));
        t9.setTourDetails("");
        t9.setTourAddress(value.getString(R.string.t9_address));
        t9.setTourPhone(value.getString(R.string.t9_phone));
        t9.setTourImage(R.drawable.food_3);

        tourGuide t10=new tourGuide();
        t10.setTourName(value.getString(R.string.t10_name));
        t10.setTourCategory(value.getString(R.string.food));
        t10.setTourDetails("");
        t10.setTourAddress(value.getString(R.string.t10_address));
        t10.setTourPhone(value.getString(R.string.t10_phone));
        t10.setTourImage(R.drawable.food_4);

        tourGuide t11=new tourGuide();
        t11.setTourName(value.getString(R.string.t11_name));
        t11.setTourCategory(value.getString(R.string.food));
        t11.setTourDetails("");
        t11.setTourAddress(value.getString(R.string.t11_address));
        t11.setTourPhone(value.getString(R.string.t11_phone));
        t11.setTourImage(R.drawable.food_5);

        tourGuide t12=new tourGuide();
        t12.setTourName(value.getString(R.string.t12_name));
        t12.setTourCategory(value.getString(R.string.food));
        t12.setTourDetails("");
        t12.setTourAddress(value.getString(R.string.t12_address));
        t12.setTourPhone(value.getString(R.string.t12_phone));
        t12.setTourImageUrl("");
        t12.setTourImage(R.drawable.food_6);

        tourGuide t13=new tourGuide();
        t13.setTourName(value.getString(R.string.t13_name));
        t13.setTourCategory(value.getString(R.string.food));
        t13.setTourDetails("");
        t13.setTourAddress(value.getString(R.string.t13_address));
        t13.setTourPhone(value.getString(R.string.t13_phone));
        t13.setTourImage(R.drawable.food_7);

        tourGuide t14=new tourGuide();
        t14.setTourName(value.getString(R.string.t14_name));
        t14.setTourCategory(value.getString(R.string.food));
        t14.setTourDetails("");
        t14.setTourAddress(value.getString(R.string.t14_address));
        t14.setTourPhone(value.getString(R.string.t14_phone));
        t14.setTourImage(R.drawable.food_8);

        tourGuide t15=new tourGuide();
        t15.setTourName(value.getString(R.string.t15_name));
        t15.setTourCategory(value.getString(R.string.arts));
        t15.setTourDetails("");
        t15.setTourAddress(value.getString(R.string.t15_address));
        t15.setTourPhone(value.getString(R.string.t15_phone));
        t15.setTourImage(R.drawable.arts_1);

        tourGuide t16=new tourGuide();
        t16.setTourName(value.getString(R.string.t16_name));
        t16.setTourCategory(value.getString(R.string.arts));
        t16.setTourDetails(value.getString(R.string.t16_detail));
        t16.setTourAddress(value.getString(R.string.t16_address));
        t16.setTourPhone(value.getString(R.string.t16_phone));
        t16.setTourImage(R.drawable.arts_2);

        tourGuide t17=new tourGuide();
        t17.setTourName(value.getString(R.string.t17_name));
        t17.setTourCategory(value.getString(R.string.arts));
        t17.setTourDetails(value.getString(R.string.t17_detail));
        t17.setTourAddress(value.getString(R.string.t17_address));
        t17.setTourPhone(value.getString(R.string.t17_phone));
        t17.setTourImage(R.drawable.arts_3);

        tourGuide t18=new tourGuide();
        t18.setTourName(value.getString(R.string.t18_name));
        t18.setTourCategory(value.getString(R.string.arts));
        t18.setTourDetails("");
        t18.setTourAddress(value.getString(R.string.t18_address));
        t18.setTourPhone(value.getString(R.string.t18_phone));
        t18.setTourImage(R.drawable.arts_4);

        tourGuide t19=new tourGuide();
        t19.setTourName(value.getString(R.string.t19_name));
        t19.setTourCategory(value.getString(R.string.transport));
        t19.setTourDetails(value.getString(R.string.t19_detail));
        t19.setTourAddress(value.getString(R.string.t19_address));
        t19.setTourPhone(value.getString(R.string.t19_phone));
        t19.setTourImage(R.drawable.transport_1);

        tourGuide t20=new tourGuide();
        t20.setTourName(value.getString(R.string.t20_name));
        t20.setTourCategory(value.getString(R.string.transport));
        t20.setTourDetails(value.getString(R.string.t20_detail));
        t20.setTourAddress(value.getString(R.string.t20_address));
        t20.setTourPhone(value.getString(R.string.t20_phone));
        t20.setTourImage(R.drawable.transport_2);

        tourGuide t21=new tourGuide();
        t21.setTourName(value.getString(R.string.t21_name));
        t21.setTourCategory(value.getString(R.string.transport));
        t21.setTourDetails(value.getString(R.string.t21_detail));
        t21.setTourAddress(value.getString(R.string.t21_address));
        t21.setTourPhone(value.getString(R.string.t21_phone));
        t21.setTourImage(R.drawable.transport_3);

        tourGuide t22=new tourGuide();
        t22.setTourName(value.getString(R.string.t22_name));
        t22.setTourCategory(value.getString(R.string.transport));
        t22.setTourDetails(value.getString(R.string.t22_detail));
        t22.setTourAddress(value.getString(R.string.t22_address));
        t22.setTourPhone(value.getString(R.string.t22_phone));
        t22.setTourImage(R.drawable.transport_4);

        tourGuide t23=new tourGuide();
        t23.setTourName(value.getString(R.string.t23_name));
        t23.setTourCategory(value.getString(R.string.transport));
        t23.setTourDetails(value.getString(R.string.t23_detail));
        t23.setTourAddress(value.getString(R.string.t23_address));
        t23.setTourPhone(value.getString(R.string.t23_phone));
        t23.setTourImage(R.drawable.transport_5);

        tourGuide t24=new tourGuide();
        t24.setTourName(value.getString(R.string.t24_name));
        t24.setTourCategory(value.getString(R.string.shop));
        t24.setTourDetails(value.getString(R.string.t24_detail));
        t24.setTourAddress(value.getString(R.string.t24_address));
        t24.setTourPhone(value.getString(R.string.t24_phone));
        t24.setTourImage(R.drawable.shop_1);

        tourGuide t25=new tourGuide();
        t25.setTourName(value.getString(R.string.t25_name));
        t25.setTourCategory(value.getString(R.string.shop));
        t25.setTourDetails("");
        t25.setTourAddress(value.getString(R.string.t25_address));
        t25.setTourPhone(value.getString(R.string.t25_phone));
        t25.setTourImage(R.drawable.shop_2);

        tourGuide t26=new tourGuide();
        t26.setTourName(value.getString(R.string.t26_name));
        t26.setTourCategory(value.getString(R.string.shop));
        t26.setTourDetails(value.getString(R.string.t26_detail));
        t26.setTourAddress(value.getString(R.string.t26_address));
        t26.setTourPhone(value.getString(R.string.t26_phone));
        t26.setTourImage(R.drawable.shop_3);

        tourGuide t27=new tourGuide();
        t27.setTourName(value.getString(R.string.t27_name));
        t27.setTourCategory(value.getString(R.string.shop));
        t27.setTourDetails("");
        t27.setTourAddress(value.getString(R.string.t27_address));
        t27.setTourPhone(value.getString(R.string.t27_phone));
        t27.setTourImage(R.drawable.shop_4);

        tourGuide t28=new tourGuide();
        t28.setTourName(value.getString(R.string.t28_name));
        t28.setTourCategory(value.getString(R.string.shop));
        t28.setTourDetails("");
        t28.setTourAddress(value.getString(R.string.t28_address));
        t28.setTourPhone(value.getString(R.string.t28_phone));
        t28.setTourImage(R.drawable.shop_5);

        tourGuide t29=new tourGuide();
        t29.setTourName(value.getString(R.string.t29_name));
        t29.setTourCategory(value.getString(R.string.outdoor));
        t29.setTourDetails(value.getString(R.string.t29_detail));
        t29.setTourAddress(value.getString(R.string.t29_address));
        t29.setTourPhone(value.getString(R.string.t29_phone));
        t29.setTourImage(R.drawable.outdoor_1);

        tourGuide t30=new tourGuide();
        t30.setTourName(value.getString(R.string.t30_name));
        t30.setTourCategory(value.getString(R.string.outdoor));
        t30.setTourDetails("");
        t30.setTourAddress(value.getString(R.string.t30_address));
        t30.setTourPhone(value.getString(R.string.t30_phone));
        t30.setTourImage(R.drawable.outdoor_2);

        tourGuide t31=new tourGuide();
        t31.setTourName(value.getString(R.string.t31_name));
        t31.setTourCategory(value.getString(R.string.outdoor));
        t31.setTourDetails("");
        t31.setTourAddress(value.getString(R.string.t31_address));
        t31.setTourPhone(value.getString(R.string.t31_phone));
        t31.setTourImage(R.drawable.outdoor_3);

        tourGuide t32=new tourGuide();
        t32.setTourName(value.getString(R.string.t32_name));
        t32.setTourCategory(value.getString(R.string.outdoor));
        t32.setTourDetails("");
        t32.setTourAddress(value.getString(R.string.t32_address));
        t32.setTourPhone(value.getString(R.string.t32_phone));
        t32.setTourImage(R.drawable.outdoor_4);

        tourGuides.add(t1);
        tourGuides.add(t2);
        tourGuides.add(t3);
        tourGuides.add(t4);
        tourGuides.add(t5);
        tourGuides.add(t6);
        tourGuides.add(t7);
        tourGuides.add(t8);
        tourGuides.add(t9);
        tourGuides.add(t10);
        tourGuides.add(t11);
        tourGuides.add(t12);
        tourGuides.add(t13);
        tourGuides.add(t14);
        tourGuides.add(t15);
        tourGuides.add(t16);
        tourGuides.add(t17);
        tourGuides.add(t18);
        tourGuides.add(t19);
        tourGuides.add(t20);
        tourGuides.add(t21);
        tourGuides.add(t22);
        tourGuides.add(t23);
        tourGuides.add(t24);
        tourGuides.add(t25);
        tourGuides.add(t26);
        tourGuides.add(t27);
        tourGuides.add(t28);
        tourGuides.add(t29);
        tourGuides.add(t30);
        tourGuides.add(t31);
        tourGuides.add(t32);
        return tourGuides;
    }


}
