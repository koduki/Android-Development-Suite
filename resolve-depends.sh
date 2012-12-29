ARTIFACT_ID="android-binding"    
GROUP_ID="gueei"                 
VERSION="v30-0.52"               
FILENAME="${ARTIFACT_ID}-${VERSION}.jar"

curl "http://android-binding.googlecode.com/files/${FILENAME}" -o ${FILENAME}

mvn install:install-file -Dfile=${FILENAME} -DgroupId=${GROUP_ID} -DartifactId=${ARTIFACT_ID} -Dversion=${VERSION} -Dpackaging=jar
rm ${FILENAME} 
