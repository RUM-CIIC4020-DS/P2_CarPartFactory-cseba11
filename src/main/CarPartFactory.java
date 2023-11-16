package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import data_structures.ArrayList;
import data_structures.BasicHashFunction;
import data_structures.HashTableSC;
import data_structures.LinkedStack;
import interfaces.List;
import interfaces.Map;
import interfaces.Queue;
import interfaces.Stack;

public class CarPartFactory {
	/**
	 * Class that holds CarPartFactory that contains orderPath, partsPath, Machines, Orders, PartCatalog, Inventory, Defective, ProductionBin and methods to get the data.
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
	private String orderPath;
	private String partsPath;
	private List<PartMachine> Machines;
	private List<Order> Orders;
	private Map<Integer, CarPart> PartCatalog;
	private Map<Integer, List<CarPart>> Inventory;
	private Map<Integer, Integer> Defective;
	private Stack<CarPart> ProductionBin;

	
	/**
	 * Constructor for the Book class.
	 * @param orderPath - (String) Represents the OrderPath(OrderFile) of the CarFactoryProject being initialized
	 * @param partsPath - (String) Represents the PartPath(PartFile) of the CarFactoryProject being initialized
	 * @throws IOException If an I/O error occurs while reading from the file
	 */
    public CarPartFactory(String orderPath, String partsPath) throws IOException {
    	this.orderPath = orderPath;
    	this.partsPath = partsPath;
    	
    	/*----------------------------------------------------*/
    	
    	/* Initialize the Machine List to and empty ArrayList */
    	this.Machines = new ArrayList<>();
    	this.setupMachines(partsPath);
    	
    	/* Initialize the Order List to and empty ArrayList */
    	this.Orders = new ArrayList<>();
    	this.setupOrders(orderPath);
    	this.PartCatalog = createPartCatalog();
    	this.Inventory = new HashTableSC<>(1, new BasicHashFunction());
    	this.setupInventory();
    	this.Defective = this.createDefective();
    	this.ProductionBin = new LinkedStack<>();             
    }
    
    /**
	 * Return the List of PartMachine of the CarPartFactoty 
	 */
    public List<PartMachine> getMachines() {
    	return this.Machines;
       
    }
    
    /**
	 * Sets the Machine List of the CarPartFactory 
	 * @param machines - (List<PartMachine>) List to assign to the Machine List.
	 */
    public void setMachines(List<PartMachine> machines) {
    	this.Machines = machines;
        
    }
    
    /**
	 * Returns the ProductionBin Stack of the CarPartFactory
	 */
    public Stack<CarPart> getProductionBin() {
    	return this.ProductionBin;
      	
    }
    
    /**
	 * Sets the ProductionBin Stack of the CarPartFactory 
	 * @param production - (Stack<CarPart>) Stack to assign to the ProductionBin Stack.
	 */
    public void setProductionBin(Stack<CarPart> production) {
    	this.ProductionBin = production;
       
    }
    
    /**
     * Create and initialize a Map that the keys represent the Id of the CarPart and the value represent the CarPart
     */
    public Map<Integer, CarPart> createPartCatalog(){
    	
    	Map<Integer, CarPart> partCatalogMap = new HashTableSC<>(1, new BasicHashFunction());
    	for (PartMachine part : this.Machines) {
    		partCatalogMap.put(part.getPart().getId(), part.getPart());
    	}
    	return partCatalogMap;
    	
    }
    
    /**
	 * Returns the PartCatalog of the CarPartFactory
	 */
    public Map<Integer, CarPart> getPartCatalog() {
    	return this.PartCatalog;
    	
    }
    
    /**
	 * Sets the PartCatalog Map of the CarPartFactory 
	 * @param partCatalog - (Map<Integer, CarPart>) Map to assign to the PartCatalog Map.
	 */
    public void setPartCatalog(Map<Integer, CarPart> partCatalog) {
    	this.PartCatalog = partCatalog;
        
    }
    
    /**
     * Returns Inventory Map of the CarPartFactory
	 */
    public Map<Integer, List<CarPart>> getInventory() {
    	return this.Inventory;
      
    
    }
    
