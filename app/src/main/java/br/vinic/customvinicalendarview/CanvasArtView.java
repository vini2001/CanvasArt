package br.vinic.customvinicalendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class CanvasArtView extends View {

    private Paint paintDoodle = new Paint();
    private Path path;
    private ArrayList<CustomPath> paths = new ArrayList<>();
    private ArrayList<CustomPath> undones = new ArrayList<>();
    private int widthAtual;
    private String corAtual = "#008577";

    public CanvasArtView(Context context) {
        super(context);
        innit(null, 0);
    }

    public CanvasArtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit(attrs, 0);
    }

    public CanvasArtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(attrs, defStyleAttr);
    }

    private void innit(AttributeSet attrs, int defStyle) {
        paintDoodle.setColor(Color.parseColor("#008577"));
        paintDoodle.setStrokeWidth(1);
        paintDoodle.setAntiAlias(true);
        paintDoodle.setStyle(Paint.Style.STROKE);
        paintDoodle.setDither(true);                    // set the dither to true
        paintDoodle.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        paintDoodle.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        paintDoodle.setPathEffect(new CornerPathEffect(10) );   // set the path effect when they join.
    }

    public void setColor(String hexColor) {
        corAtual = hexColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (CustomPath p : paths) {
            paintDoodle.setStrokeWidth(p.getWidth());
            paintDoodle.setColor(p.getColor());
            canvas.drawPath(p.getPath(), paintDoodle);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                path = new Path();
                path.moveTo(touchX, touchY);
                paths.add(new CustomPath(path, widthAtual, Color.parseColor(corAtual)));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                path.lineTo(touchX, touchY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }


        invalidate();
        return true;
    }

    public void redo() {
        if (undones.size() > 0) {
            paths.add(undones.remove(undones.size() - 1));
        }
        invalidate();
    }

    public void undo() {
        if (paths.size() > 0) {
            undones.add(paths.remove(paths.size() - 1));
            invalidate();
        }
    }

    public void setEspessura(int qtd) {
        this.widthAtual = qtd;
    }

    private class CustomPath {

        private int width;
        private int color;
        private Path path;

        public CustomPath(Path path, int width, int color) {
            this.path = path;
            this.width = width;
            this.color = color;
        }

        public int getWidth() {
            return width;
        }

        public Path getPath() {
            return path;
        }

        public int getColor(){
            return color;
        }

    }
}
