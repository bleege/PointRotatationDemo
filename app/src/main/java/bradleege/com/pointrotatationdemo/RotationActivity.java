// Based on http://stackoverflow.com/questions/7795028/get-new-position-of-coordinate-after-rotation-with-matrix

package bradleege.com.pointrotatationdemo;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class RotationActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rotation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

        Point newCoordinates = myDrawing.rotate(progress);


        // Now -> our float[] pts contains the new x,y coordinates
        Log.d("test", "Before Rotate myPoint(" + newCoordinates.x + "," + newCoordinates.y + ")");
        myDrawing.invalidate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
