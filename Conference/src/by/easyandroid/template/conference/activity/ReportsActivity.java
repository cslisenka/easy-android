package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.util.adapter.ReportsAdapter;

public class ReportsActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);
		initListView();
		initSpinner();
	}

	private void initSpinner() {
		setSpinnerData(R.id.spinnerCategories, categoryService.getAll());
	}

	private void initListView() {
		ListView scheduleListView = (ListView) findViewById(R.id.listReports);
		ReportsAdapter adapter = new ReportsAdapter(this, reportService.getAll());
		scheduleListView.setAdapter(adapter);
		scheduleListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showReportDetails(id);
			}
		});
	}
}