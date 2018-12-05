package sensor.project.creg257.wtl218.com.sensorposition;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorRotation;
    private int menuDemo = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorRotation = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        //Intent i = new Intent(this, RotationVector.class);
        //startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        int menuGroup1Id = 1112;
        menu.add(menuGroup1Id, menuDemo, 1, "Demo");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == menuDemo) {
            Intent i = new Intent(this, RotationVector.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void updateTextView(String toThis) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(toThis);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mSensorRotation != null){
            mSensorManager.registerListener(this, mSensorRotation, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float xValue = sensorEvent.values[0];
        float yValue = sensorEvent.values[1];
        float zValue = sensorEvent.values[2];
        String info = "the x value is "+ xValue +". The y value is "+ yValue + ". The z value is "+zValue;
        updateTextView(info);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
