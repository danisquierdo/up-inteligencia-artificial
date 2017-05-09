package ia.danisquierdo.test;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class ElPuntero extends WarriorManager {

	@Override
	public String getName() {
		return "El Puntero";
	}

	@Override
	public Warrior getNextWarrior() throws RuleException {
		
		//La suma de todos los puntos debe sumar este valor
		int points = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		ConfigurationManager.getInstance().getMapHeight();
		ConfigurationManager.getInstance().getMapWidth();
		ConfigurationManager.getInstance().getActionsPerTurn();
		ConfigurationManager.getInstance().getFieldCellHitPoints();
		ConfigurationManager.getInstance().getMaxRangeForWarrior();
		ConfigurationManager.getInstance().getMaxWarriorPerBattle();
		ConfigurationManager.getInstance().getTurnsToShrink();
		
		
		Warrior a = new ElPunga("El Brayan", 50, 20, 10, 5, 5);
		
		
		return a;
	}

}
