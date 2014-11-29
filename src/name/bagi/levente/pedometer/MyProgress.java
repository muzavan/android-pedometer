package name.bagi.levente.pedometer;

import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyProgress extends Activity {
	private ProgressBar progressBar;
	private TextView target;
	private TextView start;
	private final String TAGS = "MyProgress";
	public void onCreate(Bundle savedInstanceState){
		Log.i(TAGS,"[Activity] onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprogress);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		target = (TextView) findViewById(R.id.target);
		start = (TextView) findViewById(R.id.start);
		
		//melakukan penghitungan progress
		int nilaiSekarang;
		int nilaiTujuan;
		SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		//mPreferences.getInt("namavalue",defaultValue);
		nilaiSekarang = mPreferences.getInt("stepSekarang",0);
		nilaiTujuan = mPreferences.getInt("jumlahStep",1);
		
		//mengambil tanggal mulai
		long tanggalStart = mPreferences.getLong("start",0);
		Date currentDate = new Date();
		currentDate.setTime(tanggalStart);
		target.setText(String.valueOf(nilaiTujuan));
		start.setText(String.valueOf(currentDate));
		//memperlihatkkan progressBar
		progressBar.setProgress(nilaiSekarang * 100 / nilaiTujuan);
	}
}
