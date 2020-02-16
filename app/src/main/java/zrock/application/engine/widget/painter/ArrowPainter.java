package zrock.application.engine.widget.painter;

import zrock.application.R;
import zrock.application.engine.widget.SyncTextPathView;
import zrock.application.engine.widget.VelocityCalculator;

import android.graphics.Path;

public class ArrowPainter implements SyncPathPainter {
    private VelocityCalculator mVelocityCalculator = new VelocityCalculator();
    //箭头长度
    private float radius = 60;
    //箭头夹角
    private double angle = Math.PI / 8;

    public ArrowPainter(){
    }

    public ArrowPainter(int radius, double angle){
        this.radius = radius;
        this.angle = angle;
    }

    @Override
    public void onDrawPaintPath(float x, float y, Path paintPath) {
        mVelocityCalculator.calculate(x, y);
        double angleV = Math.atan2(mVelocityCalculator.getVelocityY(), mVelocityCalculator.getVelocityX());
        double delta = angleV - angle;
        double sum = angleV + angle;
        double rr = radius / (2 * Math.cos(angle));
        float x1 = (float) (rr * Math.cos(sum));
        float y1 = (float) (rr * Math.sin(sum));
        float x2 = (float) (rr * Math.cos(delta));
        float y2 = (float) (rr * Math.sin(delta));

        paintPath.moveTo(x, y);
        paintPath.lineTo(x - x1, y - y1);
        paintPath.moveTo(x, y);
        paintPath.lineTo(x - x2, y - y2);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void onStartAnimation() {
        mVelocityCalculator.reset();
    }
}
