package com.cirrastec.conversormoedas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initObjects();
        this.clearValues();

        // ViewHolder
        // ListView

    }

    private void initObjects() {
        this.mViewHolder.valor = findViewById(R.id.edit_value);
        this.mViewHolder.valorDolar = findViewById(R.id.text_dolar);
        this.mViewHolder.valorEuro = findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = this.findViewById(R.id.button_calculate);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);
        this.mViewHolder.valor.addTextChangedListener(new MoneyTextWatcher(this.mViewHolder.valor));
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_calculate) {
            String value = this.mViewHolder.valor.getText().toString();
            if ("".equals(value)) {
                Toast.makeText(this, R.string.informe_valor, Toast.LENGTH_LONG).show();
            } else {
                this.calculate(value);
            }
        }
    }

    private void clearValues() {
        this.mViewHolder.valorDolar.setText("");
        this.mViewHolder.valorEuro.setText("");
    }

    private void calculate(String value) {
        value = value.replace(",",".")
                .replaceAll("[^\\d.]","")
                .trim();
        //Toast.makeText(this, value, Toast.LENGTH_LONG).show();
        Double dReal = Double.valueOf(value), dDolar, dEuro;

        dDolar = (dReal / 4);
        dEuro = (dReal / 5);

        this.mViewHolder.valorDolar.setText("$"+String.format("%.2f",dDolar));
        this.mViewHolder.valorEuro.setText("€"+String.format("%.2f",dEuro));
    }

    private static class ViewHolder {
        EditText valor;
        TextView valorDolar;
        TextView valorEuro;
        Button buttonCalculate;
        /*
        EditText valor =
        */
    }

}
