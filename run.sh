#!/usr/bin/env sh


mvn package; java -cp ./target/webpet-1.0-SNAPSHOT.jar com.webpet.App
