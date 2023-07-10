package com.bluetree.bluetree;

import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        actualizar = findViewById(R.id.actualizar);

    }

    public void actualizarAccion(View view){
        Toast.makeText(this, "Contenido actualizado ✅", Toast.LENGTH_SHORT).show();
    }



}