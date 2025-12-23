package Services.Mahasiswa;

import java.util.Scanner;

public class Models {
    String npm;
    String nama;
    String kelas;
    Scanner input = new Scanner(System.in);

    public void setNpm() {
        System.out.print("Masukkan Npm : ");
        npm = input.nextLine();
    }

    public void setName() {
        System.out.print("Masukkan Nama : ");
        nama = input.nextLine();
    }

    public void setKelas() {
        System.out.print("Masukkan Kelas : ");
        kelas = input.nextLine();
    }

    public String getNpm() {
        return npm;
    }

    public String getName() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

}
