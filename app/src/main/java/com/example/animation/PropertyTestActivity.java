package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.FloatArrayEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PropertyTestActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
	private static final String[] PROPERTY_ANIMATION = { "ValueAnimator", "ObjectAnimator", "AnimatorSet", "ViewPropertyAnimator" };
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
		mContentLayout = (RelativeLayout) findViewById(R.id.content_layout);

		mSpinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PROPERTY_ANIMATION);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);
		mSpinner.setOnItemSelectedListener(this);

		mObjectAnimatorlayout = (RelativeLayout) findViewById(R.id.objectAnimator_layout);
		mTarget = (TextView) findViewById(R.id.property_target);
		tv1 = (TextView) findViewById(R.id.textView);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		tv4 = (TextView) findViewById(R.id.textView4);

		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
		switch (position)
		{
		case 0:
			mObjectAnimatorlayout.setVisibility(View.GONE);
			endAllAnimation();

			valueAnimator = ValueAnimator.ofFloat(0, 200, 0);// 实例化，并设置需要变化的属性值范围
			// valueAnimator = ValueAnimator.ofObject(new IntEvaluator(), -100f,
			// 100f);
//			valueAnimator = ValueAnimator.ofArgb(BLCAK, RED, BLUE);
			valueAnimator.setDuration(2000);// 设置动画时间
			valueAnimator.setRepeatCount(ValueAnimator.INFINITE);// 设置动画一直重复
			valueAnimator.setRepeatMode(ValueAnimator.REVERSE);// 设置动画重复的模式
			valueAnimator.start();// 开始动画
			// 注册监听，监听属性值的变化，并设置给目标对象
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

			objectAnimator = ObjectAnimator.ofInt(mTarget, "textColor", BLCAK, RED, BLUE);// 实例化，设置文本颜色值从BLCAK~RED~BLUE变化,对于颜色属性计算值，下面必须添加setEvaluator()方法。
//			objectAnimator = ObjectAnimator.ofArgb(mTarget, "textColor", BLCAK, RED, BLUE);// 对于颜色的变化，可以直接使用该方法，下面则不用再设置setEvaluator()
			// objectAnimator = ObjectAnimator.ofFloat(mTarget, "textColor",
			// BLCAK, RED, BLUE);//实例化
			objectAnimator.setDuration(2000);// 设置动画持续时间
			objectAnimator.setEvaluator(new ArgbEvaluator());// 设置估算值
			objectAnimator.setRepeatCount(ValueAnimator.INFINITE);// 设置一直重复
			objectAnimator.setRepeatMode(ValueAnimator.REVERSE);// 设置一直重复模式
			objectAnimator.start();// 开始动画

			break;
		case 2:
			mObjectAnimatorlayout.setVisibility(View.GONE);
			endAllAnimation();
			ObjectAnimator translationX = ObjectAnimator.ofFloat(mTarget, "translationX", 400f, 0f);
			ObjectAnimator alpha = ObjectAnimator.ofFloat(mTarget, "alpha", 1f, 0f, 1f);
			ObjectAnimator rotation = ObjectAnimator.ofFloat(mTarget, "rotation", 0f, 360f);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTarget, "scaleY", 1f, 3f, 1f);

			animatorSet = new AnimatorSet();
			animatorSet.play(rotation).with(alpha).after(translationX).before(scaleY);
			animatorSet.setDuration(3000);
			animatorSet.start();

			// 使用xml实现属性动画集合
			// Animator animator = AnimatorInflater.loadAnimator(this,
			// R.animator.property_set);
			// animator.setTarget(mTarget);
			// animator.start();
			break;
		case 3:
			mObjectAnimatorlayout.setVisibility(View.GONE);
			endAllAnimation();

			/**
			 * ViewPropertyAnimator 与 Animator对比，前者更为简洁
			 */
//			ObjectAnimator animX = ObjectAnimator.ofFloat(mTarget, "x", 50f);
//			ObjectAnimator animY = ObjectAnimator.ofFloat(mTarget, "y", 100f);
//			AnimatorSet animSetXY = new AnimatorSet();
//			animSetXY.playTogether(animX, animY);
//			animSetXY.start();
//			mTarget.animate().x(50f).y(100f);

//			mTarget.animate().xBy(20).yBy(20);  //相对于当前位置，移动的偏移量
//			mTarget.animate().x(20).y(20);      //移动到指定位置为（20,20）

			mTarget.animate().yBy(200f).rotation(360).scaleY(2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
			break;
		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{

	}

	private void endAllAnimation()
	{
		if (valueAnimator != null)
		{
			mTarget.setTranslationX(0);
			valueAnimator.end();
		}
		if (objectAnimator != null)
		{
			objectAnimator.end();
		}
		if (animatorSet != null)
		{
			animatorSet.end();
		}
	}

	@Override
	public void onClick(View v)
	{
		endAllAnimation();

		ObjectAnimator objectAnimator = null;
		switch (v.getId())
		{
		case R.id.textView:
			objectAnimator = ObjectAnimator.ofFloat(mTarget, "alpha", 1f, 0f, 1f);

			break;
		case R.id.textView2:
			// objectAnimator = ObjectAnimator.ofFloat(mTarget, "scaleX",
			// 1f,0.2f, 1f);
			objectAnimator = ObjectAnimator.ofFloat(mTarget, "scaleY", 1f, 3f, 1f);
			break;
		case R.id.textView3:
			objectAnimator = ObjectAnimator.ofFloat(mTarget, "translationX", 0f, 200, 0f);
			// objectAnimator = ObjectAnimator.ofFloat(mTarget, "translationY",
			// 0f, 200, 0f);
			break;
		case R.id.textView4:
			// 默认轴点是控件的中心，也可自行设置
			// mTarget.setPivotX(mTarget.getRight()/2);
			// mTarget.setPivotY(mTarget.getTop()/2);
			 objectAnimator = ObjectAnimator.ofFloat(mTarget, "rotationY", 0f, 360);
//			 objectAnimator = ObjectAnimator.ofFloat(mTarget, "rotationX", 0f, 360);
//			objectAnimator = ObjectAnimator.ofFloat(mTarget, "rotation", 0f, 360);
			break;

		}
		objectAnimator.setDuration(1000);
		objectAnimator.start();
	}

	private void setListener()
	{
		// 实现 AnimatorListener 接口
		objectAnimator.addListener(new Animator.AnimatorListener()
		{
			@Override
			public void onAnimationStart(Animator animation)
			{

			}

			@Override
			public void onAnimationEnd(Animator animation)
			{

			}

			@Override
			public void onAnimationCancel(Animator animation)
			{

			}

			@Override
			public void onAnimationRepeat(Animator animation)
			{

			}
		});

		// 实现 AnimatorListenerAdapter 抽象类
		objectAnimator.addListener(new AnimatorListenerAdapter()
		{
			// 可以自行覆盖未实现方法
		});

		objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{

			}
		});
	}
}
