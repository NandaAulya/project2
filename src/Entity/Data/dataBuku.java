package Entity.Data;

public class dataBuku {
    public int idBuku;
    public String namaPenulis;
    public String penerbit;
    public int tahunTerbit;
    public int stok;

    public dataBuku(int idBuku, String namaPenulis, String penerbit, int tahunTerbit, String stok) {
        this.namaPenulis = namaPenulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.stok = 0;
    }
}
