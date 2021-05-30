#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar media-services-0.0.1-SNAPSHOT.jar
