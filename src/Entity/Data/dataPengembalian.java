package Entity.Data;

import java.util.Date;

public class dataPengembalian {
    public int idPengembalian;
    public int idPeminjaman;
    public Date tanggalPengembalian;

    public dataPengembalian(int idPengembalian, int idPeminjaman, Date tanggalPengembalian) {
        this.idPengembalian = idPengembalian;
        this.idPeminjaman = idPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
    }
}
