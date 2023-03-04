#!/bin/bash

root=$(pwd)
cd "$root"/handyman && ./gradlew bootJar
cd "$root"/landscape && ./gradlew bootJar
cd "$root"/rancher && ./gradlew bootJar