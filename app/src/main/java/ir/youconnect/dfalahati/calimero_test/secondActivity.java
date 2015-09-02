package ir.youconnect.dfalahati.calimero_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class secondActivity extends ActionBarActivity {
    public static Button img_change;
    public ImageView img_view;
    public static Button back_btn;
    private int img_index=0;
    public int[] images = {R.mipmap.android_img1,R.mipmap.android_img2,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addBackToHomeBtn();
        changeImage();
    }
    public void addBackToHomeBtn(){
    back_btn = (Button) findViewById(R.id.btn_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void changeImage(){
        img_change = (Button) findViewById(R.id.btn_change_img);
        img_view = (ImageView) findViewById(R.id.img_view);

        img_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            img_index++;
                int current_image_index = img_index % images.length;
                img_view.setImageResource(images[current_image_index]);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
}
