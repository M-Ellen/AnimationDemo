package com.example.animation;


import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import static android.content.ContentValues.TAG;

public class TranslateTestActivity extends BaseActivity {

    /**
     * 使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @Override
    protected void starXmlAnimation() {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.translate);
        mImage.startAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.d(TAG, "onAnimationStart: "+mImage.getTranslationX());
//                Log.d(TAG, "onAnimationStart: "+mImage.getTranslationY());
//                Log.d(TAG, "onAnimationStart: "+mImage.getX());
//                Log.d(TAG, "onAnimationStart: "+mImage.getY());
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.d(TAG, "onAnimationEnd: "+mImage.getTranslationX());
//                Log.d(TAG, "onAnimationEnd: "+mImage.getTranslationY());
//                Log.d(TAG, "onAnimationEnd: "+mImage.getX());
//                Log.d(TAG, "onAnimationEnd: "+mImage.getY());
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }

    /**
     * 使用Animation的子类 TranslateAnimation
     */
    @Override
    protected void starCodeAnimation() {

//        TranslateAnimation translateAnimation = new TranslateAnimation(0,180,0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        mImage.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart: "+mImage.getTranslationX());
                Log.d(TAG, "onAnimationStart: "+mImage.getTranslationY());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd: "+mImage.getTranslationX());
                Log.d(TAG, "onAnimationEnd: "+mImage.getTranslationY());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
