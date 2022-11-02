package com.example.myapplication.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.example.myapplication.R;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.fragment.MainFragment;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.REVERSE;


public class ViewAnimationFragment extends BaseFragment {

    private static final String TAG = "ViewAnimationFragment";
    private static final String PARAM_COLOR = "param_color";
    private static final long ROTATE_DURATION = 2000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;


    private int mColor = Color.WHITE;
    private ImageView mRobot;
    private AlphaAnimation mAlphaAnimation;
    private RotateAnimation mRotateAnimation;
    private TranslateAnimation mTranslateAnimation;
    private ScaleAnimation mScaleAnimation;
    public static int flag = 0; //全局变量
    private LottieAnimationView lottie;

    public ViewAnimationFragment() {
        // Required empty public constructor
    }

    public static ViewAnimationFragment newInstance(int color) {
        ViewAnimationFragment fragment = new ViewAnimationFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            int givenColor = args.getInt(PARAM_COLOR);
            mColor = (0 != givenColor) ? givenColor : mColor;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animation, container, false);
        view.setBackgroundColor(mColor);
        mRobot = view.findViewById(R.id.iv_robot);
        lottie = view.findViewById(R.id.loading1);
        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onResume() {
        super.onResume();
        initAnimation();
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
                        mRobot.startAnimation(mAlphaAnimation);
                    }
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mAlphaAnimation && mAlphaAnimation.hasStarted()) {
            mAlphaAnimation.cancel();
        }
        if (null != mRotateAnimation && mRotateAnimation.hasStarted()) {
            mRotateAnimation.cancel();
        }
        if (null != mTranslateAnimation && mTranslateAnimation.hasStarted()) {
            mTranslateAnimation.cancel();
        }
        if (null != mScaleAnimation && mScaleAnimation.hasStarted()) {
            mScaleAnimation.cancel();
        }
    }

    private void initAnimation() {

        mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        mAlphaAnimation.setDuration(1000);//设置动画持续时间
        mAlphaAnimation.setRepeatCount(1);//设置重复次数
        mAlphaAnimation.setRepeatMode(REVERSE);
        mAlphaAnimation.setFillBefore(true);
        mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
                flag = 0;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
                mRobot.startAnimation(mRotateAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

        mRotateAnimation = new RotateAnimation(
                ROTATE_START_DEGREE, ROTATE_END_DEGREE,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT
        );
        mRotateAnimation.setDuration(ROTATE_DURATION);
        mRotateAnimation.setRepeatCount(1);//设置重复次数
        mRotateAnimation.setRepeatMode(REVERSE);
        mRotateAnimation.setDuration(1000);//设置动画持续时间
        mRotateAnimation.setFillBefore(true);
        mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
                flag = 1;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
                mRobot.startAnimation(mTranslateAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

        mTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
        mTranslateAnimation.setDuration(1000);
        mTranslateAnimation.setRepeatCount(1);//设置重复次数
        mTranslateAnimation.setRepeatMode(REVERSE);
        mTranslateAnimation.setFillBefore(true);
        mTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
                flag = 2;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
                mRobot.startAnimation(mScaleAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

        mScaleAnimation = new ScaleAnimation(1f,0.5f,1f,0.5f,0.5f,0.f);
        mScaleAnimation.setDuration(1000);
        mScaleAnimation.setRepeatCount(1);//设置重复次数
        mScaleAnimation.setRepeatMode(REVERSE);
        mScaleAnimation.setFillBefore(true);
        mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
                flag = 3;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
                mRobot.startAnimation(mAlphaAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

    }
}