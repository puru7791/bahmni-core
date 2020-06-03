pipeline{
    agent any
    stages{
        stage('build'){
            agent{label 'bahmnicore'}
            steps{
                git 'https://github.com/puru7791/bahmni-core.git'
                sh 'mvn clean package'
                stash name: 'bahmni-jar', includes: '**/target/*.jar'
            }
        }
        stage('deploy'){
            agent{label 'bahmnidev'}
            steps{
                unstash 'bahmni-jar'
                git 'https://github.com/puru7791/ansible-deploy.git'
                sh 'ansible-playook -i /home/ansible/inventory bahmni.yaml'
            }
        }
        stage('Terraform'){
            agent{label 'bahmniinfra'}
            steps{
                sh 'terraform init .' && 'terraform apply . -auto-approve'
            }
        }
    }
}