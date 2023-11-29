package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import Entity.Data.dataBuku;
import Entity.JSON.JSONBuku;

public class ModelJSONBuku {
    String fname = "database/buku.json";

    public boolean cekFile() {
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
    
    public void setupFile(){
        try{
            if (cekFile() == false){
                File file = new File(fname);
                if (file.createNewFile()){
                    System.out.println("File Created");
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    public void writeArrayToJSON(ArrayList<dataBuku> listBuku) {
        try (FileWriter fileWriter = new FileWriter(fname)) {
            JsonArray arrayBuku = convertArrayListToArrayJSON(listBuku);
            arrayBuku.toJson(fileWriter);
            System.out.println("Write Successful");
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + e.getMessage());
        }
    }
    public ArrayList<dataBuku> readJSONToArrayList() {
        try (FileReader fileReader = new FileReader(fname)) {
            JsonArray arrayBuku = (JsonArray) JsonArray.parse(fileReader);
            return convertJSONToArraylist(arrayBuku);
        } catch (IOException e) {
            System.out.println("Error reading from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public JsonArray convertArrayListToArrayJSON(ArrayList<dataBuku> listBuku) {
        JsonArray arrayBuku = new JsonArray();
        for (dataBuku buku : listBuku) {
            JsonObject objBuku = new JsonObject();
            objBuku.put("judul", buku.getJudul());
            objBuku.put("pengarang", buku.getPengarang());
            objBuku.put("tahunTerbit", buku.getTahunTerbit());
            arrayBuku.add(objBuku);
        }
        return arrayBuku;
    }
    public ArrayList<dataBuku> convertJSONToArraylist(JsonArray arrayBuku) {
        ArrayList<dataBuku> listBuku = new ArrayList<>();
        for (Object obj : arrayBuku) {
            JsonObject jsonObj = (JsonObject) obj;
            String judul = (String) jsonObj.get("judul");
            String pengarang = (String) jsonObj.get("pengarang");
            int tahunTerbit = Integer.parseInt(jsonObj.get("tahunTerbit").toString());
            dataBuku buku = new dataBuku(judul, pengarang, tahunTerbit);
            listBuku.add(buku);
        }
        return listBuku;
    }
}