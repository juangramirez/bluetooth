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

    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    Button actualizar;
    Button permitirBlu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actualizar = findViewById(R.id.actualizar);

        permitirBlu = findViewById(R.id.permitirBlu); //Lo primero es el nombre de la variable de tipo botón, el segundo permitirBlu es el id en ui
        // Obtener el adaptador Bluetooth
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //Si getDefaultAdapter() devuelve null, significa que el dispositivo no es compatible con Bluetooth.
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }

    }

    public void enableBluetooth(android.view.View view) {
        // Verificar si el dispositivo tiene Bluetooth
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "El dispositivo no admite Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si el Bluetooth no está habilitado, solicitar al usuario que lo habilite
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
            startActivityForResult(enableBtIntent, 1);
        } else {
            Toast.makeText(this, "Bluetooth ya está habilitado", Toast.LENGTH_SHORT).show();
        }
    }

    // Este método se ejecutará cuando el usuario responda a la solicitud de activar Bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bluetooth habilitado", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "El usuario canceló la habilitación de Bluetooth", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void actualizarAccion(View view){
        Toast.makeText(this, "Contenido actualizado (prueba) ✅", Toast.LENGTH_SHORT).show();
    }



}