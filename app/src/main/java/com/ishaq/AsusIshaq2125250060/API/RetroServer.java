package com.ishaq.AsusIshaq2125250060.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String alamatServer = "https://kulinerindonesiaishaq.000webhostapp.com/asus/";
    private static Retrofit retro;

    public static Retrofit KonekRetrofit(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(alamatServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retro;
    }
}
