package name.bagi.levente.pedometer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {
	private EditText textUsername;
	private EditText textPassword;
	private Button loginButton;
	private final String TAG = "Login";
	private SharedPreferences mSettings;
	public void onCreate(Bundle savedInstanceState){
		Log.i(TAG, "[ACTIVITY] onCreate");
		super.onCreate(savedInstanceState);
		loginButton = (Button) findViewById(R.id.loginButton);
		textUsername = (EditText) findViewById(R.id.userName); 
		textPassword = (EditText) findViewById(R.id.password);
	}

	protected void onDestoy(){
		Log.i(TAG, "[ACTIVITY] onDestroy");
		super.onDestroy();

	}
	
	public void onBackPressed(){
		
	}
	
	public void authen(){
		String username,password;
		username = textUsername.getText().toString();
		password = textPassword.getText().toString();
		Toast.makeText(this, "Isian harus diisi semua", Toast.LENGTH_SHORT).show();
	}

}

