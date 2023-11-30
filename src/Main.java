import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonArray;
import Entity.Data.dataBuku;
import Model.ModelJSONBuku;

public class Main {
    public static void main(String[] args) throws Exception {
        ModelJSONBuku model = new ModelJSONBuku();
        model.setupFile();

        // test write
        //  ArrayList<dataBuku> listBuku = new ArrayList<>();
        // listBuku.add(new dataBuku(1, "buku1 ", "pengarang1", 2002, 30));
        // listBuku.add(new dataBuku(2, "buku2", "pengarang2", 2003, 40));
        // listBuku.add(new dataBuku(3, "buku3", "pengarang3", 2004, 50));
        // model.writeFileJson(listBuku);
        // System.out.println("Data berhasil dimasukkan");

        // test readM
        ArrayList<dataBuku> listBook2 = model.readFromFile();
        for (dataBuku buku : listBook2) {
            System.out.println(buku.idBuku);
            System.out.println(buku.judulBuku);
            System.out.println(buku.pengarang);
            System.out.println(buku.tahunTerbit);
            System.out.println(buku.stok);
            System.out.println("-----------------------");
        }


    }
}
