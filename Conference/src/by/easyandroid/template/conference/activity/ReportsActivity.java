package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Category;
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
		Spinner categories = (Spinner) findViewById(R.id.spinnerCategories);
		ArrayAdapter<Category> adapterCategories = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_item, categoryService.getAll());
		adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categories.setAdapter(adapterCategories);
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