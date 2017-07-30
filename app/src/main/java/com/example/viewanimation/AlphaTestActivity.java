package com.example.viewanimation;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * 透明度动画
 * 你妹你妹你妹好的
 */

public class AlphaTestActivity extends BaseActivity {

    @Override
    protected void starXmlAnimation() {
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mImage.startAnimation(animation);
    }

    @Override
    protected void starCodeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(400);
        mImage.startAnimation(alphaAnimation);
    }

}
