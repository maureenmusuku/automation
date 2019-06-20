users {
    /**
     * User's credentials placed under corrosponding product and stage
     * */
    product(name: 'CORE') {

        stage(name: 'SIT') {
            user(automationId: "SUBRO SIT USER") {
                loginName "sshrestha"
                email "sshrestha@advalent.com"
                emailPassword "Never5Try3It2"
                password "EQN@1601"
                role['user']
            }

        }

        stage(name: 'QA') {
            user(automationId: "PLAN ADMIN") {
                loginName "planadmin"
                password "AdvDemo"
                email "sshrestha@advalent.com"
                emailPassword "Never5Try3It2"
                role['user']
            }

        }
        stage(name: 'DEV') {
            user(automationId: "SUBRO DEV USER") {
                loginName "enrolluser"
                email "sshrestha@advalent.com"
                emailPassword "Never5Try3It2"
                password "demclient"
                role['user']
            }
        }
    }

    product(name: 'CARE') {
        stage(name: 'SIT') {
            user(automationId: "CARE_QA") {
                loginName "careadmin"
                password "demclient"
                email "sshrestha@advalent.com"
                emailPassword "Never5Try3It2"
                role['user']
            }
        }
        stage(name: 'DEV') {
            user(automationId: "CARE_DEV") {
                loginName "careadmin"
                password "demclient"
                email "sshrestha@advalent.com"
                emailPassword "Never5Try3It2"
                role['user']
            }
        }
    }

}

