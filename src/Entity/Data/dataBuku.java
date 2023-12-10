package Entity.Data;

public class dataBuku {
    public int idBuku;
    public String judulBuku;
    public dataPenulis penulis; // Tambahkan atribut untuk pengarang
    public int tahunTerbit;
    public int stok;

    public dataBuku(int idBuku, String judulBuku, dataPenulis pengarang, int tahunTerbit, int stok) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.penulis = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.stok = stok;
    }

    public dataBuku(int idBuku, String buku1, String pengarang1, int tahunTerbit, int stok) {
    }
}