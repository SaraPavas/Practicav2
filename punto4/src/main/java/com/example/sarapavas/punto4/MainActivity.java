package com.example.sarapavas.punto4;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;



import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private int currentYear;
    private int currentMonth;
    private int currentDay;
    int mDia, mMes, mYear;
    static final int DATE_DIALOG_ID = 0;
    private Button btAceptar;
    private Button btDate;
    private TextView dateDisplay;
    private TextView tError;
    private EditText eLoggin;
    private EditText ePassword;
    private EditText eRepPassword;
    private EditText eEmail;
    int a;
    //private EditText
    //private EditText
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        tError = (TextView) findViewById(R.id.tError);
        eLoggin = (EditText)findViewById(R.id.eLoggin);
        ePassword = (EditText)findViewById(R.id.ePassword);
        eRepPassword = (EditText)findViewById(R.id.eRepPassword);
        eEmail = (EditText)findViewById(R.id.eEmail);
        btAceptar = (Button) findViewById(R.id.btAceptar);
        btDate = (Button) findViewById(R.id.btDate);

        //Fecha de nacimiento
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);

        btDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        //ACEPTAR
        btAceptar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!Revisar())
                    dateDisplay.setText("Ingrese bien loscampos");
                if(Revisar())
                    dateDisplay.setText("podemos Seguir");
            }
        });

    }

    public boolean Revisar(){
        tError.setHint("");
        String sLoggin = eLoggin.getText().toString();
        String sPassword = ePassword.getText().toString();
        String sRepPassword = eRepPassword.getText().toString();
        String sEmail = eEmail.getText().toString();
        if(TextUtils.isEmpty(sLoggin) || TextUtils.isEmpty(sPassword) || TextUtils.isEmpty(sRepPassword)
                || TextUtils.isEmpty(sPassword) || TextUtils.isEmpty(sEmail) ) {
            tError.setHint("Hay espacios en blanco");
            return false;
        }if(!sPassword.equals(sRepPassword)){
            tError.setHint("Las contraseñas no coinciden");
            return false;
        }
        return true;
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, reservationDate, currentYear,
                        currentMonth, currentDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener reservationDate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int month, int day) {
            mDia = day;
            mMes = month;
            mYear = year;
            dateDisplay.setText((mMes+1) + "-" + mDia + "-" + mYear);
        }

    };

}