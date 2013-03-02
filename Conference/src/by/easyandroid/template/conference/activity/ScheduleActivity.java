package by.easyandroid.template.conference.activity;

import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.filter.IFilter;
import by.easyandroid.template.conference.filter.ReportDayFilter;
import by.easyandroid.template.conference.filter.ReportSectionFilter;
import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.model.Section;
import by.easyandroid.template.conference.util.DateSpinnerProxy;
import by.easyandroid.template.conference.util.DateUtil;
import by.easyandroid.template.conference.util.adapter.ScheduleAdapter;

public class ScheduleActivity extends BasicActivity implements OnItemClickListener {

	private ReportSectionFilter sectionFilter;
	private ReportDayFilter dayFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		setSpinnerData(R.id.spinnerSection, sectionService.getAll());
		setSpinnerData(R.id.spinnerDate, DateUtil.getReportDaysProxy(reportService.getAll()));
		
		Spinner section = (Spinner) findViewById(R.id.spinnerSection);
		section.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Add section filter
				sectionFilter = new ReportSectionFilter((Section) parent.getItemAtPosition(position));
				initListView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		Spinner day = (Spinner) findViewById(R.id.spinnerDate);
		day.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Add section filter
				dayFilter = new ReportDayFilter(((DateSpinnerProxy)parent.getItemAtPosition(position)).getDate());
				initListView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});		
	}

	protected void initListView() {
		Set<IFilter<Report>> filters = new HashSet<IFilter<Report>>();
		if (sectionFilter != null) {
			filters.add(sectionFilter);
		}
		
		if (dayFilter != null) {
			filters.add(dayFilter);
		}
		
		initListView(R.id.listSchedule, new ScheduleAdapter(ScheduleActivity.this, reportService.getAll(filters)), this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showReportDetails(id);
	}
}