package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import Entity.Data.dataBuku;
import Entity.JSON.DataJSONBuku;
import Entity.Data.dataPengarang;

public class ModelJSONBuku {
    String fname = "src/database/buku.json";

    DataJSONBuku dataJSONBuku = new DataJSONBuku();

    // cek file
    public boolean cekFile(String fileName) {
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


    public JsonArray convertArrayTJsonArray(ArrayList<dataBuku> listBuku) {
        if (listBuku == null) {
            return null;
        } else {
            JsonArray arrayBuku = new JsonArray();
            for (dataBuku buku : listBuku) {
                JsonObject objBuku = new JsonObject();
                objBuku.put(dataJSONBuku.idBuku, buku.idBuku);
                objBuku.put(dataJSONBuku.judulBuku, buku.judulBuku);
                objBuku.put(dataJSONBuku.pengarang, buku.pengarang.idPengarang); // Ubah pengarang menjadi idPengarang
                objBuku.put(dataJSONBuku.tahunTerbit, buku.tahunTerbit);
                objBuku.put(dataJSONBuku.stok, buku.stok);
                arrayBuku.add(objBuku);
            }
            return arrayBuku;
        }
    }

    public ArrayList<dataBuku> convertJSONToArrayList(JsonArray arrayBuku, ArrayList<dataPengarang> listPengarang) {
        if (arrayBuku == null) {
            return null;
        } else {
            ArrayList<dataBuku> listBuku = new ArrayList<>();
            for (Object objBuku : arrayBuku) {
                JsonObject buku = (JsonObject) objBuku;
                int idBuku = Integer.parseInt(buku.get(dataJSONBuku.idBuku).toString());
                String judulBuku = buku.get(dataJSONBuku.judulBuku).toString();
                String idPengarangStr = buku.get(dataJSONBuku.pengarang).toString();
                int idPengarang = Integer.parseInt(idPengarangStr); // Mengambil idPengarang dari JSON
                dataPengarang pengarang = findPengarangById(listPengarang, idPengarang); // Mencari pengarang berdasarkan id
                int tahunTerbit = Integer.parseInt(buku.get(dataJSONBuku.tahunTerbit).toString());
                int stok = Integer.parseInt(buku.get(dataJSONBuku.stok).toString());
                listBuku.add(new dataBuku(idBuku, judulBuku, pengarang, tahunTerbit, stok));
            }
            return listBuku;
        }
    }

    public dataPengarang findPengarangById(ArrayList<dataPengarang> listPengarang, int idPengarang) {
        for (dataPengarang pengarang : listPengarang) {
            if ((int)pengarang.idPengarang == idPengarang) {
                return pengarang;
            }
        }
        return null;
    }

    // write
    public void writeFileJson(ArrayList<dataBuku> listBuku) {
        JsonArray arrayBuku = convertArrayTJsonArray(listBuku);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(arrayBuku.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // read
    public ArrayList<dataBuku> convertJSONToArrayList(JsonArray arrayBuku) {
        if (arrayBuku == null) {
            return null;
        } else {
            ArrayList<dataBuku> listBuku = new ArrayList<>();
            for (Object objBuku : arrayBuku) {
                JsonObject buku = (JsonObject) objBuku;
                int idBuku = Integer.parseInt(buku.get(dataJSONBuku.idBuku).toString());
                String judulBuku = buku.get(dataJSONBuku.judulBuku).toString();
                String pengarang = buku.get(dataJSONBuku.pengarang).toString();
                int tahunTerbit = Integer.parseInt(buku.get(dataJSONBuku.tahunTerbit).toString());
                int stok = Integer.parseInt(buku.get(dataJSONBuku.stok).toString());
                listBuku.add(new dataBuku(idBuku, judulBuku, pengarang, tahunTerbit, stok));
            }
            return listBuku;
        }
    }

    // read from file json
    public ArrayList<dataBuku> readFromFilePengarang(ArrayList<dataPengarang> listPengarang) {
        if (cekFile(fname) == false) {
            return null;
        }
        ArrayList<dataBuku> listBuku = null;
        try (FileReader file = new FileReader(fname)) {
            JsonArray arrayBuku = (JsonArray) Jsoner.deserialize(file);
            listBuku = convertJSONToArrayList(arrayBuku, listPengarang);

        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
        return listBuku;
    }

    public ArrayList<dataBuku> readBukuFromFile() {
        return null;
    }
}