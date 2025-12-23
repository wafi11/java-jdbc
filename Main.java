import Config.Config;
import Services.Mahasiswa.Mahasiswa;
import Services.MataKuliah.MataKuliah;
import Services.Penilaian.Penilaian;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Mahasiswa mhs = new Mahasiswa();
        MataKuliah matkul = new MataKuliah();
        Penilaian penilaian = new Penilaian();
        Config cfg = new Config();
        Connection conn = cfg.connect();

        System.out.println("=================================");
        System.out.println("  SISTEM KEREN");
        System.out.println("=================================");
        System.out.print("Ketik 'N' jika ingin berhenti: ");
        String yesOrNo = input.nextLine();

        while (!"n".equalsIgnoreCase(yesOrNo.trim())) {
            System.out.println("\n=================================");
            System.out.println(" Main Choice ");
            ChoiceMenu();
            System.out.print("Choicee ajaaa (1-4): ");

            int inputChoice = input.nextInt();
            input.nextLine();

            switch (inputChoice) {
                case 1 -> {
                    ChoiceTable();
                    int inputTable = input.nextInt();
                    switch (inputTable) {
                        case 1 -> mhs.FindAll(conn);
                        case 2 -> matkul.FindAll(conn);
                        case 3 -> penilaian.FindAll(conn);
                        default -> System.err.println("harap masukkan input yang sesuai");
                    }
                }
                case 2 -> {
                    ChoiceTable();
                    int inputTable = input.nextInt();
                    switch (inputTable) {
                        case 1 -> mhs.Create(conn);
                        case 2 -> matkul.Create(conn);
                        case 3 -> penilaian.Create(conn);
                        default -> System.err.println("harap masukkan input yang sesuai");
                    }
                }
                case 3 -> {
                    ChoiceTable();
                    int inputTable = input.nextInt();
                    switch (inputTable) {
                        case 1 -> mhs.Update(conn);
                        case 2 -> matkul.Update(conn);
                        case 3 -> penilaian.Update(conn);
                        default -> System.err.println("harap masukkan input yang sesuai");
                    }
                }
                case 4 -> {
                    ChoiceTable();
                    int inputTable = input.nextInt();
                    switch (inputTable) {
                        case 1 -> mhs.Delete(conn);
                        case 2 -> matkul.Delete(conn);
                        case 3 -> penilaian.Delete(conn);
                        default -> System.err.println("harap masukkan input yang sesuai");
                    }
                }
                default -> {
                    System.out.println("Pilihan Tidak Tersedia");
                }
            }

            System.out.print("\nLanjutkan? (Ketik 'N' untuk berhenti): ");
            yesOrNo = input.nextLine();
        }
        System.out.println("\n=================================");
        System.out.println("  Program selesai. Terima kasih!");
        System.out.println("=================================");
        input.close();
    }

    public static void ChoiceTable() {
        System.out.println("=================================");
        System.out.println("1. Mahasiswa");
        System.out.println("2. Mata Kuliah");
        System.out.println("3. Penilaian");
        System.out.print("Pilih Table Yang Ingin Ditampilkan : ");
    }

    public static void ChoiceMenu() {
        System.out.println("=================================");
        System.out.println("1. Find Data");
        System.out.println("2. Create Data");
        System.out.println("3. Update Data");
        System.out.println("4. Delete Data");
        System.out.println("=================================");
    }
}
