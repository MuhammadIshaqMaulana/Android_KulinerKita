package com.ishaq.kulinerkita.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ishaq.kulinerkita.API.APIRequestData;
import com.ishaq.kulinerkita.API.RetroServer;
import com.ishaq.kulinerkita.Model.ModelResponse;
import com.ishaq.kulinerkita.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yAsal, yDeskripsiSingkat;
    private String nama, asal, deskripsiSingkat;
    private EditText etNama, etAsal, etDeskripsiSingkat;
    private Button btnUbah;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yAsal = ambil.getStringExtra("xAsal");
        yDeskripsiSingkat = ambil.getStringExtra("xDeskripsiSingkat");

        etNama = findViewById(R.id.et_nama);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsiSingkat = findViewById(R.id.et_deskripsi_singkat);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(yNama);
        etAsal.setText(yAsal);
        etDeskripsiSingkat.setText(yDeskripsiSingkat);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                asal = etAsal.getText().toString();
                deskripsiSingkat = etDeskripsiSingkat.getText().toString();

                if(nama.trim().isEmpty()||asal.trim().isEmpty()||deskripsiSingkat.trim().isEmpty()){
                    if(nama.trim().isEmpty()){
                        etNama.setError("nama tidak boleh kosong");
                    }
                    if(asal.trim().isEmpty()){
                        etAsal.setError("asal tidak boleh kosong");
                    }
                    if(deskripsiSingkat.trim().isEmpty()){
                        etDeskripsiSingkat.setError("Deskripsi Singkat tidak boleh kosong");
                    }

                }

                else {
                    ubahKuliner();
                }
            }
        });

    }

    public void ubahKuliner(){
        APIRequestData ARD = RetroServer.KonekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId, nama, asal, deskripsiSingkat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode: " + kode + "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UbahActivity.this, MainActivity.class);
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
