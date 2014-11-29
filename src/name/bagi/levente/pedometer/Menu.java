package name.bagi.levente.pedometer;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Menu extends Activity {

	private Button commitButton;
	private Button progressButton;
	private final String TAG = "Menu";
	
	public void onCreate(Bundle savedInstanceState){
		Log.i(TAG, "[ACTIVITY] onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmenu);
		commitButton = (Button) findViewById(R.id.commitButton);
		progressButton = (Button) findViewById(R.id.progressButton);
		commitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Comitment.class);
				startActivity(intent);
			}
		});
		
		progressButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MyProgress.class);
				startActivity(intent);
			}
		});
	}
	
}
