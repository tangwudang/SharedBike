package com.lishu.bike.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lishu.bike.R;

public class SideBar extends View {
	
	//
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;  
    //
    /*public static String[] A_Z = { "*","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z", "#" }; */
    public static String[] A_Z = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private int choose = -1;//
    private Paint paint = new Paint();
  
    private TextView mTextDialog;
  
    /** 
     *
     * @param mTextDialog 
     */  
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;  
    }  
  
  
    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
    }  
  
    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
    public SideBar(Context context) {
        super(context);  
    }  
  
    /** 
     *
     */  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);  
        //
        int height = getHeight();//
        int width = getWidth(); //
        int singleHeight = height / A_Z.length;//
  
        for (int i = 0; i < A_Z.length; i++) {
            /*if(i==0){
                // 绘图
                // 从资源文件中生成位图
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_txl_sign);
                // 绘图
                //canvas.drawBitmap(bitmap, 10, 60, paint);
                float xPos = width / 2 - bitmap.getWidth() / 2;
                float yPos = singleHeight * i + singleHeight;
                canvas.drawBitmap(bitmap, xPos, yPos-bitmap.getHeight(), paint);
            }else*/{
                //paint.setColor(Color.rgb(33, 65, 98));  //
                //paint.setColor(0x666666);
                paint.setColor(Color.rgb(102, 102, 102));
                paint.setTypeface(Typeface.DEFAULT);  //
                paint.setAntiAlias(true);  //
                paint.setTextSize(36);  //
                //
            /*if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));  //
                paint.setFakeBoldText(true);  //
            }  */
                //
                float xPos = width / 2 - paint.measureText(A_Z[i]) / 2;
                float yPos = singleHeight * i + singleHeight;
                canvas.drawText(A_Z[i], xPos, yPos, paint);  //
            }

            paint.reset();//
        }  
  
    }  
  
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();  
        final float y = event.getY();//
        final int oldChoose = choose;  
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;  
        final int c = (int) (y / getHeight() * A_Z.length);//
  
        switch (action) {  
        case MotionEvent.ACTION_UP:
            setBackgroundDrawable(new ColorDrawable(0x00000000));
            choose = -1;//  
            invalidate();  
            if (mTextDialog != null) {  
                mTextDialog.setVisibility(View.INVISIBLE);
            }  
            break;  
  
        default:
            setBackgroundResource(R.color.transparent);
            //setBackgroundResource(R.drawable.sidebar_background);
            if (oldChoose != c) {  //
                if (c >= 0 && c < A_Z.length) {  
                    if (listener != null) {  
                        listener.onTouchingLetterChanged(A_Z[c]);  
                    }  
                    if (mTextDialog != null) {  
                        mTextDialog.setText(A_Z[c]);  
                        mTextDialog.setVisibility(View.VISIBLE);
                    }  
                    
                    choose = c;  
                    invalidate();  
                }  
            }  
  
            break;  
        }  
        return true;  
    }  
  
    /** 
     *
     *  
     * @param onTouchingLetterChangedListener 
     */  
    public void setOnTouchingLetterChangedListener(  
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {  
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;  
    }  
  
    /** 
     *
     *  
     * @author coder 
     *  
     */  
    public interface OnTouchingLetterChangedListener {  
        public void onTouchingLetterChanged(String s);
    }

}
