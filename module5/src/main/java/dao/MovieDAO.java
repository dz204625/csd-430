package dao;

import model.Movie;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // reset = drop + create
    public void resetTable() {
    	String drop = "DROP TABLE IF EXISTS zhu_movies_data";
        String create = "CREATE TABLE zhu_movies_data (" +
                "movieId INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(200) NOT NULL," +
                "director VARCHAR(150)," +
                "releaseYear INT," +
                "genre VARCHAR(100)," +
                "rating DECIMAL(3,1)" +
                ")";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("reset");
            stmt.execute(drop);   // ✅ make sure to drop if exists
            stmt.execute(create); // ✅ then recreate
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable() {
        String sql = "INSERT INTO zhu_movies_data (title, director, releaseYear, genre, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            addSample(ps, "The Shawshank Redemption", "Frank Darabont", 1994, "Drama", 9.3);
            addSample(ps, "The Godfather", "Francis Ford Coppola", 1972, "Crime", 9.2);
            addSample(ps, "The Dark Knight", "Christopher Nolan", 2008, "Action", 9.0);
            addSample(ps, "Pulp Fiction", "Quentin Tarantino", 1994, "Crime", 8.9);
            addSample(ps, "Forrest Gump", "Robert Zemeckis", 1994, "Drama", 8.8);
            addSample(ps, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
            addSample(ps, "Fight Club", "David Fincher", 1999, "Drama", 8.8);
            addSample(ps, "The Matrix", "Lana Wachowski, Lilly Wachowski", 1999, "Sci-Fi", 8.7);
            addSample(ps, "Goodfellas", "Martin Scorsese", 1990, "Crime", 8.7);
            addSample(ps, "Interstellar", "Christopher Nolan", 2014, "Sci-Fi", 8.6);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSample(PreparedStatement ps, String t, String d, int y, String g, double r) throws SQLException {
        ps.setString(1, t);
        ps.setString(2, d);
        ps.setInt(3, y);
        ps.setString(4, g);
        ps.setDouble(5, r);
        ps.executeUpdate();
    }

    public boolean createMovie(Movie movie) {
        String sql = "INSERT INTO zhu_movies_data (title, director, releaseYear, genre, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDirector());
            if (movie.getReleaseYear() != null) ps.setInt(3, movie.getReleaseYear()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, movie.getGenre());
            if (movie.getRating() != null) ps.setDouble(5, movie.getRating()); else ps.setNull(5, Types.DOUBLE);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM zhu_movies_data ORDER BY movieId ASC";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Movie m = new Movie();
                m.setMovieId(rs.getInt("movieId"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                int y = rs.getInt("releaseYear");
                if (!rs.wasNull()) m.setReleaseYear(y);
                String genre = rs.getString("genre");
                m.setGenre(genre);
                double rating = rs.getDouble("rating");
                if (!rs.wasNull()) m.setRating(rating);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    public Movie getMovieById(int id) {
        String sql = "SELECT * FROM zhu_movies_data WHERE movieId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Movie m = new Movie();
                    m.setMovieId(rs.getInt("movieId"));
                    m.setTitle(rs.getString("title"));
                    m.setDirector(rs.getString("director"));
                    int y = rs.getInt("releaseYear");
                    if (!rs.wasNull()) m.setReleaseYear(y);
                    m.setGenre(rs.getString("genre"));
                    double rating = rs.getDouble("rating");
                    if (!rs.wasNull()) m.setRating(rating);
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMovie(Movie movie) {
        String sql = "UPDATE zhu_movies_data SET title=?, director=?, releaseYear=?, genre=?, rating=? WHERE movieId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDirector());
            if (movie.getReleaseYear() != null) ps.setInt(3, movie.getReleaseYear()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, movie.getGenre());
            if (movie.getRating() != null) ps.setDouble(5, movie.getRating()); else ps.setNull(5, Types.DOUBLE);
            ps.setInt(6, movie.getMovieId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMovie(int id) {
        String sql = "DELETE FROM zhu_movies_data WHERE movieId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

