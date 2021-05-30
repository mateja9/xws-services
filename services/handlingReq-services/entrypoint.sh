#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar handlingReq-services-0.0.1-SNAPSHOT.jar
