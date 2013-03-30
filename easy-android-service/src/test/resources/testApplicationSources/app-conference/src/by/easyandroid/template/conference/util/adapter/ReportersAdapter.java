package by.easyandroid.template.conference.util.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.util.AndroidUtil;

public class ReportersAdapter extends ListAdapter<Reporter> {

	public ReportersAdapter(Context context, List<Reporter> reports) {
		super(context, reports, R.layout.list_elem_reporters);
	}
	
	@Override
	protected void fillView(Reporter item, View view) {
		AndroidUtil.setText(view, R.id.txtReporterName, item.getName());
		AndroidUtil.setText(view, R.id.txtReporterDescription, item.getDescription());
	}
}