environments(default: 'CORE_QA') { // the name of environment
    // against which test is run by default
    //the default environment to be used. Can be overridden
    // with System property environment eg mvn test -Denvironment='SUBRO_POINT-SIT'

    /**
     * Default Values for all environment
     * Can be overridden for each environment individually
     * Also all values can be overidden using system property, eg mvn test -Denvironment='SUBRO_POINT-SIT' -DrunOnGrid=false
     */
    defaultValues {
        appUrl 'https://qa.advalent.com'
        runOnGrid 'false'
        hubUrl '<%hub_url%>'
        browser 'CHROME'
        waitTime '1000'
        skipTestThatRequireDatabase true
    }

    // Environment to be used for development
    environment(name: 'SUBRO_POINT_DEV') {
        appUrl 'https://qa.advalent.com'
        product 'CORE'
        stage 'DEV'
        authConfig 'Auth0'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }
    // Environment to be used for QA(SIT)
    environment(name: 'QA') {
        appUrl 'https://frgtest.advalent.com'
        product 'SUBRO_POINT'
        stage 'SIT'
        authConfig 'Auth0'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }

    environment(name: 'QACARE') {
        appUrl 'https://qacare.advalent.com'
        product 'CARE'
        stage 'QA'

        authConfig 'Local'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }

    environment(name: 'CARE_DEMO') {
        appUrl 'https://demo.advalent.com'
        product 'SUBRO_POINT'
        authConfig 'Local'
        stage 'DEV'

        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }
    environment(name: 'NOVUSTEST') {
        appUrl 'https://novustest.advalent.com'
        product 'CARE'
        authConfig 'LOCAL'
        stage 'DEV'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }
    // Environment to be used for PROD QA
    environment(name: 'PROD_QA') {
        appUrl '<%PROD QA URL%>'
        product 'SUBRO_POINT'
        stage 'DEV'
        authConfig 'Auth0'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }

    environment(name: 'CORE_QA') {
        appUrl 'https://qa.advalent.com'
        product 'CORE'
        stage 'QA'
        authConfig 'Auth0'
        db_url '<%DB URL%>'
        db_name '<%DB NAME%>'
        db_user '<%DB USER%>'
        db_password '<%DB PASSWORD%>'
    }
}