package com.example.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void rotateAnimation(View view){
		ObjectAnimator o1=ObjectAnimator.ofFloat(view, "rotationX", 0.0f,360.0f).setDuration(1000);
		ObjectAnimator o2=ObjectAnimator.ofFloat(view, "alpha", 1.0f,0.5f).setDuration(1000);
		AnimatorSet set=new AnimatorSet();
		set.playTogether(o1,o2);
		set.start();
}
	/**
	 * 抛物线
	 * @param view
	 */
	public void paowuxian(final View view)
	{

		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(3000);
		valueAnimator.setObjectValues(new PointF(view.getX(), view.getY()));
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
		{
			// fraction = t / duration
			@Override
			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue)
			{
				// x方向200px/s ，则y方向0.5 * 10 * t
				PointF point = new PointF();
				point.x = 200 * fraction * 3;
				point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
				return point;
			}
		});

		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				PointF point = (PointF) animation.getAnimatedValue();
				view.setX(point.x);
				view.setY(point.y);

			}
		});
	}
}
