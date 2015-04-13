package com.journey.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Animations {
	/**
	 * 淡入动画
	 * 
	 * @return
	 */
	public static Animation inFromRightAnimation() {
		Animation inFromRight = new AlphaAnimation(0.5f, 1);
		inFromRight.setDuration(200);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * 淡出动画
	 * 
	 * @return
	 */
	public static Animation outToRightAnimation() {
		Animation outtoLeft = new AlphaAnimation(1, 0.5f);
		outtoLeft.setDuration(200);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}
}
