package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ActiveTurnException;
import exceptions.ExistingObjectException;

class EnterpriseTest {

	Enterprise e;
	Client c;
	Client c1;
	Client c2;
	Client c3;
	Client c4;
	
	@BeforeEach
	void setUp() throws Exception {
		e = new Enterprise();
	}
	
	@BeforeEach
	void setUp1() {
		e = new Enterprise();
		c = new Client("cc", "1036258412", "Maria", "Palacios", "3012584746", "cll 23 # 123-499");
		c1 = new Client("cc", "1036258412", "Carlos", "lopez", "3215203695", "cll 20 # 85-146");
	}
	
	@BeforeEach
	void setUp2() {
		e = new Enterprise();
		c = new Client("cc", "1036258412", "Maria", "Palacios", "3012584746", "cll 23 # 123-499");
		c1 = new Client("cc", "1545456456", "Juan", "Garcua", "1475896235", "cll 17 # 15-74");
		c2 = new Client("cc", "254781236945", "Pablo", "Ortiz", "547812032", "cll 23 # 12- 23");
		c3 = new Client("ti", "8457501121", "Carlos", "Lopez", "4563245678", "cll 13 # 324 - 232");
		c4 = new Client("rc", "4115236985", "Maria del mar", "Varela", "451238456", "crr 21 # 45-52");
	}
	
	@Test
	void test1() throws ExistingObjectException {
		setUp1();
		e.addNewClient(c);
		assertThrows(ExistingObjectException.class, () -> e.addNewClient(c1));
	}
	
	@Test
	void test2() throws ActiveTurnException, ExistingObjectException {
		setUp2();
		c.makeATurn();
		e.addNewClient(c);
		c1.makeATurn();
		e.addNewClient(c1);		
		c2.makeATurn();
		e.addNewClient(c2);
		c3.makeATurn();
		e.addNewClient(c3);
		c4.makeATurn();
		e.addNewClient(c4);
		
		c.mark(Turn.CURRENT_LETTER+""+Turn.CURRENT_NUMBER, Turn.CURRENT_POSITION);
		
		assertTrue(c.getShifts().get(0).isActive() == false);
		
	}

}
