package ia.danisquierdo.test;
import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;
import ia.battle.core.actions.BuildWall;
import ia.battle.core.actions.Skip;
import ia.battle.core.actions.Suicide;
import ia.exceptions.RuleException;

public class ElPunga extends Warrior {

	public ElPunga(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		
		//Informacion del warrior enemigo
		WarriorData ed = BattleField.getInstance().getEnemyData();
		
		
		
		Action queHago = new Skip();
		
		if (tick >= 50 && tick <= 60)
			queHago = new Suicide();
			
		//Action a = new Attack(celda a atacar);
		
		if (tick >= 150 && tick <= 200) {
			queHago = new MovimientoPunga(this);
		}
		
		//queHago = new BuildWall(celda);
		
		return queHago;
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		System.out.println();
		
	}

	@Override
	public void enemyKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub
		
	}

}
