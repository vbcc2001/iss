@echo off
set JAVA_HOME=C:\iss\jdk1.8.0_101
set RESIN_HOME=C:\iss\resin-pro-3.1.12
set APP_HOME=C:\iss\XXX_ISS_WEB
set APP_NAME=XXX_ISS_WEB
title %APP_NAME%
%JAVA_HOME%\bin\java -Dfile.encoding=GBK -Dapp.name=%APP_NAME% -Dapp.home=%APP_HOME% -jar %RESIN_HOME%\lib\resin.jar -resin-home %RESIN_HOME% -conf %APP_HOME%\resin.xml -server %APP_NAME% 
