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

import Entity.Data.dataPengarang;
import Entity.JSON.DataJSONPengarang;

public class ModelJSONPengarang {
    String fname = "src/database/penulis.json";

    DataJSONPengarang datajsonpenulis = new DataJSONPengarang();

    // cek file
    public boolean cekFilePenulis() {
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
    public void setupFilePenulis() {
        try {
            if (cekFilePenulis() == false) {
                File file = new File(fname);
                if (file.createNewFile()) {
                    System.out.println("File Database Penulis Berhasil dibuat");
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    // convert
    public JsonArray convertArrayToJsonArray(ArrayList<dataPengarang> listPenulis) {
        if (listPenulis == null) {
            return null;
        } else {
            JsonArray arrayPenulis = new JsonArray();
            for (dataPengarang penulis : listPenulis) {
                JsonObject objPenulis = new JsonObject();
                objPenulis.put(datajsonpenulis.namaPengarang, penulis.namaPengarang);
                objPenulis.put(datajsonpenulis.idPengarang, penulis.idPengarang);
                arrayPenulis.add(objPenulis);
            }
            return arrayPenulis;
        }
    }

    // write
    public void writeFileJson(ArrayList<dataPengarang> listPenulis) {
        JsonArray arrayPenulis = convertArrayToJsonArray(listPenulis);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(arrayPenulis.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // read
    public ArrayList<dataPengarang> convertJSONToArrayList(JsonArray arrayPenulis) {
        if (arrayPenulis == null) {
            return null;
        } else {
            ArrayList<dataPengarang> listPenulis = new ArrayList<>();
            for (Object objPenulis : arrayPenulis) {
                JsonObject penulis = (JsonObject) objPenulis;
                String namaPenulis = penulis.get(datajsonpenulis.namaPengarang).toString();
                int idPenulis = Integer.parseInt(penulis.get(datajsonpenulis.idPengarang).toString());
                listPenulis.add(new dataPengarang(namaPenulis, idPenulis));
            }
            return listPenulis;
        }
    }

    // read from file
    public ArrayList<dataPengarang> readFromFile() {
        if (cekFilePenulis() == false) {
            return null;
        }
        ArrayList<dataPengarang> listPenulis = null;
        try (FileReader file = new FileReader(fname)) {
            JsonArray arrayPenulis = (JsonArray) Jsoner.deserialize(file);
            listPenulis = convertJSONToArrayList(arrayPenulis);
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
        return listPenulis;
    }

    public void setupFile(String string) {
    }

    public void writePengarangJson(ArrayList<dataPengarang> listPengarang) {
    }

    public ArrayList<dataPengarang> readPengarangFromFile() {
        return null;
    }
}
