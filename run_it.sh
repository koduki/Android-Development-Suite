#!/bin/sh
cd src/test/cucumber/
SCREENSHOT_PATH="`pwd`/screenshots/" calabash-android run ../../../target/android_sample.apk
