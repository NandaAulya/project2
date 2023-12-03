package Model;

import Entity.Data.dataPengembalian;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ModelJSONPengembalian {
    // cek file
    public boolean cekFile(String fname) {
        boolean cek = false;
        try {
            if (new java.io.File(fname).exists()) {
                cek = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cek;
    }

    // setup file
    public void setupFile(String fileName) {
        try {
            if (!cekFile(fileName)) {
                File file = new File(fileName);
                if (file.createNewFile()) {
                    System.out.println("File Database " + file.getName() + " Berhasil dibuat");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Metode untuk menulis data pengembalian ke file JSON
    public void writePengembalianJson(ArrayList<dataPengembalian> listPengembalian) {
        JsonArray arrayPengembalian = convertArrayToJsonArrayPengembalian(listPengembalian);
        try {
            FileWriter file = new FileWriter("src/database/pengembalian.json");
            file.write(arrayPengembalian.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metode untuk membaca data pengembalian dari file JSON
    public ArrayList<dataPengembalian> readPengembalianFromFile() {
        if (cekFile("src/database/pengembalian.json") == false) {
            return null;
        }
        ArrayList<dataPengembalian> listPengembalian = null;
        try (FileReader file = new FileReader("src/database/pengembalian.json")) {
            JsonArray arrayPengembalian = (JsonArray) Jsoner.deserialize(file);
            listPengembalian = convertJsonArrayToArrayListPengembalian(arrayPengembalian);
        } catch (IOException | com.github.cliftonlabs.json_simple.JsonException e) {
            throw new RuntimeException(e);
        }
        return listPengembalian;
    }

    // Metode untuk mengonversi ArrayList<dataPengembalian> ke JsonArray
    public JsonArray convertArrayToJsonArrayPengembalian(ArrayList<dataPengembalian> listPengembalian) {
        if (listPengembalian == null) {
            return null;
        } else {
            JsonArray arrayPengembalian = new JsonArray();
            for (dataPengembalian pengembalian : listPengembalian) {
                JsonObject objPengembalian = new JsonObject();
                objPengembalian.put("idPengembalian", pengembalian.idPengembalian);
                objPengembalian.put("idPeminjaman", pengembalian.idPeminjaman);
                objPengembalian.put("tanggalPengembalian", pengembalian.tanggalPengembalian.getTime());
                arrayPengembalian.add(objPengembalian);
            }
            return arrayPengembalian;
        }
    }

    // Metode untuk mengonversi JsonArray ke ArrayList<dataPengembalian>
    public ArrayList<dataPengembalian> convertJsonArrayToArrayListPengembalian(JsonArray arrayPengembalian) {
        if (arrayPengembalian == null) {
            return null;
        } else {
            ArrayList<dataPengembalian> listPengembalian = new ArrayList<>();
            for (Object objPengembalian : arrayPengembalian) {
                JsonObject pengembalian = (JsonObject) objPengembalian;
                int idPengembalian = Integer.parseInt(pengembalian.get("idPengembalian").toString());
                int idPeminjaman = Integer.parseInt(pengembalian.get("idPeminjaman").toString());
                Date tanggalPengembalian = new Date(Long.parseLong(pengembalian.get("tanggalPengembalian").toString()));
                listPengembalian.add(new dataPengembalian(idPengembalian, idPeminjaman, tanggalPengembalian));
            }
            return listPengembalian;
        }
    }
}