package com.example.animation;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PropertyTestActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    private static final String[] PROPERTY_ANIMATION = {"ValueAnimator", "ObjectAnimator", "AnimatorSet"};
    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int BLCAK = 0xff000000;
    private static final int GREEN = 0xff80ff80;

    private Spinner mSpinner = null;
    private RelativeLayout mContentLayout = null;
    private ValueAnimator valueAnimator = null;
    private ObjectAnimator objectAnimator = null;
    private AnimatorSet animatorSet = null;

    private RelativeLayout mObjectAnimatorlayout;
    private TextView mTarget;
    private TextView tv1, tv2, tv3, tv4, tv5;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_test);
        mContentLayout = (RelativeLayout)findViewById(R.id.content_layout);

        mSpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, PROPERTY_ANIMATION);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        mObjectAnimatorlayout = (RelativeLayout)findViewById(R.id.objectAnimator_layout);
        mTarget = (TextView)findViewById(R.id.property_target);
        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position) {
            case 0:
                mObjectAnimatorlayout.setVisibility(View.GONE);
                endAllAnimation();

//                valueAnimator.ofPropertyValuesHolder();
                valueAnimator = ValueAnimator.ofFloat(-100, 100);//实例化，并设置需要变化的属性值范围
//                valueAnimator = ValueAnimator.ofObject(new IntEvaluator(), -100f, 100f);
                valueAnimator.setDuration(2000);//设置动画时间
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置动画一直重复
                valueAnimator.setRepeatMode(ValueAnimator.REVERSE);//设置动画重复的模式
                valueAnimator.start();//开始动画
                //注册监听，监听属性值的变化，并设置给目标对象
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        float value = (float) animation.getAnimatedValue();
                        mTarget.setTranslationX(value);
                    }
                });
                break;

            case 1:
                mObjectAnimatorlayout.setVisibility(View.VISIBLE);
                endAllAnimation();

                objectAnimator = ObjectAnimator.ofInt(mTarget, "textColor", BLCAK, RED, BLUE);//实例化，设置文本颜色值从BLCAK~RED~BLUE变化
//                objectAnimator = ObjectAnimator.ofFloat(mTarget, "textColor", BLCAK, RED, BLUE);//实例化
                objectAnimator.setDuration(2000);//设置动画持续时间
                objectAnimator.setEvaluator(new ArgbEvaluator());//设置估算值
                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置一直重复
                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);//设置一直重复模式
                objectAnimator.start();//开始动画

                break;
            case 2:
                mObjectAnimatorlayout.setVisibility(View.GONE);
                endAllAnimation();
                animatorSet = new AnimatorSet();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    private void endAllAnimation(){
        if(valueAnimator != null){
            mTarget.setTranslationX(0);
            valueAnimator.end();
        }
        if(objectAnimator != null){
            objectAnimator.end();
        }
        if(animatorSet != null){
            animatorSet.end();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.textView :

                break;
            case R.id.textView2 :

                break;
            case R.id.textView3 :

                break;
            case R.id.textView4 :

                break;
            case R.id.textView5 :

                break;

        }
    }
}
