package main;

import data_structures.ListQueue;

import java.io.IOException;
import java.util.Random;
import interfaces.Queue;

public class PartMachine {
	/**
	 * Class that holds PartMachine that contains id, p1, period, weightError, chanceOfDefective, Timer, ConveyorBelt, totalPartsProduce and methods to get the data.
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
	private CarPart p1;
	private int period;
	private double weightError;
	private int chanceOfDefective;
	
	private Queue<Integer> Timer;
	private Queue<CarPart> ConveyorBelt;
	private int totalPartsProduce;
   
	
	/**
	 * Constructor for the PartMachine class.
	 * @param id - (Integer) Represents the Id of the PartMachine being initialized
	 * @param p1 - (CarPart) Represents the CarPart of the PartMachine being initialized
	 * @param period - (Integer) Represents the Period of the PartMachien being initialized
	 * @param weightError - (Double) Represents the Weight Error of the PartMachine being initialized
	 * @param chanceOfDefective - (Integer) Represents the Chance Of Defective of the PartMachine being initialized
	 */
    public PartMachine(int id, CarPart p1, int period, double weightError, int chanceOfDefective) {
    	this.id = id;
    	this.p1 = p1;
    	this.period = period;
    	this.weightError = weightError;
    	this.chanceOfDefective = chanceOfDefective;
  
    	/*----------------------------------------------------*/
    	
    	this.Timer = this.createTimer();
    	this.ConveyorBelt = this.createConveyorBelt();
    	this.totalPartsProduce = 0;
        
    }
    
    /**
	 * Returns the Id of the PartMachine
	 */
    public int getId() {
    	return this.id;
       
    }
    
    /**
	 * Sets the Id of the PartMachine
	 * @param id - (Integer) value to assign to the Id.
	 */
    public void setId(int id) {
    	this.id = id;
        
    }
    
    /**
	 * Returns the CarPart of the PartMachine
	 */
    public CarPart getPart() {
    	return this.p1; 
    	
    }
    
    /**
	 * Sets the CarPart of the PartMachine
	 * @param part1 - (CarPart) value to assign to the CarPart.
	 */
    public void setPart(CarPart part1) {
    	this.p1 = part1;
    	
    }
    
    /**
	 * Create a Queue<Integer> that is a Queue from Period - 1 to 0 that represent the Timer of the PartMachine
	 */
    public Queue<Integer> createTimer() {
    	Timer = new ListQueue<Integer>(); 
    	for (int i = this.period - 1; i >= 0; i--) {
    		Timer.enqueue(i);
    	}
    	return Timer;
    }
    
    /**
	 * Return the Timer Queue of the PartMachine
	 */
    public Queue<Integer> getTimer() {
    	return Timer;
       
    }
    
    /**
	 * Sets the Timer of the PartMachine 
	 * @param timer - (Queue<Integer>) value to assign to the Timer Queue.
	 */
    public void setTimer(Queue<Integer> timer) {
    	this.Timer = timer;
        
    }
    
    
    /**
	 * Return the WeightError of the PartMachine
	 */
    public double getPartWeightError() {
    	return this.weightError;
    	
    }
    
    /**
	 * Sets the WeightError of the PartMachine 
	 * @param partWeightError - (Double) value to assign to the WeightError.
	 */
    public void setPartWeightError(double partWeightError) {
    	this.weightError = partWeightError;
    	
    }
    
    /**
	 * Returns the Chance Of Defective of the PartMachine
	 */
    public int getChanceOfDefective() {
    	return this.chanceOfDefective;
    	
    }
    
    /**
	 * Sets the ChanceOfDefective of the PartMachine 
	 * @param chanceOfDefective - (Integer) value to assign to the ChanceOfDefective.
	 */
    public void setChanceOfDefective(int chanceOfDefective) {
    	this.chanceOfDefective = chanceOfDefective;
    	
    }
    
    /**
     * Create and initialize 10 null spaces into a Queue<Integer> that represent the Conveyor Belt of the PartMachine
     */
    public Queue<CarPart> createConveyorBelt() {
    	Queue<CarPart> ConveyorBeltQueu = new ListQueue<CarPart>();
    	for (int i = 0; i < 10; i++) {
    		ConveyorBeltQueu.enqueue(null);
    	}
    	
    	return ConveyorBeltQueu;
    	
    }
    
