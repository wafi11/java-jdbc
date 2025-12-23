package Services.Penilaian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Penilaian {
    Models models = new Models();

    public void Create(Connection conn) {
        try {
            models.setNpm();
            models.setKodeMatkul();
            models.setId();
            models.setNilaiTugas();
            models.setNilaiUTs();
            models.setNilaiUAS();

            if (conn != null) {
                String query = """
                        insert into penilaian (
                            id,
                            nilai_tugas,
                            nilai_uts,
                            nilai_uas,
                            kode_matkul,
                            npm
                        ) values (?,?,?,?,?,?)
                        """;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setInt(1, models.getId());
                    preparedStatement.setInt(2, models.getNilaiTugas());
                    preparedStatement.setInt(3, models.getNilaiUTS());
                    preparedStatement.setInt(4, models.getNilaiUAS());
                    preparedStatement.setString(5, models.getKodeMatkul());
                    preparedStatement.setString(6, models.getNpm());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Data Penilaian berhasil ditambahkan! (" + rowsAffected +
                            " baris)");
                } catch (SQLException exception) {
                    System.err.println("Erorr");
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void Delete(Connection conn) {
        try {
            models.setId();
            if (conn != null) {
                String var3 = "DELETE FROM penilaian WHERE kode = ?\n";
                PreparedStatement var4 = conn.prepareStatement(var3);
                var4.setInt(1, models.getId());
                int var5 = var4.executeUpdate();
                var4.close();
            } else {
                System.out.println("✗ ERROR: Koneksi database tidak tersedia!");
            }
        } catch (SQLException var7) {
            System.out.println("✗ SQLException: " + var7.getMessage());
        }
    }

    public void FindAll(Connection conn) {
        try {
            if (conn != null) {
                try (Statement statement = conn.createStatement()) {
                    String query = """
                                select
                                    p.nilai_tugas as tugas,
                                    p.nilai_uts as uts,
                                    p.nilai_uas as uas,
                                    mk.name as 'nama_matkul',
                                    m.nama as 'nama_mahasiswa',
                                    m.npm
                                from penilaian p
                                left join mahasiswa m on m.npm = p.npm
                                left join mata_kuliah mk on mk.kode = p.kode_matkul
                            """;
                    try (ResultSet resultSet = statement.executeQuery(query)) {
                        boolean hasData = false;
                        System.out.println("\n+------------+---------------------------+---------------------------+");
                        System.out.println("| NPM |   MAHASISWA |  MATA KULIAH |    TUGAS   |   UTS  |  UAS  |");
                        System.out.println("+------------+---------------------------+---------------------------+");

                        while (resultSet.next()) {
                            hasData = true;
                            String npm = resultSet.getString("npm");
                            String nama_mahasiswa = resultSet.getString("nama_mahasiswa");
                            String nama_matkul = resultSet.getString("nama_matkul");
                            int tugas = resultSet.getInt("tugas");
                            int uts = resultSet.getInt("uts");
                            int uas = resultSet.getInt("uas");

                            System.out.printf("| %-5s | %-10s | %-5s | %-5d | %-5d | %-5d |%n", npm, nama_mahasiswa,
                                    nama_matkul, tugas, uts, uas);
                        }

                        System.out.println("+------------+---------------------------+---------------------------+");

                        if (!hasData) {
                            System.out
                                    .println(
                                            "|                   Tidak ada data Penilaian                |");
                            System.out.println(
                                    "+----------------------------------------------------------------------+");
                        }
                    }
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void Update(Connection conn) {
        try {
            System.out.println("Tekan Spasi atau Skip Pake Enter Jika Tidak Ingin Update");
            models.setNpm();
            System.out.println("Tekan Spasi atau Skip Pake Enter Jika Tidak Ingin Update");
            models.setKodeMatkul();
            models.setId();
            System.out.println("Tekan 0 Jika Tidak Ingin Update");
            models.setNilaiTugas();
            System.out.println("Tekan 0 Jika Tidak Ingin Update");
            models.setNilaiUTs();
            System.out.println("Tekan 0 Jika Tidak Ingin Update");
            models.setNilaiUAS();

            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE penilaian SET ");
                List<Object> values = new ArrayList<>();

                if (models.getNpm() != null && !models.getNpm().isEmpty()) {
                    query.append("npm = ?, ");
                    values.add(models.getNpm());
                }
                if (models.getKodeMatkul() != null && !models.getKodeMatkul().isEmpty()) {
                    query.append("kode_matkul = ?, ");
                    values.add(models.getKodeMatkul());
                }

                if (models.getNilaiTugas() != 0) {
                    query.append("nilai_tugas = ?, ");
                    values.add(models.getNilaiTugas());
                }

                if (models.getNilaiUTS() != 0) {
                    query.append("nilai_uts = ?, ");
                    values.add(models.getNilaiUTS());
                }

                if (models.getNilaiUAS() != 0) {
                    query.append("nilai_uas = ?, ");
                    values.add(models.getNilaiUAS());
                }

                if (values.isEmpty()) {
                    System.out.println("Tidak ada data yang diubah.");
                    return;
                }

                query.setLength(query.length() - 2);
                query.append(" WHERE id = ?");
                values.add(models.getId());

                // Eksekusi query
                try (PreparedStatement statement = conn.prepareStatement(query.toString())) {
                    for (int i = 0; i < values.size(); i++) {
                        statement.setObject(i + 1, values.get(i));
                    }

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data Penilaian berhasil diupdate! (" + rowsAffected + " baris)");
                    } else {
                        System.out.println("Kode tidak ditemukan di database.");
                    }
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

}
