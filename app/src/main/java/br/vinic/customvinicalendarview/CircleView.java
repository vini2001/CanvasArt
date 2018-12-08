package br.vinic.customvinicalendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint paintDoodle = new Paint();

    public CircleView(Context context) {
        super(context);
        innit(null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit(attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(attrs, defStyleAttr);
    }

    private void innit(AttributeSet attrs, int defStyle) {
        paintDoodle.setColor(Color.parseColor("#008577"));
        paintDoodle.setStrokeWidth(1);
        paintDoodle.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2.0f, getHeight()/2.0f, getWidth()/2.0f, paintDoodle);
    }

    public void setColor(String cor) {
        paintDoodle.setColor(Color.parseColor(cor));
        invalidate();
    }
}
