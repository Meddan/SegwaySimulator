package se.chalmers.segway.tests;

import junit.framework.TestCase;
import se.chalmers.segway.game.Upgrades;

public class UpgradesTest extends TestCase {
	public void testActivatingAndDeactivating(){
		boolean testWentWell = true;
		Upgrades.AntigravityWheels.setActive(true);
		if(!Upgrades.AntigravityWheels.isActivated()){
			testWentWell = false;
		}
		Upgrades.AntigravityWheels.setActive(false);
		if(Upgrades.AntigravityWheels.isActivated()){
			testWentWell = false;
		}
		Upgrades.SuperHelmet.setActive(false);
		if(Upgrades.SuperHelmet.isActivated()){
			testWentWell = false;
		}
		assertTrue(testWentWell);
	}
	public void testGetName(){
		assertTrue(Upgrades.RocketBoost.getName().equals("RocketBoost") && Upgrades.SuperHelmet.getName().equals("SuperHelmet"));
	}
	public void testGetValues(){
		assertNotNull(Upgrades.values());
	}
	public void testResetUpgrades(){
		for(Upgrades u : Upgrades.values()){
			u.setActive(true);
		}
		Upgrades.resetUpgrades();
		boolean testWentWell = true;
		for(Upgrades u : Upgrades.values()){
			if(u.isActivated()){
				testWentWell = false;
			}
		}
		assertTrue(testWentWell);
	}
}
