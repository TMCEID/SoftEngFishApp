// src/dao/DB.java
package dao;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.stream.Collectors;

/** Single shared connection to ONE sqlite file (greel.db). */
public final class DB {

    private static final String DB_FILE = "greel.db";
    private static final String URL     = "jdbc:sqlite:" + DB_FILE;

    private static Connection conn;

    public static synchronized Connection get() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL);
            initSchema(conn);
        }
        return conn;
    }

    //helpers

    /** runs only the first time (when table “user” does not exist) */
    private static void initSchema(Connection c) {
        try (ResultSet rs = c.getMetaData().getTables(null, null, "user", null)) {
            if (rs.next()) return;               // DB already seeded
        } catch (SQLException ignore) {}

        // Try to load src/sql/schema.sql from the runtime class-path
        String sqlText = null;
        try (InputStream in = DB.class.getResourceAsStream("/sql/schema.sql")) {
            if (in != null)
                sqlText = new BufferedReader(new InputStreamReader(in))
                        .lines().collect(Collectors.joining("\n"));
        } catch (IOException ignore) {}

        // Fallback: read the file directly from working dir
        if (sqlText == null) {
            try {
                sqlText = Files.readString(Path.of("src/sql/schema.sql"));
            } catch (IOException e) {
                throw new IllegalStateException(
                        "Cannot find schema.sql – is src/sql marked as Resources root? ", e);
            }
        }

        try (Statement st = c.createStatement()) {
            for (String stmt : sqlText.split("(?m);\\s*$"))          // split on ';'
                if (!stmt.isBlank()) st.executeUpdate(stmt);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private DB() {}
}
