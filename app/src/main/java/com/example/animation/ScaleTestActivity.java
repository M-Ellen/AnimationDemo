package com.example.animation;


import android.util.Log;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

public class ScaleTestActivity extends BaseActivity {

    /**
     *  使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @Override
    protected void starXmlAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mImage.startAnimation(animation);
        ViewParent parent = mImage.getParent();
        Log.d("debug", "starXmlAnimation: "+parent);
    }

    /**
     * 使用Animation的子类AlphaAnimation
     */
    @Override
    protected void starCodeAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        mImage.startAnimation(scaleAnimation);
    }

}
