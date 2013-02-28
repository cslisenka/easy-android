package by.easyandroid.template.conference.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import by.easyandroid.template.conference.R;
import by.easyandroid.template.conference.util.adapter.ReportersAdapter;

public class ReportersActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporters);
		initListView();
	}
	
	private void initListView() {
		ListView scheduleListView = (ListView) findViewById(R.id.listReporters);
		ReportersAdapter adapter = new ReportersAdapter(this, reporterService.getReporters());
		scheduleListView.setAdapter(adapter);
		scheduleListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			}
		});
	}
}