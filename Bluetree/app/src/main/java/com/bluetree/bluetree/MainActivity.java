package com.bluetree.bluetree;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    Button actualizar;
    Button permitirBlu;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        actualizar = findViewById(R.id.actualizar);

        permitirBlu = findViewById(R.id.permitirBlu); //Lo primero es el nombre de la variable de tipo botón, el segundo permitirBlu es el id en ui

        //Si getDefaultAdapter() devuelve null, significa que el dispositivo no es compatible con Bluetooth.
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }

    }

    public void permitirBluetooth(View view){
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void actualizarAccion(View view){
        Toast.makeText(this, "Contenido actualizado (prueba) ✅", Toast.LENGTH_SHORT).show();
    }



}