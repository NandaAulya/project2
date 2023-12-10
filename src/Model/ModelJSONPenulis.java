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

import Entity.Data.dataPenulis;
import Entity.JSON.DataJSONPenulis;

public class ModelJSONPenulis {
    String fname = "src/database/penulis.json";

    DataJSONPenulis datajsonpenulis = new DataJSONPenulis();

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
    public void setupFile() {
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
    public JsonArray convertArrayToJsonArray(ArrayList<dataPenulis> listPenulis) {
        if (listPenulis == null) {
            return null;
        } else {
            JsonArray arrayPenulis = new JsonArray();
            for (dataPenulis penulis : listPenulis) {
                JsonObject objPenulis = new JsonObject();
                objPenulis.put(datajsonpenulis.namaPenulis, penulis.namaPenulis);
                objPenulis.put(datajsonpenulis.idPenulis, penulis.idPenulis);
                arrayPenulis.add(objPenulis);
            }
            return arrayPenulis;
        }
    }

    // write
    public void writeFileJson(ArrayList<dataPenulis> listPenulis) {
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
    public ArrayList<dataPenulis> convertJSONToArrayList(JsonArray arrayPenulis) {
        if (arrayPenulis == null) {
            return null;
        } else {
            ArrayList<dataPenulis> listPenulis = new ArrayList<>();
            for (Object objPenulis : arrayPenulis) {
                JsonObject penulis = (JsonObject) objPenulis;
                String namaPenulis = penulis.get(datajsonpenulis.namaPenulis).toString();
                int idPenulis = Integer.parseInt(penulis.get(datajsonpenulis.idPenulis).toString());
                listPenulis.add(new dataPenulis(namaPenulis, idPenulis));
            }
            return listPenulis;
        }
    }

    // read from file
    public ArrayList<dataPenulis> readFromFile() {
        if (cekFilePenulis() == false) {
            return null;
        }
        ArrayList<dataPenulis> listPenulis = null;
        try (FileReader file = new FileReader(fname)) {
            JsonArray arrayPenulis = (JsonArray) Jsoner.deserialize(file);
            listPenulis = convertJSONToArrayList(arrayPenulis);
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
        return listPenulis;
    }
}
