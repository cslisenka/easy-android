package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.webkit.WebView;
import by.easyandroid.template.conference.R;

public class AboutActivity extends BasicActivity {

	private static final String ABOUT_HTML = "file:///android_asset/about.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initWebView();
	}

	private void initWebView() {
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(ABOUT_HTML);
	}
}