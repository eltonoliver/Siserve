package com.app.siserve.siserve.framents;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.siserve.siserve.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaClienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ListaClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaClienteFragment newInstance(String param1, String param2) {
        ListaClienteFragment fragment = new ListaClienteFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_cliente, container, false);

        Bundle bundle = this.getArguments();
        final ListView lista = (ListView)view.findViewById(R.id.listaClientesID);




        Log.i("DADOS JSON",""+bundle.getString("lista"));




       // Toast.makeText(getContext(), result , Toast.LENGTH_LONG).show();
        /****************************************************************/
        try {
            JSONArray data_array = new JSONArray(bundle.getString("lista"));
            String nome = "";
            ArrayList nomes = new ArrayList();
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                //Log.i("Dados Cliente",obj.getString("nomecli"));
                nomes.add(obj.getString("nomecli"));
               //nome += obj.getString("nomecli");


            }


           /***************************************************************/

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    nomes

            );

            lista.setAdapter(adaptador);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent,View view, int position, long id){

                    int codigoPosicao = position;
                    String valorClicado = lista.getItemAtPosition(codigoPosicao).toString();
                    Toast.makeText(getContext(),valorClicado,Toast.LENGTH_SHORT).show();

                }

            });

        }catch(JSONException e){
            e.printStackTrace();
        }
        /****************************************************************/



        return view;
    }



}
