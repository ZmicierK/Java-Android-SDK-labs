package com.example.lab4;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;



public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    SeekBar seekbar;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchSynk;
    Boolean synchronize=false;
    Boolean IsSwapped=false;
    Boolean IsRotated=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);

        switchSynk = (Switch) findViewById(R.id.switchSynk);
        switchSynk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                synchronize = isChecked;
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    public void startAnimation(View view) throws InterruptedException {
        float dest, dest_R;
        int Speed = 5000-seekbar.getProgress();
        int speed_d = Speed / 2;
        int speed_async;
        if (synchronize) speed_async = Speed; else speed_async=Speed+1000;
        int speed_d_async = speed_async / 2;
        ImageView aniView = (ImageView) findViewById(R.id.imageView1);
        ImageView aniView2 = (ImageView) findViewById(R.id.imageView2);
        switch (view.getId()) {

            case R.id.ButtonRotate:
                if (IsRotated) dest = 360; else dest = 0;
                ObjectAnimator Rotate1 = ObjectAnimator.ofFloat(aniView, "rotation", dest);
                ObjectAnimator Rotate2 = ObjectAnimator.ofFloat(aniView2, "rotation", dest);
                Rotate1.setDuration(Speed);
                Rotate2.setDuration(speed_async);
                AnimatorSet animatorSetRotate = new AnimatorSet();
                animatorSetRotate.play(Rotate1).with(Rotate2);
                animatorSetRotate.start();
                IsRotated=!IsRotated;
                break;

            case R.id.ButtonAnimate:
                if (IsSwapped) { dest = 360;dest_R = 0;} else { dest = 0;dest_R = 360;}
                ObjectAnimator x1 = ObjectAnimator.ofFloat(aniView, "x", dest_R);
                ObjectAnimator x2 = ObjectAnimator.ofFloat(aniView2, "x", dest);
                x1.setDuration(speed_d);
                x2.setDuration(speed_d_async);
                AnimatorSet animatorSetX = new AnimatorSet();
                animatorSetX.play(x1).with(x2);
                animatorSetX.start();
                IsSwapped=!IsSwapped;
                break;

            case R.id.ButtonFade:
                if (aniView.getAlpha() > 0) dest = 0; else dest = 1;
                ObjectAnimator fade1 = ObjectAnimator.ofFloat(aniView, "alpha", dest);
                ObjectAnimator fade2 = ObjectAnimator.ofFloat(aniView2, "alpha", dest);
                fade1.setDuration(speed_d);
                fade2.setDuration(speed_d_async);
                AnimatorSet animatorSetFade = new AnimatorSet();
                animatorSetFade.play(fade1).with(fade2);
                animatorSetFade.start();
                break;

            case R.id.ButtonGroup:
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",0f);
                fadeOut.setDuration(speed_d);
                ObjectAnimator fadeOut2 = ObjectAnimator.ofFloat(aniView2,"alpha",0f);
                fadeOut2.setDuration(speed_d_async);
                ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,"translationX", -500f, 0f);
                mover.setDuration(speed_d);
                ObjectAnimator mover2 = ObjectAnimator.ofFloat(aniView2,"translationX", 500f, 0f);
                mover2.setDuration(speed_d_async);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",1f);
                fadeIn.setDuration(speed_d);
                ObjectAnimator fadeIn2 = ObjectAnimator.ofFloat(aniView2, "alpha",1f);
                fadeIn2.setDuration(speed_d_async);
                AnimatorSet animatorSetGroup = new AnimatorSet();
                animatorSetGroup.play(mover).with(fadeIn).with(mover2).with(fadeIn2).after(fadeOut).after(fadeOut2);
                animatorSetGroup.start();
                break;

            default:break;
        }
    }
}