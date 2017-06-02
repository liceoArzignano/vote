#!/bin/bash

./gradlew build

if [ $? != 0 ]; then
    exit 1
fi

if [ -d dist ]; then
    rm -rf dist
fi

mkdir dist
cd dist
unzip ../build/distributions/vote.zip
cd ../DATA
./dataGen.sh
cd ../dist
cp -r ../DATA vote/bin/DATA
rm -f vote/bin/DATA/dataGen.sh
cd ..
exit 0