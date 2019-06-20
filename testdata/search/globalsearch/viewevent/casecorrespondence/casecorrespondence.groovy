page(name: 'Case Correspondence Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage

        section('Add New Communication') {
            title 'Activity Log Title Automation Test Input'
            activityNote 'Activity Log Note Automation Test Input'
            correspondence  'FIN–Finance - Check Endorsement Cover'
            correspondenceUpdate  'FIN–Finance - Refund Check'
        }
        section('Search Activity') {
            dateFrom '08-10-2018'
            dateTo '09-10-2018'
            activityType 'Event Update'
            activitySource 'Sumit Shrestha'

        }


    }
    environment(name: 'Task Tab') {
        section('Add') {

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

