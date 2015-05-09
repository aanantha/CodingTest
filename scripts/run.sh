#!/bin/bash
PROJECT_HOME=/home/bitnami/apps/phpmyadmin/htdocs/testfolder/CodingTest
echo $PROJECT_HOME
JAVA_HOME=/usr/bin
echo $JAVA_HOME
CLASSPATH="$PROJECT_HOME/classes:$PROJECT_HOME/lib/*:"
echo $CLASSPATH
java -cp $CLASSPATH  -mx320m -ms100m com.synqq.client.SynqqClient