    /**
	 * Sets the Inventory Map of the CarPartFactory 
	 * @param inventory - (Map<Integer, List<CarPart>>) Map to assign to the Inventory Map.
	 */
    public void setInventory(Map<Integer, List<CarPart>> inventory) {
    	this.Inventory = inventory;
        
    }
    
    /**
	 * Returns the Order List of the CarPartFactory
	 */
    public List<Order> getOrders() {
    	return this.Orders;
        
    }
    
    /**
	 * Sets the Order List of the CarPartFactory 
	 * @param orders - (List<Order>) List to assign to the Order List.
	 */
    public void setOrders(List<Order> orders) {
    	this.Orders = orders;
        
    }
    
    /**
     * Create and initialize the Defective Map that the keys represent the Id of the CarPart and the value represent the count of Defective of that CarPart
     */
    public Map<Integer, Integer>createDefective() {
    	Map<Integer, Integer> defectiveList = new HashTableSC<>(1, new BasicHashFunction());
    	for (PartMachine machin : this.Machines) {
    		defectiveList.put(machin.getPart().getId(), 0);
    	}
    	return defectiveList;
    }
    
    /**
	 * Returns the Defective Map of the CarPartFactory
	 */
    public Map<Integer, Integer> getDefectives() {
    	return this.Defective;
        
    }
    
    /**
	 * Sets the Defective Map of the CarPartFactory 
	 * @param defectives - (Map<Integer, Integer>) Map to assign to the Defective Map.
	 */
    public void setDefectives(Map<Integer, Integer> defectives) {
    	this.Defective = defectives;
        
    }
    
    /**
     * Reads order information from a specified file and sets up orders accordingly.
     *
     * This method parses the data from the specified file path and creates Order objects
     * based on the information provided. Each line in the file represents an order with
     * the following format: "id, name, (partId1 quantity1 - partId2 quantity2 ...)".
     *
     * If an order does not contain any parts, an empty map is associated with it.
     *
     * @param path The file path from which to read the order information.
     * @throws IOException If an I/O error occurs while reading the file.
     */

    /**
	 * Gets the Orders from the Customer in the Input folder. 
	 * Runs through all entire file for each line and create the Order to add it to the List<Order>
	 * if the Customer have a Map that is the amount of specific CarPart that was order create a Order with the data from the Order. 
	 * Otherwise create the User with an empty Map.  
	 * @param path - (String) The file path from which to read the order information.
	 * @throws IOException If an I/O error occurs while reading from the file
	 */
    public void setupOrders(String path) throws IOException {
    	
    	/* Read the path from the input */
    	BufferedReader br = new BufferedReader(new FileReader(path));
    	String data = "";
    	boolean firstLine = true;
    	
    	while ((data = br.readLine()) != null) {
    		
    		/* Skip the first line because the first line is not a Order */
    		if (!firstLine) {
    			
    			/* Split the line into an Array */
    			String[] information = data.split(",");
    			
    			/* Convert each value of the Array to their respective Data type */
    			int id = Integer.parseInt(information[0]);
    			String name = information[1];
    			
    			/* Check if the length of component is equals to 3 means that the User have one or more Books currently checkOut */
    			if (information.length == 3) {
    				
    				/* Remove the parenthesis and splits the maps of CarPart that is separated by - into an Array */
    				information[2] = information[2].replace("(", "").replace(")", "");
    				String[] orderRequested = information[2].split("-");
    				Map<Integer, Integer> partMap = new HashTableSC<>(1, new BasicHashFunction());
    				for (String Parts : orderRequested) {
    					String[] MapKey = Parts.split("\\s+");
    					Integer key = Integer.parseInt(MapKey[0]);
    					Integer value = Integer.parseInt(MapKey[1]);
    					
    					/* Put the the Id, Quantity as key value in the partMap*/
    					partMap.put(key, value);
    					
    				}
    				
    				/* Create a new Order and the it adds it to the Order List */
    				Order order = new Order(id, name, partMap, false);
    				this.Orders.add(order);
    				
    				
    			} 
    			
    			else {
    				/* Create a new Order with and empty map and the it adds it to the Order List */
    				Map<Integer, Integer> partMap = new HashTableSC<>(1, new BasicHashFunction());
    				Order order = new Order(id, name, partMap, false);
    				this.Orders.add(order);
    			}
    			
    		}
    		firstLine = false;
    	}
    	br.close();
 
    }
    
