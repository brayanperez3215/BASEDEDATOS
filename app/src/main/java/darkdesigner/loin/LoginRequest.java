package darkdesigner.loin;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://localhost/Register.php/";
    private Map<String,String> params;

    public LoginRequest(String name, String username, String password, String career, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL,listener,null );

        params = new HashMap<>();

        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("career", career);
    }

    }

