package com.example.sena.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.math.*;



public class ActivityFinanciera extends ActionBarActivity {


    EditText edtValVehiculo, edtCuotaInicial, edtTasa;
    TextView txvCredito;
    TextView valorCuota;
    TextView valorPrestamo;
    Spinner spnPlazos;
    Tabla tabla;
    double cuota;
    int meses=12;
    ArrayList<String> cabecera=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_financiera);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_financiera, menu);
        inicializarUi();
        return true;
    }

    public void inicializarUi(){
        this.cabecera.add("id de la fila");
        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        edtValVehiculo = (EditText) findViewById(R.id.edtValVehiculo);
        edtCuotaInicial = (EditText) findViewById(R.id.edtCuotaInicial);
        txvCredito = (TextView) findViewById(R.id.txvCredito);
        edtTasa = (EditText) findViewById(R.id.edtTasaMensual);
        spnPlazos = (Spinner)findViewById(R.id.spnPlazos);
        valorCuota=(TextView) findViewById(R.id.textCuota);
        valorPrestamo=(TextView) findViewById(R.id.textValorPrestamo);
        inicializarSpiner();


    }

    public void inicializarSpiner(){
        //Creamos la lista
        LinkedList plazos = new LinkedList();
        //La poblamos con los ejemplos
        plazos.add(new Plazos(1, "1 Año"));
        plazos.add(new Plazos(2, "2 Años"));
        plazos.add(new Plazos(3, "3 Años"));
        plazos.add(new Plazos(4, "4 Años"));
        plazos.add(new Plazos(5, "5 Años"));
        plazos.add(new Plazos(6, "6 Años"));

        //Creamos el adaptador
        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, plazos);
        //Añadimos el layout para el menú y se lo damos al spinner
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPlazos.setAdapter(spinner_adapter);
    }

    public boolean validarVacio(String[] valores) {
        for (String val : valores) {
            if (val.isEmpty()) {
                return false;
            }
        }
        return true;
    }

   public void captura() {

        edtValVehiculo.setKeyListener(new KeyListener() {


            @Override
            public int getInputType() {
                return 1;
            }

            @Override
            public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {

                return false;
            }

            @Override
            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
                Toast t = Toast.makeText(getApplicationContext(), edtValVehiculo.getText().toString(), Toast.LENGTH_SHORT);
                t.show();
                return false;
            }

            @Override
            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
                return false;
            }

            @Override
            public void clearMetaKeyState(View view, Editable content, int states) {

            }
        });


    }








    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void calcularCredito(View v){
        String[] valores={this.edtValVehiculo.getText().toString(),this.edtCuotaInicial.getText().toString(),
                this.edtTasa.getText().toString()};
        if(!validarVacio(valores)){
            Toast t = Toast.makeText(getApplicationContext(), "Campos requeridos sin diligenciar", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        String vehiculo = edtValVehiculo.getText().toString();
        String cuota = edtCuotaInicial.getText().toString();
        String credito = txvCredito.getText().toString();
        String tasa = edtTasa.getText().toString();
       String plazo = spnPlazos.getSelectedItem().toString();
        float valorTasa=Float.parseFloat(tasa);

        if(valorTasa<0||valorTasa>3){

            Toast t = Toast.makeText(getApplicationContext(), "El valor de la tasa de estar entre 0 hasta cerca de 2,19", Toast.LENGTH_SHORT);
            t.show();
            return;

        }
        Float valorVehiculo = Float.parseFloat(edtValVehiculo.getText().toString());
        Float valorCuota = Float.parseFloat(edtCuotaInicial.getText().toString());
        int valorPlazo=0;
        if(plazo.equals("1 Año")){
            valorPlazo=1;
        }
        if(plazo.equals("2 Años")){
            valorPlazo=2;
        }
        if(plazo.equals("3 Años")){

            valorPlazo=3;

        }
        if(plazo.equals("4 Años")){

            valorPlazo=4;

        }
        if(plazo.equals("5 Años")){

            valorPlazo=5;

        }
        if(plazo.equals("6 Años")){

            valorPlazo=6;

        }


        float interes=(float)valorTasa/100;
        int n=valorPlazo*this.meses;

        Float ResultadoCredito=(valorVehiculo-valorCuota);
        double valor=1+interes;
        double valor1=Math.pow(valor,n);
        double valor2=valor1*interes;
        double valor3= valor1-1;
        double valor4=valor2/valor3;
        this.cuota=ResultadoCredito*valor4;
        double valorPrestamo=n*this.cuota;
        txvCredito.setText("el valor es:"+ResultadoCredito);

        this.valorPrestamo.setText("El valor total aproximado que ud pagaría con esta cuota es: " +valorPrestamo);


        calcular();


    }

    public void calcular(){
        this.valorCuota.setText("valor cuota:"+this.cuota);
  //      tabla.agregarCabecera(5);
  //      for(int i = 0; i < 15; i++)
   //     {
//            ArrayList<String> elementos = new ArrayList<String>();
//            elementos.add(Double.toString(this.cuota));
//            elementos.add("Casilla [" + i + ", 0]");
//            elementos.add("Casilla [" + i + ", 1]");
//            elementos.add("Casilla [" + i + ", 2]");
//            elementos.add("Casilla [" + i + ", 3]");
//           tabla.agregarFilaTabla(elementos);
  //      }







    }












}
