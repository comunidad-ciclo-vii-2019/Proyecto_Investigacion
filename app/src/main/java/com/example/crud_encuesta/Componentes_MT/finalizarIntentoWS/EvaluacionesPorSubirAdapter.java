package com.example.crud_encuesta.Componentes_MT.finalizarIntentoWS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_encuesta.DatabaseAccess;
import com.example.crud_encuesta.R;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionesPorSubirAdapter  extends BaseAdapter implements AdapterView.OnItemSelectedListener{
    private LayoutInflater inflater = null;
    private Context context;
    private List<EvaluacionesPorSubir> evaluaciones = new ArrayList<>();

    public EvaluacionesPorSubirAdapter(Context context, List<EvaluacionesPorSubir> evaluaciones){
        this.context = context;
        this.evaluaciones = evaluaciones;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = inflater.inflate(R.layout.item_evas_por_subir, null);

        TextView txt_titulo = mView.findViewById(R.id.txt_titulo_eva);
        ImageView cargar = mView.findViewById(R.id.img_upload);

        txt_titulo.setText(evaluaciones.get(i).nombre_evalacion);
        cargar.setTag(i);

        final  int intento_id = evaluaciones.get(i).getIntento_id();

        cargar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(accesoInternet()){
                    subirEvaluacion(intento_id);
                }else{
                    Toast.makeText(context, "Error, no hay conexión a intenrnet "+intento_id, Toast.LENGTH_SHORT).show();
                }

            }
        });
        return mView;
    }

    @Override
    public int getCount() {
        return evaluaciones.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void subirEvaluacion(int intento_id){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        SQLiteDatabase db = databaseAccess.open();
        ContentValues contenedor = new ContentValues();
        int id_opcion;
        int id_pregunta;
        int total;
        String txt_respuesta;

        try{
            Cursor cursor_respuesta = db.rawQuery("SELECT * FROM RESPUESTA WHERE ID_INTENTO="+intento_id, null);
            total = cursor_respuesta.getCount();

            while(cursor_respuesta.moveToNext()){
                id_opcion = cursor_respuesta.getInt(0);
                id_pregunta = cursor_respuesta.getInt(2);
                txt_respuesta = cursor_respuesta.getString(3);
                RespuestaWS respuestaWS = new RespuestaWS(context, id_opcion, id_pregunta, intento_id, total, txt_respuesta);
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean accesoInternet(){
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int val = p.waitFor();
            boolean accesible = (val == 0);
            return accesible;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
