package by.easyandroid.template.conference.util.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Report;

public class ReportsAdapter extends ListAdapter<Report> {

	private static final String TIME_FORMAT = "%02d:%02d";
	
	public ReportsAdapter(Context context, List<Report> reports) {
		super(context, reports, R.layout.list_elem_reports);
	}
	
	@Override
	protected void fillView(Report item, View view) {
		TextView reportTitle = (TextView) view.findViewById(R.id.txtReportTitle);
		TextView reportAuthor = (TextView) view.findViewById(R.id.txtReportAuthor);
		TextView reportTime = (TextView) view.findViewById(R.id.txtReportDateTime);
		TextView reportSection = (TextView) view.findViewById(R.id.txtReportSection);
		
		reportTitle.setText(item.getTitle());
		reportAuthor.setText(item.getReporter().getName());
		reportTime.setText(String.format(TIME_FORMAT, item.getTime().getHours(), item.getTime().getMinutes()));
		reportSection.setText(item.getSection().getName());	
	}
}