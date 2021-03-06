package com.app.siserve.siserve;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.app.siserve.siserve.framents.ClientesFragment;
import com.app.siserve.siserve.framents.HomeFragment;
import com.app.siserve.siserve.util.SettingsHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Iniciar com o Fragmento Home*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);


        HomeFragment home = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.content_main_for_fragment,
                home,
                home.getTag()
        ).commit();


        /*Fragmento Home*/

        /*Nome do usuário logado*/

        SettingsHelper helper = new SettingsHelper();
        String[] nomeusuarioLogado = helper.listaNome(MainActivity.this);
        String   listaEmail        = helper.listaEmail(MainActivity.this);
      //  String   codEmpresa
        if("".equals(nomeusuarioLogado) && "".equals(listaEmail)){
            redirecionaLogin();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
             View hView =  navigationView.getHeaderView(0);

            TextView nav_user = (TextView)hView.findViewById(R.id.nomeIDHOME);
            if(nomeusuarioLogado.length > 1) {
                nav_user.setText(nomeusuarioLogado[0] + " " + nomeusuarioLogado[1]);
            }else{

                nav_user.setText(nomeusuarioLogado[0]);
            }

        /*Email*/

        TextView email = (TextView)hView.findViewById(R.id.emailID);
        email.setText(listaEmail);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_clientes) {
            // Handle the camera action
            getSupportActionBar().setTitle("Pesquisar Clientes");
            ClientesFragment clientesFragment = new ClientesFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                                                 R.id.content_main_for_fragment,
                                                 clientesFragment,
                                                 clientesFragment.getTag()
                                              ).commit();


        } else if (id == R.id.ic_menu_close_clear_cancel) {
            SettingsHelper helper = new SettingsHelper();
            helper.destroySharedPreference(MainActivity.this);
            Toast.makeText(MainActivity.this, "Até mais!", Toast.LENGTH_SHORT).show();
            redirecionaLogin();
        } else if (id == R.id.nav_home) {

            HomeFragment home = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_main_for_fragment,
                    home,
                    home.getTag()
            ).commit();

        }/* else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void redirecionaLogin(){
        Intent t = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(t);
    }
}
