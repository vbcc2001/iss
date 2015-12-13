@echo off
set JAVA_HOME=D:\YTZQ_Web_Dev\jdk1.8.0_40
set RESIN_HOME=D:\YTZQ_Web_Dev\resin-pro-3.1.12
set APP_HOME=D:\iss\XXX_ISS_WEB
set APP_NAME=XXX_ISS_WEB
title %APP_NAME%
%JAVA_HOME%\bin\java -Dfile.encoding=GBK -Dapp.name=%APP_NAME% -Dapp.home=%APP_HOME% -jar %RESIN_HOME%\lib\resin.jar -resin-home %RESIN_HOME% -conf %APP_HOME%\resin.xml -server %APP_NAME% 
