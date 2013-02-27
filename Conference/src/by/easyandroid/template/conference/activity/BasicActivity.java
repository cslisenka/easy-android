package by.easyandroid.template.conference.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.service.ReportService;

public class BasicActivity extends Activity {

	protected ReportService reportService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		reportService = new ReportService();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void setOpenNewActivity(int buttonId, final Class<?> activity) {
		setOpenIntent(buttonId, new Intent(getApplicationContext(), activity));
	}
	
	protected void setOpenUrl(int buttonId, final String url) {
		setOpenIntent(buttonId, new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
	
	protected void setOpenIntent(int buttonId, final Intent intent) {
		findViewById(buttonId).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(intent);
			}
		});		
	}
}