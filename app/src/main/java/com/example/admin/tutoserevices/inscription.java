package com.example.admin.tutoserevices;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.admin.tutoserevices.Functions.REGISTER_URL;

public class inscription extends AppCompatActivity {
    private static final String TAG = inscription.class.getSimpleName();
    private Button btnRegister, btnLinkToLogin, valider, Annuller;
    private EditText emailEd;
    private EditText passwordEd;
    private EditText nomEd;
    private EditText prenomEd;
    private EditText telEd;
    private EditText cinEd;
    private ProgressDialog pDialog;

    public inscription() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        nomEd = (EditText) findViewById(R.id.Nomins);
        prenomEd = (EditText) findViewById(R.id.prénomins);
        emailEd = (EditText) findViewById(R.id.Emailins);
        telEd = (EditText) findViewById(R.id.Tel);
        cinEd = (EditText) findViewById(R.id.cin);
        passwordEd = (EditText) findViewById(R.id.textPasswordins);
        valider = (Button) findViewById(R.id.Validerins);
        Annuller = (Button) findViewById(R.id.Annulerins);
        Button btnRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        Button btnLinkToLogin = (Button) findViewById(R.id.btnLogin);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
    }

    private void init() {
        // Login button Click Event
        valider.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.v("MY_LOG", "valid clicked");
//                // Hide Keyboard
                Functions.hideSoftKeyboard(inscription.this);

                String nom_c = nomEd.getText().toString().trim();
                String email_c = emailEd.getText().toString().trim();
                String password_c = passwordEd.getText().toString().trim();
                String tel_c = telEd.getText().toString().trim();
                String id_c = cinEd.getText().toString().trim();
                String prenom_c = prenomEd.getText().toString().trim();

                Log.v("MY_LOG1", "name entred : " + nom_c);
                Log.v("MY_LOG1", "email entred : " + email_c);
                Log.v("MY_LOG1", "password entred : " + password_c);
                Log.v("MY_LOG1", "tel entred : " + tel_c);
                Log.v("MY_LOG1", "cin entred : " + id_c);
                Log.v("MY_LOG1", "prenom entred : " + prenom_c);
                if (!nom_c.isEmpty() && !email_c.isEmpty() && !password_c.isEmpty() && !tel_c.isEmpty() && !id_c.isEmpty() && !prenom_c.isEmpty()) {

                    registerUser(nom_c, prenom_c, email_c, password_c, tel_c, id_c);

                    Intent intent = new Intent(inscription.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();

                    // Check for empty data in the form
//                if (!nom_c.isEmpty() && !email_c.isEmpty() && !password_c.isEmpty() && !tel_c.isEmpty() && !id_c.isEmpty() && !prenom_c.isEmpty()) {
//                   if (passwordEd.length() < 4) {
//
//                       Toast.makeText(getApplicationContext(), "Password too short, enter minimum 4 characters!", Toast.LENGTH_SHORT).show();
//                       return;
////                    }
//                    Log.v("MY_LOG3", "valid clicked7");
//
                    // if (Functions.isValidEmailAddress(email_c)) {
                    // registerUser(namee, prenomE, emails, passwordE, telf, cins);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                   Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
//               }
//
                }

            }


        });
        Annuller.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void registerUser(final String namee, final String prenomE, final String emails, final String passwordE, final String telf, final String id) {

        Log.v("MY_LOG", "ok1");

        String tag_string_req = "req_register";


        pDialog.setMessage("Registering ...");
         showDialog();



        try {
            Log.v("MY_LOG", "ok2");

            RequestQueue requestQueue = Volley.newRequestQueue(inscription.this);
            String URL = "http://192.168.1.11/reclamamtionprojectdev/app/registerservice.php";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("nom_c", "nom_c");
            jsonBody.put("prenom_c", "prenom_c");
            jsonBody.put("email_c", "email_c");
            jsonBody.put("tel_c", "tel_c");
            jsonBody.put("id_c", "id_c");
            jsonBody.put("password_c", "password_c");

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
                    return params;
                }
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Log.v("MY_LOG", "PARAAAAAAAAAAAAAAAAAAMS");
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id_c",id);
                    params.put("nom_c",namee);
                    params.put("prenom_c",prenomE);
                    params.put("email_c",emails);
                    params.put("tel_c",telf);
                    params.put("password_c",passwordE);


                    return params;
                }



                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String jsonString = "";
                    try {
                        jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.v("MY_LOG", "ok55");
                    String responseString = "";
                    Log.v("MY_LOG", "ok7");
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Log.v("MY_LOG", "the server said : " + response.toString());
                        // Arrays.toString(data.toByteArray());

                        String str = "";
                        for (int i = 0; i < response.data.length; i++) {
                            char c = (char) response.data[i];
                            str += c;
                        }
                        System.out.println(str);
                        Log.v("MY_LOG", "server response string : " + str);

                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            Log.v("MY_LOG", "ok31");

            e.printStackTrace();
            Log.v("MY_LOG", "ok3");

        } finally {
            Log.v("MY_LOG", "ok4");

        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

}




