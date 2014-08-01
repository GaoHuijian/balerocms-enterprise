#! /bin/sh
# Support Multi-Websites

# If port 8080 are in use change this
port=8080
# Print
echo "Starting Balero CMS Enterprise Service... on http://localhost:$port/"
# Run in background
nohup nice java -jar standalone.jar -httpPort=$port 2>&1