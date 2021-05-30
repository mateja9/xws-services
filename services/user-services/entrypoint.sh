#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar user-services-0.0.1-SNAPSHOT.jar
