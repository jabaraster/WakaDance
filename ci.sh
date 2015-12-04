#!/bin/sh

export PROJECT_DIR=/Users/jabaraster/Documents/Develop/Java/workspace/projects
export GLASSFISH_PATH=/Applications/GlassFish/glassfish-4.1.1-full
cd $PROJECT_DIR/WakaDance
mvn package -DskipTests=true
cd $GLASSFISH_PATH/bin
./asadmin --host wakadance.jabara.info -W pass.txt deploy --force=true --contextroot=/ --name=WakaDance --precompilejsp $PROJECT_DIR/WakaDance/target/WakaDance.war
cd $PROJECT_DIR/WakaDance
