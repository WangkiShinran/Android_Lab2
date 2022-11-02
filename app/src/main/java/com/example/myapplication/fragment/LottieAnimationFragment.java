package com.example.myapplication.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.example.myapplication.R;

public class LottieAnimationFragment extends BaseFragment {

    public static final String TAG = "LottieAnimationFragment";
    private LottieAnimationView lottie, robot;

    public LottieAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lottie_animation, container, false);
        lottie = view.findViewById(R.id.loading3);
        robot = view.findViewById(R.id.lottieView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //lottie.setAnimation(R.raw.loading_paperplane);
        //监听动画播放进度 [0,1]
        lottie.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // 判断动画加载结束
                if (valueAnimator.getAnimatedFraction() == 1f) {
                    lottie.setVisibility(View.GONE);
                    robot.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
