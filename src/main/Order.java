package main;

import interfaces.Map;

public class Order {
	/**
	 * Class that holds Order that contains id, customerName, weight, requestedParts, fulfilled and methods to get the data.
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
	private String customerName;
	private Map<Integer, Integer> requestedParts;
	private boolean fulfilled;
   
	
	/**
	 * Constructor for the Order class.
	 * @param id - (Integer) Represents the Id of the Order being initialized
	 * @param customerName - (String) Represents the Name of the Order being initialized
	 * @param requestedParts - (Map<Integer, Integer>) Represents the Map of requestedParts that the Order have being initialized
	 * @param fulfilled - (Boolean) Represents the Fulfilled status of the Order being initialized
	 */
    public Order(int id, String customerName, Map<Integer, Integer> requestedParts, boolean fulfilled) {
    	this.id = id;
    	this.customerName = customerName;
    	this.requestedParts = requestedParts;
    	this.fulfilled = fulfilled;
        
    }
    
    /**
	 * Returns the Id of the Order
	 */
    public int getId() {
    	return this.id;
        
    }
    
    /**
	 * Sets the Id of the Order
	 * @param id - (Integer) value to assign to the Id.
	 */
    public void setId(int id) {
    	this.id = id;
        
    }
    
    /**
	 * Returns the Customer Name of the Order 
	 */
    public String getCustomerName() {
    	return this.customerName;
    	
    }
    
    /**
	 * Sets the customerName of the Order
	 * @param customerName - (String) value to assign to the customerName.
	 */
    public void setCustomerName(String customerName) {
    	this.customerName = customerName;
    	
    }
    
    /**
	 * Returns the status of the Order
	 */
    public boolean isFulfilled() {
    	return this.fulfilled;
    	
    }
    
    /**
	 * Sets the isFulfilled of the Order
	 * @param isFulfilled - (Boolean) value to assign to the status of the Order(isFulfilled).
	 */
    public void setFulfilled(boolean fulfilled) {
    	this.fulfilled = fulfilled;
        
    }
    
    /**
	 * Return a Map of RequestedParts and the amount needed of the Order 
	 */
    public Map<Integer, Integer> getRequestedParts() {
    	return this.requestedParts;
       
    }
    
    /**
	 * Sets the requestedParts of the Order
	 * @param requestedParts - (Map<Integer, Integer>) Map to assign to the requestedParts.
	 */
    public void setRequestedParts(Map<Integer, Integer> requestedParts) {
    	this.requestedParts = requestedParts;
       
    }
    /**
     * Returns the order's information in the following format: {id} {customer name} {number of parts requested} {isFulfilled}
     */
    @Override
    public String toString() {
        return String.format("%d %s %d %s", this.getId(), this.getCustomerName(), this.getRequestedParts().size(), (this.isFulfilled())? "FULFILLED": "PENDING");
    }

    
    
}
