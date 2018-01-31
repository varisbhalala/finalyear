package com.example.panchamkhaitan.wifi;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    WifiManager wifiManager;
    //TextView textView;
    ListView listView;
    double distance = 0;
    //int strength[] = new int[15];
    //int freq[] = new int[15];
    String bssid[] = new String[15];
    //double distance[] = new double[15];
    ArrayList<Double> distance1 = new ArrayList<>();
    //int count=0;
    //StringBuffer buffer = new StringBuffer();
    Context context;
    Button mapsButton,positionButton;
    Manager manager = null;
    Database database = null;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch) findViewById(R.id.myswitch);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        //textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        context = this;
        //positionButton = (Button) findViewById(R.id.position_btn);

        mapsButton = (Button) findViewById(R.id.map_button);
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(Build.VERSION.SDK_INT >= 23 && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);

                    listWifi(isChecked);
                }else {
                    listWifi(isChecked);
                }
            }
        });

        positionButton = (Button) findViewById(R.id.position_button);

        positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position(distance1);
//                Toast.makeText(context, position(distance1), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }

                builder.setTitle("Position in Cartesian")
                        .setMessage(position(distance1))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
                }
        });

        // Create a manager

        try {
            manager = new Manager(new AndroidContext(getApplicationContext()), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create or open the database named app

        try {
            database = manager.getDatabase("app");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        // The properties that will be saved on the document

        // Create replicators to push & pull changes to & from Sync Gateway.
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:8091/wifi");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Replication push = database.createPushReplication(url);
        Replication pull = database.createPullReplication(url);
        push.setContinuous(true);
        pull.setContinuous(true);

        // Start replicators
        push.start();
        pull.start();


































    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1001 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return;
        }else {
            Toast.makeText(getApplicationContext(), "Please Allow the permission!", Toast.LENGTH_LONG).show();
        }
    }

    void listWifi(boolean isChecked){
        if (isChecked && !wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
            MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
            registerReceiver(myBroadCastReceiver, new IntentFilter(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        }
        else {
            wifiManager.setWifiEnabled(false);

        }
    }

    public void callme(ArrayList arrayList) {
        Toast toast = Toast.makeText(context, "We found something!", Toast.LENGTH_SHORT);
        toast.show();

//        String[] val = arrayList.toString().split("\n");

        final WiFiAdapter wiFiAdapter = new WiFiAdapter(this, arrayList);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, val);
        listView.setAdapter(wiFiAdapter);

        Toast toast1 = Toast.makeText(context, "Done!", Toast.LENGTH_SHORT);
        toast1.show();
    }



    public String position(ArrayList arrayList){
        double r1 = (double) arrayList.get(0);
        double r2 = (double) arrayList.get(1);
        double r3 = (double) arrayList.get(2);
        double p1[] = {0.0,0.0};
        double p2[] = {5.0,0.0};
        double p3[] = {0.0,8.0};
        double p2p1distance = Math.pow(Math.pow(p2[0] - p1[0] , 2) + Math.pow(p2[1] - p1[1] , 2) , 0.5);
        double exx = (p2[0] - p1[0])/p2p1distance;
        double exy = (p2[1] - p1[1])/p2p1distance;
        double auxx = (p3[0] - p1[0]);
        double auxy = (p3[1] - p1[1]);
        double i = exx * auxx + exy * auxy;
        double aux2x = p3[0] - p1[0] - (i * exx);
        double aux2y = p3[1] - p1[1] - (i * exy);
        double eyx = aux2x / (Math.pow(Math.pow(aux2x,2) + Math.pow(aux2y,2) , 0.5)) ;
        double eyy = aux2y / (Math.pow(Math.pow(aux2x,2) + Math.pow(aux2y,2) , 0.5)) ;
        double j = eyx * auxx + eyy * auxy;
        double x = (Math.pow(r1,2) - Math.pow(r2,2) + Math.pow(p2p1distance,2))/ (2 * p2p1distance);
        double y = (Math.pow(r1,2) - Math.pow(r3,2) + Math.pow(i,2) + Math.pow(j,2))/(2*j) - i*x/j;
        double finalX = (p1[0] + x * exx + y * eyx)/1000;
        double finalY = (p1[1] + x * exy + y * eyy)/1000;
        String ans = "X: "+finalX + "\n" + "Y: " + finalY;

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("p1x", p1[0]);
        properties.put("p1y", p1[1]);
        properties.put("p2x", p2[0]);
        properties.put("p2y", p2[1]);
        properties.put("p3x", p3[0]);
        properties.put("p3y", p3[1]);
        properties.put("x", finalX);
        properties.put("y", finalY);
        // Create a new document
        Document document = database.createDocument();
        // Save the document to the database
        try {
            document.putProperties(properties);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        // Log the document ID (generated by the database)
        // and properties
        Log.d("app", String.format("Document ID :: %s", document.getId()));
        Log.d("app", String.format("coordinate x : %s %s , coordinate y : %s %s , coordinate z : %s %s , finalx : %s finaly : %s", document.getProperty(String.valueOf("p1x")), document.getProperty(String.valueOf("p1y")), document.getProperty(String.valueOf("p2x")), document.getProperty(String.valueOf("p2y")), document.getProperty(String.valueOf("p3x")), document.getProperty(String.valueOf("p3y")), document.getProperty(String.valueOf("x")), document.getProperty(String.valueOf("y"))));

























        return ans;








































    }

    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
//            StringBuffer stringBuffer = new StringBuffer();

            final ArrayList<WiFi> wifiList = new ArrayList<>();

            Comparator<ScanResult> comparator = new Comparator<ScanResult>() {
                @Override
                public int compare(ScanResult lhs, ScanResult rhs) {
                    return (lhs.level > rhs.level ? -1 : (lhs.level == rhs.level ? 0 : 1));
                }
            };
            List<ScanResult> list = wifiManager.getScanResults();
            Collections.sort(list, comparator);
            for (ScanResult scanResult : list){
//                stringBuffer.append(scanResult.SSID+ ": "+scanResult.level+" "+scanResult.BSSID+"\n");

                String wifiName = scanResult.SSID;
                int wifiStrength = scanResult.level;
                //strength[count] = wifiStrength;
                String wifiMAC = scanResult.BSSID;
                //bssid[count] = wifiMAC;
                int wifiFreq = scanResult.frequency;
                //freq[count] = wifiFreq;
                //count++;

                double exp = (27.55 - (20 * Math.log10(wifiFreq)) + Math.abs(wifiStrength)) / 20.0;
                distance = Math.pow(10.0, exp);
                String finalDistance = String.format("%.4f", distance);
//                Log.v("Wifi name: ", wifiName);
//                Log.v("Wifi MAC: ", wifiMAC);
                distance1.add(distance);
                WiFi wifi = new WiFi(wifiName, wifiStrength, wifiMAC , finalDistance);
                wifiList.add(wifi);
            }
//            positionButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    int avg_dB = (strength[0] + strength[1] + strength[2])/3;
////                    int avg_freq = (freq[0] + freq[1] + freq[2])/3;
//                    //double distance =  (( 10 * (avg_dB - 32.44 - (20 * Math.log10(avg_freq))) ) / 20);
//                    for (int i=0;i<3;i++) {
//
//                    }
//                    Context context1 = getApplicationContext();
//                    Toast toast2 = Toast.makeText(context1, bssid[0] + " "+distance[0] + "/n" + bssid[1] +" "+ distance[1] + "/n" + bssid[2] +" "+ distance[2] + "/n" ,Toast.LENGTH_LONG);
//                    toast2.show();
//                }
//            });

            if(wifiManager.isWifiEnabled())
                callme(wifiList);
            else if(!wifiManager.isWifiEnabled())
                wifiList.removeAll(list);
            Log.v("Wifi li:", wifiList.toString());
        }
    }
}