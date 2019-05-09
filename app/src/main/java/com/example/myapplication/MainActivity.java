package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MarqueeView mTextMarequee;
    private MarqueeView mTextsMarequee;
    private MarqueeView mImageMarequee;
    private MarqueeView mImageAndTextMarequee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        mTextMarequee = findViewById(R.id.marquee1);
        mTextsMarequee = findViewById(R.id.marquee2);
        mImageMarequee = findViewById(R.id.marquee3);
        mImageAndTextMarequee = findViewById(R.id.marquee4);

        initTextMarequee();
        initTextsMarequee();
        initImageMarequee();
        initImageAndTextMarequee();
    }

    void initTextMarequee() {
        TextView tv = new TextView(this);
        tv.setText("啊啊啊啊啊啊啊啊啊刹不住车了");
        tv.setTextColor(R.color.colorAccent);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

        mTextMarequee.addViewInQueue(tv);

        mTextMarequee.setScrollSpeed(40);
        mTextMarequee.setScrollDirection(MarqueeView.RIGHT_TO_LEFT);
        mTextMarequee.setViewMargin(200);
    }

    void initTextsMarequee() {
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TextView tv = new TextView(this);
            tv.setText("我是第" + i + "个View");
            tv.setTextColor(Color.parseColor("#424242"));
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            viewList.add(tv);
        }

        mTextsMarequee.addViewsInQueue(viewList);

        mTextsMarequee.setScrollSpeed(4);
        mTextsMarequee.setScrollDirection(MarqueeView.RIGHT_TO_LEFT);
        mTextsMarequee.setViewMargin(200);
    }

    void initImageMarequee() {

        ImageView iv = new ImageView(this);
        iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        mImageMarequee.addViewInQueue(iv);
        mImageMarequee.setScrollSpeed(10);
        mImageMarequee.setScrollDirection(MarqueeView.RIGHT_TO_LEFT);
        mImageMarequee.setViewMargin(200);

    }

    void initImageAndTextMarequee() {
        TextView tv = new TextView(this);
        tv.setText("我是个文字");
        tv.setTextColor(Color.parseColor("#424242"));
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mImageAndTextMarequee.addViewInQueue(tv);

        ImageView iv = new ImageView(this);
        iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        mImageAndTextMarequee.addViewInQueue(iv);

        mImageAndTextMarequee.setScrollSpeed(8);
        mImageAndTextMarequee.setScrollDirection(MarqueeView.LEFT_TO_RIGHT);
        mImageAndTextMarequee.setViewMargin(200);

    }

    @Override
    protected void onStart() {
        super.onStart();


        mTextMarequee.startScroll();
        mTextsMarequee.startScroll();
        mImageMarequee.startScroll();
        mImageAndTextMarequee.startScroll();
    }
}
