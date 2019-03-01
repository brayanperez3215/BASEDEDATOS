package darkdesigner.loin;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
//HASHMAP ES PARA GUARDAR CUALQUIER TIPO DE OBJETO ASIGNANDOLE UNA LLAVE PARA MAS TARDE RECUPERAR ESA LLAME
import java.util.HashMap;
import java.util.Map;

public class RegistrarTodo extends StringRequest {

    private static final String REGISTRAR_TODO_URL = "http://localhost/Register.php/";
    private Map<String,String> params;

    //crear parametros
    public RegistrarTodo(String username, String password, Response.Listener<String> listener){

        //LLAMAR A LA URL
        super(Method.POST, REGISTRAR_TODO_URL,listener,null );

        //parametros que van hacer enviados, SE GUARDAN LAS LLAVES
        params = new HashMap<>();


        params.put("username", username);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
