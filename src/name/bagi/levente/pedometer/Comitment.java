package name.bagi.levente.pedometer;


import java.util.Date;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Comitment extends Activity {
	private EditText jangkaWaktu;
	private EditText jumlahStep;
	private EditText uPoint;
	private EditText repeat;

	private Button saveButton;
	private final String TAG = "Comitment";
	private SharedPreferences mSettings;
	

    private TextView mStepValueView;
    private TextView mPaceValueView;
    private TextView mDistanceValueView;
    private TextView mSpeedValueView;
    private TextView mCaloriesValueView;

    private int mStepValue;
    private int mPaceValue;
    private float mDistanceValue;
    private float mSpeedValue;
    private int mCaloriesValue;
    
	
	public void onCreate(Bundle savedInstanceState){
		Log.i(TAG, "[ACTIVITY] onCreate");
		bindStepService();
        mStepValue = 0;
        mPaceValue = 0;
        
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

        mStepValueView     = (TextView) findViewById(R.id.step_value);
        mPaceValueView     = (TextView) findViewById(R.id.pace_value);
        mDistanceValueView = (TextView) findViewById(R.id.distance_value);
        mSpeedValueView    = (TextView) findViewById(R.id.speed_value);
        mCaloriesValueView = (TextView) findViewById(R.id.calories_value);
	}

	protected void onDestroy(){
		unbindStepService();
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
	

    private StepService mService;
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = ((StepService.StepBinder)service).getService();

            mService.registerCallback(mCallback);
            mService.reloadSettings();
            
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };
	

    private void bindStepService() {
        Log.i(TAG, "[SERVICE] Bind");
        bindService(new Intent(Comitment.this, 
                StepService.class), mConnection, Context.BIND_AUTO_CREATE + Context.BIND_DEBUG_UNBIND);
    }
    private void unbindStepService() {
        Log.i(TAG, "[SERVICE] Unbind");
        unbindService(mConnection);
    }
    
	// TODO: unite all into 1 type of message
    private StepService.ICallback mCallback = new StepService.ICallback() {
        public void stepsChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(STEPS_MSG, value, 0));
        }
        public void paceChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(PACE_MSG, value, 0));
        }
        public void distanceChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(DISTANCE_MSG, (int)(value*1000), 0));
        }
        public void speedChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(SPEED_MSG, (int)(value*1000), 0));
        }
        public void caloriesChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(CALORIES_MSG, (int)(value), 0));
        }
    };
    
    private static final int STEPS_MSG = 1;
    private static final int PACE_MSG = 2;
    private static final int DISTANCE_MSG = 3;
    private static final int SPEED_MSG = 4;
    private static final int CALORIES_MSG = 5;
    
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEPS_MSG:
                    mStepValue = (int)msg.arg1;
                    mStepValueView.setText("" + mStepValue);
                    //TODO: proses step yg berubah disini
                    break;
                case PACE_MSG:
                    mPaceValue = msg.arg1;
                    if (mPaceValue <= 0) { 
                        mPaceValueView.setText("0");
                    }
                    else {
                        mPaceValueView.setText("" + (int)mPaceValue);
                    }
                    break;
                case DISTANCE_MSG:
                    mDistanceValue = ((int)msg.arg1)/1000f;
                    if (mDistanceValue <= 0) { 
                        mDistanceValueView.setText("0");
                    }
                    else {
                        mDistanceValueView.setText(
                                ("" + (mDistanceValue + 0.000001f)).substring(0, 5)
                        );
                    }
                    break;
                case SPEED_MSG:
                    mSpeedValue = ((int)msg.arg1)/1000f;
                    if (mSpeedValue <= 0) { 
                        mSpeedValueView.setText("0");
                    }
                    else {
                        mSpeedValueView.setText(
                                ("" + (mSpeedValue + 0.000001f)).substring(0, 4)
                        );
                    }
                    break;
                case CALORIES_MSG:
                    mCaloriesValue = msg.arg1;
                    if (mCaloriesValue <= 0) { 
                        mCaloriesValueView.setText("0");
                    }
                    else {
                        mCaloriesValueView.setText("" + (int)mCaloriesValue);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
        
    };
}

