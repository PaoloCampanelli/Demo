
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class TestStruttura {
	
	private static Struttura Seed() {
		Struttura st = new Struttura();
		
		st.CreaLivello("qwe", "root");
		st.CreaLivello("qwerty", "leg");
		st.CreaLivello("qwertyuio", "leaf");
		return st;	
	}
	
	@Test
	public void TestCreaLivello() {
		Struttura st = Seed();
		assertThrows(IllegalArgumentException.class, ()-> {st.CreaLivello("12345678", "8 caretteri di codice")
			;
		});
		assertThrows(IllegalArgumentException.class, ()-> {st.CreaLivello("qwe", "leg")
			;
		});
		assertThrows(IllegalArgumentException.class, ()-> {st.CreaLivello("123456", "errore")
			;
		});
		
	}
		
	@Test
	public void TestRitornaPadre() {	
		assertTrue(Categoria.RitornaPadre("qwertyuio").equals("qwerty"));
		assertTrue(Categoria.RitornaPadre("qwerty").equals("qwe"));
		assertTrue(Categoria.RitornaPadre("qwe")== null);
		assertFalse(Categoria.RitornaPadre("qwertyuio").equals("qwertsy"));

	}
	
	@Test
	public void TestgetCategoriaByCodice() {	
		Struttura st= Seed();
		st.GetCategoriaByCodice("qwe");
		assertTrue(st.GetCategoriaByCodice("qwe")!= null);

	}

}
