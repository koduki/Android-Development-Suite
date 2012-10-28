# Android-Example

Android development framework suite.
Maven + AndroidBinding + roboguice + mockito!

## create keysotre for signed apk

        # JDK6 only.
        keytool -genkey -v \
        -keystore hoge.keystore \
        -storepass pass_keystore \
        -alias alias_key1 \
        -keypass pass_key1 \
        -dname "CN=HOGE,O=FUGA,C=JP" \
        -keyalg RSA \
        -validity 10000

## setting password for signed apk

        vi ~/.m2/settings.xml
        <?xml version="1.0" encoding="UTF-8"?>
        <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                 http://maven.apache.org/xsd/settings-1.0.0.xsd">
          <profiles>
            <profile>
              <id>android</id>
              <properties>
                <android.sdk.path>/opt/android-sdk-linux_x86/android-sdk_r20.0.1-linux_x86/</android.sdk.path>
                <sign.keystore>/tmp/hoge.keystore</sign.keystore>
                <sign.alias>alias_key1</sign.alias>
                <sign.storepass>pass_keystore</sign.storepass>
                <sign.keypass>pass_key1</sign.keypass>
              </properties>
            </profile>
          </profiles>
        
          <activeProfiles>
            <activeProfile>android</activeProfile>
          </activeProfiles>
        </settings>

## dependency

        ./resolve-depends.sh

## build 

        mvn clean install

## deploy on Android

        mvn clean install android:deploy android:run -Prelease


