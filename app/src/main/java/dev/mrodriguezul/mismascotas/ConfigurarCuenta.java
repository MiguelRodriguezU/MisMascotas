package dev.mrodriguezul.mismascotas;

import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.mrodriguezul.mismascotas.db.Preferences;

public class ConfigurarCuenta extends AppCompatActivity {

    private EditText etCuentaUsuario;
    private Button btnGuardarCuenta;
    private String usuarioNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar myActionBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myActionBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCuentaUsuario = (EditText)findViewById(R.id.et_cuenta_usuario);
        btnGuardarCuenta = (Button)findViewById(R.id.btn_cuenta_guardar);

        usuarioNombre = Preferences.getInstancia(ConfigurarCuenta.this).getUsuarioNombre();
        etCuentaUsuario.setText(usuarioNombre);

        btnGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioNombre = etCuentaUsuario.getText().toString();
                if(!usuarioNombre.equals("")){
                    Preferences.getInstancia(ConfigurarCuenta.this).setUsuarioNombre(usuarioNombre);
                    cerrarConfiguracion();
                }
            }
        });
    }

    private void cerrarConfiguracion(){
        finish();
    }
}
