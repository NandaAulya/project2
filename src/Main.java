import java.util.ArrayList;
import java.util.Date;

import Entity.Data.dataBuku;
import Entity.Data.dataPeminjaman;
import Entity.Data.dataPengembalian;
import Entity.Data.dataAdmin;
import Entity.Data.dataPenulis;
import Model.ModelJSONBuku;
import Model.ModelBuku;
import Model.ModelJSONAdmin;
import Model.ModelJSONPeminjaman;
import Model.ModelJSONPengembalian;
import Model.ModelJSONPenulis;

public class Main {
    public static void main(String[] args) throws Exception {
        ModelJSONBuku buku = new ModelJSONBuku();
        ModelJSONPengembalian pengembalian = new ModelJSONPengembalian();
        ModelJSONPenulis penulis = new ModelJSONPenulis();
        ModelJSONPeminjaman peminjaman = new ModelJSONPeminjaman();
        ModelJSONAdmin admin = new ModelJSONAdmin();
        
        penulis.setupFile(); // Setup file untuk pengarang
        buku.setupFile("src/database/buku.json");// Setup file untuk buku
        admin.setupFile("src/database/admin.json");// Setup file untuk admin
        peminjaman.setupFile("src/database/peminjaman.json");// Setup file untuk peminjaman
        pengembalian.setupFile("src/database/pengembalian.json");// Setup file untuk pengembalian

        ArrayList <dataBuku> listBuku = new ArrayList<>();
        listBuku.add(new dataBuku(1, "Buku1", new dataPenulis("Penulis1", 1), 2020, 10));
        listBuku.add(new dataBuku(2, "Buku2", new dataPenulis("Penulis2", 2), 2021, 5));
        listBuku.add(new dataBuku(3, "Buku3", new dataPenulis("Penulis3", 3), 2022, 15));
        buku.writeFileJson(listBuku);
        System.out.println("Data Buku berhasil dimasukkan");

        ArrayList<dataPenulis> listPenulis = new ArrayList<>();
        listPenulis.add(new dataPenulis("Penulis1", 1));
        listPenulis.add(new dataPenulis("Penulis2", 2));
        listPenulis.add(new dataPenulis("Penulis3", 3));
        penulis.writeFileJson(listPenulis);
        System.out.println("Data penulis berhasil dimasukkan");

        ArrayList<dataBuku> listBuku2 = buku.readBukuFromFile(listPenulis);
        if (listBuku2 != null) {
            for (dataBuku buku1 : listBuku2) {
                System.out.println("ID Buku: " + buku1.idBuku);
                System.out.println("Judul Buku: " + buku1.judulBuku);
                System.out.println("Penulis : " + buku1.penulis.idPenulis);
                System.out.println("Tahun Terbit: " + buku1.tahunTerbit);
                System.out.println("Stok: " + buku1.stok);
                System.out.println("-----------------------");
            }
        }

        // Membaca objek dataPengarang dari file JSON
        ArrayList<dataPenulis> listPenulis2 = penulis.readFromFile();
        if (listPenulis2 != null) {
            for (dataPenulis penulis1 : listPenulis2) {
                System.out.println("ID Penulis: " + penulis1.idPenulis);
                System.out.println("Nama Penulis: " + penulis1.namaPenulis);
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
        listPengembalian.add(new dataPengembalian(3, 3, new Date()));
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
        listAdmin.add(new dataAdmin(3, "Admin3"));
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

        //test load buku
        ModelBuku modelBuku = new ModelBuku();
        modelBuku.getBook().forEach(book -> {
            System.out.println(book.judulBuku);
            System.out.println(book.penulis);
            System.out.println(book.tahunTerbit);
            System.out.println(book.stok);
            System.out.println("-----------------------");
        });

        //test tambah buku
        modelBuku.tambahBuku("Buku4", "Penulis4", 2023, 2);
        modelBuku.tambahBuku("Buku5", "Penulis5", 2024, 3);

        modelBuku.getBook().forEach(book -> {
            System.out.println(book.idBuku);
            System.out.println(book.judulBuku);
            System.out.println(book.penulis);
            System.out.println(book.tahunTerbit);
            System.out.println(book.stok);
            System.out.println("-----------------------");
        });
    }
}