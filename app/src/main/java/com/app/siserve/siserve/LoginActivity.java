package com.app.siserve.siserve;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.siserve.siserve.util.SettingsHelper;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    //Dados formulario
    private AutoCompleteTextView emailT;
    private Button btnLogin;
    private EditText codEmpresaT;
    private EditText senhaT;

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;

    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String COD_EMPRESA = "codEmpresa";
    private static final String URL = "http://pronorteweb.com.br/webservice8be2dc0905a239101a41debb8ebe552a/rest/api.php?rquest=efetuaLogin";


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailT = (AutoCompleteTextView) findViewById(R.id.email);
        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        senhaT = (EditText) findViewById(R.id.password);
        codEmpresaT = (EditText) findViewById(R.id.codigoEmpresaID);

        SettingsHelper logado = new SettingsHelper();

        /*Verifica se o usuário ja logou antes no app*/
        if(logado.usuarioLogado(LoginActivity.this)) {

            redireciona();

        }
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                login();


            }
        });


    }

    private void login() {



        final String email = emailT.getText().toString().trim();
        final String senha = senhaT.getText().toString().trim();
        final String codEmpresa = codEmpresaT.getText().toString().trim();

        if (email.equals("") || senha.equals("") || codEmpresa.equals("")) {

            Toast.makeText(getApplicationContext(), "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();

        } else {
            JsonObjectRequest req = new JsonObjectRequest(URL + "&email=" + email + "&senha=" + senha + "&codEmpresa=" + codEmpresa, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                /*
                                    Parse JSON

                                 */
                                String cod_usuario_cli = response.getString("cod_usuario_cli");
                                String cod_usuario = response.getString("cod_usuario");
                                String cod_empresa = response.getString("cod_empresa");
                                String nome = response.getString("nome");
                                String email = response.getString("email");
                                String codvend = response.getString("codvend");

                                SettingsHelper helper = new SettingsHelper();
                                helper.setUserLogin(LoginActivity.this, cod_usuario_cli, cod_usuario, cod_empresa, nome, email, codvend);


                                // Log.i("LOGADO",""+helper.usuarioLogado(LoginActivity.this));


                                // Toast.makeText(LoginActivity.this, response.toString(4), Toast.LENGTH_LONG).show();
                                // Toast.makeText(LoginActivity.this, cod_usuario_cli, Toast.LENGTH_LONG).show();

                                redireciona();

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(LoginActivity.this, "Dados inválidos, verifique seus dados com o Administrador!", Toast.LENGTH_LONG).show();
                }
            });

            // add the request object to the queue to be executed
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(req);
        }

    }

    public void redireciona(){
        Intent t = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(t);
    }


}

