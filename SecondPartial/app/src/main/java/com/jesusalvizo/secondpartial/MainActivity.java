package com.jesusalvizo.secondpartial;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import static com.jesusalvizo.secondpartial.R.id.ansTxt;
import static com.jesusalvizo.secondpartial.R.raw.airhorn;

public class MainActivity extends AppCompatActivity {

    private EditText resultado;
    //private Button empezar;
    private Button siguiente;
    private Button reset;
    private TextView numero1;
    private TextView numero2;
    private TextView correctas;
    private TextView malas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApp();

    }

    private void initializeApp() {

        //time = (TextView) findViewById(R.id.time);

        numero1 = (TextView) findViewById(R.id.n1);
        numero2 = (TextView) findViewById(R.id.n2);

        resultado = (EditText) findViewById(ansTxt);
        resultado.setEnabled(false);

        correctas = (TextView) findViewById(R.id.textView13);
        correctas.setText("0");
        //correctas.setText(Integer.toString(0));

        malas = (TextView) findViewById(R.id.textView14);
        malas.setText("0");
        //malas.setText(Integer.toString(0));

        empezar = (Button) findViewById(R.id.strButton);
        //empezar.setOnClickListener(btnClickListener);
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empezar.setEnabled(false);
                resultado.setEnabled(true);
                siguiente.setEnabled(true);
                siguiente.setVisibility(View.VISIBLE);
                random1();
                random2();
                controlTimer();
            }
        });
        siguiente = (Button) findViewById(R.id.nxtBtn);
        siguiente.setEnabled(false);
        siguiente.setVisibility(View.INVISIBLE);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                random1();
                random2();
            }
        });
        reset = (Button) findViewById(R.id.button2);
        //reset.setOnClickListener(btnClickListener);
        reset.setVisibility(View.INVISIBLE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerTextView.setEnabled(false);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void random1() {
        Random rand = new Random();
        int n1 = rand.nextInt(100) + 1;
        TextView numero1 = (TextView) findViewById(R.id.n1);
        String myString = String.valueOf(n1);
        numero1.setText(myString);
    }

    public void random2() {
        Random rand = new Random();
        int n2 = rand.nextInt(100) + 1;
        TextView numero2 = (TextView) findViewById(R.id.n2);
        String myString = String.valueOf(n2);
        numero2.setText(myString);
    }


    public void calcular() {
        int n1, n2, s, t;
        int x;
        int y;

        if (resultado.getText().toString().matches("")) {
            return;
        }

        n1 = Integer.parseInt(numero1.getText().toString());
        n2 = Integer.parseInt(numero2.getText().toString());
        t = Integer.parseInt(resultado.getText().toString());
        y = Integer.parseInt(malas.getText().toString());
        x = Integer.parseInt(correctas.getText().toString());


        s = suma(n1, n2);

        if (t == s) {
            x++;
            correctas.setText(Integer.toString(x));
        } else {
            y++;
            malas.setText(Integer.toString(y));
        }

        resultado.setText("");
    }

    public int suma(int num1, int num2) {
        return num1 + num2;
    }

    public void reset() {
        empezar.setEnabled(true);
        reset.setEnabled(false);
        reset.setVisibility(View.INVISIBLE);
        resultado.setEnabled(false);
        resultado.setText("");
        correctas.setText("0");
        malas.setText("0");

    }

    SeekBar timerSeekBar;

    TextView timerTextView;

    Boolean counterIsActive = false;

    Button empezar;

    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
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

        timerTextView.setText(minutesString + ":" + secondsString);
    }

    public void controlTimer() {

        if (!counterIsActive) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("00:00");
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), airhorn);
                    mplayer.start();
                    resultado.setEnabled(false);
                    reset.setEnabled(true);
                    reset.setVisibility(View.VISIBLE);
                    siguiente.setEnabled(false);

                }
            }.start();

        } else {
            resetTimer();
        }
    }

}


