#!/bin/bash

root=$(pwd)
cd "$root"/handyman && ./gradlew bootJar && docker build -t handyman .
cd "$root"/landscape && ./gradlew bootJar && docker build -t landscape .
cd "$root"/rancher && ./gradlew bootJar && docker build -t rancher .