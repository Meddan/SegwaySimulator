package se.chalmers.segway.tests;

import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.game.SaveManager;
import junit.framework.TestCase;

public class UpgradesManagerTest extends TestCase {
	public void writeTest(){
		Upgrades.AntigravityWheels.setActive(true);
		Upgrades.SuperHelmet.setActive(false);
		SaveManager.saveUpgrades();
		Upgrades.AntigravityWheels.setActive(false);
		Upgrades.SuperHelmet.setActive(true);
		SaveManager.loadUpgrades();
		
		assertTrue(Upgrades.AntigravityWheels.isActivated() == true 
				&& Upgrades.SuperHelmet.isActivated() == false);
	}

}
