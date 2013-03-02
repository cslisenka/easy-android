package by.easyandroid.template.conference.util;

import android.view.View;
import android.widget.TextView;

public class AndroidUtil {

	public static void setText(View baseView, int viewId, String text) {
		TextView textView = (TextView) baseView.findViewById(viewId);
		textView.setText(text);
	}
}