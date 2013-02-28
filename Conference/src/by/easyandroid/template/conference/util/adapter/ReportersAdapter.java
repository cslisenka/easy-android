package by.easyandroid.template.conference.util.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Reporter;

public class ReportersAdapter extends ListAdapter<Reporter> {

	public ReportersAdapter(Context context, List<Reporter> reports) {
		super(context, reports, R.layout.list_elem_reporters);
	}
	
	@Override
	protected void fillView(Reporter item, View view) {
		TextView reporterName = (TextView) view.findViewById(R.id.txtReporterName);
		TextView reporterDescription = (TextView) view.findViewById(R.id.txtReporterDescription);
		
		reporterName.setText(item.getName());
		reporterDescription.setText(item.getDescription());
	}
}