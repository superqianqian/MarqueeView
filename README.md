# MarqueeView
Android 富文本跑马灯，可以自定义添加View


![image](https://github.com/superqianqian/MarqueeView/blob/master/app/src/main/res/drawable/1557367759769.gif)



使用方法：
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
        mImageAndTextMarequee.startScroll();
