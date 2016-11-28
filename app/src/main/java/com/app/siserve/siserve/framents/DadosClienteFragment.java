package com.app.siserve.siserve.framents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.siserve.siserve.R;
import com.app.siserve.siserve.adapter.Cliente;

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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dados completos");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_cliente, container, false);
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
        final Cliente cliente = new Cliente();
        cliente.setNomeCliente(bundle.getString("nomecli"));
        cliente.setCpf(bundle.getString("cgccpf"));
        cliente.setTipoCli(bundle.getString("tpPessoa"));
        cliente.setEndereco(bundle.getString("endereco"));
        cliente.setTelefone(bundle.getString("telefone"));
        cliente.setCelular(bundle.getString("celular"));
        cliente.setEmail(bundle.getString("email"));
        cliente.setNomeFantasia(bundle.getString("fantasia"));

        nome     = (TextView) view.findViewById(R.id.nomeIDVIEW);
        cgccpf   = (TextView) view.findViewById(R.id.cpfIDVIEW);
        tpPessoa = (TextView)view.findViewById(R.id.tipoIDVIEW);
        endereco = (TextView)view.findViewById(R.id.enderecoIDVIEW);
        telefone = (TextView)view.findViewById(R.id.telefoneIDVIEW);
        celular  = (TextView)view.findViewById(R.id.celularIDVIEW);
        email    = (TextView)view.findViewById(R.id.emailIDVIEW);
        fantasia = (TextView)view.findViewById(R.id.fantasiaIDVIEW);

        nome.setText(cliente.getNomeCliente());
        cgccpf.setText((cliente.getCpf() == null)?"Dados não cadastrados":cliente.getCpf());
        tpPessoa.setText((cliente.getTipoCli()==null)?"Dados não cadastrados":cliente.getTipoCli());
        endereco.setText((cliente.getEndereco()==null)?"Dados não cadastrados":cliente.getEndereco());
        telefone.setText(cliente.getTelefone());
        celular.setText(cliente.getCelular());
        email.setText(cliente.getEmail());
        fantasia.setText(cliente.getNomeFantasia());



        return view;
    }

}
