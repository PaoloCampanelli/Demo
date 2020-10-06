package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Una classe che implementa questa interfaccia deve avere i metodi per gestire le statistiche
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public interface IGetStatistiche<T> {

    List<T> GetMovimentiByDates(Date dataInizio, Date dataFine);
    List<T> GetMovimentiByDatesAndCategory(Date dataInizio, Date dataFine, String codice);
    List<T> GetMovimentiByDatesAndTag(Date dataInizio, Date dataFine, String tag);
    T GetMovimentoById(int id);
    BigDecimal GetSaldoAttuale();
    BigDecimal GetSaldoByDate(Date data);
}
