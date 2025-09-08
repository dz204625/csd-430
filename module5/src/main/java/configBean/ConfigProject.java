package configBean;

/**
 * Dan Zhu
 * CSD-430
 * Assignment 5 & 6
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigProject implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	//initial connection and statement
	private Connection connection;
	private Statement statement;
	
	//database credentials
	private final String url = "jdbc:mysql://localhost:3306/CSD430";
	private final String username = "student1";
    private final String password = "pass";
    
    //constructor
    public ConfigProject() {
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection = java.sql.DriverManager.getConnection(url, username, password);
    		statement = connection.createStatement(); 
    	}
    	catch(ClassNotFoundException cnfe) {
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		System.out.print("SQL Exception" + sqle);
    	}
    }
    
	// create table
	public String createTable() {
		StringBuilder dataStringBuilder = new StringBuilder();
		try {
			statement.executeUpdate("DROP TABLE IF EXISTS zhu_movies_data");
			dataStringBuilder.append("Dropped existing table if it existed.<br>");

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
			dataStringBuilder.append("Created table zhu_movies_data.<br>");
		} catch (SQLException e) {
			return "Error creating table: " + e.getMessage();
		}
		return dataStringBuilder.toString();
	}
    
    //populate table
    public String populateTable() {
        StringBuilder dataStringBuilder = new StringBuilder();
        
        // SQL queries to insert data into the table
        String sql = """
                INSERT INTO zhu_movies_data (movieId, title, director, releaseYear, genre, rating) VALUES
					(1, 'The Shawshank Redemption', 'Frank Darabont', 1994, 'Drama', 9.3),
					(2, 'The Godfather', 'Francis Ford Coppola', 1972, 'Crime', 9.2),
					(3, 'The Dark Knight', 'Christopher Nolan', 2008, 'Action', 9.0),
					(4, 'Pulp Fiction', 'Quentin Tarantino', 1994, 'Crime', 8.9),
					(5, 'Forrest Gump', 'Robert Zemeckis', 1994, 'Drama', 8.8),
					(6, 'Inception', 'Christopher Nolan', 2010, 'Sci-Fi', 8.8),
					(7, 'Fight Club', 'David Fincher', 1999, 'Drama', 8.8),
					(8, 'The Matrix', 'Lana Wachowski, Lilly Wachowski', 1999, 'Sci-Fi', 8.7),
					(9, 'Goodfellas', 'Martin Scorsese', 1990, 'Crime', 8.7),
					(10, 'Interstellar', 'Christopher Nolan', 2014, 'Sci-Fi', 8.6);			
        """;

        try {
            // Execute the SQL query to populate the table
            statement.executeUpdate(sql);
            dataStringBuilder.append("The movie table has been populated.");
        } catch (SQLException e) {
        	dataStringBuilder.append("Error populating table: ").append(e.getMessage());
        }
        
        return dataStringBuilder.toString();  // Return result message
    }

    
    //read table
    public String read() {
    	
    	StringBuilder dataStringBuilder = new StringBuilder();    	
    	
    	ResultSet resultSet = null;   	
    	
        try{
        	resultSet = statement.executeQuery("SELECT * FROM World_Series");
        }
        catch(java.sql.SQLException e){
        }
        
        try{
            
        	dataStringBuilder.append("<table border='1' bgcolor='FA8072'>");
            
            while(resultSet.next()){
            	dataStringBuilder.append("<tr>");
                  
              for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
            	  dataStringBuilder.append("<td>");
            	  dataStringBuilder.append((resultSet.getString(i)).trim());
            	  dataStringBuilder.append("</td>");
              }
                  
              dataStringBuilder.append("</tr>");
            }
              
            dataStringBuilder.append("</table>");            
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	return dataStringBuilder.toString();
    }
    
    //close connection
    public void closeConnection(){
    	
    	try {
    		
    		statement.close();
    		connection.close();
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);    		
    	}    	
    }

}
