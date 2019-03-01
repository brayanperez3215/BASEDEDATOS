package darkdesigner.loin;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class BaseDatos extends AppCompatActivity {

    Button btn_registrar , btn_iniciar;
    EditText etusuario,etcodigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);

        btn_registrar = (Button) findViewById(R.id.btnregistrar);
        btn_iniciar = (Button) findViewById(R.id.btniniciar);
        etusuario = (EditText) findViewById(R.id.Editusuario);
        etcodigo = (EditText) findViewById(R.id.Editcodigo);
        //cuando se le de a registrara hara tal cosa
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent es para que una actividad llame a otra
                Intent IntentRegistrar = new Intent(BaseDatos.this, Registro.class);
                BaseDatos.this.startActivity(IntentRegistrar);

            }
        });

        btn_iniciar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final String username = etusuario.getText().toString();
                final String password = etcodigo.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);

                            //devolver boolean  captura del lgin si se hace el registro en el DB
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){

                                String name = jsonResponse.getString("name");
                                String career = jsonResponse.getString("career");

                                Intent intent = new Intent(BaseDatos.this,Uuario.class);
                                Intent.putExtra("name",name);
                                Intent.putExtra("username",username);
                                Intent.putExtra("career",career);
                                Intent.putExtra("password",password);

                                BaseDatos.this.startActivity(intent);



                            }else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                builder.setMessage ("error en el registro")
                                        .setNegativeButton ("Retry", null)
                                        .create().show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            }




                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(BaseDatos.this);
                queue.add(loginRequest);
            }

                BaseDatos baseDatos = new BaseDatos()
            });
    }
}
