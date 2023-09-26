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
                // almacenar en la variable palabra lo que se escribe en el edit text
                String palabra = editTextPalabras.getText().toString();

                //condicional para añadir la palabra al arrayList
                if(!palabra.isEmpty() && contadorPalabras < 10){
                    // se añade la palabra al arrayList
                    listaPalabras.add(palabra);
                    //contador bandera aumenta en 1 (max 10)
                    contadorPalabras++;
                    //se limpia el edit Text
                    editTextPalabras.setText("");
                    // bandera para habilitar botón de crear arreglo
                    palabraAgregada = true;

                    // condicional para deshabilitar boton para agregar palabras
                    if(contadorPalabras == 10){
                        // se deshabilita el botón
                        btnAgregar.setEnabled(false);
                    }

                    // llamamos funcion que actualiza text view del arreglo original
                    actualizarTextViewArregloOriginal();
                    // llamamos funcion que habilita el botón de crear arreglo nuevo
                    actualizarEstadoBtnCrearArreglo();

                }
            }
        });

        /**
         * funcionalidad del botón que crea el nuevo arreglo de palabras con mayor longitud
         *
         */
        btnCrearArreglo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verificamos si se han agregado palabras al array list
                if(palabraAgregada){
                    // creamos array list para las palabras mas largas
                    List<String> listaPalabrasMasLargas = new ArrayList<>();
                    // variable para ver cual es la palabra mas larga
                    int mayorLongitud = 0;

                    // recorremos las palabras que agregó el usuario
                    for(String palabra : listaPalabras){
                        /** verificamos si al recorrer el tamaño de la palabra es mayor a
                         * la variable mayorLongitud y si es menor o igual a 10
                        */
                        if(palabra.length() > mayorLongitud && palabra.length() <= 10){
                            // guardamos el tamaño de la palabra mas larga hasta ahora
                            mayorLongitud = palabra.length();
                            // limpiamos la lista de palabras mas largas
                            listaPalabrasMasLargas.clear();
                            // agregamos la palabra mas larga hasta ahora
                            listaPalabrasMasLargas.add(palabra);

                        }
                        // verificamos si la palabra que se recorre sigue siendo del mismo tamaño
                        else if(palabra.length() == mayorLongitud && palabra.length() <= 10){
                            // si es del mismo tamaño, la agregamos a la lista
                            listaPalabrasMasLargas.add(palabra);
                        }
                    }
                    // creamos la variable que mostrará la lista de palabras mas largas
                    String resultado = TextUtils.join(", ", listaPalabrasMasLargas);
                    //mostramos en text view el resultado
                    arregloNuevo.setText(resultado);
                }
            }
        });

        /*
         * botón para devolver variables y views a su valor inicial
         * */
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

    /*
    * Función para habilitar el botón para crear el nuevo arreglo
    * con la condición de que se hayan agregado por lo menos dos elementos
    * */
    private void actualizarEstadoBtnCrearArreglo() {
        if(palabraAgregada && contadorPalabras > 1){
            btnCrearArreglo.setEnabled(true);
        }
    }

    /*
    * Función para actualizar el text view del arreglo original
    * cada vez que se agrega una nueva palabra
    * */
    private void actualizarTextViewArregloOriginal(){
        StringBuilder construirArregloOriginal = new StringBuilder();

        for(String palabra : listaPalabras){
            construirArregloOriginal.append(palabra).append("\n");
        }

        arregloOriginal.setText(construirArregloOriginal.toString());
    }
}