#!/bin/bash
# Grabs and kill a process from the pidlist that has the word myapp

kill `ps -ef | grep standalone.jar | grep -v grep | awk '{ print $2 }'`

echo "Killing Balero CMS Enterprise :-("
