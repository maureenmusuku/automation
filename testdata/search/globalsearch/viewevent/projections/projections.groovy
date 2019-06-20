page(name: 'Projections Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage

        section('Info') {
            eventId '56824'
        }
        section('Add New Projections') {
            agreementWith '08-10-2018'
        }
        section('Search Activity') {
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
        section('Info') {
            eventId '56824'
        }
        section('Add New Projections') {
            agreementWith '08-10-2018'
        }
        section('Search Activity') {
            dateTo '09-10-2018'
            activityType 'Event Update'
            activitySource 'Sumit Shrestha'

        }


    }


}

