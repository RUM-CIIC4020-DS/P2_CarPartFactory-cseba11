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
     * Create and initialize a Queue<Integer> that the keys represent the Id of the CarPart and the value represent the CarPart
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
    
    
    public Map<Integer, List<CarPart>> getInventory() {
    	return this.Inventory;
       
    }
    public void setInventory(Map<Integer, List<CarPart>> inventory) {
    	this.Inventory = inventory;
        
    }
    public List<Order> getOrders() {
    	return this.Orders;
        
    }
    public void setOrders(List<Order> orders) {
    	this.Orders = orders;
        
    }
    
    public Map<Integer, Integer>createDefective() {
    	Map<Integer, Integer> defectiveList = new HashTableSC<>(1, new BasicHashFunction());
    	for (PartMachine machin : this.Machines) {
    		defectiveList.put(machin.getPart().getId(), 0);
    	}
    	return defectiveList;
    }
    
    public Map<Integer, Integer> getDefectives() {
    	return this.Defective;
        
    }
    public void setDefectives(Map<Integer, Integer> defectives) {
    	this.Defective = defectives;
        
    }

    public void setupOrders(String path) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new FileReader(orderPath));
    	String data = "";
    	boolean firstLine = true;
    	
    	while ((data = br.readLine()) != null) {
    		if (!firstLine) {
    			
    			String[] information = data.split(",");
    			
    			int id = Integer.parseInt(information[0]);
    			String name = information[1];
    			
    			if (information.length == 3) {
    				
    				information[2] = information[2].replace("(", "").replace(")", "");
    				String[] orderRequested = information[2].split("-");
    				Map<Integer, Integer> partMap = new HashTableSC<>(1, new BasicHashFunction());
    				for (String Parts : orderRequested) {
    					String[] MapKey = Parts.split("\\s+");
    					Integer key = Integer.parseInt(MapKey[0]);
    					Integer value = Integer.parseInt(MapKey[1]);
    					partMap.put(key, value);
    					int view = 2;
    					
    				}
    				Order order = new Order(id, name, partMap, false);
    				this.Orders.add(order);
    				
    				
    			} 
    			
    			else {
    				Map<Integer, Integer> partMap = new HashTableSC<>(1, new BasicHashFunction());
    				Order order = new Order(id, name, partMap, false);
    				this.Orders.add(order);
    			}
    			
    		}
    		firstLine = false;
    	}
    	br.close();
 
    }
    public void setupMachines(String path) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new FileReader(partsPath));
    	String data = "";
    	boolean firstLine = true;
    	
    	while ((data = br.readLine()) != null) {
    		
    		if (!firstLine) {
    			
    			String[] parts = data.split(",");
    			
    			int id = Integer.parseInt(parts[0]);
    			String name = parts[1];
    			double weight = Double.parseDouble(parts[2]);
    			double weightError = Double.parseDouble(parts[3]);
    			int period = Integer.parseInt(parts[4]);
    			int chanceOfDefective = Integer.parseInt(parts[5]);
    			
    			CarPart carPart = new CarPart(id, name, weight, false); 
    			PartMachine partMachine = new PartMachine(id, carPart, period, weightError, chanceOfDefective);
    			this.Machines.add(partMachine);
    			
    		}
    		firstLine = false;
 
    	}
    	br.close();
       
    }
    
    public void setupInventory() {
    	
    	for (PartMachine partMachineId : this.Machines) {
    		ArrayList<CarPart> emptyList = new ArrayList<>();
    		this.Inventory.put(partMachineId.getPart().getId(), emptyList);
    	}
        
    }
    public void storeInInventory() {
    	
    	while (!ProductionBin.isEmpty()) {
    		if (!(ProductionBin.top().isDefective())) {
    			
    			List<CarPart> InventoryListToAdd = this.Inventory.get(this.ProductionBin.top().getId());
    			InventoryListToAdd.add(this.ProductionBin.top());
    			this.Inventory.put(ProductionBin.top().getId(), InventoryListToAdd);
    		}
    		
    		else {
    			this.Defective.put(this.ProductionBin.top().getId(), this.Defective.get(this.ProductionBin.top().getId() ) + 1);
    		}
    		ProductionBin.pop();
    		
    	}
    }
    
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
    
    public void runFactory(int days, int minutes) {
    	
    	for (int i = 0; i < days; i++) {
    		for (int j = 0; j < minutes; j++) {
    			for (PartMachine parts : this.Machines) {
    				CarPart view = parts.produceCarPart();
    				if (view != null) {
    					this.ProductionBin.push(view);
    				}
    			}
    		}
    		ArrayList<CarPart> temp = this.createCoveyorBelt();
    		for (CarPart j : temp) {
    			this.ProductionBin.push(j);
    		}
    		
    		for (PartMachine parts : this.Machines) {
    			parts.resetConveyorBelt();
    		}
    	
    		this.storeInInventory();
    		
    	}
    	this.processOrders();
    	
    }
   
    public void processOrders() {
    	
    	for (Order currOrder : this.getOrders()) {
            Map<Integer, Integer> requestedParts = currOrder.getRequestedParts();

            boolean orderFulfilled = true;

            for (Integer key : requestedParts.getKeys()) {
                int amtRequested = requestedParts.get(key);
                List<CarPart> inventoryList = this.Inventory.get(key);

                if (amtRequested > inventoryList.size()) {
                    orderFulfilled = false;
                    break; 
                }
            }

            if (orderFulfilled) {
                // Fulfill the order
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
