package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.util.DateUtil;
import by.easyandroid.template.conference.util.adapter.ScheduleAdapter;

public class ScheduleActivity extends BasicActivity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		setSpinnerData(R.id.spinnerSection, sectionService.getAll());
		setSpinnerData(R.id.spinnerDate, DateUtil.getReportDaysProxy(reportService.getAll()));
		initListView(R.id.listSchedule, new ScheduleAdapter(this, reportService.getAll()), this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showReportDetails(id);
	}
}