package com.example.a1_1_arreglocadenamaslarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Preparamos variables
    private EditText editTextPalabras;
    private TextView textArregloOriginal;
    private TextView arregloOriginal;
    private TextView textArregloNuevo;
    private TextView arregloNuevo;
    private Button btnAgregar;
    private Button btnCrearArreglo;
    private Button btnCancelar;
    private List<String> listaPalabras = new ArrayList<>();
    private int contadorPalabras = 0;
    private boolean palabraAgregada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPalabras = findViewById(R.id.editTextPalabras);
        textArregloOriginal = findViewById(R.id.textArregloOriginal);
        textArregloNuevo = findViewById(R.id.textArregloNuevo);
        arregloOriginal = findViewById(R.id.arregloOriginal);
        arregloNuevo = findViewById(R.id.arregloNuevo);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnCrearArreglo = findViewById(R.id.btnCrearArreglo);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCrearArreglo.setEnabled(false);

        /*
        * Funcionalidad del botón para agregar palabras al arreglo original,
        * habilitar el botón de crear el arreglo nuevo
        * y bloquear el botón una vez el arreglo tenga 10 items
        * */
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String palabra = editTextPalabras.getText().toString();
                if(!palabra.isEmpty() && contadorPalabras < 10){
                    listaPalabras.add(palabra);
                    contadorPalabras++;
                    editTextPalabras.setText("");
                    palabraAgregada = true;

                    if(contadorPalabras == 10){
                        btnAgregar.setEnabled(false);
                    }

                    actualizarTextViewArregloOriginal();
                    actualizarEstadoBtnCrearArreglo();

                }
            }
        });

        btnCrearArreglo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(palabraAgregada){
                    List<String> listaPalabrasMasLargas = new ArrayList<>();
                    int mayorLongitud = 0;

                    for(String palabra : listaPalabras){
                        if(palabra.length() > mayorLongitud && palabra.length() <= 10){
                            mayorLongitud = palabra.length();
                            listaPalabrasMasLargas.clear();
                            listaPalabrasMasLargas.add(palabra);
                        }else if(palabra.length() == mayorLongitud && palabra.length() <= 10){
                            listaPalabrasMasLargas.add(palabra);
                        }
                    }
                    String resultado = TextUtils.join(", ", listaPalabrasMasLargas);
                    arregloNuevo.setText(resultado);
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //devolvemos valores originales
                listaPalabras.clear();
                editTextPalabras.setText("");
                arregloOriginal.setText("");
                arregloNuevo.setText("");
                contadorPalabras = 0;
                btnAgregar.setEnabled(true);
                palabraAgregada = false;
                btnCrearArreglo.setEnabled(false);
            }
        });
    }

    private void actualizarEstadoBtnCrearArreglo() {
        if(palabraAgregada && contadorPalabras > 1){
            btnCrearArreglo.setEnabled(true);
        }
    }

    private void actualizarTextViewArregloOriginal(){
        StringBuilder construirArregloOriginal = new StringBuilder();

        for(String palabra : listaPalabras){
            construirArregloOriginal.append(palabra).append("\n");
        }

        arregloOriginal.setText(construirArregloOriginal.toString());
    }
}