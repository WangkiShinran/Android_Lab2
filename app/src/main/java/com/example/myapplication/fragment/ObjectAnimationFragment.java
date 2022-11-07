package com.example.myapplication.fragment;

import static com.example.myapplication.fragment.ViewAnimationFragment.flag;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;

public class ObjectAnimationFragment extends BaseFragment {

    private static final String TAG = "StaticFragment";
    private static final long ANIMATION_DURATION = 1000;
    private static final float ALPHA_START = 1f;
    private static final float ALPHA_END = 0f;

    private ImageView mRobot;
    private ObjectAnimator AlphaAnimator, RotationAnimator, ScaleXAnimator, ScaleYAnimator, TransXAnimator;
    //AnimatorSet setAnimation;
    AnimatorSet ScaleSet;
    private LottieAnimationView lottie;

    public ObjectAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object_animation, container, false);
        mRobot = view.findViewById(R.id.iv_robot);
        lottie = view.findViewById(R.id.loading2);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mRobot) {
            AlphaAnimator = ObjectAnimator.ofFloat(mRobot, "alpha", ALPHA_START, ALPHA_END, ALPHA_START);
            AlphaAnimator.setDuration(ANIMATION_DURATION);
            AlphaAnimator.setRepeatCount(0);
            AlphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
            AlphaAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.i(TAG, "onAnimationStart: ");
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i(TAG, "onAnimationEnd: ");
                    //RotationAnimator.start();
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.i(TAG, "onAnimationCancel: ");
                }
                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.i(TAG, "onAnimationRepeat: ");
                }
            });

            RotationAnimator = ObjectAnimator.ofFloat(mRobot, "rotation", 0.0f, 360.0f);
            RotationAnimator.setDuration(ANIMATION_DURATION);
            RotationAnimator.setRepeatCount(0);
            RotationAnimator.setRepeatMode(ValueAnimator.REVERSE);
            RotationAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.i(TAG, "onAnimationStart: ");
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i(TAG, "onAnimationEnd: ");
                    //ScaleSet.start();
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.i(TAG, "onAnimationCancel: ");
                }
                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.i(TAG, "onAnimationRepeat: ");
                }
            });

            TransXAnimator = ObjectAnimator.ofFloat(mRobot, "translationX", 0f, 200f, -200f, 0f);
            TransXAnimator.setDuration(ANIMATION_DURATION);
            TransXAnimator.setRepeatCount(0);
            TransXAnimator.setRepeatMode(ValueAnimator.REVERSE);
            TransXAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.i(TAG, "onAnimationStart: ");
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i(TAG, "onAnimationEnd: ");
                    //AlphaAnimator.start();
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.i(TAG, "onAnimationCancel: ");
                }
                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.i(TAG, "onAnimationRepeat: ");
                }
            });

            ScaleXAnimator = ObjectAnimator.ofFloat(mRobot, "scaleX", 1.0f, 1.2f, 0.8f, 1.0f);
            ScaleXAnimator.setDuration(ANIMATION_DURATION);
            ScaleXAnimator.setRepeatCount(0);
            ScaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);

            ScaleYAnimator = ObjectAnimator.ofFloat(mRobot, "scaleY", 1.0f, 1.2f, 0.8f, 1.0f);
            ScaleYAnimator.setDuration(ANIMATION_DURATION);
            ScaleYAnimator.setRepeatCount(0);
            ScaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);

            ScaleSet = new AnimatorSet();
            ScaleSet.playTogether(ScaleXAnimator, ScaleYAnimator);
            ScaleSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.i(TAG, "onAnimationStart: ");
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i(TAG, "onAnimationEnd: ");
                    //TransXAnimator.start();
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.i(TAG, "onAnimationCancel: ");
                }
                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.i(TAG, "onAnimationRepeat: ");
                }
            });

            if (null != mRobot) {
                //lottie.setAnimation(R.raw.loading_paperplane);
                //监听动画播放进度 [0,1]
                lottie.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        // 判断动画加载结束
                        if (valueAnimator.getAnimatedFraction() == 1f) {
                            lottie.setVisibility(View.GONE);
                            mRobot.setVisibility(View.VISIBLE);
                            mRobot.setOnClickListener(v -> {
                                Log.i("record", "ok"+flag);
                                switch (flag)
                                {
                                    case 0: AlphaAnimator.start(); break;
                                    case 1: RotationAnimator.start(); break;
                                    case 2: TransXAnimator.start(); break;
                                    case 3: ScaleSet.start(); break;
                                }
                                flag = (flag + 1) % 4;
                            });
                        }
                    }
                });
            }

//            setAnimation = new AnimatorSet();
//            setAnimation.playSequentially(AlphaAnimator, RotationAnimator, ScaleSet, TransXAnimator);
//            setAnimation.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    Log.i(TAG, "onAnimationStart: ");
//                }
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    Log.i(TAG, "onAnimationEnd: ");
//                    setAnimation.start();
//                }
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                    Log.i(TAG, "onAnimationCancel: ");
//                }
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                    Log.i(TAG, "onAnimationRepeat: ");
//                }
//            });
//            setAnimation.start();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != AlphaAnimator && AlphaAnimator.isRunning()) {
            AlphaAnimator.cancel();
        }
        if (null != RotationAnimator && RotationAnimator.isRunning()) {
            RotationAnimator.cancel();
        }
        if (null != ScaleSet && ScaleSet.isRunning()) {
            ScaleSet.cancel();
        }
        if (null != TransXAnimator && TransXAnimator.isRunning()) {
            TransXAnimator.cancel();
        }
        //Log.i(TAG, "pauseX");
    }

}
