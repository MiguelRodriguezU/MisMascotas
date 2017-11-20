package dev.mrodriguezul.mismascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {
    private EditText etNombre;
    private EditText etEmail;
    private EditText etMensaje;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar myActionBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myActionBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = (EditText)findViewById(R.id.et_contacto_nombre);
        etEmail = (EditText)findViewById(R.id.et_contacto_email);
        etMensaje = (EditText)findViewById(R.id.et_contacto_mensaje);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });



    }

    private void enviarMensaje(){
        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String mensaje = etMensaje.getText().toString();

        if(!nombre.equals("") && !email.equals("") && !mensaje.equals("")){

        }else{
            Toast.makeText(getBaseContext(),"Los datos no puedes ser vacíos",Toast.LENGTH_LONG).show();
        }


    }
}
