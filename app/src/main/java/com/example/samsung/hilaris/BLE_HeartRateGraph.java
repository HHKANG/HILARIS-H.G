package com.example.samsung.hilaris;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import static com.example.samsung.hilaris.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

public class BLE_HeartRateGraph extends Graph {
    TextView textView;
    private BluetoothLeService mBluetoothLeService;
    private String mDeviceAddress;
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    public int IntData;
    public String StringData;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_heartrategraph);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        textView = (TextView)findViewById(R.id.text_rate);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);


        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(100);
        viewport.setMinX(0);
        viewport.setMaxX(40);
    // activate horizontal zooming and scrolling
        viewport.setScalable(true);

    // activate horizontal scrolling
        viewport.setScrollable(true);

    // activate horizontal and vertical zooming and scrolling
        viewport.setScalableY(true);

    // activate vertical scrolling
        viewport.setScrollableY(true);



    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                StringData=intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
                textView.setText(StringData);
                IntData = Integer.parseInt(StringData);
                addEntry(IntData);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 1000; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry(IntData);
                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }

    private void addEntry(int IntData) {
        // here, we choose to display max 100 points on the viewport and we scroll to end
        series.appendData(new DataPoint(lastX++, IntData), true, 1000);    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.logout:
                Intent intent = new Intent(BLE_HeartRateGraph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}