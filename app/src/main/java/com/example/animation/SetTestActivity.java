package com.example.animation;


import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

public class SetTestActivity extends BaseActivity {

    @Override
    protected void starXmlAnimation() {
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.set);
        mImage.startAnimation(animation);

    }

    @Override
    protected void starCodeAnimation() {
        AnimationSet animationSet1 = new AnimationSet(false);//一级集合
        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1, 1.4f, 1, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet animationSet2 = new AnimationSet(true);//二级集合
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.4f, 0, 1.4f, 0, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

        animationSet2.addAnimation(scaleAnimation2);
        animationSet2.addAnimation(rotateAnimation);
        animationSet2.setInterpolator(new DecelerateInterpolator());
        animationSet2.setDuration(2000);
        animationSet2.setStartOffset(2000);

        animationSet1.addAnimation(scaleAnimation1);
        animationSet1.addAnimation(animationSet2);
        animationSet1.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet1.setDuration(2000);

        mImage.startAnimation(animationSet1);

    }
}
