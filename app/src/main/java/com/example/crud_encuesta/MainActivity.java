package com.example.crud_encuesta;

import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.crud_encuesta.Componentes_AP.Activities.EvaluacionActivity;
import com.example.crud_encuesta.Componentes_AP.Activities.TurnoActivity;
import com.example.crud_encuesta.Componentes_Docente.ActivityDocente;
import com.example.crud_encuesta.Componentes_EL.Encuesta.Encuesta;
import com.example.crud_encuesta.Componentes_EL.Materia.MateriaUsersActivity;
import com.example.crud_encuesta.Componentes_Estudiante.ActivityEstudiante;
import com.example.crud_encuesta.Componentes_MT.Area.AreaActivity;
import com.example.crud_encuesta.Componentes_MT.Clave.ClaveActivity;
import com.example.crud_encuesta.Componentes_MT.Intento.IntentoActivity;
import com.example.crud_encuesta.Componentes_EL.Carrera.CarreraActivity;
import com.example.crud_encuesta.Componentes_AP.Activities.LoginActivity;

import com.example.crud_encuesta.Componentes_EL.Encuesta.EncuestaActivity;
import com.example.crud_encuesta.Componentes_EL.Escuela.EscuelaActivity;
import com.example.crud_encuesta.Componentes_EL.Materia.MateriaActivity;

import com.example.crud_encuesta.Componentes_MateriaCiclo.ActivityMateriaCiclo;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar myTopToolBar;
    private ImageView loggin;

    int id=5;
    int rol=2;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        myTopToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myTopToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView bienvenida=findViewById(R.id.title_bienvenida);

        //Recuperando datos de usuario logueado
        //id=getIntent().getExtras().getInt("id_user");
        //rol=getIntent().getExtras().getInt("rol_user");

        switch (rol){
            case 0: bienvenida.setText("Administrador");break;
            case 1: bienvenida.setText("Docente: "+"nombreDocente");break;
            case 2:bienvenida.setText("Estudiante: "+"nombre");break;
        }



        ImageView materia=findViewById(R.id.el_btnMateria);
        ImageView carrera=findViewById(R.id.el_btnCarrera);

        CardView cardViewCarrera=findViewById(R.id.cardCarrera);

        //PARA OCULTARSELO AL ESTUDIANTE Y DOCENTE
        //CUANDO
        if(rol==2){
            //Estudiante
            cardViewCarrera.setVisibility(View.GONE);
        }


        materia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                switch (rol){
                    //Admin
                    case 0:i=new Intent(MainActivity.this,MateriaActivity.class);
                        startActivity(i);
                    break;
                    //Docente
                    case 1: i= new Intent(MainActivity.this, MateriaUsersActivity.class);
                        i.putExtra("id_user",id);
                        i.putExtra("rol_user",rol);
                        startActivity(i);
                    //Estudiante
                    case 2: i=new Intent(MainActivity.this,MateriaUsersActivity.class);
                        i.putExtra("id_user",id);
                        i.putExtra("rol_user",rol);
                        startActivity(i);
                    break;


                }
            }
        });

        carrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(MainActivity.this,CarreraActivity.class);
                Intent i=new Intent(MainActivity.this, EncuestaActivity.class);
                startActivity(i);
            }
        });

        loggin = (ImageView) findViewById(R.id.log);
        loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login();
                //intento();
                //evaluacion();
                pressed();

            }
        });
    }

    /*
     * Para acceder a la base de datos
     *
     * DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
     * databaseAccess.open();
     * .
     * .
     * .
     * your code
     * .
     * .
     * .
     * databaseAccess.close();
     */


    public void pressed() {
        Intent i = new Intent(getApplicationContext(), AreaActivity.class);
        startActivity(i);
    }

    public void area(){
        Intent i = new Intent(this, AreaActivity.class);
        startActivity(i);
    }

    public void intento(){
        Intent i = new Intent(this, IntentoActivity.class);
        startActivity(i);
    }

    public void clave(){
        Intent i = new Intent(this, ClaveActivity.class);
        startActivity(i);
    }

    public void prueba_escuela(View view) {
        Intent i = new Intent(this, EscuelaActivity.class);
        startActivity(i);
    }


    public void prueba_carrera(View view) {
        Intent i = new Intent(this, CarreraActivity.class);
        startActivity(i);
    }

    public void prueba_encuesta(View view) {
        Intent i = new Intent(this, EncuestaActivity.class);
        startActivity(i);
    }

    public void Login(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void evaluacion(){
        Intent i = new Intent(this, EvaluacionActivity.class);
        startActivity(i);
    }
    public void turno(){
        Intent i = new Intent(this, TurnoActivity.class);
        startActivity(i);
    }

    public void activity_docente(View view){
        Intent i= new Intent(this, ActivityDocente.class);
        startActivity(i);
    }

    public void activity_estudiante(View view){
        Intent i= new Intent(this, ActivityEstudiante.class);
        startActivity(i);
    }

    public void activity_materia_ciclo(View view){
        Intent i= new Intent(this, ActivityMateriaCiclo.class);
        startActivity(i);
    }
}
