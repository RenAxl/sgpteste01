pipeline {
    agent any

environment {
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${PATH}" // Inclua o Java no PATH
    }

    stages {

        stage('Clean Workspace') {
            steps {
                echo '🧹 Limpando workspace...'
                cleanWs()
    }
}
        stage('Checkout') {
            steps {
                echo '📥 Fazendo checkout do código...'
                git branch: 'main', url: 'https://github.com/RenAxl/sgpteste01.git'
            }
        }

       stage('Build & Package') {
    steps {
        script {
            echo "🔨 Iniciando o Build e Empacotamento..."
            sh 'pwd'
            sh 'ls -la'
            sh 'chmod +x mvnw' // Garantir permissões
            sh './mvnw clean install' // Executar Maven Wrapper
        }
    }
}

        stage('Verify Artifact') {
            steps {
                echo '🧐 Verificando se o artefato foi gerado...'
                sh '''
                if [ -f target/*.jar ]; then
                    echo "✅ Artefato JAR encontrado!"
                else
                    echo "❌ Artefato JAR não encontrado! Verifique logs do build."
                    exit 1
                fi
                '''
            }
        }

        stage('Clean EC2 Directory') {
            steps {
                sshagent(['sgp-ec2-key']) { // Carrega as credenciais SSH configuradas no Jenkins
                    echo '🧹 Limpando diretório ~/sgp na instância EC2...'
                    sh '''
                    ssh -o StrictHostKeyChecking=no ubuntu@54.221.141.59 "rm -rf ~/sgp/*"
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['sgp-ec2-key']) { // Carrega as credenciais SSH configuradas no Jenkins
                    script {
                        // Caminho para o arquivo JAR no servidor Jenkins (WSL)
                        def jarFilePath = '/var/lib/jenkins/workspace/teste03/target/sgp-authuser-0.0.1-SNAPSHOT.jar'
                        // Caminho no destino na instância EC2
                        def ec2DestinationPath = 'ubuntu@54.221.141.59:~/sgp/'
        
                        // Comando SCP para transferência do arquivo JAR
                        sh """
                            scp -o StrictHostKeyChecking=no ${jarFilePath} ${ec2DestinationPath}
                        """
                    }
                }
            }
        }


        stage('Deploy') {
            steps {
                echo '🚀 Deploy Finalizado. Artefato disponível no diretório target.'
            }
        }
    }

    post {
        success {
            echo '🎉 Build finalizado com sucesso!'
        }
        failure {
            echo '🚨 Erro durante o Build! Verifique os logs no Jenkins.'
        }
    }
}
