package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.util.adapter.ScheduleAdapter;

public class ScheduleActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		initListView();
	}

	private void initListView() {
		ListView scheduleListView = (ListView) findViewById(R.id.listSchedule);
		ScheduleAdapter adapter = new ScheduleAdapter(this, reportService.getReports());
		scheduleListView.setAdapter(adapter);
		scheduleListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showReportDetails(id);
			}
		});
	}
}