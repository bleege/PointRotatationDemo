// Based on http://stackoverflow.com/questions/7795028/get-new-position-of-coordinate-after-rotation-with-matrix

package bradleege.com.pointrotatationdemo;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class RotationActivity extends Activity implements SeekBar.OnSeekBarChangeListener {


    private MyDrawing myDrawing;
    private SeekBar mSeekbar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        Rect rect = new Rect(150,150,440,630);

        int x = (int) (rect.left + Math.random() * rect.width());
        int y = (int) (rect.top + Math.random() * rect.height());
        Point coordinate = new Point(x, y);

        // To draw the rect we create a CustomView
        myDrawing = new MyDrawing(this, rect, coordinate);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.container);
        rl.addView(myDrawing);

        mSeekbar = (SeekBar)findViewById(R.id.seekBar1);
        mSeekbar.setMax(360);
        mSeekbar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

        Point newCoordinates = myDrawing.rotate(progress);

        // Now -> our float[] pts contains the new x,y coordinates
        Log.i("test", "Before Rotate myPoint(" + newCoordinates.x + "," + newCoordinates.y + ")");
        myDrawing.invalidate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
