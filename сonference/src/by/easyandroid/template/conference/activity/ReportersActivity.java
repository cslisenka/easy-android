package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.util.adapter.ReportersAdapter;

public class ReportersActivity extends BasicActivity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporters);
		initListView(R.id.listReporters, new ReportersAdapter(this, reporterService.getAll()), this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showReporterDetails(id);
	}
}