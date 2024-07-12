#!/bin/bash

kafka-topics --bootstrap-server kafka-broker:29092 --create --if-not-exists --topic "input-topic" --replication-factor 1 --partitions 3
kafka-topics --bootstrap-server kafka-broker:29092 --create --if-not-exists --topic "output-topic" --replication-factor 1 --partitions 3
