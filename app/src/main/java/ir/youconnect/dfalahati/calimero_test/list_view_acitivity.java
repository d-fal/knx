package ir.youconnect.dfalahati.calimero_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class list_view_acitivity extends ActionBarActivity {
    private int img_index=0;
    public int[] images = {R.mipmap.lamp_off,R.mipmap.lamp_on};
    public Button Btn1;
    public ImageView img;
    public static ListView list_view;
    public static String[] values = new String[] {"Any","Many","Mini","Mo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_acitivity);
        imageClickListener();
        addListViewItems();

    }
    public void addListViewItems(){
        list_view = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.name_layout,values);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = list_view.getItemAtPosition(position).toString();
                Toast.makeText(list_view_acitivity.this,"You picked "+selectedItem,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void imageClickListener(){
        img = (ImageView) findViewById(R.id.icon);
        img.setImageResource(images[0]);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_index++;
                int current_image_index = img_index % images.length;
                img.setImageResource(images[current_image_index]);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_acitivity, menu);
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
