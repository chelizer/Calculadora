package com.example.sena.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBasica extends ActionBarActivity {


    EditText valor1;
    EditText valor2;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_basica);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_basica, menu);
        inicializar();
        return true;
    }
public void inicializar(){

    valor1 = (EditText) findViewById(R.id.Text1);
    valor2 = (EditText) findViewById(R.id.Text2);
    resultado=(TextView)findViewById(R.id.textResultado);

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
    public boolean validarVacio(String[] valores) {
        for (String val : valores) {
            if (val.isEmpty()) {

                return false;
            }
        }
        return true;
    }




    public void sumar(View v) {
    String[] valores={this.valor1.getText().toString(),this.valor2.getText().toString()};
     if(!validarVacio(valores)){
         Toast t = Toast.makeText(getApplicationContext(), "Campos requeridos sin diligenciar", Toast.LENGTH_SHORT);
         t.show();
         return;
     }
  int val1= Integer.parseInt( this.valor1.getText().toString());
    int val2=Integer.parseInt(this.valor2.getText().toString());

    int resu=(val1+val2);

    this.resultado.setText("el valor es:"+resu);

}



    public void restar(View v) {
        String[] valores={this.valor1.getText().toString(),this.valor2.getText().toString()};
        if(!validarVacio(valores)){
            Toast t = Toast.makeText(getApplicationContext(), "Campos requeridos sin diligenciar", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        int val1= Integer.parseInt( this.valor1.getText().toString());
        int val2=Integer.parseInt(this.valor2.getText().toString());

        int resu=(val1-val2);

        this.resultado.setText("el valor es:"+resu);

    }


    public void multiplicar(View v) {
        String[] valores={this.valor1.getText().toString(),this.valor2.getText().toString()};
        if(!validarVacio(valores)){
            Toast t = Toast.makeText(getApplicationContext(), "Campos requeridos sin diligenciar", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        int val1= Integer.parseInt( this.valor1.getText().toString());
        int val2=Integer.parseInt(this.valor2.getText().toString());

        int resu=(val1*val2);

        this.resultado.setText("el valor es:"+resu);

    }

    public void dividir(View v) {
        String[] valores={this.valor1.getText().toString(),this.valor2.getText().toString()};
        if(!validarVacio(valores)){
            Toast t = Toast.makeText(getApplicationContext(), "Campos requeridos sin diligenciar", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        int val1= Integer.parseInt( this.valor1.getText().toString());
        int val2=Integer.parseInt(this.valor2.getText().toString());
        if(val2==0){

            Toast t = Toast.makeText(getApplicationContext(), "Este valor no puede ser cero", Toast.LENGTH_SHORT);
            t.show();
            return;

        }

        float resu=((float)val1/val2);

        this.resultado.setText("el valor es:"+resu);

    }




}
