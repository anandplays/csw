# csw

Build PreRequirements:
Please ensure maven is installed in the system (Apache Maven 3.6.3 ), if not installed it can be downloaded from the below link
https://maven.apache.org/download.cgi

Clone the repo using git clone command

Please execute the below commands 
cd json-xmlconverter
mvn clean install

Built jar file can be found inside the target folder

Command to run 
java -jar target/json-xmlconverter-0.0.1-SNAPSHOT.jar <location path of input json file> <location path of output xml file>

Example:
 java -jar target/json-xmlconverter-0.0.1-SNAPSHOT.jar ../example.json ../example.xml

