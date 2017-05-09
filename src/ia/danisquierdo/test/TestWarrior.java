package ia.danisquierdo.test;
import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;
import ia.battle.core.actions.Move;
import ia.battle.core.specialitems.SpecialItem;
import ia.exceptions.RuleException;

public class TestWarrior extends Warrior {

	private boolean attackMode = true;
	private ArrayList<FieldCell> camino;
	
	public TestWarrior(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		WarriorData enemyData = BattleField.getInstance().getEnemyData();

		if(enemyData.getInRange()){
			attackMode = false;
			return new Attack(enemyData.getFieldCell());
		}
		else{
			if(attackMode){
				TestMove move = new TestMove(this, enemyData.getFieldCell());
				camino = move.calculatePath();
				
				if(camino.get(0).getFieldCellType() == FieldCellType.BLOCKED){
					return new Attack(camino.get(0));
				}
				
				return move;
			}
			else{
				attackMode = true;
				
				TestMove move = null;
				
				ArrayList<FieldCell> items = BattleField.getInstance().getSpecialItems();
				
				boolean itemFound = false;
				
				for(int i = 0; i < items.size() && !itemFound; i++){
					if(isPositionInRange(items.get(i))){
						move = new TestMove(this, items.get(i));
					}
				}
				
				if(!itemFound){
					move = new TestMove(this, BattleField.getInstance().getFieldCell(0, 0));
				}
				
				return move;
			}
		}
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}
	
	boolean isPositionInRange(FieldCell field) {
		int centerX = this.getPosition().getX();
		int centerY = this.getPosition().getY();

		int range = this.getRange();

		int x = field.getX();
		int y = field.getY();

		if ((Math.pow(centerX - x, 2)) + (Math.pow(centerY - y, 2)) <= Math.pow(range, 2)) {
			return true;
		}

		return false;
	}

}
