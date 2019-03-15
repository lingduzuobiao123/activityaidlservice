package com.dongxl.activityaidlservice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dongxl.activityaidlservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class MyFragment extends Fragment {
    @BindView(R.id.bottom1)
    Button bottom1;
    @BindView(R.id.bottom2)
    Button bottom2;
    @BindView(R.id.bottom3)
    Button bottom3;
    @BindView(R.id.bottom4)
    Button bottom4;
    @BindView(R.id.bottom5)
    Button bottom5;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("dongxl", "fragment--onAttach==");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("dongxl", "fragment--onCreate==");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dongxl", "fragment--onCreateView==");
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e("dongxl", "fragment--onViewCreated==");
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("dongxl", "fragment--onActivityCreated==");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("dongxl", "fragment--onResume==");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("dongxl", "fragment--onPause==");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("dongxl", "fragment--onStop==");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("dongxl", "fragment--onDestroy==");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("dongxl", "fragment--onDetach==");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.bottom4, R.id.bottom5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom1:
                break;
            case R.id.bottom2:
                break;
            case R.id.bottom3:
                break;
            case R.id.bottom4:
                break;
            case R.id.bottom5:
                break;
        }
    }
}
