package by.easyandroid.template.conference;

import android.os.Bundle;

public class MainActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initMainMenu();
	}

	protected void initMainMenu() {
		setOpenNewActivity(R.id.btnSchedule, ScheduleActivity.class);
	}
}