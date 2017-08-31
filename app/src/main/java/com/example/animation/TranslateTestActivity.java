package com.example.animation;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class TranslateTestActivity extends BaseActivity {

    /**
     * 使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @Override
    protected void starXmlAnimation() {
        mImage.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.translate);
        mImage.startAnimation(animation);
    }

    /**
     * 使用Animation的子类 TranslateAnimation
     */
    @Override
    protected void starCodeAnimation() {
        mImage.clearAnimation();
        TranslateAnimation translateAnimation = new TranslateAnimation(0,180,0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        mImage.startAnimation(translateAnimation);


    }

}
