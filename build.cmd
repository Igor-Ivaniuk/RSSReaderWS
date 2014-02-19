set tomcat_path=d:\Software\apache-tomcat-6.0.35
rmdir /S /Q %tomcat_path%\webapps\RSSReader-1.3
del /F /S /Q %tomcat_path%\webapps\RSSReader-1.3.war
call buildr clean package
copy target\RSSReader-1.3.war %tomcat_path%\webapps\

pause
