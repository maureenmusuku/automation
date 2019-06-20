page(name: 'Global Search') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage
        section('Event_Incident Search') {// section
            eventId '56824'
            incidentId 'REF000022'
            eventStatus 'Open'
            eventOwner 'Manish'
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientID 'PID12399'
            patientDOB '12-13-1989'
            patientDOBVer '12/13/89'
            majorClient 'Test Major Client 01'
            client 'Test Client 01'
            employerGroup 'Test C01BG01 EG 01'
        }

        section('Member Search') {// section
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientState 'AA'
            client 'Test Client 01'
            patientID 'PID12399'
            gender 'Male'
            employerGroup 'Test C01BG01 EG 01'
            patientDOB '12-13-1989'
            majorClient 'United Health Group'
            lineOfBusiness 'Medicare'
        }
        section('New Incident') {// section
            majorClient 'United Health Group'
            investigationSource 'Client Letter'
            eventType 'ASSAULT'
            client 'Test Client 01'
            lossDate '12-03-2018'
            lossDetails 'Auto Test Loss Details'
            injuryDescription 'Auto Test Injury Description'
            patientFirstName 'Auto'
            patientMiddleName 'A'
            patientLastName 'Test'
            suffix 'Mr'
            partyRole '1P INDIVIDUAL'
            partyFirstName 'PartyAuto'
            partyMiddleName 'PartyA'
            partyLastName 'PartyTest'
        }
        section('Add') {
//            Incident_ReferralFrom ''
        }
        section('View Event') {
//            Incident_ReferralFrom ''
            majorClient 'United Health Group'
        }
    }
    environment(name: 'PROD_QA') {
        section('GlobalSearchPage') {
            eventId '56824'
            incidentId 'REF000022'
            eventStatus 'Open'
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientID 'PID12399'
            patientDOB '12-13-1989'
            patientDOBVer '12/13/89'
            majorClient 'Test Major Client 01'
            client 'Test Client 01'
            employerGroup 'Test C01BG01 EG 01'
        }
        section('ViewEventPage') {
//            Incident_ReferralFrom ''
        }
    }
    environment(name: 'POJ') {// environment or stage
        section('Search') {// section
            eventId '36587'
            incidentId 'REF000022'
            eventStatus 'Open'
            eventOwner 'Manish'
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientID 'PID12399'
            patientDOB '12-13-1989'
            patientDOBVer '12/13/89'
            majorClient 'Test Major Client 01'
            client 'Test Client 01'
            employerGroup 'Test C01BG01 EG 01'
        }
        section('View Event') {
//            Incident_ReferralFrom ''
            mojorClient 'United Health Group'
        }
    }


}

