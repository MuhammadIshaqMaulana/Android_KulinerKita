package com.ishaq.AsusIshaq2125250060.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.ishaq.AsusIshaq2125250060.API.APIRequestData;
import com.ishaq.AsusIshaq2125250060.API.RetroServer;
import com.ishaq.AsusIshaq2125250060.Model.ModelResponse;
import com.ishaq.asusishaq2125250060.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etHarga, etLink, etSpek;
    private Button btnSimpan;
    private String nama, harga, link, spek;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        getSupportActionBar().setTitle("TAMBAH ITEM");
        etNama = findViewById(R.id.et_nama);
        etHarga = findViewById(R.id.et_harga);
        etLink = findViewById(R.id.et_link);
        etSpek = findViewById(R.id.et_spek);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                harga = etHarga.getText().toString();
                link = etLink.getText().toString();
                spek = etSpek.getText().toString();

                if(nama.trim().isEmpty()|| harga.trim().isEmpty()|| link.trim().isEmpty()|| spek.trim().isEmpty()){
                    if(nama.trim().isEmpty()){
                        etNama.setError("nama tidak boleh kosong");
                    }
                    if(harga.trim().isEmpty()){
                        etHarga.setError("harga tidak boleh kosong");
                    }
                    if(link.trim().isEmpty()){
                        etLink.setError("link foto laptop tidak boleh kosong");
                    }
                    if(spek.trim().isEmpty()){
                        etSpek.setError("spesifikasi tidak boleh kosong");
                    }

                }

                else {
                    tambahAsus();
                }
            }
        });
    }

    public void tambahAsus(){
        APIRequestData ARD = RetroServer.KonekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, harga, link, spek);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: " + kode + "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
