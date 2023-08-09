package com.bluetree.bluetree;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    BroadcastReceiver receiver;
    Button actualizar;
    Button permitirBlu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Register for broadcasts when a device is discovered.
        //Detección de dispositivos
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actualizar = findViewById(R.id.actualizar);

        permitirBlu = findViewById(R.id.crearGrupo); //Lo primero es el nombre de la variable de tipo botón, el segundo permitirBlu es el id en ui
        // Obtener el adaptador Bluetooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        setContentView(R.layout.activity_main);

        //detección de dispositivos
        devicesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(devicesArrayAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivityForResult(enableBtIntent, 1);
        }

        registerReceiver(bluetoothReceiver, filter);
        bluetoothAdapter.startDiscovery();
        //fin de detección
    }

    //detección de dispositivos
        private ArrayAdapter<String> devicesArrayAdapter;

        private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    String deviceName = device.getName();
                    String deviceAddress = device.getAddress();
                    devicesArrayAdapter.add(deviceName + "\n" + deviceAddress);
                }
            }
        };

        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(bluetoothReceiver);
        }
    //fin de detección (borrar esto)

    public void enableBluetooth(android.view.View view) {
        // Verificar si el dispositivo tiene Bluetooth

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "El dispositivo no admite Bluetooth 😖", Toast.LENGTH_SHORT).show();
        } else {
            // Si el Bluetooth no está habilitado, solicitar al usuario que lo habilite
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivityForResult(enableBtIntent, 1);
            } else {
                Toast.makeText(this, "Bluetooth ya está habilitado", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // Este método se ejecutará cuando el usuario responda a la solicitud de activar Bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bluetooth habilitado ✅", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Se canceló la habilitación de Bluetooth ❌", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void actualizarAccion(View view) {
        Toast.makeText(this, "Contenido actualizado (prueba) ✅", Toast.LENGTH_SHORT).show();
    }


    //Botón solicitar info de dispositivos vinculados
    /*
    public void consultarDispositivos(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    }
    */


}