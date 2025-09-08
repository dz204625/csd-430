package database;

/**
 * Dan Zhu
 * CSD-430
 * Assignment 5 & 6
 */

import java.sql.*;
import java.util.*;

public class DbBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	private Statement statement;

	private final String url = "jdbc:mysql://localhost:3306/CSD430";
	private final String username = "student1";
	private final String password = "pass";

	// Constructor opens connection and creates statement
	public DbBean() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error initializing DB connection: " + e.getMessage());
		}
	}
	
	// Create table
		public String createTable() {
			StringBuilder result = new StringBuilder();
			try {
				statement.executeUpdate("DROP TABLE IF EXISTS zhu_movies_data");
				result.append("Dropped existing table if it existed.<br>");

				String createSQL = """
						    CREATE TABLE zhu_movies_data (
						        movieId INT AUTO_INCREMENT PRIMARY KEY,
						        title VARCHAR(255),
						        director VARCHAR(255),
						        releaseYear INT,
						        genre VARCHAR(100),
						        rating DECIMAL(2,1)
						    )
						""";
				statement.executeUpdate(createSQL);
				result.append("Created table zhu_movies_data.<br>");
			} catch (SQLException e) {
				return "Error creating table: " + e.getMessage();
			}
			return result.toString();
		}
		
		// Create movie
		public String createMovie(String title, String director, int releaseYear, String genre, float rating) {
			String sql = "INSERT INTO zhu_movies_data (title, director, releaseYear, genre, rating) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setString(1, title);
				pstmt.setString(2, director);
				pstmt.setInt(3, releaseYear);
				pstmt.setString(4, genre);
				pstmt.setFloat(5, rating);
				pstmt.executeUpdate();
				return "Movie added successfully.<br>";
			} catch (SQLException e) {
				return "Error adding movie: " + e.getMessage();
			}
		}



    // ***************************************************************
    // ------------------------ Update Record ------------------------
    // ***************************************************************

    public String updateRecord(int movieId, String title, String director, int releaseYear, String genre, float rating) {
        String result = "";
        String sql = "UPDATE zhu_movies_data SET title=?, director=?, releaseYear=?, genre=?, rating=? WHERE movieId=?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, releaseYear);
            stmt.setString(5, genre);
            stmt.setFloat(6, rating);
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0 ? "Record updated successfully." : "Update failed.";
        } catch (SQLException e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    public Map<String, String> getRecordById(int movieId) {
        Map<String, String> data = new HashMap<>();
        String sql = "SELECT * FROM zhu_movies_data WHERE movieId = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                data.put("movieId", rs.getString("movieId"));
                data.put("title", rs.getString("title"));
                data.put("director", rs.getString("director"));
                data.put("releaseYear", rs.getString("releaseYear"));
                data.put("genre", rs.getString("genre"));
                data.put("rating", rs.getString("rating"));
            }
        } catch (SQLException e) {
        }
        return data;
    }
    
    public String populateTable() {
		String[] inserts = {
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(1, 'The Shawshank Redemption', 'Frank Darabont', 1994, 'Drama', 9.3)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(2, 'The Godfather', 'Francis Ford Coppola', 1972, 'Crime', 9.2)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(3, 'The Dark Knight', 'Christopher Nolan', 2008, 'Action', 9.0)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(4, 'Pulp Fiction', 'Quentin Tarantino', 1994, 'Crime', 8.9)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(5, 'Forrest Gump', 'Robert Zemeckis', 1994, 'Drama', 8.8)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(6, 'Inception', 'Christopher Nolan', 2010, 'Sci-Fi', 8.8)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(7, 'Fight Club', 'David Fincher', 1999, 'Drama', 8.8)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(8, 'The Matrix', 'Lana Wachowski, Lilly Wachowski', 1999, 'Sci-Fi', 8.7)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(9, 'Goodfellas', 'Martin Scorsese', 1990, 'Crime', 8.7)",
				"INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES(10, 'Interstellar', 'Christopher Nolan', 2014, 'Sci-Fi', 8.6)" };
		try {
			for (String sql : inserts) {
				statement.executeUpdate(sql);
			}
			return "Movies management populated with 10 movies.<br>";
		} catch (SQLException e) {
			return "Error populating table: " + e.getMessage();
		}
	}
    

    // ***************************************************************
    // ------------------------ Create Record ------------------------
    // ***************************************************************

    public String createRecord(String title, String director, int releaseYear, String genre, float rating) throws SQLException {
        String sql = "INSERT INTO zhu_movies_data(title, director, releaseYear, genre, rating) VALUES(?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, title);
        stmt.setString(2, director);
        stmt.setInt(3, releaseYear);
        stmt.setString(4, genre);
        stmt.setFloat(5, rating);
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        return String.valueOf(keys.getInt(1));
    }
    
    // ***************************************************************
    // ------------------------ FormGetCreate ------------------------
    // ***************************************************************
    
    public String formGetCreateOrUpdate(String requestURL) {
        return "<form method='post' action='" + requestURL + "'>\n" +
                "<br /><br />" +
                "<label for='title'>Title</label>&nbsp;&nbsp;" +
                "<input type='text' name='title' id='title' required><br />\n" +
                "<label for='director'>Director</label>&nbsp;&nbsp;" +
                "<input type='text' name='director' id='director' required maxlength='255'><br />\n" +
                "<label for='releaseYear'>ReleaseYear</label>&nbsp;&nbsp;" +
                "<input type='number' name='releaseYear' id='releaseYear'><br />\n" +
                "<label for='genre'>Genre</label>&nbsp;&nbsp;" +
                "<input type='text' name='genre' id='genre' required><br />\n" +
                "<label for='rating'>rating</label>&nbsp;&nbsp;" +
                "<input type='number' name='rating' id='rating' maxlength='50'><br />\n" +
                "<input type='submit' value='Create Record'>\n" +
                "</form>\n";
    }

    // ***************************************************************
    // ------------------------ FormGetPK ----------------------------
    // ***************************************************************
    
    public String formGetPK(String unused) {
        StringBuilder dataStringBuilder = new StringBuilder();
        dataStringBuilder.append("<select name=\"movieId\" id=\"movieId\">\n");

        try (java.sql.Connection conn = getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT movieId, title FROM zhu_movies_data ORDER BY title ASC");
             java.sql.ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                dataStringBuilder.append("<option value=\"");
                dataStringBuilder.append(resultSet.getString("movieId"));
                dataStringBuilder.append("\">");
                dataStringBuilder.append(resultSet.getString("title"));
                dataStringBuilder.append("</option>");
            }
        } catch (java.sql.SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        dataStringBuilder.append("</select>");
        return dataStringBuilder.toString();
    }

    // ***************************************************************
    // ------------------------ Read ---------------------------------
    // ***************************************************************
    
    public String read(String movieId) {
        StringBuilder dataStringBuilder = new StringBuilder();
        
        if (movieId == null || movieId.trim().isEmpty()) {
            return "No Movie ID provided";
        }
        
        String sql = "SELECT * FROM zhu_movies_data WHERE movieId = ?";
        try (java.sql.Connection conn = getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, movieId);
            
            try (java.sql.ResultSet resultSet = stmt.executeQuery()) {
                dataStringBuilder.append("<table border='1'>");
                dataStringBuilder.append("<thead><tr>"
                		+ "<th>Movie ID</th>"
                		+ "<th>Title</th>"
                		+ "<th>Director</th>"
                		+ "<th>Release Year</th>"
                		+ "<th>Genre</th>"
                		+ "<th>Rating</th>"
                		+ "</tr></thead>");
                dataStringBuilder.append("<tbody>");

                boolean found = false;
                while(resultSet.next()) {
                    found = true;
                    dataStringBuilder.append("<tr>");
                    
                    for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        dataStringBuilder.append("<td>");
                        String value = resultSet.getString(i);
                        dataStringBuilder.append(value != null ? value.trim() : "");
                        dataStringBuilder.append("</td>");
                    }
                    dataStringBuilder.append("</tr>");
                }
                
                if (!found) {
                    dataStringBuilder.append("<tr><td colspan='6'>No Movie found with ID: ").append(movieId).append("</td></tr>");
                }
                
                dataStringBuilder.append("</tbody>");
                dataStringBuilder.append("</table>");
            }
            
        } catch(java.sql.SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            dataStringBuilder.append("<b>Error retrieving data: </b>").append(e.getMessage()).append("<br />");
        }
        
        return dataStringBuilder.toString();
    }

    // ***************************************************************
    // ------------------------ Delete -------------------------------
    // ***************************************************************

  
    public String delete(String movieId) {
        String sql = "DELETE FROM zhu_movies_data WHERE movieId = ?";
        
        try (java.sql.Connection conn = getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, movieId);
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                return "Record deleted successfully";
            } else {
                return "No record found";
            }
            
        } catch(java.sql.SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return "Error deleting record: " + e.getMessage();
        }
    }

    // ***************************************************************
    // ------------------------ Read All -----------------------------
    // ***************************************************************

    public String readAll() {
        StringBuilder dataStringBuilder = new StringBuilder();
        
        String sql = "SELECT * FROM zhu_movies_data";
        
        try (java.sql.Connection conn = getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            java.sql.ResultSet resultSet = stmt.executeQuery()) {
            
            dataStringBuilder.append("<table border='1'>");
            dataStringBuilder.append("<thead><tr>"
            		+ "<th>Movie ID</th>"
            		+ "<th>Title</th>"
            		+ "<th>Director</th>"
            		+ "<th>Release Year</th>"
            		+ "<th>Genre</th>"
            		+ "<th>Rating</th>"
            		+ "</tr>"
            		+ "</thead>");
            dataStringBuilder.append("<tbody>");

            while(resultSet.next()) {
                dataStringBuilder.append("<tr>");
                for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    dataStringBuilder.append("<td>");
                    dataStringBuilder.append(resultSet.getString(i).trim());
                    dataStringBuilder.append("</td>");
                }
                dataStringBuilder.append("</tr>");
            }
            dataStringBuilder.append("</tbody>");
            dataStringBuilder.append("</table>");
            
        } catch(java.sql.SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            dataStringBuilder.append("<b>Error retrieving data: </b>").append(e.getMessage()).append("<br />");
        }

        return dataStringBuilder.toString();
    }
    
	public String getMovieDetails(String id) {
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT * FROM zhu_movies_data WHERE movieId = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sb.append("<form method='post'>");
				sb.append("ID: <input type='text' name='movieId' value='").append(rs.getInt("movieId"))
						.append("' readonly><br>");
				sb.append("Title: <input type='text' name='title' value='").append(rs.getString("title"))
						.append("'><br>");
				sb.append("Director: <input type='text' name='director' value='").append(rs.getString("director"))
						.append("'><br>");
				sb.append("Release Year: <input type='number' name='releaseYear' value='").append(rs.getInt("releaseYear"))
						.append("'><br>");
				sb.append("Genre: <input type='text' name='genre' value='").append(rs.getString("genre"))
						.append("'><br>");
				sb.append("Rating: <input type='number' name='rating' value='").append(rs.getFloat("rating"))
				.append("'><br>");
				sb.append("<input type='submit' value='Update movie'>");
				sb.append("</form>");
			} else {
				sb.append("<p>No movie found with ID ").append(id).append(".</p>");
			}
		} catch (Exception e) {
			sb.append("<p>Error: ").append(e.getMessage()).append("</p>");
		}
		return sb.toString();
	}
	
	// Generate <option> list for movie IDs (for dropdown)
	public String getMovieIdOptions() {
		StringBuilder sb = new StringBuilder();
		try {
			ResultSet rs = statement.executeQuery("SELECT movieId FROM zhu_movies_data ORDER BY movieId ASC");
			while (rs.next()) {
				int id = rs.getInt("movieId");
				sb.append("<option value='").append(id).append("'>").append(id).append("</option>");
			}
		} catch (SQLException e) {
			sb.append("<option>Error loading IDs</option>");
		}
		return sb.toString();
	}

    // Database Connection
    private java.sql.Connection getConnection() throws java.sql.SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = url + "?user=" + username + "&password=" + password + 
                "&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            return java.sql.DriverManager.getConnection(connectionUrl);
        } catch(ClassNotFoundException e) {
            throw new java.sql.SQLException("Database driver not found: " + e.getMessage(), e);
        }
    }
}