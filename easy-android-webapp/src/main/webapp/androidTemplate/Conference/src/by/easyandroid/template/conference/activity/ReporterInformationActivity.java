package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.util.Constants;

public class ReporterInformationActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporter_information);
		initContent(getIntent().getExtras().getLong(Constants.ITEM_ID));
	}

	private void initContent(long reportId) {
		Reporter reporter = reporterService.getById(reportId);
		setText(R.id.subTitle, reporter.getName());
		setText(R.id.txtReporterDescription, reporter.getDescription());
	}
}