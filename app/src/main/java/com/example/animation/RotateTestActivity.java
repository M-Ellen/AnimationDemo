package com.example.animation;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

/**
 * 旋转动画
 *
 */
public class RotateTestActivity extends BaseActivity {

    /**
     * 使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @Override
    protected void starXmlAnimation() {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        mImage.startAnimation(animation);
    }

    /**
     * 使用Animation的子类 TranslateAnimation
     */
    @Override
    protected void starCodeAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0,180);
        rotateAnimation.setDuration(3000);
        mImage.startAnimation(rotateAnimation);
    }

}
