package Model;

import Entity.Data.dataPeminjaman;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ModelJSONPeminjaman {
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
    // Metode untuk menulis data peminjaman ke file JSON
    public void writePeminjamanJson(ArrayList<dataPeminjaman> listPeminjaman) {
        JsonArray arrayPeminjaman = convertArrayToJsonArrayPeminjaman(listPeminjaman);
        try {
            FileWriter file = new FileWriter("src/database/peminjaman.json");
            file.write(arrayPeminjaman.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metode untuk membaca data peminjaman dari file JSON
    public ArrayList<dataPeminjaman> readPeminjamanFromFile() {
        if (cekFile("src/database/peminjaman.json") == false) {
            return null;
        }
        ArrayList<dataPeminjaman> listPeminjaman = null;
        try (FileReader file = new FileReader("src/database/peminjaman.json")) {
            JsonArray arrayPeminjaman = (JsonArray) Jsoner.deserialize(file);
            listPeminjaman = convertJsonArrayToArrayListPeminjaman(arrayPeminjaman);
        } catch (IOException | com.github.cliftonlabs.json_simple.JsonException e) {
            throw new RuntimeException(e);
        }
        return listPeminjaman;
    }

    // Metode untuk mengonversi ArrayList<dataPeminjaman> ke JsonArray
    public JsonArray convertArrayToJsonArrayPeminjaman(ArrayList<dataPeminjaman> listPeminjaman) {
        if (listPeminjaman == null) {
            return null;
        } else {
            JsonArray arrayPeminjaman = new JsonArray();
            for (dataPeminjaman peminjaman : listPeminjaman) {
                JsonObject objPeminjaman = new JsonObject();
                objPeminjaman.put("idPeminjaman", peminjaman.idPeminjaman);
                objPeminjaman.put("idBuku", peminjaman.idBuku);
                objPeminjaman.put("idPeminjam", peminjaman.idPeminjam);
                objPeminjaman.put("tanggalPeminjaman", peminjaman.tanggalPeminjaman.getTime());
                arrayPeminjaman.add(objPeminjaman);
            }
            return arrayPeminjaman;
        }
    }

    // Metode untuk mengonversi JsonArray ke ArrayList<dataPeminjaman>
    public ArrayList<dataPeminjaman> convertJsonArrayToArrayListPeminjaman(JsonArray arrayPeminjaman) {
        if (arrayPeminjaman == null) {
            return null;
        } else {
            ArrayList<dataPeminjaman> listPeminjaman = new ArrayList<>();
            for (Object objPeminjaman : arrayPeminjaman) {
                JsonObject peminjaman = (JsonObject) objPeminjaman;
                int idPeminjaman = Integer.parseInt(peminjaman.get("idPeminjaman").toString());
                int idBuku = Integer.parseInt(peminjaman.get("idBuku").toString());
                int idPeminjam = Integer.parseInt(peminjaman.get("idPeminjam").toString());
                Date tanggalPeminjaman = new Date(Long.parseLong(peminjaman.get("tanggalPeminjaman").toString()));
                listPeminjaman.add(new dataPeminjaman(idPeminjaman, idBuku, idPeminjam, tanggalPeminjaman));
            }
            return listPeminjaman;
        }
    }

}