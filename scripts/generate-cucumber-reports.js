const report = require('multiple-cucumber-html-reporter');

const customData = {
    title: 'BRP API',
    data: [
        { label: 'Applicatie', value: 'Gezag Service' },
        { label: 'Versie', value: `${process.argv[2]}` },
        { label: 'Build', value: `${process.argv[3]}`},
        { label: 'Branch', value: `${process.argv[4]}`}
    ]
};
report.generate({
    jsonDir: 'test-reports/cucumber-js/step-definitions',
    reportPath: 'test-reports/cucumber-js/reports/step-definitions',
    reportName: 'Stap definities tests',
    hideMetadata: true
});

report.generate({
    jsonDir: 'test-reports/cucumber-js/gezag',
    reportPath: 'test-reports/cucumber-js/reports/gezag/gezag-service',
    reportName: 'Gezag service features',
    hideMetadata: true,
    customData: customData
});