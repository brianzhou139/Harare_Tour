package com.edufree.harare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

public class FlashScreenActivity extends AppCompatActivity {

    private CountDownTimer myTimer;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        foto=(ImageView)findViewById(R.id.back_foto);

        myTimer=new CountDownTimer(2500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };

        myTimer.start();
    }
}
