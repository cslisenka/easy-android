package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.filter.FilterSet;
import by.easyandroid.template.conference.filter.ReportCategoryFilter;
import by.easyandroid.template.conference.model.Category;
import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.util.adapter.ReportsAdapter;

public class ReportsActivity extends BasicActivity implements OnItemClickListener {

	private ReportCategoryFilter categoryFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);
		setSpinnerData(R.id.spinnerCategories, categoryService.getAll());
		
		Spinner section = (Spinner) findViewById(R.id.spinnerCategories);
		section.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Add section filter
				categoryFilter = new ReportCategoryFilter((Category) parent.getItemAtPosition(position));
				initListView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showReportDetails(id);
	}
	
	protected void initListView() {
		FilterSet<Report> filterSet = new FilterSet<Report>();
		filterSet.addFilter(categoryFilter);
		initListView(R.id.listReports, new ReportsAdapter(this, reportService.getAll(filterSet.getFilters())), this);
	}
}