    /**
	 * Sets the Parts from of the Requested Part from the Customers in the Input folder. 
	 * Create PartMachine given the information from each line and adds it to the List<PartMachines>.
	 * @param path - (String) The file path from which to read the machine information. 
	 * @throws IOException If an I/O error occurs while reading from the file
	 */
    public void setupMachines(String path) throws IOException {
    	
    	/* Read the path from the input */
    	BufferedReader br = new BufferedReader(new FileReader(path));
    	String data = "";
    	boolean firstLine = true;
    	
    	/* Read from the first line of the path to the end */
    	while ((data = br.readLine()) != null) {
    		
    		/* Skip the first line because the first line is not a Order */
    		if (!firstLine) {
    			
    			/* Split the line into an Array */
    			String[] parts = data.split(",");
    			
    			/* Convert each value of the Array to their respective Data type */
    			int id = Integer.parseInt(parts[0]);
    			String name = parts[1];
    			double weight = Double.parseDouble(parts[2]);
    			double weightError = Double.parseDouble(parts[3]);
    			int period = Integer.parseInt(parts[4]);
    			int chanceOfDefective = Integer.parseInt(parts[5]);
    			
    			/* Create a new CarPart and a new PartMachine with the CarPart created and then adds it to the Machines List */
    			CarPart carPart = new CarPart(id, name, weight, false); 
    			PartMachine partMachine = new PartMachine(id, carPart, period, weightError, chanceOfDefective);
    			this.Machines.add(partMachine);
    			
    		}
    		firstLine = false;
 
    	}
    	br.close();
       
    }
    
    /**
	 * Sets the Inventory Map for each Machine of the CarPartFactory 
	 * @param defectives - (Map<Integer, Integer>) Map to assign to the Defective Map.
	 */
    
    
    /**
     * Sets the Inventory Map for each Machine of the CarPartFactory.
     * Initialize the inventory Map with the CarPart Id as key and an empty List as value 
     * This method initializes the inventory for each machine by creating an empty list of CarPart
     */
    public void setupInventory() {
    	
    	for (PartMachine partMachineId : this.Machines) {
    		ArrayList<CarPart> emptyList = new ArrayList<>();
    		this.Inventory.put(partMachineId.getPart().getId(), emptyList);
    	}
        
    }
    
    /**
     * Stores the CarParts from the ProductionBin Stack and checks if the CarPart is Defective 
     * and add it to its corresponding Map and and counts Defective CarParts.
     */
    public void storeInInventory() {
    	
    	while (!ProductionBin.isEmpty()) {
    		
    		/* Check if the CarPart is Defective */
    		if (!(ProductionBin.top().isDefective())) {
    			
    			/* Save the List of the value of the Map */
    			List<CarPart> InventoryListToAdd = this.Inventory.get(this.ProductionBin.top().getId());
    			InventoryListToAdd.add(this.ProductionBin.top());
    			this.Inventory.put(ProductionBin.top().getId(), InventoryListToAdd);
    		}
    		
    		
    		else {
    			/* Adds one to the count of Defective CarPart */
    			this.Defective.put(this.ProductionBin.top().getId(), this.Defective.get(this.ProductionBin.top().getId() ) + 1);
    		}
    		ProductionBin.pop();
    		
    	}
    }
    
    /**
     * Creates a list of CarParts from the machines conveyorBelts.
     * This method iterates through each PartMachine, retrieves its conveyorBelts,
     * and adds the CarParts from the belt to a list. The method then resets each
     * conveyorBelts for the next production cycle.
     * @return An ArrayList containing CarParts from the conveyorBelts of all machines.
     */
    public ArrayList<CarPart> createCoveyorBelt(){
    	ArrayList<CarPart> view = new ArrayList<>();
    	for (PartMachine part : this.Machines) {
    		
    		Queue<CarPart> still = part.getConveyorBelt();
    		
    		while (!still.isEmpty()) {
    			if (still.front() != null) {
    				view.add(still.front());
    			}
    			still.dequeue();
    			
    		}
    		part.resetConveyorBelt();
    	}
    	return view;
    }	
    
