package darkdesigner.loin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Uuario extends AppCompatActivity {

    TextView tvNombre, tvUsuario, tvCarrera , tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uuario);

        tvNombre = (TextView) findViewById(R.id.tv_nombre);
        tvUsuario = (TextView) findViewById(R.id.tv_usuario);
        tvCarrera = (TextView) findViewById(R.id.tv_career);
        tvPassword = (TextView) findViewById(R.id.tv_password);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String career = intent.getStringExtra("career");

        tvNombre.setText(name);
        tvUsuario.setText(username);
        tvCarrera.setText(career);
        tvPassword.setText(password);

    }
}
