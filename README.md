# Gezag - TODO: update naar haalcentraal situatie

Gezag bestaat uit: 
- BDV -> voor bepalen van bevoegdheid
- GM-API -> voor opvragen gezag
-> Gezagsmodule -> voor bepalen gezag
-> BRP adapter -> voor communicatie met BRP

```
## Getting Started
Je kunt de Gezag applicatie lokaal opstarten via dit command:
```
mvn spring-boot:run -Dspring-boot.run.profiles=it-test
```
Je kan ook direct de applicatie opstarten met Java via `GezagApplication`, zorg er dan wel voor dat je start met bovenstaand Spring profiel en dat de working directory staat ingesteld op de project root directory.

### Gezag op de ontwikkel of pre-productie omgeving bevragen

De interne URL van de Gm-API van Gezag op de ontwikkel omgeving is:
```
https://gezag.bzk-mev-ont.test4.s15m.nl/api/v1/
```

De interne URL van de BDV-API van Gezag op de ontwikkel omgeving is:
```
https://gezag.bzk-mev-ont.test4.s15m.nl/bdv/api/ouderlijkgezag/v1/
```

De interne URL van de Gm-API van Gezag op de pre-prod omgeving is:
```
https://gezag.bzk-mev-pre.prod4.s15m.nl/api/v1/
```

De publieke URL van de Gm-API van Gezag op de pre-prod omgeving is:
```
https://ca-pre.logius.nl/mev/gezag-module/1.0/api/v1/
```

De interne URL van de BDV-API van Gezag op de pre-prod omgeving is:
```
https://gezag.bzk-mev-pre.prod4.s15m.nl/bdv/api/ouderlijkgezag/v1/
```

## IDE Setup

In dit project worden sources gegenereerd met de Apache cxf-codegen-plugin. Daarom moet je target/generated-sources nog als source folder/generated sources markeren in je IDE. Bijvoorbeeld in IntelliJ:

![Generated sources als zodanig in IDE markeren](images/MarkGeneratedSourcesAsSuch.png)

## Prerequisites
- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) of later
- Maven -  3.8.  of later

## Gezagsmodule

### Aansluitinstructies voor functioneel beheerders

Om een nieuwe afnemer toegang te verlenen tot de Gezagsmodule API dient er een API key voor ze gegenereerd te worden via
de WSO2 API Manager en **de afnemers dienen zelf een PKIO G1-certificaat aan te vragen**.

De informatie die de afnemer nodig heeft staat hieronder in [](#Aansluitinstructies voor afnemers)

#### WSO2 API Manager

Er zijn voor Pre-Productie en Productie aparte WSO2 API Managers beschikbaar waarop de beheerders toegang hebben. Deze
zijn alleen beschikbaar binnen de DWR omgeving.

- [WSO2 API Manager Pre-Productie](https://ca-devportal-pre.logius.nl/devportal/apis)
- [WSO2 API Manager Productie](https://ca-devportal.logius.nl/devportal/apis)

Log in op de desbetreffende omgeving om de API Key aan te kunnen maken.

## Docs
Verdere documentatie is te vinden in het Software Architectuur project in GitLab:
- ![Software Architectuur](https://gitlab.cicd.s15m.nl/bzk/mev/gezag_documentatie)
