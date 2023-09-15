#!/usr/bin/env sh
mvn clean; mvn package; mvn exec:java -Dexec.mainClass="com.webpet.App"
