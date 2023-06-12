package com.ishaq.AsusIshaq2125250060.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ishaq.AsusIshaq2125250060.API.APIRequestData;
import com.ishaq.AsusIshaq2125250060.API.RetroServer;
import com.ishaq.AsusIshaq2125250060.Activity.DetailActivity;
import com.ishaq.AsusIshaq2125250060.Activity.MainActivity;
import com.ishaq.AsusIshaq2125250060.Activity.UbahActivity;
import com.ishaq.AsusIshaq2125250060.Model.ModelAsus;
import com.ishaq.AsusIshaq2125250060.Model.ModelResponse;
import com.ishaq.asusishaq2125250060.R;
import com.squareup.picasso.Picasso;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAsus extends RecyclerView.Adapter<AdapterAsus.VHAsus> {

    private Context ctx;
    private List<ModelAsus> listAsus;

    public AdapterAsus(Context ctx, List<ModelAsus> listAsus) {
        this.ctx = ctx;
        this.listAsus = listAsus;
    }

    @NonNull
    @Override
    public VHAsus onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_asus, parent, false);
        return new VHAsus(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAsus holder, int position) {
        ModelAsus MK = listAsus.get(position);
        holder.tvId.setText(MK.getId());
        holder.tvNama.setText(MK.getNama());
        holder.tvHarga.setText(MK.getHarga());
        holder.tvLink.setText(MK.getLink());
        holder.tvSpek.setText(MK.getSpek());
        Picasso.get()
                .load(MK.getLink())
                .resize(200, 170)
                .centerCrop()
                .into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        if(listAsus==null) return 0;
        return listAsus.size();
    }

    public class VHAsus extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvHarga, tvLink, tvSpek;
        FloatingActionButton fabHapus, fabUbah;
        ImageView ivFoto;
        public VHAsus(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvLink = itemView.findViewById(R.id.tv_link);
            tvSpek = itemView.findViewById(R.id.tv_spek);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            fabHapus = itemView.findViewById(R.id.fab_hapus);
            fabUbah = itemView.findViewById(R.id.fab_ubah);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DetailActivity.class);
                    intent.putExtra("xId", tvId.getText().toString());
                    intent.putExtra("xNama", tvNama.getText().toString());
                    intent.putExtra("xHarga", tvHarga.getText().toString());
                    intent.putExtra("xLink", tvLink.getText().toString());
                    intent.putExtra("xSpek", tvSpek.getText().toString());
                    ctx.startActivity(intent);
                }
            });

            fabUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, UbahActivity.class);
                    intent.putExtra("xId", tvId.getText().toString());
                    intent.putExtra("xNama", tvNama.getText().toString());
                    intent.putExtra("xHarga", tvHarga.getText().toString());
                    intent.putExtra("xLink", tvLink.getText().toString());
                    intent.putExtra("xSpek", tvSpek.getText().toString());
                    ctx.startActivity(intent);
                }
            });

            fabHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Apakah kamu yakin ingin menghapus data?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusAsus(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    pesan.show();
                }
            });
        }
    }
    private void hapusAsus(String idAsus){
        APIRequestData ARD = RetroServer.KonekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardDelete(idAsus);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ctx, "Kode: " + kode + "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                new MainActivity().retrieveAsus();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(ctx, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
