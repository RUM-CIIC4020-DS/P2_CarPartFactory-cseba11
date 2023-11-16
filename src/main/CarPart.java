package main;

public class CarPart {
	/**
	 * Class that holds CarParts that contains id, name, weight, isDefective and methods to get the data.
	 * @author Carlos Hernandez
	 * @Class CIIC4020 FALL 2023
	 * @Date 2023-11-15
	 */
	
	/*TODO ADD THE FOLLOWING:
	 * PRIVATE FIELDS, 
	 * CONSTRUCTOR, 
	 * GETTERS, 
	 * SETTERS, 
	 * MEMBER METHODS
	 */
	private int id;
	private String name;
	private double weight;
	private boolean isDefective;
	
	
	/**
	 * Constructor for the CarPar class.
	 * @param id - (Integer) Represents the Id of the CarPart being initialized
	 * @param name - (String) Represents the Name of the CarPart being initialized
	 * @param weight - (Double) Represents the Weight of the CarPart being initialized
	 * @param isDefective - (Boolean) Represents the isDefective of the CarPart being initialized
	 */
    public CarPart(int id, String name, double weight, boolean isDefective) {
    	this.id = id;
    	this.name = name;
    	this.weight = weight;
    	this.isDefective = isDefective;
        
    }
    
    /**
	 * Returns the Id of the CarPart
	 */
    public int getId() {
    	return this.id;
        
    }
    
    /**
	 * Sets the Id of the CarPart
	 * @param id - (Integer) value to assign to the Id.
	 */
    public void setId(int id) {
    	this.id = id;
        
    }
    
    /**
	 * Return the Name of the CarPart
	 */
    public String getName() {
    	return this.name;
        
    }
    
    /**
	 * Sets the Name of the CarPart
	 * @param name = (String) value to assign to the Name.
	 */
    public void setName(String name) {
    	this.name = name;
        
    }
    
    /**
	 * Return the Weight of the CarPart
	 */
    public double getWeight() {
    	return this.weight;
      
    }
    
    /**
	 * Sets the Weight of the CarPart
	 * @param weight - (Double) value to assign to the weight.
	 */
    public void setWeight(double weight) {
    	this.weight = weight;
      
    }

    /**
	 * Return the isDefective of the CarPart
	 */
    public boolean isDefective() {
    	return this.isDefective;
      
    }
    
    /**
	 * Sets the isDefective of the CarPart
	 * @param isDefective - (Boolean) value to assign to the isDefective.
	 */
    public void setDefective(boolean isDefective) {
    	this.isDefective = isDefective;
        
    }
    /**
     * Returns the parts name as its string representation
     * @return (String) The part name
     */
    public String toString() {
        return this.getName();
    }
}