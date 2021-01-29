#!/bin/bash
set -ev
cd eu.brain.iot.ros.edge.node

export MAVEN_CLI_OPTS='-s ./.m2/settings.xml -B'
echo $MAVEN_CLI_OPTS

cat ~/.m2/settings.xml

#cp .m2/settings.xml ~/.m2

mvn initialize
mvn $MAVEN_CLI_OPTS verify
mvn $MAVEN_CLI_OPTS $MAVEN_DEPLOY_OPTS clean package org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy

#new-UC
