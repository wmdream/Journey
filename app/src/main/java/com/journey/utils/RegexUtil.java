package com.journey.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {


	public static boolean regIsUserName(String username) {
		String strPattern = "^[a-zA-Z]\\w{4,20}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(username);
		return m.matches();
	}

	public static boolean regIsUserPassWord(String password) {
		String strPattern = "^[A-Za-z0-9]+$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(password);
		return m.matches();
	}


	public static boolean isBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
/**
 *\ w  匹配任何字类字符，包括下划线。与“[A-Za-z0-9_]”等效。
 * */
	public static boolean isEmail(String strEmail) {
	//	String strPattern = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        String strPattern = "([a-zA-Z0-9_]+)+\\@(([a-zA-Z0-9_]+)+\\.)+([a-zA-Z0-9]{2,4})";
        Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

    public static boolean isEmailEnding(String strEmail) {
        if(!hasOnlyDot(strEmail)){
            return false;
        }
        if(strEmail.endsWith(".com")||strEmail.endsWith(".COM")){
            return true;
        }else if(strEmail.endsWith(".cn")||strEmail.endsWith(".CN")){
            return true;
        }else if(strEmail.endsWith(".net")||strEmail.endsWith(".NET")){
            return true;
        }
        return false;
    }

    private static boolean hasOnlyDot(String strEmail){
        int count = 0;
        int len = strEmail.length();
        for(int i=0;i<len;i++){
            if(String.valueOf(strEmail.charAt(i)).equals(".")){
                count++;
            }
        }
        if(count==1){
            return true;
        }
        return false;
    }

	public static boolean isMobile(String strMobile) {
		if (!RegexUtil.isBlank(strMobile) && strMobile.length() == 11) {
			try {
				Long mobile = Long.parseLong(strMobile);
				String estr = String.valueOf(mobile).substring(0, 2);
				if (estr.equals("13") || estr.equals("14") || estr.equals("15") || estr.equals("17") || estr.equals("18")) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isQQ(String strQQ) {
		if (isBlank(strQQ)) {
			return true;
		} else {
			String matchQQ = "[1-9][0-9]{4,}";
			Pattern p = Pattern.compile(matchQQ);
			Matcher m = p.matcher(strQQ);
			return m.matches();
		}
	}

	public static String createSign(String secret_key) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(secret_key.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
		}

		byte[] byteArray = messageDigest.digest();

		StringBuilder md5StrBuff = new StringBuilder();

		for (byte aByteArray : byteArray) {
			if (Integer.toHexString(0xFF & aByteArray).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
		}
		return md5StrBuff.toString();
	}

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
	}

	public static boolean countStringLength(String content, int stringNum) {
		int result = 0;
		if (content != null && !"".equals(content)) {
			char[] contentArr = content.toCharArray();
			for (char c : contentArr) {
				if (isChinese(c)) {
					result += 3;
				} else {
					result += 1;
				}
			}
		}
		return result > stringNum * 3;
	}

	public static String createImageName(String imageUrl) {
		return createSign(imageUrl) + ".jpg";
	}

	public static String createImageName(String imageUrl, String imgSuffix) {
		return createSign(imageUrl) + imgSuffix;
	}

	public static String trimNull(String str) {
		if (str == null || "null".equalsIgnoreCase(str))
			return "";
		else
			return str;
	}

	public static String getExceptionInfo(Exception e) {
		String result = "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		result = e.getMessage() + "/r/n" + sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {

		}
		return result;
	}

	public static boolean isSpecialCharacter(String str) {
		return str.contains("ï¿½");
	}

	public static boolean isChineseCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (char aCharArray : charArray) {
			if ((aCharArray >= '\u0000' && aCharArray < '\uFFFD') || ((aCharArray > '\uFFFD' && aCharArray < '\uFFFF'))) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean checkNum(String num) {
		String check = "^[0-9]*$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(num);
		return matcher.matches();
	}

	public static String DoubleToAmountString(Double num) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(num);
	}

	public static int StringLength(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public static boolean isStringLengthOut(String value, int limitLength) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength <= limitLength;
	}

	public static int isStringLengthInLimit(String value, int minLength, int maxLength) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		if (valueLength >= minLength && valueLength <= maxLength) {
			return 0;
		} else if (valueLength > maxLength) {
			return 1;
		} else {
			return -1;
		}
	}


	public static void limitEditTextLength(final EditText editText, final int limitLength, final Context context) {
		editText.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			@Override
			public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
				temp = s;
			}

			@Override
			public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				selectionStart = editText.getSelectionStart();
				selectionEnd = editText.getSelectionEnd();
				Log.i("gongbiao1", "" + selectionStart);
				boolean isStringLengthOut = isStringLengthOut(String.valueOf(temp), limitLength);
				if (!isStringLengthOut) {
					Toast.makeText(context, "长度已超出" + limitLength + "个字", Toast.LENGTH_SHORT).show();
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					editText.setText(s);
					editText.setSelection(tempSelection);
				}
			}
		});
	}

    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */
    public boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;

            }
        }
        return res;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
   /* public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }*/


}
