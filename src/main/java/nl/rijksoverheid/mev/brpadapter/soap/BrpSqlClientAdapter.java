package nl.rijksoverheid.mev.brpadapter.soap;

import nl.rijksoverheid.mev.brp.PersoonslijstFinder;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Profile("real-brp")
public class BrpSqlClientAdapter implements BrpClient {

    private final PersoonslijstFinder persoonslijstFinder;

    public BrpSqlClientAdapter(PersoonslijstFinder persoonslijstFinder) {
        this.persoonslijstFinder = persoonslijstFinder;
    }

    @Override
    public Persoonslijst opvragenPersoonslijst(String bsn, Transaction transaction) {
        var burgerservicenummer = new Burgerservicenummer(Long.parseLong(bsn));
        return persoonslijstFinder.findPersoonslijst(burgerservicenummer)
            .orElse(null);
    }
}
