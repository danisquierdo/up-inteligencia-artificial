package ia.danisquierdo.test;

import ia.battle.core.ConfigurationManager;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class TestWarriorManager extends WarriorManager {

	private String name = "Test Manager";
	
	@Override
	public String getName() {
		return this.name;
	}	

	@Override
	public Warrior getNextWarrior() throws RuleException {
		//La suma de todos los puntos debe sumar este valor
		int points = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
//		ConfigurationManager.getInstance().getMapHeight();
//		ConfigurationManager.getInstance().getMapWidth();
//		ConfigurationManager.getInstance().getActionsPerTurn();
//		ConfigurationManager.getInstance().getFieldCellHitPoints();
//		ConfigurationManager.getInstance().getMaxRangeForWarrior();
//		ConfigurationManager.getInstance().getMaxWarriorPerBattle();
//		ConfigurationManager.getInstance().getTurnsToShrink();
		
//		int range = ConfigurationManager.getInstance().getMaxRangeForWarrior(); //10
//		int remainingPoints = points - range;
//		int speed = remainingPoints/2; //30
//		remainingPoints = remainingPoints - speed;
//		int strength = remainingPoints/2; //25
//		remainingPoints = remainingPoints - strength;
//		int health = remainingPoints
		
		//name, health, defense, strength, speed, range
		Warrior a = new TestWarrior("Test Warrior", 20, 10, 30, 30, 10);
		
		
		return a;
	}

}
