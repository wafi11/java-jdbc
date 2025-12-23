package Services.MataKuliah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MataKuliah {
    Models models = new Models();

    public void Create(Connection conn) {
        try {
            models.clearAll();
            models.setKode();
            models.setName();
            models.setSks();

            if (conn != null) {
                String query = """
                          insert into mata_kuliah (kode,name,sks) values (?,?,?)
                        """;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, models.getKode());
                    preparedStatement.setString(2, models.getName());
                    preparedStatement.setInt(3, models.getSks());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Data Mata Kuliah berhasil ditambahkan! (" + rowsAffected +
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
            models.setKode();
            if (conn != null) {
                String var3 = "DELETE FROM mata_kuliah WHERE kode = ?\n";
                PreparedStatement var4 = conn.prepareStatement(var3);
                var4.setString(1, models.getKode());
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
                    String query = "SELECT kode,name,sks FROM mata_kuliah ORDER BY kode";
                    try (ResultSet resultSet = statement.executeQuery(query)) {
                        boolean hasData = false;
                        System.out.println("\n+------------+---------------------------+---------------------------+");
                        System.out.println("|    KODE     |           NAME            |          SKS           |");
                        System.out.println("+------------+---------------------------+---------------------------+");

                        while (resultSet.next()) {
                            hasData = true;
                            String kode = resultSet.getString("kode");
                            String name = resultSet.getString("name");
                            int sks = resultSet.getInt("sks");

                            System.out.printf("| %-10s | %-25s | %-25d |%n", kode, name, sks);
                        }

                        System.out.println("+------------+---------------------------+---------------------------+");

                        if (!hasData) {
                            System.out
                                    .println(
                                            "|                   Tidak ada data Mata Kuliah                          |");
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
            models.setKode();
            System.out.println("Tekan Spasi atau Skip Pake Enter Jika Tidak Ingin Update");
            models.setName();
            System.out.println("Tekan 0 Jika Tidak Ingin Update");
            models.setSks();
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE mata_kuliah SET ");
                List<Object> values = new ArrayList<>();

                if (models.getName() != null && !models.getName().isEmpty()) {
                    query.append("name = ?, ");
                    values.add(models.getName());
                }

                if (models.getSks() != 0) {
                    query.append("sks = ?, ");
                    values.add(models.getSks());
                }

                if (values.isEmpty()) {
                    System.out.println("Tidak ada data yang diubah.");
                    return;
                }

                query.setLength(query.length() - 2);
                query.append(" WHERE kode = ?");
                values.add(models.getKode());

                // Eksekusi query
                try (PreparedStatement statement = conn.prepareStatement(query.toString())) {
                    for (int i = 0; i < values.size(); i++) {
                        statement.setObject(i + 1, values.get(i));
                    }

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data Mata Kuliah berhasil diupdate! (" + rowsAffected + " baris)");
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