    /**
	 * Returns the Conveyor Belt Queue of the PartMachine
	 */
    public Queue<CarPart> getConveyorBelt() {
    	return this.ConveyorBelt;
    	
    }
    
    /**
	 * Sets the ConveyorBelt Queue of the PartMachine 
	 * @param conveyorBelt - (Queue<Integer>) Queue to assign to the ConveyorBelt.
	 */
    public void setConveyorBelt(Queue<CarPart> conveyorBelt) {
    	this.ConveyorBelt = conveyorBelt;
    	
    }
    
    /**
	 * Returns the Total Parts Produce of the PartMachine
	 */
    public int getTotalPartsProduced() {
    	return this.totalPartsProduce;
         
    }
    
    /**
	 * Sets the TotalPartsProduce of the PartMachine 
	 * @param count - (Integer) value to assign to the TotalPartsProduce.
	 */
    public void setTotalPartsProduced(int count) {
    	this.totalPartsProduce = count;
    	
    }
    
    /**
	 * Reset all the values of the ConveyorBelt to null of the PartMachine 
	 */
    public void resetConveyorBelt() {
    	this.ConveyorBelt = this.createConveyorBelt();
        
    }
    
    /**
	 * Return the front value of the Timer Queue of the PartMachine
	 */
    public int tickTimer() {
    	Integer timerToReturn = Timer.dequeue();
    	Timer.enqueue(timerToReturn);
    	return timerToReturn;
       
    }
    
    /**
	 * Method that produces new CarParts with the given Id, CarPart, 
	 * NewWeight that depends of the Weight of the CarPart and the WeightError of the PartMachine that produces that CarPart
	 * to check if the CarPart created will be Defective it depends of the totalPartsProduce and the chanceOfDefective.
	 * In the end of the Method will return the front of the ConveyorBelt.
	 */
    public CarPart produceCarPart() {
    	
    	/* The min and max WeightError is the range for the newWeight */
    	double minWeightError = this.p1.getWeight() - weightError; 
    	double maxWeightError = this.p1.getWeight() + weightError; 
    	
    	/* Stores the front of the ConveyorBelt to return it at the end */
    	CarPart front = this.ConveyorBelt.front();
    	
    	/* If the tickTimer is equal to 0 produces a new CarPart */
    	if (this.tickTimer() == 0) {
    		
        	/* Create the newWeight for the new CarPart to produce */
    		double newWeight = minWeightError + (Math.random() * (maxWeightError - minWeightError));
    		
        	/* Change the newWeight to only have one decimal place without rounding */
    		newWeight = (int)(newWeight * 10) / 10.0;
    		boolean deFect;
    		
        	/* Check if the CarPart to be produce will be Defective */
    		if (this.totalPartsProduce % chanceOfDefective == 0) {
    			deFect = true;
    		}
    		else {
    			deFect = false;
    		}
    		
        	/* Create the new CarPart and add it to the end of the ConveyorBelt */
    		CarPart car = new CarPart(p1.getId(), p1.getName(), newWeight, deFect);
    		this.ConveyorBelt.dequeue();
    		this.ConveyorBelt.enqueue(car);
    		this.totalPartsProduce++;
    		
    	}
    	
    	else {
    		this.ConveyorBelt.dequeue();
    		this.ConveyorBelt.enqueue(null);
    		
    	}
    	return front;
    	
    }
 
    /**
     * Returns string representation of a Part Machine in the following format:
     * Machine {id} Produced: {part name} {total parts produced}
     */
    @Override
    public String toString() {
        return "Machine " + this.getId() + " Produced: " + this.getPart().getName() + " " + this.getTotalPartsProduced();
    }
    /**
     * Prints the content of the conveyor belt. 
     * The machine is shown as |Machine {id}|.
     * If the is a part it is presented as |P| and an empty space as _.
     */
    public void printConveyorBelt() {
        // String we will print
        String str = "";
        // Iterate through the conveyor belt
        for(int i = 0; i < this.getConveyorBelt().size(); i++){
            // When the current position is empty
            if (this.getConveyorBelt().front() == null) {
                str = "_" + str;
            }
            // When there is a CarPart
            else {
                str = "|P|" + str;
            }
            // Rotate the values
            this.getConveyorBelt().enqueue(this.getConveyorBelt().dequeue());
        }
        System.out.println("|Machine " + this.getId() + "|" + str);
    }
}
