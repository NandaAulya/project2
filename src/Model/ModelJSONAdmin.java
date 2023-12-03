package Model;

import Entity.Data.dataAdmin;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModelJSONAdmin {

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
    // Metode untuk menulis data admin ke file JSON
    public void writeAdminJson(ArrayList<dataAdmin> listAdmin) {
        JsonArray arrayAdmin = convertArrayToJsonArrayAdmin(listAdmin);
        try {
            FileWriter file = new FileWriter("src/database/admin.json");
            file.write(arrayAdmin.toJson());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metode untuk membaca data admin dari file JSON
    public ArrayList<dataAdmin> readAdminFromFile() {
        if (cekFile("src/database/admin.json") == false) {
            return null;
        }
        ArrayList<dataAdmin> listAdmin = null;
        try (FileReader file = new FileReader("src/database/admin.json")) {
            JsonArray arrayAdmin = (JsonArray) Jsoner.deserialize(file);
            listAdmin = convertJsonArrayToArrayListAdmin(arrayAdmin);
        } catch (IOException | com.github.cliftonlabs.json_simple.JsonException e) {
            throw new RuntimeException(e);
        }
        return listAdmin;
    }

    // Metode untuk mengonversi ArrayList<dataAdmin> ke JsonArray
    public JsonArray convertArrayToJsonArrayAdmin(ArrayList<dataAdmin> listAdmin) {
        if (listAdmin == null) {
            return null;
        } else {
            JsonArray arrayAdmin = new JsonArray();
            for (dataAdmin admin : listAdmin) {
                JsonObject objAdmin = new JsonObject();
                objAdmin.put("idAdmin", admin.idAdmin);
                objAdmin.put("namaAdmin", admin.namaAdmin);
                arrayAdmin.add(objAdmin);
            }
            return arrayAdmin;
        }
    }

    // Metode untuk mengonversi JsonArray ke ArrayList<dataAdmin>
    public ArrayList<dataAdmin> convertJsonArrayToArrayListAdmin(JsonArray arrayAdmin) {
        if (arrayAdmin == null) {
            return null;
        } else {
            ArrayList<dataAdmin> listAdmin = new ArrayList<>();
            for (Object objAdmin : arrayAdmin) {
                JsonObject admin = (JsonObject) objAdmin;
                int idAdmin = Integer.parseInt(admin.get("idAdmin").toString());
                String namaAdmin = admin.get("namaAdmin").toString();
                listAdmin.add(new dataAdmin(idAdmin, namaAdmin));
            }
            return listAdmin;
        }
    }

}