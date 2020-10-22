#!/bin/bash

rm -rf output/*
hadoop fs -copyToLocal output
