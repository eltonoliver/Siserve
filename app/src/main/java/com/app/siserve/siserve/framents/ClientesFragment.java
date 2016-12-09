package com.app.siserve.siserve.framents;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.siserve.siserve.R;
import com.app.siserve.siserve.adapter.Cliente;
import com.app.siserve.siserve.util.SettingsHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class ClientesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URL = "http://pronorteweb.com.br/webservice8be2dc0905a239101a41debb8ebe552a/rest/api.php?rquest=consultaNomeCliente";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ClientesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientesFragment newInstance(String param1, String param2) {

        ClientesFragment fragment = new ClientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        /*Pesquisar Clientes****************************************************/
        Button btnPesquisar;
        final EditText nomeCliente;
        final EditText cpfCliente;
        final EditText codigoCliente;


        btnPesquisar = (Button)view.findViewById(R.id.btnPesquisar);
        nomeCliente  = (EditText)view.findViewById(R.id.nomeClienteID);
        cpfCliente   = (EditText)view.findViewById(R.id.cpfID);
        codigoCliente= (EditText)view.findViewById(R.id.codigoID);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsHelper helper = new SettingsHelper();

                final String empresa = helper.listaEmpresa(getContext());
                final String nome = nomeCliente.getText().toString().trim();
                final String cpf  = cpfCliente.getText().toString().trim();
                final String codigocli = codigoCliente.getText().toString().trim();


                    pesquisaCliente(nome, cpf, codigocli, empresa);

            }
        });



        return view;
        
    }


    private void pesquisaCliente( String nomeCliente, String cpfCliente, String codCliente,String codEmpresa) {

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL + "&nomeCli=" + nomeCliente + "&cpfCli=" + cpfCliente + "&codCli=" + codCliente+ "&codEmpresa="+codEmpresa,(String)null,
                        new Response.Listener<JSONArray>(){


                            @Override
                            public void onResponse(JSONArray response) throws JSONException {
                                final ListaClienteFragment lista = new ListaClienteFragment();


                                final Bundle bundle = new Bundle();

                                bundle.putString("lista", response.toString());
                                /*ITERARJSON*/
                                /*String result = "";
                                for(int i = 0; i < response.length();i++)  {
                                      JSONObject person = (JSONObject)response.get(i);
                                      result += person.getString("nomecli");

                                }
                                */
                                Toast.makeText(getContext(), response.toString() , Toast.LENGTH_LONG).show();

                                /*FIM ITERAR JSON*/
                                final ProgressDialog pDialog = new ProgressDialog(getActivity());
                                pDialog.setMessage("Buscando Dados ...");
                                pDialog.setIndeterminate(false);
                                pDialog.setCancelable(true);
                                pDialog.show();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        lista.setArguments(bundle);

                                        FragmentManager manager = getFragmentManager();
                                        manager.beginTransaction().replace(
                                                R.id.content_main_for_fragment,
                                                lista,
                                                lista.getTag()
                                        ).commit();
                                        try {
                                            sleep(500);
                                            pDialog.dismiss();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run()
                                            {

                                            }
                                        });
                                    }

                                    private void runOnUiThread(Runnable runnable) {
                                    }
                                }).start();

                            }
                        },new Response.ErrorListener(){


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Não foi encontrado cliente com os dados informados!", Toast.LENGTH_LONG).show();
                    }
                });
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(jsonArrayRequest);
        }






}






