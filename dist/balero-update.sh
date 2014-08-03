#!/bin/bash

# Update Balero CMS .jar

#--------------------------------------------
# the .jar file is simply downloaded to the .folder
echo "Updating..."
echo "Killing Balero CMS Enterprise Service..."
kill `ps -ef | grep standalone.jar | grep -v grep | awk '{ print $2 }'`
rm -rf .extract
rm rf standalone.jar
echo -ne Downloading Balero CMS Latest Version standalone.jar
dots
wget -q http://www.balerocms.com/dist/updates/latest/standalone.jar
echo "Downloaded."
bash ./balero-start.sh
done