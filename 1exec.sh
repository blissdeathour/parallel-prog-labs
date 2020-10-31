#!/bin/bash

mvn package
hadoop fs -rm -r output
export HADOOP_CLASSPATH=target/parallel-prog-labs-1.0-SNAPSHOT.jar
