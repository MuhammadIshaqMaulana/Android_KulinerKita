package com.ishaq.AsusIshaq2125250060.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ishaq.AsusIshaq2125250060.API.APIRequestData;
import com.ishaq.AsusIshaq2125250060.API.RetroServer;
import com.ishaq.AsusIshaq2125250060.Model.ModelResponse;
import com.ishaq.asusishaq2125250060.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvSpek, tvHarga;
    private String yId, yNama, yHarga, yLink, ySpek;
//    private FloatingActionButton fabHapus, fabUbah;
    private ImageView ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("DETAIL ITEM");

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yHarga = ambil.getStringExtra("xHarga");
        yLink = ambil.getStringExtra("xLink");
        ySpek = ambil.getStringExtra("xSpek");

        tvNama = findViewById(R.id.tv_nama);
        tvSpek = findViewById(R.id.tv_spek);
        tvHarga = findViewById(R.id.tv_harga);
//        fabHapus = findViewById(R.id.fab_hapus);
//        fabUbah = findViewById(R.id.fab_ubah);
        ivFoto = findViewById(R.id.iv_foto);

        tvNama.setText(yNama);
        tvHarga.setText(yHarga);
        tvSpek.setText(ySpek);
        Picasso.get()
                .load(yLink)
                .into(ivFoto);

//        fabUbah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toUbah = new Intent(DetailActivity.this, UbahActivity.class);
//                toUbah.putExtra("yId", yId);
//                toUbah.putExtra("yNama", yNama);
//                toUbah.putExtra("yHarqa", yHarga);
//                toUbah.putExtra("yLink", yLink);
//                toUbah.putExtra("ySpek", ySpek);
//                DetailActivity.this.startActivity(toUbah);
//            }
//        });

//            fabHapus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder pesan = new AlertDialog.Builder(DetailActivity.this);
//                    pesan.setTitle("Perhatian");
//                    pesan.setMessage("Apakah kamu yakin ingin menghapus data?");
//                    pesan.setCancelable(true);
//
//                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            hapusAsus(yId);
//                            dialog.dismiss();
//                        }
//                    });
//
//                    pesan.setPositiveButton("Batal", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    pesan.show();
//                }
//            });
        }
//    private void hapusAsus(String idAsus){
//        APIRequestData ARD = RetroServer.KonekRetrofit().create(APIRequestData.class);
//        Call<ModelResponse> proses = ARD.ardDelete(idAsus);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//
//                Toast.makeText(DetailActivity.this, "Kode: " + kode + "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//                new MainActivity().retrieveAsus();
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(DetailActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}