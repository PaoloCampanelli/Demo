
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class TestBudget {
	
	private static Budget Seed()
	{
		Budget bd = new Budget();
	    List<String> tags = Arrays.asList("Tag1", "Tag2", "Tag3", "Tag4", "Tag6");
	    List<String> tag = Arrays.asList("Tag1");
	    Categoria root = new Categoria();
	    root.CreaLivello("qwe", "root");
	    Categoria leg = new Categoria();
	    leg.CreaLivello("qwerty", "leg");
	    Categoria leaf = new Categoria();
	    leaf.CreaLivello("qwertyuio", "leaf");
	    //saldo iniziale
	    bd.CreaEntrata(new BigDecimal(50), Date.from(Instant.now()), "prova1", tag, root);
	    bd.CreaEntrata(new BigDecimal(50), Date.from(Instant.now()), "prova2", tags, root);
	    //entrata tra un giorno
	    bd.CreaEntrata(new BigDecimal(50), Date.from(Instant.now().plusSeconds(96400)), "prova3", tags, leg);
	    //uscita tra un giorno
	    bd.CreaUscita(new BigDecimal(25), Date.from(Instant.now().plusSeconds(96400)), "prova4", tags, leg);
	    //entrata di ieri
	    bd.CreaEntrata(new BigDecimal(50), Date.from(Instant.now().minusSeconds(96400)), "prova5", tags, leaf);
	    //uscita di ieri
	    bd.CreaUscita(new BigDecimal(25), Date.from(Instant.now().minusSeconds(96400)), "prova6", tags, leaf);
	    
	    return bd;
	}
	
	@Test
	public void TestCreaEntrata() {
		Budget bd = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {
			bd.CreaEntrata(new BigDecimal(50).negate(), Date.from(Instant.now()), "prova1", null,null);
		});
		
	}
	
	@Test
	public void TestCreaUscita() {
		Budget bd = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {
			bd.CreaUscita(new BigDecimal(50).negate(), Date.from(Instant.now()), "prova1", null,null);
		});
		
	}
	
	@Test
	public void TestGetSaldoAttuale() {
		Budget bd = Seed();
	    assertFalse(bd.GetSaldoAttuale().equals(new BigDecimal("30")));
	    assertFalse(bd.GetSaldoAttuale().equals(new BigDecimal("150")));
	    assertTrue(bd.GetSaldoAttuale().equals(new BigDecimal("125")));
	}
	
	
	@Test
	public void TestGetSaldoByDate() {
		Budget bd = Seed();
	    assertFalse(bd.GetSaldoByDate(Date.from(Instant.now().plusSeconds(360))).equals(new BigDecimal("100")));
	    assertTrue(bd.GetSaldoByDate(Date.from(Instant.now().plusSeconds(360))).equals(new BigDecimal("125")));		
	    assertTrue(bd.GetSaldoByDate(Date.from(Instant.now().plusSeconds(96900))).equals(new BigDecimal("150")));
	    
	}
	
	@Test
	public void TestGetMovimentiByDates() {
		Budget bd = Seed();
	    List<MovBudget> var = bd.GetMovimentiByDates(Date.from(Instant.now().minusSeconds(96499)),Date.from(Instant.now().plusSeconds(96499)));
	    List<MovBudget> var1 = bd.GetMovimentiByDates(Date.from(Instant.now()),Date.from(Instant.now().plusSeconds(96499)));
	    assertTrue(var.size()==6);
	    assertFalse(var1.size()==6);
	    
	    
	}
	
	@Test
	public void TestGetMovimentoById() {
		
		Budget bd = Seed();
		MovBudget mov;
		mov = bd.GetMovimentoById(2);
		assertTrue(mov.Id==2);
		assertFalse(mov.Id==0);		
	}
	
	@Test
	public void TestGetMovimentiByDatesAndTag() {
		
		Budget bd = Seed();
		List<MovBudget> var = bd.GetMovimentiByDatesAndTag(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)), "Tag1");
		assertTrue(var.size()==6);
		
		List<MovBudget> var1 = bd.GetMovimentiByDatesAndTag(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)), "Tag2");
		assertFalse(var1.size()==6);
		assertTrue(var1.size()==5);		
			
	}
	
	@Test
	public void TestGetMovimentiByDatesAndCategory() {
		
		Budget bd = Seed();
		List<MovBudget> var1 = bd.GetMovimentiByDatesAndCategory(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)),"qwe");
		
		assertTrue(var1.size()==2);
		    
			
	}

}
