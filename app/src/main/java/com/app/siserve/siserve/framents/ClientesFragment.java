package com.app.siserve.siserve.framents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.siserve.siserve.MainActivity;
import com.app.siserve.siserve.R;

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
        EditText cpfCliente;
        EditText codigoCliente;

        btnPesquisar = (Button)view.findViewById(R.id.btnPesquisar);
        nomeCliente  = (EditText)view.findViewById(R.id.nomeClienteID);
        cpfCliente   = (EditText)view.findViewById(R.id.cpfID);
        codigoCliente= (EditText)view.findViewById(R.id.codigoID);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicou "+nomeCliente.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });



        return view;
        
    }

}
