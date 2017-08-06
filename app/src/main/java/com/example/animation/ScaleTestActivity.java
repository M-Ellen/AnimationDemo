package com.example.animation;


import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import static android.R.attr.animation;
import static android.content.ContentValues.TAG;

public class ScaleTestActivity extends BaseActivity {

    /**
     *  使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void starXmlAnimation() {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.scale);
        mImage.startAnimation(animation);

    }

    /**
     * 使用Animation的子类AlphaAnimation
     */
    @Override
    protected void starCodeAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setDuration(3000);
        mImage.startAnimation(scaleAnimation);

    }

}
