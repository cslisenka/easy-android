package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.util.Constants;

public class SingleReportActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_report);
		initContent(getIntent().getExtras().getLong(Constants.ITEM_ID));
	}

	private void initContent(long reportId) {
		Report report = reportService.getById(reportId);
		setText(R.id.subTitle, report.getTitle());
		setText(R.id.txtReportDateTime, report.getTime().toString());
		setText(R.id.txtReportDescription, report.getDesctiption());
	}
}