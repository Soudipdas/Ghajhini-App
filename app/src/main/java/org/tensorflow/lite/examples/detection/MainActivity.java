package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static org.tensorflow.lite.examples.detection.app.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button recadd,games,edit;
    SharedPreferences sharedPreferences;
    TextView name,age,mobile,yourmob,email;
    SensorManager sensorManager;
    boolean running=false;
    ImageButton notify;
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recadd=findViewById(R.id.recadd);
        edit=findViewById(R.id.etdetails);
        games=findViewById(R.id.game);
        notify=findViewById(R.id.notify);

        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        mobile=findViewById(R.id.mobile);
        yourmob=findViewById(R.id.your_mobile);
        email=findViewById(R.id.email);

        notificationManager= NotificationManagerCompat.from(this);

        sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        name.append(sharedPreferences.getString("name",""));

        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        recadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetectorActivity.class);
                startActivity(intent);
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(v);
            }
        });


        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Games.class);
                startActivity(intent);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,edit_details.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor count=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(count!= null){
            sensorManager.registerListener(this,count,SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this,"Not Found !!!",Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running=false;
    }
    //To display number of steps uncomment the below code
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            //email.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void sendOnChannel1(View v) {
        String title = "Ghajini";
        String message = "Hiii, Don't forget to click and save photos of people whom you meet";
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }
}