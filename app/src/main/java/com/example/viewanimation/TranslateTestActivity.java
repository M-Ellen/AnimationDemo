package com.example.viewanimation;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class TranslateTestActivity extends BaseActivity {

    @Override
    protected void starXmlAnimation() {
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        mImage.startAnimation(animation);
    }

    @Override
    protected void starCodeAnimation() {

//        TranslateAnimation translateAnimation = new TranslateAnimation();
//        translateAnimation.setDuration(400);
//        mImage.startAnimation(translateAnimation);
    }

}
