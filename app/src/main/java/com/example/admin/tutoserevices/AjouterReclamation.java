package com.example.admin.tutoserevices;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AjouterReclamation extends AppCompatActivity {
    Button button1v, button22, button3, button2A;
    Spinner spinner1, spinner2;
    TextView tit, lieu, commentaire, titre, theme, li, th, com, e, n, libelle;


    public static final String MyPREFERENCES = "MyPrefs";

    public static final String libellee = "libelleKey";
    public static final String themee = "themeKey";
    public static final String lieux = "lieuKey";
    public static final String commmentaire = "commentaireKey";
    public static final String date = "dateKey";
    public static final String image = "imageKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_reclamation);
        tit = (TextView) findViewById(R.id.titre1);
        com = (TextView) findViewById(R.id.textcom);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinnerS = (Spinner) findViewById(R.id.spinner2);
        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Spinner click listener
        spinnerS.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//        spinner.setOnItemSelectedListener(this);
//        spinnerS.setOnItemSelectedListener(this);

        button1v = (Button) findViewById(R.id.buttonvalid);
        button2A = (Button) findViewById(R.id.button2A);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(libellee)) {
            tit.setText(sharedpreferences.getString(libellee, ""));
        }

        if (sharedpreferences.contains(commmentaire)) {
            com.setText(sharedpreferences.getString(commmentaire, ""));

        }


        button1v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MY_LOG", "valid clicked");
//                // Hide Keyboard
                Functions.hideSoftKeyboard(AjouterReclamation.this);


                Intent intent = new Intent(AjouterReclamation.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Ajout avec succes", Toast.LENGTH_SHORT).show();
                Log.v("MY_LOG", "valid clicked1");
                checkajout(titre, com);

                String libelle = tit.getText().toString();
                String commentaire = com.getText().toString();
                Log.v("MY_LOG1", "titre entred : " + libelle);
                Log.v("MY_LOG1", "comm entred : " + commentaire);

            }
        });


    }

    public void Save(View view) {
        DateFormat.getDateInstance(DateFormat.SHORT).format(date);

        String n = tit.getText().toString();
        String commentaire = com.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(String.valueOf(tit), n);
        editor.putString(String.valueOf(com), commentaire);
        editor.commit();

        Log.v("MY_LOG1", "name entred : " + li);
        Log.v("MY_LOG1", "name entred : " + com);

        Toast.makeText(AjouterReclamation.this, "Thanks", Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(), "Ajout avec succes", Toast.LENGTH_SHORT).show();


        button2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(AjouterReclamation.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // Spinner click listener
//        spinner1.setOnItemSelectedListener(this);
//        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> Theme = new ArrayList<String>();
        Theme.add("Habitat");
        Theme.add("Batiment civile");
        Theme.add("Urbanisme");
        Theme.add("Amenagement du territoire");
        Theme.add("pont et route");


        // Spinner Drop down elements
        List<String> Lieux = new ArrayList<String>();
        Lieux.add("Tunis");
        Lieux.add("Bizerte");
        Lieux.add("Ariana");
        Lieux.add("Manaouba");
        Lieux.add("Gafsa");
        Lieux.add("Zaghouan");
        Lieux.add("Tataouin");
        Lieux.add("Mounastir");
        Lieux.add("Mahdia");
        Lieux.add("Sousse");
        Lieux.add("Kairoun");
        Lieux.add("Gabes");
        Lieux.add("Nabeul");
        Lieux.add("Sfax");
        Lieux.add("Touzeur");
        Lieux.add("Kef");
        Lieux.add("Jandouba");
        Lieux.add("Kebeli");
        Lieux.add("Beja");
        Lieux.add("Siliana");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Theme);

        ArrayAdapter<String> dataAdapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Lieux);

        // Drop down layout style - list view with radio button
        dataAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);


    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

//        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
    }


    private void checkajout(final TextView libelle, final TextView commmentaire) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(AjouterReclamation.this);
            String URL = "http://192.168.0.87/reclamamtionprojectdev/app/ajouterRec.php";
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
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Log.v("MY_LOG", "FROM GET PARAMS : " + libelle);
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("libelle_r", String.valueOf(libelle));
                    params.put("commentaire_r", String.valueOf(commmentaire));
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
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Log.v("MY_LOG", "the server said : " + response.toString());
                        // Arrays.toString(data.toByteArray());
                        String str = "";
                        for (int i = 0; i < response.data.length; i++) {
                            char c = (char) response.data[i];
                            str += c;

                        }

                        try {
                            JSONArray jsonArray = new JSONArray(str);
                            JSONObject repJson = jsonArray.getJSONObject(0);
                            String msg = repJson.getString("msg");
                            Log.v("MY_LOG", msg);
//                            if (msg.equals("ok")){
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            Log.v("MY_LOG", "ok31");

            e.printStackTrace();
            Log.v("MY_LOG", "ok3");

        } finally {
            Log.v("MY_LOG", "ok4");

        }
    }

}
