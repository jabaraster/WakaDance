#!/bin/sh

export PROJECT_DIR=/Users/jabaraster/Documents/Develop/Java/workspace/projects
export GLASSFISH_PATH=/Applications/GlassFish/glassfish-4.1.1-full/glassfish

cd $PROJECT_DIR/waka-dance-lib
mvn dependency:copy-dependencies
mvn package -DskipTests=true
cp target/waka-dance-lib-1.0-SNAPSHOT.jar $GLASSFISH_PATH/domains/domain1/lib/ext/
cp target/dependency/*.jar $GLASSFISH_PATH/domains/domain1/lib/ext/
cd $PROJECT_DIR/WakaDance
mvn package -DskipTests=true
cd $GLASSFISH_PATH/bin
./asadmin deploy --force=true --contextroot=/ --name=WakaDance --precompilejsp $PROJECT_DIR/WakaDance/target/WakaDance.war
./asadmin restart-domain
cd $PROJECT_DIR/WakaDance
