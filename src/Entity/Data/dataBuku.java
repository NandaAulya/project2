package Entity.Data;

public class dataBuku {
    public int idBuku;
    public String judulBuku;
    public String pengarang;
    public int tahunTerbit;
    public int stok;

    public dataBuku(int idBuku,String judulBuku, String pengarang,int tahunTerbit) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.stok = 0;
    }
}
