package ir.youconnect.dfalahati.calimero_test;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.exception.KNXException;
import tuwien.auto.calimero.knxnetip.KNXnetIPConnection;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class MainActivity extends ActionBarActivity {
    private Button btn1;
    private EditText txt1;
    private Button layout_Btn;
    public static String MY_LOG="MY_LOGGER";

    private static Button next_activity_btn;
    private static  Button btn_list_view;
    public static String error_msg;
    private static String group;
    //
    private static final String remoteHost = "192.168.1.232";
    private static String localHost ;
    private static final int knxServerPort = KNXnetIPConnection.DEFAULT_PORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerButton();
        addNextPageJumper();
        gotoListView();
        declareIpAddress();
        gotoLayoutActivity();
        gotoRelativeLayout();

    }
    public void gotoRelativeLayout(){
        Button rel = (Button) findViewById(R.id.relative_layout_btn);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ir.youconnect.dfalahati.calimero_test.relative_layout_test");
                startActivity(intent);
            }
        });
    }
    public void gotoLayoutActivity(){
        layout_Btn = (Button) findViewById(R.id.layout_test_btn);
        layout_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ir.youconnect.dfalahati.calimero_test.layout_test");
                startActivity(intent);
            }
        });
    }
    public void declareIpAddress(){
        Thread th = new Thread(){
            public void run(){
                WifiManager wifi = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo wiInf = wifi.getConnectionInfo();
                int ipAddress = wiInf.getIpAddress();
                localHost= String.format("%d.%d.%d.%d",
                        (ipAddress & 0xff), (ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
                System.out.println("Your ip address is: "+localHost);
            }
        };
        th.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
          if (id == R.id.action_settings) {
            return true;
          }

        return super.onOptionsItemSelected(item);
    }
    public void gotoListView(){
        btn_list_view = (Button) findViewById(R.id.btn_list_activity);
        btn_list_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ir.youconnect.dfalahati.calimero_test.list_view_acitivity");
                startActivity(intent);
            }
        });
    }
    public void addNextPageJumper(){
        next_activity_btn = (Button) findViewById(R.id.nextActivity_btn);

        next_activity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ir.youconnect.dfalahati.calimero_test.secondActivity");
                startActivity(intent);
            }
        });

    }

    public void addListenerButton(){

        txt1 = (EditText) findViewById(R.id.text);
        btn1 = (Button) findViewById(R.id.btn1);


        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
               // remoteHost = txt1.getText().toString();
            Toast.makeText(MainActivity.this,txt1.getText(),Toast.LENGTH_SHORT).show();
                //

            System.out.println("Starting...");
        Thread th = new Thread(){
            public void run(){
                EditText txt2 = (EditText) findViewById(R.id.group_address);
                group =  txt2.getText().toString();
                System.out.println("This example shows how to establish a KNX connection "
                        + "to a KNXnet/IP server.");

                ProcessCommunicator pc = null;
                try {

                    if(localHost.matches("0.0.0.0")) return;
                    final KNXNetworkLinkIP knxLink;
                    final InetSocketAddress localEP = new InetSocketAddress(
                            InetAddress.getByName(localHost), 0);
                    // now the remote port
                    final InetSocketAddress remoteEP = new InetSocketAddress(remoteHost, knxServerPort);
                    System.out.println("Try connecting to " + remoteHost + " on port " + knxServerPort + "...");
                    knxLink = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNELING, localEP, remoteEP, false,
                            TPSettings.TP1);
                    pc = new ProcessCommunicatorImpl(knxLink);
                    System.out.println("read the group value from datapoint " + group);
                    System.out.println("Connection to " + remoteHost + " successfully established");
                    boolean value = pc.readBool(new GroupAddress(group));
                    if(value){
                        pc.write(new GroupAddress(group),false);
                    }else{
                        pc.write(new GroupAddress(group),true);
                    }
                    System.out.println("value read from datapoint " + group + ": " + value);
                    // We always make sure to close the connection after we used it
                    knxLink.close();

                    System.out.println("Connection got closed");
                }
                catch (final KNXException e) {

                    // All checked Calimero-specific exceptions are subtypes of KNXException
                    System.out.println("Error connecting to " + remoteHost + ": " + e.getMessage());
                }
                catch (final InterruptedException e) {

                    System.out.println("Connecting to " + remoteHost + " was interrupted, quit");
                }
                catch (final UnknownHostException e) {
                    System.out.println("Host resolution for local endpoint " + localHost + " failed");
                }
                Log.i(MY_LOG,"End of knx command");
            }
        };
                th.start();

            }

        });
        Log.i("MY_LOGGER", "test2");
    }

}
