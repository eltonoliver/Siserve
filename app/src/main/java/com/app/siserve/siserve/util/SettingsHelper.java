package com.app.siserve.siserve.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by elton on 22/11/2016.
 */

public class SettingsHelper {

    private static final String SETTINGS = "SETTING_USER";

    public static void setUserLogin(Context context, String cod_usuario_cli,String cod_usuario,String cod_empresa,String nome, String email, String codvend) {
        SharedPreferences settings = context.getSharedPreferences(SETTINGS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("cod_usuario_cli", cod_usuario_cli);
        editor.putString("cod_usuario", cod_usuario);
        editor.putString("cod_empresa", cod_empresa);
        editor.putString("nome", nome);
        editor.putString("email", email);
        editor.putString("codvend", codvend);

        editor.commit();
    }

    public boolean usuarioLogado(Context context){

        SharedPreferences settings = context.getSharedPreferences(SETTINGS, 0);
        if(settings.contains("cod_usuario_cli") && settings.contains("cod_usuario")){

            return true;
        }else{

           return false;

        }

    }

    public String[] listaNome(Context context) {
        SharedPreferences settings = context.getSharedPreferences(SETTINGS, 0);
        String nome = null;
        String[] splited = null;
        if (settings.contains("nome")) {

            nome = settings.getString("nome", "");


           splited = nome.split("\\s+"); /*split nome por espaço*/

        }

        return splited;
    }

    public String listaEmail(Context context) {
        SharedPreferences settings = context.getSharedPreferences(SETTINGS, 0);
        String email = null;
        if (settings.contains("email")) {

            email = settings.getString("email", "");

        }

        return email;
    }

    public void destroySharedPreference(Context context){

        SharedPreferences settings = context.getSharedPreferences(SETTINGS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
