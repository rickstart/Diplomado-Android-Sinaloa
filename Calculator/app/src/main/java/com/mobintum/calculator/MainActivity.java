package com.mobintum.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnZero,btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    Button btnAC, btnPlusLess, btnPercent, btnDivision, btnMultiply, btnLess, btnPlus, btnEqual, btnDot ;
    TextView textResult;

    Double oper1=0.0, oper2=0.0;
    boolean flagOper = true;
    int typeOper=0;
    final int PLUS = 1, LESS=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = (TextView) findViewById(R.id.textResult);

        btnZero = (Button) findViewById(R.id.btnZero);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnAC= (Button) findViewById(R.id.btnAC);
        btnPlusLess = (Button) findViewById(R.id.btnPlusLess);
        btnPercent = (Button) findViewById(R.id.btnPercent);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnLess = (Button) findViewById(R.id.btnLess);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnDot = (Button) findViewById(R.id.btnDot);

        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnPlusLess.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnLess.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnDot.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    @Override
    public void onClick(View v) {
        Button btnTemp;

        switch(v.getId()){

            case R.id.btnAC:
                textResult.setText("");
                oper1=0.0;
                oper2=0.0;
                break;

            case R.id.btnDot:
                btnTemp = (Button) findViewById(v.getId());


                if(flagOper){
                    if(!textResult.getText().toString().contains("."))
                    textResult.append(btnTemp.getText().toString());
                }else{
                    textResult.setText(btnTemp.getText().toString());
                    flagOper = true;
                }


                break;
            case R.id.btnEqual:

                switch(typeOper){

                    case PLUS:
                        if(oper1==0.0){
                            oper1 = Double.valueOf(textResult.getText().toString());

                            flagOper = false;
                            return;
                        }else if(flagOper){
                            oper2 = Double.valueOf(textResult.getText().toString());

                        }

                        if(oper2!=0.0){
                            Double operation = oper1+oper2;
                            textResult.setText(operation.toString());
                            oper1=operation;
                            oper2=0.0;

                            flagOper = false;
                            return;
                        }
                        break;
                }

                break;

            case R.id.btnPlus:

                typeOper = PLUS;

                if(oper1==0.0){
                    oper1 = Double.valueOf(textResult.getText().toString());

                    flagOper = false;
                    return;
                }else if(flagOper){
                    oper2 = Double.valueOf(textResult.getText().toString());

                }

                if(oper2!=0.0){
                    Double operation = oper1+oper2;
                    textResult.setText(operation.toString());
                    oper1=operation;
                    oper2=0.0;

                    flagOper = false;
                    return;
                }

                break;


            default:
                btnTemp = (Button) findViewById(v.getId());
                if(flagOper){
                    textResult.append(btnTemp.getText().toString());
                }else{
                    textResult.setText(btnTemp.getText().toString());
                    flagOper = true;
                }



                break;

        }

    }
}
