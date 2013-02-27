package by.easyandroid.template.conference.util.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.model.Report;

public class ScheduleAdapter extends BaseAdapter {

	private List<Report> reports = new ArrayList<Report>();
	private Context context;
	
	public ScheduleAdapter(Context context, List<Report> reports) {
		this.context = context;
		this.reports = reports;
	}
	
	@Override
	public int getCount() {
		return reports.size();
	}

	@Override
	public Object getItem(int position) {
		return reports.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(reports.get(position).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listElementView = inflater.inflate(R.layout.list_elem_schedule, parent, false);
		TextView reportTitle = (TextView) listElementView.findViewById(R.id.txtReportTitle);
		reportTitle.setText(reports.get(position).getTitle());
		return listElementView;
	}
}