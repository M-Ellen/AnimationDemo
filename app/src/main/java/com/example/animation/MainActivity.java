package com.example.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 介绍View动画的具体的实现 属性动画的具体实现
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTvAlpha, mTvScale, mTvTranslate, mTvRotate, mTvSet;
    private TextView mTvInterpolator;
    private TextView mTvProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvAlpha = (TextView)findViewById(R.id.tv_alpha);
        mTvScale = (TextView)findViewById(R.id.tv_scale);
        mTvTranslate = (TextView)findViewById(R.id.tv_translate);
        mTvRotate = (TextView)findViewById(R.id.tv_rotate);
        mTvSet = (TextView)findViewById(R.id.tv_set);
        mTvInterpolator = (TextView)findViewById(R.id.tv_interpolator);
        mTvProperty = (TextView) findViewById(R.id.tv_property);
        
        mTvAlpha.setOnClickListener(this);
        mTvScale.setOnClickListener(this);
        mTvTranslate.setOnClickListener(this);
        mTvRotate.setOnClickListener(this);
        mTvSet.setOnClickListener(this);
        mTvInterpolator.setOnClickListener(this);
        mTvProperty.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case  R.id.tv_alpha:
                enterTestActivity(AlphaTestActivity.class);

                break;
            case  R.id.tv_scale:
                enterTestActivity(ScaleTestActivity.class);

                break;
            case  R.id.tv_translate:
                enterTestActivity(TranslateTestActivity.class);

                break;
            case  R.id.tv_rotate:
                enterTestActivity(RotateTestActivity.class);
                break;
            case  R.id.tv_set:
                enterTestActivity(SetTestActivity.class);
                break;
            case  R.id.tv_interpolator:
                enterTestActivity(InterpolatorTestActivity.class);
                break;
            case  R.id.tv_property:
                enterTestActivity(PropertyTestActivity.class);
                break;

            default:
                break;
        }
    }

    /**
     * 调到一个Activity的方法，通用的方法
     * @param clazz
     */
    private void enterTestActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
//        overridePendingTransition();
    }
}
