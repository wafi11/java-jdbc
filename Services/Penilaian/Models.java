package Services.Penilaian;

import java.util.Scanner;

public class Models {
    int Id;
    int nilaiUTS;
    int nilaiUAS;
    int nilaiTUGAS;
    String kodeMatkul;
    String npm;
    Scanner input = new Scanner(System.in);

    public void setId() {
        System.out.print("Masukkan Id : ");
        Id = input.nextInt();
    }

    public void setNilaiTugas() {
        System.out.print("Masukkan Nilai Tugas : ");
        nilaiTUGAS = input.nextInt();
    }

    public void setNilaiUAS() {
        System.out.print("Masukkan Nilai UAS : ");
        nilaiUAS = input.nextInt();
    }

    public void setNilaiUTs() {
        System.out.print("Masukkan Nilai UTS : ");
        nilaiUTS = input.nextInt();
    }

    public void setKodeMatkul() {
        System.out.print("Masukkan Kode Mata Kuliah  : ");
        kodeMatkul = input.nextLine();
    }

    public void setNpm() {
        System.out.print("Masukkan Npm : ");
        npm = input.nextLine();
    }

    public int getId() {
        return Id;
    }

    public int getNilaiTugas() {
        return nilaiTUGAS;
    }

    public int getNilaiUTS() {
        return nilaiUTS;
    }

    public int getNilaiUAS() {
        return nilaiUAS;
    }

    public String getNpm() {
        return npm;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

}
