package ia.danisquierdo.test;
import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Move;

public class MovimientoPunga extends Move {
	
	private Warrior warrior;
	
	public MovimientoPunga(Warrior warrior) {
		this.warrior = warrior;
	}
	
	@Override
	public ArrayList<FieldCell> move() {
		ArrayList<FieldCell> pasos = new ArrayList<>();
		
		FieldCell dondeEstoy = warrior.getPosition();
		FieldCell dondeVoy = BattleField.getInstance().
				getFieldCell(dondeEstoy.getX() + 1, dondeEstoy.getY());
		
		pasos.add(dondeVoy);
		
		return pasos;
	}

}
