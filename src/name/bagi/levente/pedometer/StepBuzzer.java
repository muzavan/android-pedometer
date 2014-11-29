package name.bagi.levente.pedometer;

import android.content.Context;
import android.os.Vibrator;

/**
 * Vibrates whenever a step is detected.
 * Normally, this is not attached, used for debugging purposes.
 * @author Levente Bagi
 */
public class StepBuzzer implements StepListener {
    
    private Context mContext;
    private Vibrator mVibrator;
    
    public StepBuzzer(Context context) {
        mContext = context;
        mVibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }
    
    public void onStep() {
        buzz();
    }
    
    public void passValue() {
        
    }
    
    private void buzz() {
        mVibrator.vibrate(50);
    }
}

