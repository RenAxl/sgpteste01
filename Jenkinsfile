pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk'
        PATH = "${env.PATH}:${env.JAVA_HOME}/bin"
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📥 Fazendo checkout do código...'
                git branch: 'main', url: 'https://github.com/RenAxl/sgp-authuser.git'
            }
        }

        stage('Build & Package') {
            steps {
                echo '🔨 Iniciando o Build e Empacotamento...'
                sh 'bash mvnw clean install'
                sh './mvnw clean install'
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
