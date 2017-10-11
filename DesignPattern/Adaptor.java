public interface EnemyAttacker {	
	public void fireWeapon();
	public void driveForward();
	public void assignDriver(String driverName);
}


ENEMYTANK.JAVA

// EnemyTank implements EnemyAttacker perfectly
// Our job is to make classes with different methods
// from EnemyAttacker to work with the EnemyAttacker interface

import java.util.Random;

public class EnemyTank implements EnemyAttacker{
	
	Random generator = new Random();

	public void fireWeapon() {
		
		int attackDamage = generator.nextInt(10) + 1;
		
		System.out.println("Enemy Tank Does " + attackDamage + " Damage");
		
	}

	public void driveForward() {
		
		int movement = generator.nextInt(5) + 1;
		
		System.out.println("Enemy Tank moves " + movement + " spaces");
		
	}

	public void assignDriver(String driverName) {
		
		System.out.println(driverName + " is driving the tank");
		
	}
	
}

//ENEMYROBOT.JAVA

// This is the Adaptee. The Adapter sends method calls
// to objects that use the EnemyAttacker interface
// to the right methods defined in EnemyRobot

import java.util.Random;

public class EnemyRobot{

	Random generator = new Random();
	
	public void smashWithHands() {
		
		int attackDamage = generator.nextInt(10) + 1;
		
		System.out.println("Enemy Robot Causes " + attackDamage + " Damage With Its Hands");
		
	}

	public void walkForward() {
		
		int movement = generator.nextInt(5) + 1;
		
		System.out.println("Enemy Robot Walks Forward " + movement + " spaces");
		
	}

	public void reactToHuman(String driverName) {
		
		System.out.println("Enemy Robot Tramps on " + driverName);
		
	}
	
	
	
}

//ENEMYROBOTADAPTER.JAVA

// The Adapter must provide an alternative action for 
// the the methods that need to be used because
// EnemyAttacker was implemented.

// This adapter does this by containing an object
// of the same type as the Adaptee (EnemyRobot)
// All calls to EnemyAttacker methods are sent
// instead to methods used by EnemyRobot

public class EnemyRobotAdapter implements EnemyAttacker{

	EnemyRobot theRobot;
	
	public EnemyRobotAdapter(EnemyRobot newRobot){
		
		theRobot = newRobot;
		
	}
	
	public void fireWeapon() {
		
		theRobot.smashWithHands();
		
	}

	public void driveForward() {
		
		theRobot.walkForward();
		
	}

	public void assignDriver(String driverName) {
		
		theRobot.reactToHuman(driverName);
		
	}
	
	
	
}

//TESTENEMYATTACKERS.JAVA

public class TestEnemyAttackers{
	
	public static void main(String[] args){
		
		EnemyTank rx7Tank = new EnemyTank();
		
		EnemyRobot fredTheRobot = new EnemyRobot();
		
		EnemyAttacker robotAdapter = new EnemyRobotAdapter(fredTheRobot);
		
		System.out.println("The Robot");
		
		fredTheRobot.reactToHuman("Paul");
		fredTheRobot.walkForward();
		fredTheRobot.smashWithHands();
		System.out.println();
		
		System.out.println("The Enemy Tank");
		
		rx7Tank.assignDriver("Frank");
		rx7Tank.driveForward();
		rx7Tank.fireWeapon();
		System.out.println();
		
		System.out.println("The Robot with Adapter");
		
		robotAdapter.assignDriver("Mark");
		robotAdapter.driveForward();
		robotAdapter.fireWeapon();

	}
	
}