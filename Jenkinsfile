pipeline
{
	agent any
	environment 
	{
        KUBECONFIG = '/var/lib/jenkins/.kube/config'
        
    }
	stages
	{
		stage('Code Checkout')
		{
			steps
			{
				git 'https://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@github.com/SumangalaBhat/Edureka_Certificate_Repo.git'
			}
		}
		
		stage('Code Compile')
		{
			steps
			{
				sh 'mvn compile'
			}
		}

		stage('Test')
		{
			steps
			{
				sh 'mvn test'
			}
		}

		stage('Build')
		{
			steps
			{
				sh 'mvn package'
			}
		}


		stage('Build Docker Image')
		{
			steps
			{
			    sh 'cp /var/lib/jenkins/workspace/$JOB_NAME/target/ABCtechnologies-1.0.war /var/lib/jenkins/workspace/$JOB_NAME/ABCtechnologies-1.0.war'
				
                sh 'docker build -t sumabhat/abc_technologies:$BUILD_NUMBER .'

			}
		}

		stage('Push Docker Image')
		{ 
			steps
			{   
			    withDockerRegistry([credentialsId: "Dockerhub_sumabhat", url:"https://index.docker.io/v1/"])
			    {   
			       sh 'docker push sumabhat/abc_technologies:$BUILD_NUMBER'
				   
			    } 
			}
		}

		stage('Deploy as container')
		{
			steps
			{
				sh 'docker run -itd -P sumabhat/abc_technologies:$BUILD_NUMBER'
			}
		}
		stage('Deploy to kubernetes')
		{
			steps
			{
				sh 'kubectl apply -f abc-deployment.yaml'
				sh 'kubectl apply -f service.yaml'
			}
		}
   }
}
