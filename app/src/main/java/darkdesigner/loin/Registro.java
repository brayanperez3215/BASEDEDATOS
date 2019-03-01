package darkdesigner.loin;
import android.content.Intent;
import android.app.AlertDialog;
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

public class Registro extends AppCompatActivity implements View.OnClickListener {

    //definir variables

    EditText etnombre, etusuario, etpassword, etcareer;
    Button btn_REGISTRAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = (EditText) findViewById(R.id.Editnombre);
        etusuario = (EditText) findViewById(R.id.Edit_usuario);
        etpassword = (EditText) findViewById(R.id.Editpassword);
        etcareer = (EditText) findViewById(R.id.Editcareer);

        btn_REGISTRAR = (Button) findViewById(R.id.btnREGISTRAR);

        //implemento evento onclick
        btn_REGISTRAR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //definir variables para recibir datos de los edittext
        final String name = etnombre.getText().toString();
        final String username = etusuario.getText().toString();
        final String password = etpassword.getText().toString();
        final String career = etcareer.getText().toString();

        //RESPONSE:muestra el resultado del codigo
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //JSON ES UN INTERCAMBIO DE DATOS LIVIANO BASADO EN TEXTO UTILIZA MATRICES Y OBJETOS
                // Esta clase representa un objeto en el Json; es decir, un conjunto de pares clave-valor donde las claves son strings y los valores son  cadenas, números, booleanos.
                try {
                    JSONObject jsonresponse =  new JSONObject(response);

                    //devolver boolean  captura del lgin si se hace el registro en el DB
                    boolean success = jsonresponse.getBoolean("success");

                    if (success){
                        Intent intent = new Intent(Registro.this, BaseDatos.class);
                        Registro.this.startActivity(intent);
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
        RegistrarTodo registrarTodo = new RegistrarTodo(name, String:username, password, career, respoListener );
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registrarTodo);

    }
}
