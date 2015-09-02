package ir.youconnect.dfalahati.calimero_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class relative_layout_test extends ActionBarActivity {
    public static ListView list_view;
    public static String[] values = new String[] {"Any","Many","Mini","Mo"};
    ListView list;
    String[] web = {
            "Living room",
            "office",
            "Parking",
            "Restroom"
    } ;
    Integer[] imageId = {
            R.mipmap.livingroom,
            R.mipmap.office,
            R.mipmap.parking,
            R.mipmap.restroom
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout_test);
        addListViewItems();

    }
    public void addListViewItems(){
        costumList adapter = new
                costumList(relative_layout_test.this, web, imageId);
        list=(ListView)findViewById(R.id.list_view_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(relative_layout_test.this, "You Clicked at " + web[position]+
                        " which means "+(position+1)+"th row", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_relative_layout_test, menu);
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
