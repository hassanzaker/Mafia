package com.example.mafia;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;

public class TimeHandling extends Fragment {
    public GiveRolePage giveRolePage;
    public TextView textView;
    public Button day, back, challenge,start;
    public boolean flag;
    public TimeHandling(GiveRolePage giveRolePage) {
        this.giveRolePage = giveRolePage;
        flag = false;
    }

    public TimeHandling() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView = view.findViewById(R.id.time);
        textView.setText( "زمان باقیمانده \n ۳۰ثانیه");
        day = view.findViewById(R.id.day);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rooster_sound);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        back = view.findViewById(R.id.back_back);
        start = view.findViewById(R.id.start_time);
        final CountDownTimer turn = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView.setText("زمان باقیمانده\n" + millisUntilFinished / 1000 + "ثانیه");
            }

            public void onFinish() {
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        final CountDownTimer chalesh = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView.setText("زمان باقیمانده\n" + millisUntilFinished / 1000 + "ثانیه");
            }

            public void onFinish() {
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chalesh.cancel();
                turn.cancel();
                turn.start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, giveRolePage);
                fragmentTransaction.commit();
            }
        });
        challenge = view.findViewById(R.id.challange);
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn.cancel();
                chalesh.cancel();
                chalesh.start();
            }
        });


    }
}
