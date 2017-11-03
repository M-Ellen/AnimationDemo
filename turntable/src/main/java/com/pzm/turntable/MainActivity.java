package com.pzm.turntable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackIcon = null;
    private Button mPlayBtn = null;
    private ObjectAnimator animator1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBackIcon = (ImageView)findViewById(R.id.icon_base);
        mPlayBtn = (Button)findViewById(R.id.btn_play);
        mPlayBtn.setOnClickListener(this);
        mPlayBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
        mBackIcon.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        normalAni();

    }

    private void normalAni(){
        animator1 = ObjectAnimator.ofFloat(mBackIcon, "rotation", 0, 360);
        animator1.setRepeatCount(-1);
        animator1.setDuration(7000);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play :
                starAni();
                break;
        }
    }

    private void starAni() {
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mBackIcon, "rotation", 360*getRandom() + mBackIcon.getRotation());
//        animator2.setRepeatCount(8);
        animator2.setDuration(6000);
        animator2.setInterpolator(new MyAccelerateDecelerateInterpolator());

        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animator1.cancel();
                mPlayBtn.setClickable(false);
                mPlayBtn.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mPlayBtn.setClickable(true);
                mPlayBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
            }
        });
        animator2.start();
    }

    private float getRandom(){
        Random random = new Random();
        return random.nextFloat() + 6;
    }
}
