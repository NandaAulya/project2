import java.util.ArrayList;
import java.util.Date;

import Entity.Data.dataBuku;
import Entity.Data.dataPeminjaman;
import Entity.Data.dataPengembalian;
import Entity.Data.dataAdmin;
import Entity.Data.dataPengarang;
import Model.ModelJSONBuku;
import Model.ModelJSONAdmin;
import Model.ModelJSONPeminjaman;
import Model.ModelJSONPengembalian;
import Model.ModelJSONPengarang;

public class Main {
    public static void main(String[] args) throws Exception {
        ModelJSONBuku buku = new ModelJSONBuku();
        ModelJSONPengembalian pengembalian = new ModelJSONPengembalian();
        ModelJSONPengarang pengarang = new ModelJSONPengarang();
        ModelJSONPeminjaman peminjaman = new ModelJSONPeminjaman();
        ModelJSONAdmin admin = new ModelJSONAdmin();
        // model.setupFile();
        // Setup file untuk pengarang
        pengarang.setupFile("src/database/pengarang.json");

        // Setup file untuk buku
        buku.setupFile("src/database/buku.json");

        // Setup file untuk admin
        admin.setupFile("src/database/admin.json");

        // Setup file untuk peminjaman
        peminjaman.setupFile("src/database/peminjaman.json");

        // Setup file untuk pengembalian
        pengembalian.setupFile("src/database/pengembalian.json");

        ArrayList <dataBuku> listBuku = new ArrayList<>();
        listBuku.add(new dataBuku(1, "Buku1", new dataPengarang("Pengarang1", 1), 2020, 10));
        listBuku.add(new dataBuku(2, "Buku2", new dataPengarang("Pengarang2", 2), 2021, 5));
        buku.writeFileJson(listBuku);
        System.out.println("Data Buku berhasil dimasukkan");

        ArrayList<dataBuku> listBuku2 = buku.readBukuFromFilePengarang(listPengarang);
        if (listBuku2 != null) {
            for (dataBuku buku1 : listBuku2) {
                System.out.println("ID Buku: " + buku1.idBuku);
                System.out.println("Judul Buku: " + buku1.judulBuku);
                System.out.println("Pengarang: " + buku1.pengarang.idPengarang);
                System.out.println("Tahun Terbit: " + buku1.tahunTerbit);
                System.out.println("Stok: " + buku1.stok);
                System.out.println("-----------------------");
            }
        }
        // Membuat objek dataPengarang dan menuliskannya ke file JSON
        ArrayList<dataPengarang> listPengarang = new ArrayList<>();
        listPengarang.add(new dataPengarang("Pengarang1", 1));
        listPengarang.add(new dataPengarang("Pengarang2", 2));
        pengarang.writePengarangJson(listPengarang);
        System.out.println("Data Pengarang berhasil dimasukkan");

        // Membaca objek dataPengarang dari file JSON
        ArrayList<dataPengarang> listPengarang2 = pengarang.readPengarangFromFile();
        if (listPengarang2 != null) {
            for (dataPengarang pengarang1 : listPengarang2) {
                System.out.println("ID Pengarang: " + pengarang1.idPengarang);
                System.out.println("Nama Pengarang: " + pengarang1.namaPengarang);
                System.out.println("-----------------------");
            }
        }
        // Membuat objek dataPeminjaman dan menuliskannya ke file JSON
        ArrayList<dataPeminjaman> listPeminjaman = new ArrayList<>();
        listPeminjaman.add(new dataPeminjaman(1, 1, 101, new Date()));
        listPeminjaman.add(new dataPeminjaman(2, 2, 102, new Date()));
        peminjaman.writePeminjamanJson(listPeminjaman);
        System.out.println("Data Peminjaman berhasil dimasukkan");

        // Membaca objek dataPeminjaman dari file JSON
        ArrayList<dataPeminjaman> listPeminjaman2 = peminjaman.readPeminjamanFromFile();
        if (listPeminjaman2 != null) {
            for (dataPeminjaman peminjaman1 : listPeminjaman2) {
                System.out.println("ID Peminjaman: " + peminjaman1.idPeminjaman);
                System.out.println("ID Buku: " + peminjaman1.idBuku);
                System.out.println("ID Peminjam: " + peminjaman1.idPeminjam);
                System.out.println("Tanggal Peminjaman: " + peminjaman1.tanggalPeminjaman);
                System.out.println("-----------------------");
            }
        }

        // Membuat objek dataPengembalian dan menuliskannya ke file JSON
        ArrayList<dataPengembalian> listPengembalian = new ArrayList<>();
        listPengembalian.add(new dataPengembalian(1, 1, new Date()));
        listPengembalian.add(new dataPengembalian(2, 2, new Date()));
        pengembalian.writePengembalianJson(listPengembalian);
        System.out.println("Data Pengembalian berhasil dimasukkan");

        // Membaca objek dataPengembalian dari file JSON
        ArrayList<dataPengembalian> listPengembalian2 = pengembalian.readPengembalianFromFile();
        if (listPengembalian2 != null) {
            for (dataPengembalian pengembalian1 : listPengembalian2) {
                System.out.println("ID Pengembalian: " + pengembalian1.idPengembalian);
                System.out.println("ID Peminjaman: " + pengembalian1.idPeminjaman);
                System.out.println("Tanggal Pengembalian: " + pengembalian1.tanggalPengembalian);
                System.out.println("-----------------------");
            }
        }

        // Membuat objek dataAdmin dan menuliskannya ke file JSON
        ArrayList<dataAdmin> listAdmin = new ArrayList<>();
        listAdmin.add(new dataAdmin(1, "Admin1"));
        listAdmin.add(new dataAdmin(2, "Admin2"));
        admin.writeAdminJson(listAdmin);
        System.out.println("Data Admin berhasil dimasukkan");

        // Membaca objek dataAdmin dari file JSON
        ArrayList<dataAdmin> listAdmin2 = admin.readAdminFromFile();
        if (listAdmin2 != null) {
            for (dataAdmin admin1 : listAdmin2) {
                System.out.println("ID Admin: " + admin1.idAdmin);
                System.out.println("Nama Admin: " + admin1.namaAdmin);
                System.out.println("-----------------------");
            }
        }
    }
}