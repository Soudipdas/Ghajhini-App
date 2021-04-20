package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_details extends AppCompatActivity {

    EditText name,age,mobile,yourmob,email;
    SharedPreferences sharedPreferences;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        mobile=findViewById(R.id.mobile);
        yourmob=findViewById(R.id.your_mobile);
        email=findViewById(R.id.email);
        save=findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("Name",name.getText().toString());
                myEdit.putString("Age",age.getText().toString());
                myEdit.putString("Mobile",mobile.getText().toString());
                myEdit.putString("Yourmob",yourmob.getText().toString());
                myEdit.putString("Email",email.getText().toString());
                myEdit.apply();
            }
        });
    }
}