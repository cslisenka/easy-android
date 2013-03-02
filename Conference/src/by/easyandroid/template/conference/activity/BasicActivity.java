package by.easyandroid.template.conference.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.service.CategoryService;
import by.easyandroid.template.conference.service.ReportService;
import by.easyandroid.template.conference.service.ReporterService;
import by.easyandroid.template.conference.service.SectionService;
import by.easyandroid.template.conference.util.Constants;

public class BasicActivity extends Activity {

	protected ReportService reportService;
	protected ReporterService reporterService;
	protected SectionService sectionService;
	protected CategoryService categoryService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initServices();
	}

	private void initServices() {
		reporterService = new ReporterService(getApplicationContext());
		sectionService = new SectionService(getApplicationContext());
		categoryService = new CategoryService(getApplicationContext());

		reportService = new ReportService(getApplicationContext());
		reportService.setReporterService(reporterService);
		reportService.setSectionService(sectionService);
		reportService.setCategoryService(categoryService);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void showReportDetails(long reportId) {
		showItemDetails(reportId, SingleReportActivity.class);
	}	
	
	protected void showReporterDetails(long reporterId) {
		showItemDetails(reporterId, ReporterInformationActivity.class);
	}
	
	protected void showItemDetails(long itemId, Class<?> activity) {
		Intent intent = new Intent(this, activity);
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.ITEM_ID, itemId);
		intent.putExtras(bundle);
		startActivity(intent);
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
	
	protected void setText(int viewId, String text) {
		TextView textView = (TextView) findViewById(viewId);
		textView.setText(text);
	}
}