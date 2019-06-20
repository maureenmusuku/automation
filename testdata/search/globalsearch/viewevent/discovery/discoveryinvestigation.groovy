page(name: 'Discovery Investigation Page') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage

        section('Discovery Investigation') {
            callerType 'Patient'
            callerName 'Automation Test Name'
            callerNumber '1234567890'
            eventType 'DOGBITE'
            lossDate '10-10-2018'
            investigationSource 'Attorney Call'
            lossDetails 'Automation Test Loss Details'
            injuryDescription 'Automation Test Injury Description'
        }

        section('Update') {
            title 'Task Note Updated'
            assignedToUser 'Manis '
            city 'Follow-Up'
            description 'test description Updated'
            note 'test note Updated'
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

