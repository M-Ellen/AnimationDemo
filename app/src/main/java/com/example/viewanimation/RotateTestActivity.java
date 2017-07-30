package com.example.viewanimation;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

/**
 * 旋转动画
 *
 */
public class RotateTestActivity extends BaseActivity {

    /**
     * 使用xml实现
     */
    @Override
    protected void starXmlAnimation() {
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mImage.startAnimation(animation);
    }

    /**
     * 使用Code实现
     */
    @Override
    protected void starCodeAnimation() {
//        RotateAnimation rotateAnimation = new RotateAnimation();
//        rotateAnimation.setDuration(400);
//        mImage.startAnimation(rotateAnimation);
    }

}
