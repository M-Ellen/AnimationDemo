package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class InterpolatorTestActivity extends Activity implements AdapterView.OnItemSelectedListener, MyAccelerateInterpolator.onInterpolationChangeListener
{
    private static final String[] INTERPOLATORS = {
            "Test MyAccelerate/Decelerate",
            "Accelerate", "Decelerate", "Accelerate/Decelerate",
            "Anticipate", "Overshoot", "Anticipate/Overshoot",
            "Linear","Bounce","CycleInterpolator", "PathInterpolator","FastInterpolator"};

    private MyAccelerateInterpolator mInterpolator = new MyAccelerateInterpolator();

//    private MyAccelerateInterpolator mListener = new MyAccelerateInterpolator();

    private ObjectAnimator a = null;
    private int mTotalTime = 2000;

    private RelativeLayout mTestInterpolator = null;
    private View target = null;
    private View targetParent = null;

    private TextView distance_y = null;
    private TextView total_y = null;
    private TextView distance_y_percent = null;
    private TextView interpolator_y = null;

    private TextView distance_time = null;
    private TextView total_time = null;
    private TextView distance_time_percent = null;
    private TextView interpolator_t = null;
    private Button pause = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_test);
        mInterpolator.setOnInterpolationListener(this);

        mTestInterpolator = (RelativeLayout)findViewById(R.id.testInterpolator);

        target = findViewById(R.id.target);
        targetParent = (View) target.getParent();

        distance_y = (TextView)findViewById(R.id.distance_y);
        total_y = (TextView)findViewById(R.id.total_y);
        distance_y_percent = (TextView)findViewById(R.id.distance_y_percent);
        interpolator_y = (TextView)findViewById(R.id.interpolator_y);

        distance_time = (TextView)findViewById(R.id.distance_time);
        total_time = (TextView)findViewById(R.id.total_time);
        distance_time_percent = (TextView)findViewById(R.id.distance_time_percent);
        interpolator_t = (TextView)findViewById(R.id.interpolator_t);

        pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(a != null)
                {
                    if(a.isPaused()){
                        a.start();
                        pause.setText("Pause");
                    }else if(a.isRunning())
                    {
                        a.pause();
                        pause.setText("Start");
                    }else
                    {
                        a.start();
                        pause.setText("Pause");
                    }
                }

            }
        });

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, INTERPOLATORS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

        final float startY = target.getY();
        final float engY = targetParent.getHeight() - target.getY() - target.getHeight() - targetParent.getPaddingBottom();

        Animation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f,targetParent.getHeight() - target.getY() - target.getHeight() - targetParent.getPaddingBottom());
//        animation.setStartOffset(100);
        animation.setDuration(3000);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(-1);
//        target.clearAnimation();

        if(position == 0)
        {
            mTestInterpolator.setVisibility(View.VISIBLE);
        }else {
            mTestInterpolator.setVisibility(View.GONE);
            target.clearAnimation();
            a.end();
        }

        switch (position) {
            case 0:
                distance_y.setText("运动距离："+startY+"");
                total_y.setText("总距离："+ engY+"");
                total_time.setText("总时间："+mTotalTime+"");

                a = ObjectAnimator.ofFloat(target, "translationY", engY);
                a.setDuration(mTotalTime);
                a.setRepeatMode(ValueAnimator.RESTART);
                a.start();
                mInterpolator.setTargetView(target);
                a.setInterpolator(mInterpolator);
                a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        distance_y.setText("运动距离："+(target.getY() - startY) +"");
                        distance_time.setText("动画时间："+a.getCurrentPlayTime());
                        distance_y_percent.setText("距离百分比："+(target.getY()-startY) / engY);
                        distance_time_percent.setText("时间百分比：" + ((float)a.getCurrentPlayTime() / (float) a.getDuration()));
                    }
                });

                a.addListener(new AnimatorListenerAdapter()
                {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        super.onAnimationEnd(animation);
                        distance_y.setText("运动距离："+(target.getY() - startY) +"");
                        distance_y_percent.setText("距离百分比："+(target.getY()-startY) / engY+"");

                        distance_time.setText("动画时间："+a.getDuration());
                        distance_time_percent.setText("时间百分比："+1.0);
                        pause.setText("Start");
                        //恢复原来状态
                        target.setX(10);
                        target.setY(200);
                    }
                });
                return;

            case 1:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.accelerate_interpolator));
                break;
            case 2:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.decelerate_interpolator));
                break;
            case 3:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.accelerate_decelerate_interpolator));
                break;
            case 4:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.anticipate_interpolator));
                break;
            case 5:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.overshoot_interpolator));
                break;
            case 6:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.anticipate_overshoot_interpolator));
                break;
            case 7:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.linear_interpolator));
                break;
            case 8:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.bounce_interpolator));
                break;
            case 9:
                animation.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.cycle_interpolator));
                break;
            case 10:
                animation.setInterpolator(new PathInterpolator(10, 30));
                break;
            case 11:
                animation.setInterpolator(new FastOutLinearInInterpolator());
                break;
             }

        target.startAnimation(animation);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onInterpolationChange(float y, float t)
    {
        interpolator_y.setText("公式y="+ y);
        interpolator_t.setText("公式t="+ t);
    }
}
