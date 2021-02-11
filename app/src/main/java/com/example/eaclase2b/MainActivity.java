package com.example.eaclase2b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int counter1=0;
    private TextView counter1TV;
    private Button Play, Stop;
    private boolean counterIsPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter1TV= findViewById(R.id.counter1TV);
        Play= findViewById(R.id.Play);
        Stop= findViewById(R.id.Stop);



        Play.setOnClickListener(
                v->{
                    if(counterIsPlaying == false){
                        playCounter();
                    }else{
                        Toast.makeText(this, "ya esta corriendo", Toast.LENGTH_SHORT).show();
                    }

                }
        );

        Stop.setOnClickListener(
                v->{
                    counterIsPlaying = false;
                }
        );




    }

    public void playCounter(){
        counterIsPlaying=true;
        new Thread(
                ()->{
                    while(counterIsPlaying){

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        counter1++;
                        Log.e(">>>", ""+counter1);

                        //para cambiar lo que se ve en pantalla de acuerdo a lo que pasa en este hilo
                        // es necesario usar RUNONVITHREAD para cambiar la vista en el hilo principal

                        runOnUiThread(
                                ()->{
                                    counter1TV.setText(""+counter1);
                                }
                        );
                    }
                }
        ).start();
    }
}