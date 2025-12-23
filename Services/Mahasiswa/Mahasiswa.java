package Services.Mahasiswa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
    Models models = new Models();

    public void Create(Connection conn) {
        try {
            models.setNpm();
            models.setName();
            models.setKelas();

            if (conn != null) {
                String query = """
                          insert into mahasiswa (npm,nama,kelas) values (?,?,?)
                        """;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, models.getNpm());
                    preparedStatement.setString(2, models.getName());
                    preparedStatement.setString(3, models.getKelas());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Data mahasiswa berhasil ditambahkan! (" + rowsAffected +
                            " baris)");
                } catch (SQLException exception) {
                    System.err.println("Erorr");
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: NPM harus berupa angka!");
        }
    }

    public void Delete(Connection conn) {
        try {
            models.setNpm();
            if (conn != null) {
                String query = """
                        DELETE FROM mahasiswa WHERE npm = ?
                        """;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, models.getNpm());

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data mahasiswa berhasil dihapus! (" + rowsAffected + " baris)");
                    } else {
                        System.out.println("NPM tidak ditemukan di database.");
                    }
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: NPM harus berupa angka!");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void FindAll(Connection conn) {
        try {
            if (conn != null) {
                try (Statement statement = conn.createStatement()) {
                    String query = "SELECT npm,nama,kelas FROM mahasiswa ORDER BY npm";
                    try (ResultSet resultSet = statement.executeQuery(query)) {
                        boolean hasData = false;
                        System.out.println("\n+------------+---------------------------+---------------------------+");
                        System.out.println("|    NPM     |           NAMA            |          kelas           |");
                        System.out.println("+------------+---------------------------+---------------------------+");

                        while (resultSet.next()) {
                            hasData = true;
                            String npm = resultSet.getString("npm");
                            String nama = resultSet.getString("nama");
                            String kelas = resultSet.getString("kelas");

                            System.out.printf("| %-10s | %-25s | %-25s |%n", npm, nama, kelas);
                        }

                        System.out.println("+------------+---------------------------+---------------------------+");

                        if (!hasData) {
                            System.out
                                    .println("|                   Tidak ada data mahasiswa                          |");
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
            models.setNpm();
            models.setName();
            models.setKelas();
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE mahasiswa SET ");
                List<Object> values = new ArrayList<>();

                if (models.getName() != null && !models.getName().isEmpty()) {
                    query.append("nama = ?, ");
                    values.add(models.getName());
                }

                if (models.getKelas() != null && !models.getKelas().isEmpty()) {
                    query.append("kelas = ?, ");
                    values.add(models.getKelas());
                }

                if (values.isEmpty()) {
                    System.out.println("Tidak ada data yang diubah.");
                    return;
                }
                query.setLength(query.length() - 2);
                query.append(" WHERE npm = ?");
                values.add(models.getNpm());

                // Eksekusi query
                try (PreparedStatement statement = conn.prepareStatement(query.toString())) {
                    for (int i = 0; i < values.size(); i++) {
                        statement.setObject(i + 1, values.get(i));
                    }

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data mahasiswa berhasil diupdate! (" + rowsAffected + " baris)");
                    } else {
                        System.out.println("NPM tidak ditemukan di database.");
                    }
                }
            } else {
                System.out.println("ERROR: Koneksi database tidak tersedia!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: NPM harus berupa angka!");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
