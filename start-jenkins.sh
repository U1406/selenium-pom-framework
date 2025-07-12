#!/bin/bash

echo "🚀 Starting Jenkins with Selenium Grid..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Start the services
echo "📦 Starting Jenkins and Selenium Grid containers..."
docker-compose up -d

# Wait for Jenkins to start
echo "⏳ Waiting for Jenkins to start up..."
sleep 30

# Get Jenkins initial admin password
echo "🔑 Getting Jenkins initial admin password..."
JENKINS_PASSWORD=$(docker exec selenium-jenkins cat /var/jenkins_home/secrets/initialAdminPassword 2>/dev/null)

if [ ! -z "$JENKINS_PASSWORD" ]; then
    echo "==============================================="
    echo "🎉 Jenkins is starting up!"
    echo "==============================================="
    echo "📍 Jenkins URL: http://localhost:8080"
    echo "🔑 Initial Admin Password: $JENKINS_PASSWORD"
    echo "🕸️  Selenium Grid: http://localhost:4444"
    echo "==============================================="
    echo ""
    echo "📋 Next Steps:"
    echo "1. Open http://localhost:8080 in your browser"
    echo "2. Use the password above for initial setup"
    echo "3. Install suggested plugins"
    echo "4. Create your admin user"
    echo "5. Follow jenkins-setup.md for job configuration"
    echo "==============================================="
else
    echo "⚠️  Jenkins might still be starting up..."
    echo "📍 URL: http://localhost:8080"
    echo "🔍 To get password later: docker exec selenium-jenkins cat /var/jenkins_home/secrets/initialAdminPassword"
fi

echo ""
echo "🛑 To stop: docker-compose down"
echo "📊 To view logs: docker-compose logs -f jenkins"
