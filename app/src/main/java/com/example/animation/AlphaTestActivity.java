package com.example.animation;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * 透明度动画
 */

public class AlphaTestActivity extends BaseActivity {

    /**
     *  使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @Override
    protected void starXmlAnimation() {
        mImage.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mImage.startAnimation(animation);
    }

    /**
     * 使用Animation的子类AlphaAnimation
     */
    @Override
    protected void starCodeAnimation() {
        mImage.clearAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(3000);
        mImage.startAnimation(alphaAnimation);
    }

}
