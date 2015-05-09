set libjars=../lib/log4j-1.2-api-2.2.jar;../lib/log4j-api-2.2.jar;../lib/log4j-core-2.2.jar;
set classpath=.;../classes;%libjars%

java -cp %classpath% com.synqq.client.SynqqClient
java -cp %classpath% com.synqq.client.SynqqClient
