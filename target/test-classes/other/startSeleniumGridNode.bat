SET DRV_PATH=\Academic\UOM\TestAutomationAssignment\src\test\resources\Drivers\
SET SEL_JAR=\Academic\UOM\TestAutomationAssignment\src\test\resources\jarFiles\

java -Dwebdriver.chrome.driver=%DRV_PATH%chromedriver-83.exe -Dwebdriver.gecko.driver=%DRV_PATH%geckodriver.exe -jar %SEL_JAR%selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.244:4444/grid/register/ -browser browserName=chrome,version=83,maxInstances=2,platform=WINDOWS -browser browserName=firefox,version=77,maxInstances=2,platform=WINDOWS



