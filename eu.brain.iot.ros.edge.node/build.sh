#!/bin/bash
set -ev
cd eu.brain.iot.ros.edge.node

export MAVEN_CLI_OPTS='-s .m2/settings.xml -B'
echo $MAVEN_CLI_OPTS

export MAVEN_DEPLOY_OPTS='-DaltReleaseDeploymentRepository=brain-iot-releases::default::https://nexus.repository-pert.ismb.it/repository/maven-releases -DaltSnapshotDeploymentRepository=brain-iot-snapshots::default::https://nexus.repository-pert.ismb.it/repository/maven-snapshots'
echo $MAVEN_DEPLOY_OPTS

#cd eu.brain.iot.ros.edge.node

mvn initialize
mvn $MAVEN_CLI_OPTS verify
mvn $MAVEN_CLI_OPTS $MAVEN_DEPLOY_OPTS clean package org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy

#new-UC
