package com.journey.utils;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 对话框类
 *
 * @author PGQ
 */
public class DialogUtil {

	/**
	 * 只显示标题
	 *
	 * @param context
	 * @param title
	 */
	public static void showTitle(Context context, String title) {
		new SweetAlertDialog(context).setContentText(title).show();
	}

	/**
	 * 只显示标题
	 *
	 * @param context
	 * @param title
	 */
	public static void showTitle(Context context, int title) {
		new SweetAlertDialog(context).setContentText(context.getResources().getString(title)).show();
	}

	/**
	 * 显示标题和内容
	 *
	 * @param context
	 * @param title
	 * @param content
	 */
	public static void showTitleAndContent(Context context, String title, String content) {
		new SweetAlertDialog(context).setTitleText(title).setContentText(content).show();
	}

	/**
	 * 显示内容
	 *
	 * @param context
	 * @param content
	 */
	public static void showContent(Context context, String content) {
		new SweetAlertDialog(context).setContentText(content).show();
	}

	/**
	 * 显示异常样式 String
	 *
	 * @param context
	 * @param title
	 * @param content
	 */
	public static void error(Context context, String title, String content) {
		new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(title).setContentText(content).show();
	}

	/**
	 * 显示异常样式 int
	 *
	 * @param context
	 * @param title
	 */
	public static void error(Context context, int title) {
		new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(context.getResources().getString(title)).show();
	}

	/**
	 * 显示警告样式
	 *
	 * @param context
	 * @param title 标题
	 * @param content 内容
	 * @param confirmText 确认按钮上的字
	 */
	public static void warning(Context context, String title, String content, String confirmText) {
		new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText(title).setContentText(content)
				.setConfirmText(confirmText).show();
	}

	/**
	 * 自定义头部图像
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @param image
	 */
	public static void customImage(Context context, String title, String content, int image) {
		new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).setTitleText(title).setContentText(content)
				.setCustomImage(image).show();
	}

	/**
	 * 成功 String
	 *
	 * @param context
	 * @param title
	 */
	public static void successful(Context context, String title) {
		new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
				.setContentText(title)
				.show();
	}

	/**
	 * 成功 int
	 *
	 * @param context
	 * @param title
	 */
	public static void successful(Context context, int title) {
		new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
				.setContentText(context.getResources().getString(title))
				.show();
	}

}
