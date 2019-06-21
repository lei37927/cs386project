package com.example.cs386project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), alarm.class);
                this.startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), lullaby.class);
                this.startActivity(intent2);
                break;
        }
    }
}
