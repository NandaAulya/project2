package Entity.Data;

import java.util.Date;

public class dataPeminjaman {
    public int idPeminjaman;
    public int idBuku;
    public int idPeminjam;
    public Date tanggalPeminjaman;

    public dataPeminjaman (int idPeminjaman, int idBuku, int idPeminjam, Date tanggalPeminjaman) {
        this.idPeminjaman = idPeminjaman;
        this.idBuku = idBuku;
        this.idPeminjam = idPeminjam;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }
}