    /**
     * Simulates the operation of the factory for a specified duration.
     *
     * This method simulates the factory's operation over a given number of days and
     * minutes per day. It iterates through each minute, produces CarParts using
     * the PartMachines, adds them to the ProductionBin, processes the conveyorBelts,
     * resets the conveyorBelts and stores the produced parts in the inventory.
     * After the simulation period, it processes the accumulated orders.
     * @param days - (Integer) The number of days to simulate.
     * @param minutes - (Integer) The number of minutes per day to simulate.
     */
    public void runFactory(int days, int minutes) {
    	
    	/* Iterate for the amount of days and the amount of minutes */
    	for (int daysPassed = 0; daysPassed < days; daysPassed++) {
    		for (int minutesPassed = 0; minutesPassed < minutes; minutesPassed++) {
    			/* For every minutes use the method produceCarPart for all the Machines List */
    			for (PartMachine parts : this.Machines) {
    				CarPart view = parts.produceCarPart();
    				if (view != null) {
    					this.ProductionBin.push(view);
    				}
    			}
    		}
    		/* Create an ArrayList of all the parts that at the end of the day are still in the conveyorBelt and adds it to the ProductionBin */
    		ArrayList<CarPart> listCarParts = this.createCoveyorBelt();
    		for (CarPart carPart : listCarParts) {
    			this.ProductionBin.push(carPart);
    		}
    		/* Reset the conveyorBelt at the end of the day */
    		for (PartMachine parts : this.Machines) {
    			parts.resetConveyorBelt();
    		}
    		
    		/* Store all the Orders */
    		this.storeInInventory();
    		
    	}
    	this.processOrders();
    	
    }
   
    /**
     * Processes pending orders and fulfills them if possible.
     * This method iterates through each pending order, checks if there are enough
     * available CarParts in the inventory to fulfill the order, and updates the
     * inventory accordingly. If the order can be fulfilled, it marks the order as
     * fulfilled.
     */
    public void processOrders() {
    	
    	/* Iterate through each order in the list of orders */
    	for (Order currOrder : this.Orders) {
    		/* Get the map of requested parts for the current order */
            Map<Integer, Integer> requestedParts = currOrder.getRequestedParts();

            boolean orderFulfilled = true;

            /* Check if there are enough parts in the inventory to fulfill the order */
            for (Integer key : requestedParts.getKeys()) {
                int amtRequested = requestedParts.get(key);
                List<CarPart> inventoryList = this.Inventory.get(key);

                /* If the requested quantity exceeds the available inventory, mark order as unfulfilled and break the loop */
                if (amtRequested > inventoryList.size()) {
                    orderFulfilled = false;
                    break; 
                }
            }

            if (orderFulfilled) {
            	
            	/* Iterate through the requested parts again to update the inventory */
                for (Integer key : requestedParts.getKeys()) {
                    int amtRequested = requestedParts.get(key);
                    List<CarPart> inventoryList = this.Inventory.get(key);

                    // Remove requested number of units from inventory
                    for (int i = 0; i < amtRequested; i++) {
                        inventoryList.remove(0);
                    }

                    // Update inventory
                    this.Inventory.put(key, inventoryList);
                }
                currOrder.setFulfilled(true);
            }
            
        }
    	
    }
    /**
     * Generates a report indicating how many parts were produced per machine,
     * how many of those were defective and are still in inventory. Additionally, 
     * it also shows how many orders were successfully fulfilled. 
     * @throws IOException 
     */
    public void generateReport() throws IOException {
        String report = "\t\t\tREPORT\n\n";
        report += "Parts Produced per Machine\n";
        for (PartMachine machine : this.getMachines()) {
            report += machine + "\t(" + 
            this.getDefectives().get(machine.getPart().getId()) +" defective)\t(" + 
            this.getInventory().get(machine.getPart().getId()).size() + " in inventory)\n";
        }
       
        report += "\nORDERS\n\n";
        for (Order transaction : this.getOrders()) {
            report += transaction + "\n";
        }
        System.out.println(report);
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CarPartFactory lc = new CarPartFactory("input/orders.csv", "input/parts.csv");
//			System.out.println( lc.Machines.get(0));
			lc.generateReport();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    

   

}
