package ia.danisquierdo.test;

import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Move;

public class TestMove extends Move {
	
	private FieldCell origin;
	private FieldCell destiny;
	private int totalCost = 0;
	private int maxMovePoints;
	private ArrayList<FieldCell> path;

	public TestMove(Warrior warrior, FieldCell destiny){
		this.origin = warrior.getPosition();
		this.destiny = destiny;
		this.maxMovePoints = warrior.getSpeed() / 5;
	}
	
	@Override
	public ArrayList<FieldCell> move() {
		return path;
	}

	public ArrayList<FieldCell> calculatePath(){
		ArrayList<FieldCell> camino = new ArrayList<FieldCell>();
		
		FieldCell nextCell = this.origin;
		int increment;

		int difY = origin.getY() - destiny.getY();

		while(difY != 0 && totalCost <= maxMovePoints){
			
			increment = difY < 0 ? 1 : -1;
			
			nextCell = BattleField.getInstance().getFieldCell(nextCell.getX(), nextCell.getY() + increment);
			
			camino.add(nextCell);
			 
			totalCost += nextCell.getCost();
			
			difY = nextCell.getY() - destiny.getY();
			
		}

		int difX = origin.getY() - destiny.getY();
		
		while(difX != 0 && totalCost <= maxMovePoints){
			
			increment = difX < 0 ? 1 : -1;
			
			nextCell = BattleField.getInstance().getFieldCell(nextCell.getX() + increment, nextCell.getY());
			
			camino.add(nextCell);
			 
			totalCost += nextCell.getCost();
			
			difY = nextCell.getY() - destiny.getY();
			
		}
		
		
		return camino;
	}
	
}
