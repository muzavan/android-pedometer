package name.bagi.levente.pedometer;


import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Comitment extends Activity {
	private EditText jangkaWaktu;
	private EditText jumlahStep;
	private EditText uPoint;
	private EditText repeat;

	private Button saveButton;
	private final String TAG = "Comitment";
	private SharedPreferences mSettings;

	public void onCreate(Bundle savedInstanceState){
		Log.i(TAG, "[ACTIVITY] onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comitment);
		jangkaWaktu = (EditText) findViewById(R.id.jangkaWaktu); 
		jumlahStep = (EditText) findViewById(R.id.jumlahStep);
		uPoint = (EditText) findViewById(R.id.uPoint);
		repeat = (EditText) findViewById(R.id.repeat);
		saveButton = (Button) findViewById(R.id.saveButton);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				save();
			}
		});
	}

	protected void onDestroy(){

	}

	protected void onDestoy(){
		Log.i(TAG, "[ACTIVITY] onDestroy");
		super.onDestroy();
		
		//Nyimpen masukan usernya ke shared preferences
		mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor mEdit = mSettings.edit();
		mEdit.putInt("jangkaWaktu", Integer.valueOf(jangkaWaktu.getText().toString()));
		mEdit.putInt("jumlahStep", Integer.valueOf(jumlahStep.getText().toString()));
		mEdit.putInt("uPoint", Integer.valueOf(uPoint.getText().toString()));
		mEdit.putInt("repeat", Integer.valueOf(repeat.getText().toString()));
		mEdit.putLong("start",new Date().getTime());
		mEdit.commit();
		this.finish(); // menyelesaikan activity
	}
	
	
	public void save(){
		if(isSemuaDiIsi()){
			onDestoy();
		}
		else{
			Toast.makeText(this, "Isian harus diisi semua", Toast.LENGTH_SHORT).show();

		}
	}
	
	private boolean isSemuaDiIsi(){
		boolean b1,b2,b3,b4;
		b1 = jangkaWaktu.getText().toString().equals("") || jangkaWaktu.getText().toString().equals("0");
		b2 = jumlahStep.getText().toString().equals("") || jumlahStep.getText().toString().equals("0");
		b3 = uPoint.getText().toString().equals("") || uPoint.getText().toString().equals("0");
		b4 = repeat.getText().toString().equals("") || repeat.getText().toString().equals("0");
		
		return (!b1 && !b2 && !b3 && !b4);
	}

}

