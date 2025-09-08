package beanCollections;

public class MyBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] cities;
	private String[] states;
	private String[] countries;
	private String[] populations;
	private String[] attractions;


    public MyBean() {
    	cities = new String[] { "Denver", "San Diego", "New Orleans", "Nashville", "Chicago" };
    	states = new String[] { "Colorado", "California", "Louisiana", "Tennessee", "Illinois" };
    	countries = new String[] { "United States", "United States", "United States", "United States", "United States" };
    	populations = new String[] { "715,891", "1.4 million", "351,399", "686,513", "2,611,867" };
    	attractions = new String[] { "Red Rocks Amphitheatre and Denver Botanic Gardens", "San Diego Zoo and Balboa Park",
    			"French Quarter and St. Louis Cathedral", "Country Music Hall of Fame and Broadway",
    			"Willis Tower Skydeck and Navy Pier" };
    }


	public String[] getCities() {
		return cities;
	}


	public void setCities(String[] cities) {
		this.cities = cities;
	}


	public String[] getStates() {
		return states;
	}


	public void setStates(String[] states) {
		this.states = states;
	}

	public String[] getCountries() {
		return countries;
	}


	public void setCountries(String[] countries) {
		this.countries = countries;
	}


	public String[] getPopulations() {
		return populations;
	}


	public void setPopulations(String[] populations) {
		this.populations = populations;
	}


	public String[] getAttractions() {
		return attractions;
	}


	public void setAttractions(String[] attractions) {
		this.attractions = attractions;
	}
    
}

