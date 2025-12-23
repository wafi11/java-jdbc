package Services.MataKuliah;

import java.util.Scanner;

public class Models {
    String kode;
    String nama;
    int sks;

    Scanner input = new Scanner(System.in);

    public void setKode() {
        System.out.print("Masukkan Kode : ");
        kode = input.nextLine();
    }

    public void setName() {
        System.out.print("Masukkan Nama : ");
        nama = input.nextLine();
    }

    public void setSks() {
        System.out.print("Masukkan Sks : ");
        sks = input.nextInt();
    }

    public String getKode() {
        return kode;
    }

    public String getName() {
        return nama;
    }

    public int getSks() {
        return sks;
    }

    public void clearAll() {
        kode = "";
        nama = "";
        sks = 0;
    }

}
