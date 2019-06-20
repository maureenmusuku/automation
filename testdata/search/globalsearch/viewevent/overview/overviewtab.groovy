page(name: 'Overview Tab') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage
        section('Reassign Event Ownership') {
            effectiveAsOf ''
            newDepartment 'Screening'
            newEventOwner 'Surya'
            newEventSupervisor ''
            reassignmentNote 'reassigned by automation test'
        }

    }
    environment(name: 'Task Tab') {
        section('Add') {
        }
    }
    environment(name: 'POJ') {// environment or stage
        section('Reassign Event Ownership') {
            effectiveAsOf ''
            newDepartment 'Screening'
            newEventOwner 'Surya'
            newEventSupervisor ''
            reassignmentNote 'reassigned by automation test'
        }

    }
}

