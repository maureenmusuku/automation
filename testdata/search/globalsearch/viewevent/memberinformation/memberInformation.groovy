page(name: 'Member Information Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage

        section('Add Contact') {
            eventId '56824'
            eventStatus 'Open'
            address1 'Auto Test Address 1'
            address2 'Auto Test Address 2'
            homePhone '2222222222'
            description 'test description'
            note ' test note'
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


}

