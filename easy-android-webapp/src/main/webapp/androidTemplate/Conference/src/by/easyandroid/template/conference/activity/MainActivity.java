package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import by.easyandroid.template.conference.R;

public class MainActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initMainMenu();
	}

	protected void initMainMenu() {
		setOpenNewActivity(R.id.btnSchedule, ScheduleActivity.class);
		setOpenNewActivity(R.id.btnReports, ReportsActivity.class);
		setOpenNewActivity(R.id.btnReporters, ReportersActivity.class);
		setOpenNewActivity(R.id.btnAbout, AboutActivity.class);
		setOpenUrl(R.id.btnWebsite, getString(R.string.main_website_url));
	}
}