#!/bin/bash

mvn package
hadoop fs -rm -r output
