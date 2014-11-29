package name.bagi.levente.pedometer;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
<<<<<<< HEAD
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
=======
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
import android.widget.EditText;
import android.widget.Toast;


public class Comitment extends Activity {
	private EditText jangkaWaktu;
	private EditText jumlahStep;
	private EditText uPoint;
	private EditText repeat;
<<<<<<< HEAD
	private Button saveButton;
=======
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
	private final String TAG = "Comitment";
	private SharedPreferences mSettings;

	public void onCreate(Bundle savedInstanceState){
		Log.i(TAG, "[ACTIVITY] onCreate");
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		setContentView(R.layout.comitment);
		
=======

>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
		jangkaWaktu = (EditText) findViewById(R.id.jangkaWaktu); 
		jumlahStep = (EditText) findViewById(R.id.jumlahStep);
		uPoint = (EditText) findViewById(R.id.uPoint);
		repeat = (EditText) findViewById(R.id.repeat);
<<<<<<< HEAD
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
=======
	}

	protected void onDestoy(){
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
		Log.i(TAG, "[ACTIVITY] onDestroy");
		super.onDestroy();
		
		//Nyimpen masukan usernya ke shared preferences
		mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor mEdit = mSettings.edit();
		mEdit.putInt("jangkaWaktu", Integer.valueOf(jangkaWaktu.getText().toString()));
		mEdit.putInt("jumlahStep", Integer.valueOf(jumlahStep.getText().toString()));
		mEdit.putInt("uPoint", Integer.valueOf(uPoint.getText().toString()));
		mEdit.putInt("repeat", Integer.valueOf(repeat.getText().toString()));
<<<<<<< HEAD
		mEdit.putLong("start",new Date().getTime());
		mEdit.commit();
		this.finish(); // menyelesaikan activity
	}
	
	
	
	public void save(){
=======
		mEdit.commit();
	}
	
	public void onBackPressed(){
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
		if(isSemuaDiIsi()){
			onDestroy();
		}
		else{
			Toast.makeText(this, "Isian harus diisi semua", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
			
>>>>>>> 8cce4fe3c99f24fa92d408f5584d36e240719dab
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

