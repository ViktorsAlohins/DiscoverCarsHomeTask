#!/bin/bash

# set the maven version to use
MAVEN_VERSION=3.5.2

# check if maven is already installed
if [ ! -d ".maven-dist/maven-$MAVEN_VERSION" ]; then
  # download and install maven
  echo "Downloading and installing Maven $MAVEN_VERSION..."
  mkdir -p .maven-dist
  cd .maven-dist
  curl -O https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz
  tar -xzf apache-maven-$MAVEN_VERSION-bin.tar.gz
  cd ..
fi

# execute mvn with the assembled maven distribution path
./.maven-dist/apache-maven-$MAVEN_VERSION/bin/mvn "$@"
