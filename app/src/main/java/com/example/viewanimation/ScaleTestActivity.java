package com.example.viewanimation;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

public class ScaleTestActivity extends BaseActivity {

    @Override
    protected void starXmlAnimation() {
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mImage.startAnimation(animation);
    }

    @Override
    protected void starCodeAnimation() {
//        ScaleAnimation scaleAnimation = new ScaleAnimation();
//        scaleAnimation.setDuration(400);
//        mImage.startAnimation(scaleAnimation);
    }

}
