package com.example.panchamkhaitan.wifi;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    //int count=0;
    //StringBuffer buffer = new StringBuffer();
    Context context;
    Button mapsButton,positionButton;

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