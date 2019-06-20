page(name: 'Task Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage

        section('Add') {
            title 'Task Note'
            assignedToUser 'Manish'
            taskType 'Follow-Up'
            description 'test description'
            note ' test note'
        }

        section('Update') {
            title 'Task Note Updated'
            assignedToUser 'Manis '
            taskType 'Follow-Up'
            description 'test description Updated'
            note 'test note Updated'
        }


    }
    environment(name: 'Task Tab') {
        section('Add') {

        }

    }


}

