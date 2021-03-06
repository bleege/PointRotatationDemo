package bradleege.com.pointrotatationdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class MyDrawing extends View {
    
    private Rect myRect;
    private Point myPoint;
    private Paint rectPaint;
    private Paint pointPaint;

    private Matrix transform;

    public MyDrawing(Context context, Rect rect, Point point) {
        super(context);

        // Store the Rect and Point
        myRect = rect;
        myPoint = point;

        // Create Paint so we can see something :)
        rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        pointPaint = new Paint();
        pointPaint.setColor(Color.YELLOW);

        // Create a matrix to do rotation
        transform = new Matrix();

    }


    /**
     * Add the Rotation to our Transform matrix.
     * <p/>
     * A new point, with the rotated coordinates will be returned
     *
     * @param degrees
     * @return
     */
    public Point rotate(float degrees) {
        // This is to rotate about the Rectangles center
        transform.setRotate(degrees, myRect.exactCenterX(), myRect.exactCenterY());

        // Create new float[] to hold the rotated coordinates
        float[] pts = new float[2];

        // Initialize the array with our Coordinate
        pts[0] = myPoint.x;
        pts[1] = myPoint.y;

        // Use the Matrix to map the points
        transform.mapPoints(pts);

        // NOTE: pts will be changed by transform.mapPoints call
        // after the call, pts will hold the new cooridnates

        // Now, create a new Point from our new coordinates
        Point newPoint = new Point((int) pts[0], (int) pts[1]);

        // Return the new point
        return newPoint;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (myRect != null && myPoint != null) {
            // This is an easy way to apply the same transformation (e.g. rotation)
            // To the complete canvas.
            canvas.setMatrix(transform);

            // With the Canvas being rotated, we can simply draw
            // All our elements (Rect and Point)
            canvas.drawRect(myRect, rectPaint);
            canvas.drawCircle(myPoint.x, myPoint.y, 5, pointPaint);
        }
    }
}
