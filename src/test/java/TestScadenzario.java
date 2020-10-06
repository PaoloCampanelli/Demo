
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class TestScadenzario {
	
	private static Scadenzario Seed() {
		Scadenzario sc = new Scadenzario();
		sc.CreaScadenza(new BigDecimal(390),  Date.from(Instant.now().plusNanos(1)), "prima scadenza",null);
		sc.CreaScadenza(new BigDecimal(380), new BigDecimal (10), Date.from(Instant.now().plusSeconds(180000)), "",null);
		
		return sc;	
	}
	
	@Test
	public void TestCreaScadenza() {
		Scadenzario sc = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {
			sc.CreaScadenza(new BigDecimal(390).negate(), Date.from(Instant.now().plusSeconds(90050)), "errore",null);
		});
		
		assertThrows(IllegalArgumentException.class, ()-> {
			sc.CreaScadenza(new BigDecimal(390), Date.from(Instant.now().minusSeconds(90000)), "errore",null);
		});
		
		assertThrows(IllegalArgumentException.class, ()-> {
			sc.CreaScadenza(new BigDecimal(380).negate(), new BigDecimal (10), Date.from(Instant.now().plusSeconds(180001)), "errore",null);
		});
		
		assertThrows(IllegalArgumentException.class, ()-> {
			sc.CreaScadenza(new BigDecimal(380), new BigDecimal (10).negate(), Date.from(Instant.now().plusSeconds(180002)), "errore",null);
		});
		
		assertThrows(IllegalArgumentException.class, ()-> {
			sc.CreaScadenza(new BigDecimal(380), new BigDecimal (10), Date.from(Instant.now().minusSeconds(180002)), "errore",null);
		});
		
	}
	
	
	@Test
	public void TestSetPagata() throws InterruptedException {
		Scadenzario sc = Seed();
		ContoCorrente cc = new ContoCorrente();
	    List<String> tags = Arrays.asList("Tag1", "Tag2", "Tag3", "Tag4", "Tag6");
	    Categoria root = new Categoria();
	    root.CreaLivello("qwe", "root");
	    cc.CreaEntrata(new BigDecimal(400), Date.from(Instant.now().minusSeconds(600)), "prova1", tags, root);
	    //aspetto un secondo
		Thread.sleep(1000);
	    sc.SetPagata(cc);
	    assertTrue(cc.GetSaldoAttuale().equals(new BigDecimal(10)));
		
		
	}
	

}
