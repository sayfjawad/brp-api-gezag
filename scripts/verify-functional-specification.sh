#!/bin/bash

PARAMS="{ \
    \"apiUrl\": \"http://localhost:8080/api/v1/opvragenBevoegdheidTotGezag\" \
}"

npx cucumber-js -f json:./test-reports/cucumber-js/gezag/test-result.json \
                -f summary:./test-reports/cucumber-js/gezag/test-result-summary.txt \
                -f summary \
                features/gezag-minderjarige \
                --tags "not @skip-verify" \
                --world-parameters "$PARAMS"
