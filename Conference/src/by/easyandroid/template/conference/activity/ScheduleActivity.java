package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Section;
import by.easyandroid.template.conference.util.adapter.ScheduleAdapter;

public class ScheduleActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		initSpinners();
		initListView();
	}

	private void initSpinners() {
		Spinner sections = (Spinner) findViewById(R.id.spinnerSection);
		ArrayAdapter<Section> adapter = new ArrayAdapter<Section>(getApplicationContext(), android.R.layout.simple_spinner_item, sectionService.getAll());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sections.setAdapter(adapter);
	}

	private void initListView() {
		ListView scheduleListView = (ListView) findViewById(R.id.listSchedule);
		ScheduleAdapter adapter = new ScheduleAdapter(this, reportService.getAll());
		scheduleListView.setAdapter(adapter);
		scheduleListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showReportDetails(id);
			}
		});
	}
}