When a method returns one of several possible classes that share a common super class

-when you dun know ahead of time what class object you need
- when all of the potential classes are in teh same subclass hierarchy
-to encapsulate object creation

//EnemyShip.java

public abstract class EnemyShip {
	private String name;
	private double speed;
	private double directionX;
	private double directionY;
	private double amtDamage;
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public double getDamage() { return amtDamage; }
	public void setDamage(double newDamage) { amtDamage = newDamage; }
	
	public void followHeroShip(){
		
		System.out.println(getName() + " is following the hero");
		
	}
	
	public void displayEnemyShip(){
		
		System.out.println(getName() + " is on the screen");
		
	}
	
	public void enemyShipShoots() {
		
		System.out.println(getName() + " attacks and does " + getDamage() + " damage to hero");
		
	}
	
}


public class UFOEnemyShip extends EnemyShip {
	
	public UFOEnemyShip(){
		
		setName("UFO Enemy Ship");
		
		setDamage(20.0);
		
	}
	
}

public class UFOEnemyShip extends EnemyShip {
	
	public UFOEnemyShip(){
		
		setName("UFO Enemy Ship");
		
		setDamage(20.0);
		
	}
	
}

import java.util.Scanner;

public class EnemyShipTesting {

	public static void main(String[] args){
		
		// Create the factory object
		EnemyShipFactory shipFactory = new EnemyShipFactory();
		
		// Enemy ship object
		
		EnemyShip theEnemy = null;
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("What type of ship? (U / R / B)");
		
		if (userInput.hasNextLine()){
			
			String typeOfShip = userInput.nextLine();
		
			theEnemy = shipFactory.makeEnemyShip(typeOfShip);
			
			if(theEnemy != null){
				
				doStuffEnemy(theEnemy);
				
			} else System.out.print("Please enter U, R, or B next time");
		
		}
		
		/*
		EnemyShip theEnemy = null;
		
		// Old way of creating objects
		// When we use new we are not being dynamic
		
		EnemyShip ufoShip = new UFOEnemyShip();
		
		doStuffEnemy(ufoShip);
		
		System.out.print("\n");
		
		// -----------------------------------------
		
		// This allows me to make the program more dynamic
		// It doesn't close the code from being modified
		// and that is bad!
		
		// Defines an input stream to watch: keyboard
		Scanner userInput = new Scanner(System.in);
		
		String enemyShipOption = "";
		
		System.out.print("What type of ship? (U or R)");
		
		if (userInput.hasNextLine()){
			
			enemyShipOption = userInput.nextLine();
			
		}
		
		if (enemyShipOption == "U"){
			
			theEnemy = new UFOEnemyShip();

			
		} else 
		
		if (enemyShipOption == "R"){
			
			theEnemy = new RocketEnemyShip();
			
		} else {
			
			theEnemy = new BigUFOEnemyShip();
			
		}
		
		doStuffEnemy(theEnemy);
		
		// --------------------------------------------
		*/
		
	}
	
	// Executes methods of the super class
	
	public static void doStuffEnemy(EnemyShip anEnemyShip){
		
		anEnemyShip.displayEnemyShip();
		
		anEnemyShip.followHeroShip();
		
		anEnemyShip.enemyShipShoots();
		
	}
	
}

public class BigUFOEnemyShip extends UFOEnemyShip {
	
	public BigUFOEnemyShip(){
		
		setName("Big UFO Enemy Ship");
		
		setDamage(40.0);
		
	}
	
}

// This is a factory thats only job is creating ships
// By encapsulating ship creation, we only have one
// place to make modifications

public class EnemyShipFactory{
	
	// This could be used as a static method if we
	// are willing to give up subclassing it
	
	public EnemyShip makeEnemyShip(String newShipType){
		
		EnemyShip newShip = null;
		
		if (newShipType.equals("U")){
			
			return new UFOEnemyShip();
			
		} else 
		
		if (newShipType.equals("R")){
			
			return new RocketEnemyShip();
			
		} else 
		
		if (newShipType.equals("B")){
			
			return new BigUFOEnemyShip();
			
		} else return null;
		
	}
	
}