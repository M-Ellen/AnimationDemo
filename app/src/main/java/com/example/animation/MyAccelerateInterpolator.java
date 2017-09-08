
package com.example.animation;

import android.view.View;
import android.view.animation.Interpolator;


public class MyAccelerateInterpolator implements Interpolator
{
    private final float mFactor;
    private final double mDoubleFactor;
    private View mView = null;

    public MyAccelerateInterpolator() {
        mFactor = 1.0f;
        mDoubleFactor = 2.0;
    }

    /**
     * Constructor
     *
     * @param factor Degree to which the animation should be eased. Seting
     *        factor to 1.0f produces a y=x^2 parabola. Increasing factor above
     *        1.0f  exaggerates the ease-in effect (i.e., it starts even
     *        slower and ends evens faster)
     */
    public MyAccelerateInterpolator(float factor) {
        mFactor = factor;
        mDoubleFactor = 2 * mFactor;
    }

    public float getInterpolation(float t) {
        float y = 0;
        if (mFactor == 1.0f) {
            y =  t * t;
        } else {
            y =  (float)Math.pow(t, mDoubleFactor);
        }
        mListener.onInterpolationChange(y, t);
        return y;
    }

    public void setTargetView(View target)
    {
        mView = target;
    }

    public interface onInterpolationChangeListener
    {
        void onInterpolationChange(float y, float t);
    }

    onInterpolationChangeListener  mListener = null;

    public void setOnInterpolationListener(onInterpolationChangeListener  listener){
        mListener = listener;
    }
}
