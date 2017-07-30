package com.example.viewanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 介绍View动画的具体的实现 海域属性动画的具体实现
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTvAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvAlpha = (TextView)findViewById(R.id.tv_alpha);

        mTvAlpha.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case  R.id.tv_alpha:
                enterTestActivity(AlphaTestActivity.class);

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
    }
}
