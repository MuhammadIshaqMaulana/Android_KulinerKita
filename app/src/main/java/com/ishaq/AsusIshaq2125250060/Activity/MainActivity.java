package com.ishaq.AsusIshaq2125250060.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ishaq.AsusIshaq2125250060.API.APIRequestData;
import com.ishaq.AsusIshaq2125250060.API.RetroServer;
import com.ishaq.AsusIshaq2125250060.Adapter.AdapterAsus;
import com.ishaq.AsusIshaq2125250060.Model.ModelAsus;
import com.ishaq.AsusIshaq2125250060.Model.ModelResponse;
import com.ishaq.asusishaq2125250060.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAsus;
    private FloatingActionButton fabTambah;
    private RecyclerView.Adapter adAsus;
    private RecyclerView.LayoutManager lmAsus;
    private List<ModelAsus> listAsus = new ArrayList<ModelAsus>();
    private ProgressBar pbAsus;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("ASUS SHOP");

        rvAsus = findViewById(R.id.rv_asus);
        fabTambah = findViewById(R.id.fab_tambah);
        pbAsus = findViewById(R.id.pb_asus);

        lmAsus = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAsus.setLayoutManager(lmAsus);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveAsus();
    }

    public void retrieveAsus(){
        pbAsus.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.KonekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listAsus = response.body().getData();

                adAsus = new AdapterAsus(MainActivity.this, listAsus);
                rvAsus.setAdapter(adAsus);
                adAsus.notifyDataSetChanged();

                pbAsus.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbAsus.setVisibility(View.GONE);
            }
        });
    }
}
