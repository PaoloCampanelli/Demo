
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class TestContoCorrente {
	
	private static ContoCorrente Seed()
	{
		ContoCorrente cc = new ContoCorrente();
	    List<String> tags = Arrays.asList("Tag1", "Tag2", "Tag3", "Tag4", "Tag6");
	    List<String> tag = Arrays.asList("Tag1");
	    Categoria root = new Categoria();
	    root.CreaLivello("qwe", "root");
	    Categoria leg = new Categoria();
	    leg.CreaLivello("qwerty", "leg");
	    Categoria leaf = new Categoria();
	    leaf.CreaLivello("qwertyuio", "leaf");
	    //saldo iniziale
	    cc.CreaEntrata(new BigDecimal(50), Date.from(Instant.now()), "prova1", tag, root);
	    cc.CreaEntrata(new BigDecimal(50), Date.from(Instant.now()), "prova2", tags, root);
	    //entrata tra un giorno
	    cc.CreaEntrata(new BigDecimal(50), Date.from(Instant.now().plusSeconds(96400)), "prova3", tags, leg);
	    //uscita tra un giorno
	    cc.CreaUscita(new BigDecimal(25), Date.from(Instant.now().plusSeconds(96400)), "prova4", tags, leg);
	    //entrata di ieri
	    cc.CreaEntrata(new BigDecimal(50), Date.from(Instant.now().minusSeconds(96400)), "prova5", tags, leaf);
	    //uscita di ieri
	    cc.CreaUscita(new BigDecimal(25), Date.from(Instant.now().minusSeconds(96400)), "prova6", tags, leaf);
	    
	    return cc;
	}
	
	@Test
	public void TestCreaEntrata() {
		ContoCorrente cc = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {
			cc.CreaEntrata(new BigDecimal(50).negate(), Date.from(Instant.now()), "prova1", null,null);
		});
		
	}
	
	@Test
	public void TestCreaUscita() {
		ContoCorrente cc = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {
			cc.CreaUscita(new BigDecimal(50).negate(), Date.from(Instant.now()), "prova1", null,null);
		});
		
	}
		
	
	@Test
	public void TestGetSaldoAttuale() {
		ContoCorrente cc = Seed();
	    assertFalse(cc.GetSaldoAttuale().equals(new BigDecimal("30")));
	    assertFalse(cc.GetSaldoAttuale().equals(new BigDecimal("150")));
	    assertTrue(cc.GetSaldoAttuale().equals(new BigDecimal("125")));
	}
	
	@Test
	public void TestGetSaldoByDate() {
		ContoCorrente cc = Seed();
	    assertFalse(cc.GetSaldoByDate(Date.from(Instant.now().plusSeconds(360))).equals(new BigDecimal("100")));
	    assertTrue(cc.GetSaldoByDate(Date.from(Instant.now().plusSeconds(360))).equals(new BigDecimal("125")));		
	    assertTrue(cc.GetSaldoByDate(Date.from(Instant.now().plusSeconds(96900))).equals(new BigDecimal("150")));
	    
	}
	
	@Test
	public void TestGetMovimentiByDates() {
		ContoCorrente cc = Seed();
	    List<MovContoCorrente> var = cc.GetMovimentiByDates(Date.from(Instant.now().minusSeconds(96499)),Date.from(Instant.now().plusSeconds(96499)));
	    List<MovContoCorrente> var1 = cc.GetMovimentiByDates(Date.from(Instant.now()),Date.from(Instant.now().plusSeconds(96499)));
	    assertTrue(var.size()==6);
	    assertFalse(var1.size()==6);
	    
	    
	}
	
	@Test
	public void TestGetMovimentoById() {
		
		ContoCorrente cc = Seed();
		MovContoCorrente mov;
		mov = cc.GetMovimentoById(2);
		assertTrue(mov.Id==2);
		assertFalse(mov.Id==0);		
	}
	
	@Test
	public void TestGetMovimentiByDatesAndTag() {
		
		ContoCorrente cc = Seed();
		List<MovContoCorrente> var = cc.GetMovimentiByDatesAndTag(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)), "Tag1");
		assertTrue(var.size()==6);
		
		List<MovContoCorrente> var1 = cc.GetMovimentiByDatesAndTag(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)), "Tag2");
		assertFalse(var1.size()==6);
		assertTrue(var1.size()==5);		
			
	}
	
	@Test
	public void TestGetMovimentiByDatesAndCategory() {
		
		ContoCorrente cc = Seed();
				
		List<MovContoCorrente> var1 = cc.GetMovimentiByDatesAndCategory(Date.from(Instant.now().minusSeconds(96499)), Date.from(Instant.now().plusSeconds(96499)),"qwe");
		
		assertTrue(var1.size()==2);
	
			
	}
	
	
	
	
}
