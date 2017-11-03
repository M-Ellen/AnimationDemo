package com.pzm.turntable;

import android.view.animation.Interpolator;


/**
 * Created by pzm on 2017/11/3
 */

public class MyAccelerateDecelerateInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float input) {

//        (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
//
//        (float)Math.pow(input, mDoubleFactor);
//
//        (float)(1.0f - Math.pow((1.0f - input), 2 * mFactor));

//        if(input <= 0.7)
//        {
//            return (float)(Math.cos((input + 1) * Math.PI) / 2f) + 0.5f;
//        }else
//        {
//            return (float)(1.0f - Math.pow((1.0f - input), 2 ));
//        }


        if(input <= 0.5)
        {
            return (float) Math.pow(input,2) * 3f;
        }
        else
        {
            return (float)(0.75f - Math.pow((1.0f - input), 2)) * 0.9f ;
        }


//        if(input <= 0.5)
//        {
//            return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
//        }


    }
}
