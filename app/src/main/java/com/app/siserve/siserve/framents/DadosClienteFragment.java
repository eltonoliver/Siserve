package com.app.siserve.siserve.framents;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DadosClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DadosClienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URL = "http://pronorteweb.com.br/webservice8be2dc0905a239101a41debb8ebe552a/rest/api.php?rquest=consultaDadosPorCliente";
    final Cliente cliente = new Cliente();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DadosClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DadosClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DadosClienteFragment newInstance(String param1, String param2) {
        DadosClienteFragment fragment = new DadosClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dados completos");

        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_dados_cliente, container, false);
        //Recebendo Dados do Cliente
        final TextView nome;
        final TextView cgccpf;
        final TextView tpPessoa;
        final TextView endereco;
        final TextView telefone;
        final TextView celular;
        final TextView email;
        final TextView fantasia;


        Bundle bundle = this.getArguments();


        nome = (TextView) view.findViewById(R.id.nomeIDVIEW);
        cgccpf = (TextView) view.findViewById(R.id.cpfIDVIEW);
        tpPessoa = (TextView) view.findViewById(R.id.tipoIDVIEW);
        endereco = (TextView) view.findViewById(R.id.enderecoIDVIEW);
        telefone = (TextView) view.findViewById(R.id.telefoneIDVIEW);
        celular = (TextView) view.findViewById(R.id.celularIDVIEW);
        email = (TextView) view.findViewById(R.id.emailIDVIEW);
        fantasia = (TextView) view.findViewById(R.id.fantasiaIDVIEW);

        ImageView btnVoltar = (ImageView)view.findViewById(R.id.backlistID);

        /*Voltar para listagem*/
        btnVoltar.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                 getFragmentManager().popBackStack();
            }
        });


        String clienteNome = bundle.getString("nomeCli").trim();
        String query = null;
        try {
            query = URLEncoder.encode(clienteNome, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL + "&nomeCli=" + query, (String) null,
                new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) throws JSONException {


                        for (int i = 0; i < response.length(); i++) {
                            JSONObject person = (JSONObject) response.get(i);


                            nome.setText((person.getString("nomecli") == "null" || "".equals(person.getString("nomecli"))) ? "Dados não cadastrados" : person.getString("nomecli"));
                            cgccpf.setText((person.getString("cgccpf") == "null" || "".equals(person.getString("cgccpf"))) ? "Dados não cadastrados" : person.getString("cgccpf"));
                            tpPessoa.setText((person.getString("tpPessoa") == "null" || "".equals(person.getString("tpPessoa"))) ? "Dados não cadastrados" : person.getString("tpPessoa"));
                            endereco.setText((person.getString("endereco") == "null" || "".equals(person.getString("endereco"))) ? "Dados não cadastrados" : person.getString("endereco"));
                            telefone.setText((person.getString("telefone") == "null" || "".equals(person.getString("telefone"))) ? "Dados não cadastrados" : person.getString("telefone"));
                            celular.setText((person.getString("celular") == "null" || "".equals(person.getString("celular"))) ? "Dados não cadastrados" : person.getString("celular"));
                            email.setText((person.getString("email") == "null" || "".equals(person.getString("email"))) ? "Dados não cadastrados" : person.getString("email"));
                            fantasia.setText((person.getString("fantasia") == "null" || "".equals(person.getString("fantasia"))) ? "Dados não cadastrados" : person.getString("fantasia"));


                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Não foi encontrado cliente com os dados informados!", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);


        return view;
    }



}



