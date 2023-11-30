package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import Entity.Data.dataBuku;
import Entity.JSON.DataJSONBuku;

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
 
    // public ArrayList<dataBuku> readJSONToArrayList() {
    //     try (FileReader fileReader = new FileReader(fname)) {
    //         JsonArray arrayBuku = (JsonArray) JsonArray.parse(fileReader);
    //         return convertJSONToArraylist(arrayBuku);
    //     } catch (IOException e) {
    //         System.out.println("Error reading from JSON file: " + e.getMessage());
    //         return new ArrayList<>();
    //     }
    // }
    public JsonArray convertArrayListToArrayJSON(ArrayList<dataBuku> listBuku) {
        JsonArray arrayBuku = new JsonArray();
        for (dataBuku buku : listBuku) {
            JsonObject objBuku = new JsonObject();
            objBuku.put("idBuku", buku.idBuku);
            objBuku.put("pengarang", buku.pengarang);
            objBuku.put("judulBuku", buku.judulBuku);
            objBuku.put("tahunTerbit", buku.tahunTerbit);
            arrayBuku.add(objBuku);
        }
        return arrayBuku;
     }
       public void writeArrayToJSON(ArrayList<dataBuku> listBuku) {
        JsonArray arrayBuku = convertArrayListToArrayJSON(listBuku);
        try {
            FileWriter file = new FileWriter(fname);
            file.write(arrayBuku.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // public ArrayList<dataBuku> convertJSONToArraylist(JsonArray arrayBuku) {
    //     ArrayList<dataBuku> listBuku = new ArrayList<>();
    //     for (Object obj : arrayBuku) {
    //         JsonObject jsonObj = (JsonObject) obj;
    //         String judul = (String) jsonObj.get("judul");
    //         String pengarang = (String) jsonObj.get("pengarang");
    //         int tahunTerbit = Integer.parseInt(jsonObj.get("tahunTerbit").toString());
    //         dataBuku buku = new dataBuku(judul, pengarang, tahunTerbit);
    //         listBuku.add(buku);
    //     }
    //     return listBuku;
    // }
}