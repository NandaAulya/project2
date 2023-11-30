import Model.ModelJSONBuku;
import Entity.Data.dataBuku;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonArray;


public class Main {
    public static void main(String[] args) throws Exception {
        ModelJSONBuku model = new ModelJSONBuku();
        
        ArrayList<dataBuku> listBuku1 = null;
        JsonArray arrayBuku1 = model.convertArrayListToArrayJSON(listBuku1);
        System.out.println("arrayBuku1 = " + arrayBuku1);

        ArrayList<dataBuku> listBuku2 = new ArrayList<>();
        listBuku1.add(new dataBuku(1, "Buku 1", "Pengarang 1", 2010));
        listBuku2.add(new dataBuku(2, "Buku 2", "Pengarang 2", 2011));
        JsonArray arrayBuku2 = model.convertArrayListToArrayJSON(listBuku2);
        System.out.println("arrayBuku2 = " + arrayBuku2);
    }
}
