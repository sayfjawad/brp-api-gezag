# BRP-API Gezag

Gezag bestaat uit een interface en gezagsmodule voor het kunnen bevragen van de gezagsrelaties van personen.
De module kan worden bevraagd met een lijst van bsns, waarop de gezagsmodule de gezagsrelaties voor de opgegeven personen bepaald en teruggeeft. 

```
## Getting Started
Je kunt de Gezag applicatie lokaal opstarten via dit command:
```
./gradlew bootRun
```
Je kan ook direct de applicatie opstarten met Java via `GezagApplication`.
De gezagmodule kan lokaal worden geconfigureerd in `src/main/resources/developer.yml`.

Om de module lokaal te kunnen draaien moet de database verbinding worden geconfigureerd.
Zonder verbinding met de database, werkt de applicatie niet.

TODO: link naar opzetten database instructies

### Gezag bevragen

De URL van de gezagmodule is:
```
http://{host}:{port}/api/v1/opvragenBevoegdheidTotGezag
```

## IDE Setup

In dit project worden sources gegenereerd met de Apache cxf-codegen-plugin. Daarom moet je target/generated-sources nog als source folder/generated sources markeren in je IDE. Bijvoorbeeld in IntelliJ:

![Generated sources als zodanig in IDE markeren](images/MarkGeneratedSourcesAsSuch.png)

## Prerequisites
- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21) of later
- Gradle - 8  of later

## Deployment

De gezagmodule wordt door Github CI pipelines gebouwd en 

## Testen

De gezagmodule heeft twee test suites beschikbaar

### (unit) code testen

In de map `src/main/testen` zijn testen opgenomen op de applicatie technisch test

### Cucumber testen

In de map `features` zijn gherkin testen opgenomen die de applicatie functioneel testen.
De features kunnen worden uitgevoerd met Cucumber, met bijvoorbeeld: `npx cucumber-js features/S-en-T-test`

TODO: link naar congifureren van cucumber en andere informatie over gherkin testen

## Docs
Verdere documentatie is te vinden in het Software Architectuur project in Github:
- ![Software Architectuur](https://github.com/BRP-API/gezagsmodule-documentatie)
