package projekt;

import java.sql.*;
import java.util.HashSet;

public class DBConnection {
    private Connection connection;

    public boolean getDBConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:db/SQLite/Kniznica.db");
                createTableKnihy();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableKnihy() {
        String sql = "CREATE TABLE IF NOT EXISTS knihy (nazov VARCHAR(255), autor VARCHAR(255), rokVydania INT, dostupna BOOLEAN, zaner VARCHAR(50), hodnotenie INT);";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveKnihy(HashSet<Kniha> knihy) {
        createTableKnihy();
        String sql = "INSERT INTO knihy (nazov, autor, rokVydania, dostupna, zaner, hodnotenie) VALUES (?,?,?,?,?,?)";
        try {
            for (Kniha kniha : knihy) {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, kniha.getNazov());
                pstmt.setString(2, kniha.getAutor());
                pstmt.setInt(3, kniha.getRokVydania());
                pstmt.setBoolean(4, kniha.getDostupna());
                pstmt.setString(5, kniha.getZaner().name());
                pstmt.setInt(6, kniha.getHodnotenie());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public HashSet<Kniha> loadKnihy() {
        HashSet<Kniha> knihy = new HashSet<>();
        String sql = "SELECT * FROM knihy";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nazov = rs.getString("nazov");
                String autor = rs.getString("autor");
                int rokVydania = rs.getInt("rokVydania");
                boolean dostupna = rs.getBoolean("dostupna");
                String zanerString = rs.getString("zaner");
                Zaner zaner = Zaner.valueOf(zanerString);
                int hodnotenie = rs.getInt("hodnotenie");
                Kniha kniha = new Kniha(nazov, autor, rokVydania, dostupna, zaner, hodnotenie);
                knihy.add(kniha);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return knihy;
    }
}