package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class InterpolatorTestActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    private static final String[] INTERPOLATORS = {
            "Accelerate", "Decelerate", "Accelerate/Decelerate",
            "Anticipate", "Overshoot", "Anticipate/Overshoot",
            "Linear","Bounce","CycleInterpolator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_test);

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, INTERPOLATORS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        final View target = findViewById(R.id.target);
        final View targetParent = (View) target.getParent();

        Animation a = new TranslateAnimation(0.0f, 0.0f, 0.0f,
                targetParent.getHeight() - target.getY() - target.getHeight() - targetParent.getPaddingBottom());
        a.setDuration(1000);
        a.setStartOffset(300);
        a.setRepeatMode(Animation.RESTART);
        a.setRepeatCount(Animation.INFINITE);

        switch (position) {
            case 0:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.accelerate_interpolator));
//                a.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 1:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.decelerate_interpolator));
                break;
            case 2:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.accelerate_decelerate_interpolator));
                break;
            case 3:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.anticipate_interpolator));
                break;
            case 4:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.overshoot_interpolator));
                break;
            case 5:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.anticipate_overshoot_interpolator));
                break;
            case 6:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.linear_interpolator));
                break;
            case 7:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.bounce_interpolator));
                break;
            case 8:
                a.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.cycle_interpolator));
                break;
             }
        target.startAnimation(a);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
