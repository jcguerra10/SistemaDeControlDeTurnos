package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ActiveTurnException;

class ClientTest {

	Client c;
	
	Client c1;
	Client c2;
	Client c3;
	Client c4;
	
	@BeforeEach
	void setUp() {
		c = new Client("cc", "1036258412", "Maria", "Palacios", "3012584746", "cll 23 # 123-499");
	}
	
	@Test
	void test() throws Exception {
		setUp();
		String idType = "cc";
		String id = "1036258412";
		String name = "Carlos";
		String lastName = "lopez";
		String cellphone = "3215203695";
		String direction = "cll 20 # 85-146";
		
		Client c = new Client(idType, id, name, lastName, cellphone, direction);
		
		assertTrue(idType.equals(c.getIdType()));
		assertTrue(id.equals(c.getId()));
		assertTrue(name.equals(c.getName()));
		assertTrue(lastName.equals(c.getLastName()));
		assertTrue(cellphone.equals(c.getCellphone()));
		assertTrue(direction.equals(c.getDirection()));
		
	}

	@Test
	void test0() throws ActiveTurnException {
		setUp();
		c.makeATurn();
		ArrayList<Turn> t = c.getShifts();
		
		assertEquals(t.get(t.size()-1).getTurn(), "A00");
		assertEquals(t.get(t.size()-1).getPosition(), 0);
		
	}
	
	@Test
	void test1() throws ActiveTurnException {
		setUp();
		c.makeATurn();
		
		assertThrows(ActiveTurnException.class, () -> c.makeATurn());
		
	}
	
	@Test
	void test2() throws ActiveTurnException {
		setUp();
		c.makeATurn();
		ArrayList<Turn> t = c.getShifts();
		t.get(t.size()-1).setActive(false);
		c.makeATurn();
		t = c.getShifts();
		
		String[] sp1 = t.get(0).getTurn().split("");
		String[] sp2 = t.get(1).getTurn().split("");
		
		assertTrue(sp1[2].compareTo(sp2[2]) < 0);
		
	}
	
	@Test
	void test3() throws ActiveTurnException {
		setUp();
		Turn.LETTER = 'D';
		Turn.NUMBER = "99";
		c.makeATurn();
		ArrayList<Turn> t = c.getShifts();
		t.get(t.size()-1).setActive(false);
		c.makeATurn();
		t = c.getShifts();
		
		assertTrue(t.get(t.size()-1).getTurn().equals("E00"));
		
	}
	
	@Test
	void test4() throws ActiveTurnException {
		setUp();
		Turn.LETTER = 'Z';
		Turn.NUMBER = "99";
		c.makeATurn();
		ArrayList<Turn> t = c.getShifts();
		t.get(t.size()-1).setActive(false);
		c.makeATurn();
		t = c.getShifts();
		
		assertTrue(t.get(t.size()-1).getTurn().equals("A00"));
	}
	
	

}
