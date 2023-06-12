package com.ishaq.AsusIshaq2125250060.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List <ModelAsus> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelAsus> getData() {
        return data;
    }
}
