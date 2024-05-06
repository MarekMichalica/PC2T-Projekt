package projekt;

import java.sql.*;
import java.util.HashSet;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        getDBConnection();
    }

    public boolean getDBConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:SQLite/Kniznica.db");
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
            if (connection != null) {
                connection.close();
            }
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
        String insertSql = "INSERT INTO knihy (nazov, autor, rokVydania, dostupna, zaner, hodnotenie) VALUES (?,?,?,?,?,?)";
        String updateSql = "UPDATE knihy SET autor = ?, rokVydania = ?, dostupna = ?, zaner = ?, hodnotenie = ? WHERE nazov = ?";
        
        try {
            for (Kniha kniha : knihy) {
                String nazov = kniha.getNazov();
                String autor = kniha.getAutor();
                int rokVydania = kniha.getRokVydania();
                boolean dostupna = kniha.getDostupna();
                Zaner zaner = kniha.getZaner();
                int hodnotenie = kniha.getHodnotenie();

                PreparedStatement checkStmt = connection.prepareStatement("SELECT COUNT(*) FROM knihy WHERE nazov = ?");
                checkStmt.setString(1, nazov);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                    updateStmt.setString(1, autor);
                    updateStmt.setInt(2, rokVydania);
                    updateStmt.setBoolean(3, dostupna);
                    updateStmt.setString(4, zaner.name());
                    updateStmt.setInt(5, hodnotenie);
                    updateStmt.setString(6, nazov);
                    updateStmt.executeUpdate();
                } else {
                    PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                    insertStmt.setString(1, nazov);
                    insertStmt.setString(2, autor);
                    insertStmt.setInt(3, rokVydania);
                    insertStmt.setBoolean(4, dostupna);
                    insertStmt.setString(5, zaner.name());
                    insertStmt.setInt(6, hodnotenie);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Chyba pri ukladani knih do databazy: " + e.getMessage());
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
