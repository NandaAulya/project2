package Model;

import java.util.ArrayList;

import Entity.Data.dataBuku;
import Entity.Data.dataPenulis;



public class ModelBuku {
    private ArrayList<dataBuku> book;
    private ModelJSONBuku modelJSONBuku;
    private ArrayList<dataPenulis> listPenulis;

    public ModelBuku(){
        book = new ArrayList<dataBuku>();
        modelJSONBuku = new ModelJSONBuku();
        listPenulis = new ArrayList<dataPenulis>();
        loadBuku();
    }

    private void loadBuku(){
        book = modelJSONBuku.readBukuFromFile(listPenulis);
    }

    public ArrayList<dataBuku> getBook(){
        return book;
    }

    private int getLastKode(){
        int last = book.size() - 1;
        return book.get(last).idBuku;
    }

    public void tambahBuku(String judulBuku, String penulis, int tahunTerbit, int stok){
        int idBuku = getLastKode() + 1;
        dataBuku buku = new dataBuku(idBuku, judulBuku, penulis , tahunTerbit, stok);
        book.add(buku);
    }
}
