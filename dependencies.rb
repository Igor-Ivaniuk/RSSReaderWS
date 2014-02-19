#Repositories

repositories.remote << 'http://mirrors.ibiblio.org/pub/mirrors/maven2'
repositories.remote << 'https://maven.java.net/content/groups/public/'
repositories.remote << 'https://maven.atlassian.com/repository/public'
repositories.remote << 'https://repository.jboss.org/nexus'
repositories.remote << 'http://repository.jboss.org/maven2'



#Dependencies

COMMON_COMPILE_LIBS 		= [
	LOG4J					= 'log4j:log4j:jar:1.2.16',
							]

PERSIST_COMPILE_LIBS 		=	[
	HIBERNATE_CORE			= 'org.hibernate:hibernate-core:jar:3.6.0.Final',
	HIBERNATE_COMMONS_ANNO	= 'org.hibernate:hibernate-commons-annotations:jar:3.2.0.Final',
							]

TESTING_LIBS 				=	[
	JUNIT					= 'junit:junit:jar:4.8.2',
	MOCKITO					= 'org.mockito:mockito-all:jar:1.8.5',
							]

PERSIST_RUNTIME_LIBS 		= 	[
	HIBERNATE_ENTITY		= 'org.hibernate:hibernate-entitymanager:jar:3.4.0.GA',
	HIBERNATE_VALIDATOR		= 'org.hibernate:hibernate-validator:jar:3.1.0.GA',
	HIBERNATE_JPA_API		= 'org.hibernate.javax.persistence:hibernate-jpa-2.0-api:jar:1.0.0.Final',
	COMMONS_COLLECTIONS		= 'commons-collections:commons-collections:jar:3.2.1',
	MYSQL_JDBC_CONNECTOR	= 'mysql:mysql-connector-java:jar:5.1.13',
	SLF4J_SIMPLE			= 'org.slf4j:slf4j-simple:jar:1.6.1',
	SLF4J_API				= 'org.slf4j:slf4j-api:jar:1.6.1',					
	DOM4J					= 'dom4j:dom4j:jar:1.6.1',
	JAVASSIST				= 'org.javassist:javassist:jar:3.14.0-GA',
	JTA						= 'javax.transaction:jta:jar:1.0.1B',
	ANTLR					= 'antlr:antlr:jar:2.7.6',
							]
				
WS_COMPILE_LIBS 			= [
	JSR						= 'javax.ws.rs:jsr311-api:jar:1.1',
	JERSEY_BUNDLE			= 'com.sun.jersey:jersey-bundle:jar:1.11',
	ASM						= 'asm:asm:jar:3.3.1',
							]	
							
WS_COMPILE_TRANSITIVE_LIBS	= [				
	XSTREAM					= 'com.thoughtworks.xstream:xstream:jar:1.4.2',
							]
							
FEED_COMPILE_LIBS			= [

							]
				