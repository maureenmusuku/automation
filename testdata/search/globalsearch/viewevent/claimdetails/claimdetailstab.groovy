page(name: 'Member Information Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage
        section('Info') {
            eventId '56824'
            columns 'Details', 'Service Date', 'Claim Num.', 'Trauma', 'Claim Description', 'Provider', 'Billed Amount', 'Paid Amount', 'Paid Date', 'Load Date'
        }

        section('Injury Desc') {
            injuryDescription 'Auto Test injury description'
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

    }


}

