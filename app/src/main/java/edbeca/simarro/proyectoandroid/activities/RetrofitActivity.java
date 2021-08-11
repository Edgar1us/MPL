package edbeca.simarro.proyectoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.proyectoandroid.R;
import edbeca.simarro.proyectoandroid.fragments.FechaSevice;
import edbeca.simarro.proyectoandroid.pojo.Fecha;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtv1;
    TextView txtvTitle;
    Button btnGetFecha;
    FechaSevice service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        txtv1 = (TextView)findViewById(R.id.txtv1);
        txtvTitle = (TextView)findViewById(R.id.txtvTitle);
        btnGetFecha = (Button)findViewById(R.id.btnGetComic);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FechaSevice.class);

        btnGetFecha.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Call<Fecha> call = service.getFecha();
        call.enqueue(new Callback<Fecha>() {
            @Override
            public void onResponse(Call<Fecha> call, Response<Fecha> response) {
                Fecha actor = response.body();
                try {
                    if (actor != null){
                        txtvTitle.setText(actor.getFecha());
                        txtv1.setText(actor.getResultadoConsulta());
                    }
                } catch (Exception e){
                    Log.e("MainActivity", e.toString());
                }

            }

            @Override
            public void onFailure(Call<Fecha> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "Ocurrio un error en la API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}