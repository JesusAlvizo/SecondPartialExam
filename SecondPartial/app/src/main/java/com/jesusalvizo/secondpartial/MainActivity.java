package com.jesusalvizo.secondpartial;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static com.jesusalvizo.secondpartial.R.id.ansTxt;
import static com.jesusalvizo.secondpartial.R.raw.airhorn;

public class MainActivity extends AppCompatActivity {

    private EditText resultado;
    private Button empezar;
    private Button siguiente;
    private Button reset;
    private TextView numero1;
    private TextView numero2;
    private TextView correctas;
    private TextView malas;
    //private EditText textviewTime;
    //Boolean counterIsOn = false;
    //CountDownTimer Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApp();
    }

    private void initializeApp(){
        numero1 = (TextView) findViewById(R.id.n1);
        numero2 = (TextView) findViewById(R.id.n2);
        //textviewTime = (EditText) findViewById(R.id.cronometro);
        //textviewTime.setEnabled(false);
        resultado = (EditText) findViewById(ansTxt);
        resultado.setEnabled(false);
        correctas = (TextView) findViewById(R.id.textView13);
        correctas.setText("");
        malas = (TextView) findViewById(R.id.textView14);
        malas.setText("");
        empezar = (Button) findViewById(R.id.strButton);
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empezar.setEnabled(false);
                resultado.setEnabled(true);
                random1();
                random2();
            }
        });
        siguiente = (Button) findViewById(R.id.nxtBtn);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                random1();
                random2();
            }
        });
        reset = (Button) findViewById(R.id.button2);
        reset.setVisibility(View.INVISIBLE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void random1(){
        Random rand = new Random();
        int n1 = rand.nextInt(100)+1;
        TextView numero1 = (TextView) findViewById(R.id.n1);
        String myString = String.valueOf(n1);
        numero1.setText(myString);
    }

    public void random2(){
        Random rand = new Random();
        int n2 = rand.nextInt(100)+1;
        TextView numero2 = (TextView) findViewById(R.id.n2);
        String myString = String.valueOf(n2);
        numero2.setText(myString);
    }

    public void calcular() {
        int n1, n2, s, t;
        int x = 0;
        int y = 0;

        n1 = Integer.parseInt(numero1.getText().toString());
        n2 = Integer.parseInt(numero2.getText().toString());
        t = Integer.parseInt(resultado.getText().toString());

        s = suma(n1, n2);

        if (t == s){
            x++;
            correctas.setText(Integer.toString(x));
        }else{
            y++;
            malas.setText(Integer.toString(y));
        }

    }

    public int suma(int num1, int num2){
        return num1 + num2;
    }

    public void reset(){
        empezar.setEnabled(true);
        reset.setEnabled(false);
        correctas.setText("");
        malas.setText("");

    }

    /*
    public void controlTimer(View view) {

        if (!counterIsOn) {

            counterIsOn = true;

            Timer = new CountDownTimer(1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    textviewTime.setText("00:00");
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), airhorn);
                    mplayer.start();
                }

            }.start();

        } else {

            resetTimer();

        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = (int) secondsLeft % 60;

        String secondsString = Integer.toString(seconds);
        if (secondsString.length() < 2) {
            secondsString = "0" + secondsString;
        }

        String minutesString = Integer.toString(minutes);
        if (minutesString.length() < 2) {
            minutesString = "0" + minutesString;
        }

        textviewTime.setText(minutesString + ":" + secondsString);
    }

    public void resetTimer() {
        textviewTime.setText("00:30");
        Timer.cancel();
        counterIsOn = false;
    }
    */
}