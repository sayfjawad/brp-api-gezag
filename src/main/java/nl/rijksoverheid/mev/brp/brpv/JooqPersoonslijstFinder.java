package nl.rijksoverheid.mev.brp.brpv;

import nl.rijksoverheid.mev.brp.PersoonslijstFinder;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.Lo3PlGezagsverhouding;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.Lo3PlVerblijfplaats;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlVerblijfplaatsRecord;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;
import nl.rijksoverheid.mev.logging.LoggingContext;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static nl.rijksoverheid.mev.brp.brpv.generated.tables.Lo3Pl.LO3_PL;
import static nl.rijksoverheid.mev.brp.brpv.generated.tables.Lo3PlPersoon.LO3_PL_PERSOON;

@Repository
@Transactional(readOnly = true)
public class JooqPersoonslijstFinder implements PersoonslijstFinder {

    private static final String PERSOON = "P";
    private static final String OUDER_1 = "1";
    private static final String OUDER_2 = "2";
    private static final String KIND = "K";
    private static final String RELATIE = "R";

    private final Clock clock;
    private final DSLContext create;
    private final LoggingContext loggingContext;

    public JooqPersoonslijstFinder(Clock clock, DSLContext create, LoggingContext loggingContext) {
        this.clock = clock;
        this.create = create;
        this.loggingContext = loggingContext;
    }

    @Override
    public Optional<Persoonslijst> findPersoonslijst(Burgerservicenummer burgerservicenummer) {
        var plPersoonPersoon = findPlPersoonByPersoonTypeIsPersoonAndBurgerservicenummer(burgerservicenummer);
        if (plPersoonPersoon.isEmpty()) return Optional.empty();

        var result = new Persoonslijst(clock);

        var plPersoonPersoonRecent = plPersoonPersoon.getFirst();
        result.addPersoon(plPersoonPersoonRecent);
        plPersoonPersoon.stream()
            .skip(1)
            .forEach(result::addPersoonGeschiedenis);

        var plId = plPersoonPersoonRecent.getPlId();
        loggingContext.addPlIdIfBurgerservicenummerMatches(plId, burgerservicenummer);

        var inschrijvingen = findInschrijvingen(plId);
        inschrijvingen.forEach(result::addInschrijving);

        var plPersoonByPersoonType = findPlPersoonByPersoonTypeNotPersoonAndPlId(plId);

        var plPersoonOuder1 = plPersoonByPersoonType.getOrDefault(OUDER_1, Collections.emptyList());
        if (!plPersoonOuder1.isEmpty()) {
            var plPersoonOuder1Recent = plPersoonOuder1.getFirst();
            result.addOuder1(plPersoonOuder1Recent);
            plPersoonOuder1.stream()
                .skip(1)
                .forEach(result::addOuder1Geschiedenis);
        }

        var plPersoonOuder2 = plPersoonByPersoonType.getOrDefault(OUDER_2, Collections.emptyList());
        if (!plPersoonOuder2.isEmpty()) {
            var plPersoonOuder2Recent = plPersoonOuder2.getFirst();
            result.addOuder2(plPersoonOuder2Recent);
            plPersoonOuder2.stream()
                .skip(1)
                .forEach(result::addOuder2Geschiedenis);
        }

        var plPersoonKinderenMetGeschiedenis = plPersoonByPersoonType.getOrDefault(KIND, Collections.emptyList());
        var plPersoonKinderenZonderGeschiedenis = plPersoonKinderenMetGeschiedenis.stream()
            .filter(it -> it.getVolgNr() == (short) 0);
        plPersoonKinderenZonderGeschiedenis.forEach(result::addKind);

        var plPersoonRelaties = plPersoonByPersoonType.getOrDefault(RELATIE, Collections.emptyList());
        plPersoonRelaties.forEach(result::addRelatie);

        var optionalPlVerblijfplaats = findPlVerblijfplaatsByPlId(plId);
        optionalPlVerblijfplaats.ifPresent(result::addVerblijfplaats);

        var optionalPlGezagsverhouding = findPlGezagsverhoudingByPlId(plId);
        optionalPlGezagsverhouding.ifPresent(result::addGezagsverhouding);

        return Optional.of(result);
    }

    private List<Lo3PlRecord> findInschrijvingen(final long plId) {
        return create.selectFrom(LO3_PL)
            .where(LO3_PL.PL_ID.equal(plId))
            .fetch()
            .stream()
            .toList();
    }

    private List<Lo3PlPersoonRecord> findPlPersoonByPersoonTypeIsPersoonAndBurgerservicenummer(
        Burgerservicenummer burgerservicenummer
    ) {
        return create.selectFrom(LO3_PL_PERSOON)
            .where(LO3_PL_PERSOON.BURGER_SERVICE_NR.equal(burgerservicenummer.value())
                .and(LO3_PL_PERSOON.PERSOON_TYPE.equal(PERSOON))
                .and(LO3_PL_PERSOON.ONJUIST_IND.isNull())
            )
            .orderBy(LO3_PL_PERSOON.STAPEL_NR.asc(), LO3_PL_PERSOON.VOLG_NR.asc())
            .fetch()
            .stream()
            .toList();
    }

    private Map<String, List<Lo3PlPersoonRecord>> findPlPersoonByPersoonTypeNotPersoonAndPlId(long plId) {
        return create.selectFrom(LO3_PL_PERSOON)
            .where(LO3_PL_PERSOON.PL_ID.equal(plId)
                .and(LO3_PL_PERSOON.PERSOON_TYPE.notEqual(PERSOON))
                .and(LO3_PL_PERSOON.ONJUIST_IND.isNull())
            )
            .orderBy(LO3_PL_PERSOON.PERSOON_TYPE.asc(), LO3_PL_PERSOON.STAPEL_NR.desc(), LO3_PL_PERSOON.VOLG_NR.asc())
            .fetch()
            .stream()
            .collect(Collectors.groupingBy(Lo3PlPersoonRecord::getPersoonType));
    }

    private Optional<Lo3PlVerblijfplaatsRecord> findPlVerblijfplaatsByPlId(long plId) {
        return create.selectFrom(Lo3PlVerblijfplaats.LO3_PL_VERBLIJFPLAATS)
            .where(Lo3PlVerblijfplaats.LO3_PL_VERBLIJFPLAATS.PL_ID.equal(plId)
                .and(Lo3PlVerblijfplaats.LO3_PL_VERBLIJFPLAATS.VOLG_NR.equal((short) 0))
                .and(Lo3PlVerblijfplaats.LO3_PL_VERBLIJFPLAATS.ONJUIST_IND.isNull())
            )
            .fetchOptionalInto(Lo3PlVerblijfplaatsRecord.class);
    }

    private Optional<Lo3PlGezagsverhoudingRecord> findPlGezagsverhoudingByPlId(long plId) {
        return create.selectFrom(Lo3PlGezagsverhouding.LO3_PL_GEZAGSVERHOUDING)
            .where(Lo3PlGezagsverhouding.LO3_PL_GEZAGSVERHOUDING.PL_ID.equal(plId)
                .and(Lo3PlGezagsverhouding.LO3_PL_GEZAGSVERHOUDING.VOLG_NR.equal((short) 0))
                .and(Lo3PlGezagsverhouding.LO3_PL_GEZAGSVERHOUDING.ONJUIST_IND.isNull())
            )
            .fetchOptionalInto(Lo3PlGezagsverhoudingRecord.class);
    }
}
