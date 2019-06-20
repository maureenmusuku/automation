navigationPanels() {
    product('SUBRO_POINT') {// name of product
        module(name: 'Client Config', linkId: 'Client Config') {
            landingPage(name: 'Clients', linkId: 'Clients', pageTitle: 'Client Search')
        }
        module(name: 'Search', linkId: 'Search') {
            landingPage(name: 'Global Search', linkId: 'Global Search', pageTitle: 'Global Search')
            landingPage(name: 'Incident/Referral', linkId: 'Incident/Referral', pageTitle: 'Incident/Referral Search')
        }
    }
}