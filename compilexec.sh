mvn compile
mvn org.codehaus.mojo:exec-maven-plugin:1.5.0:java -Dexec.mainClass="com.diderot.projetGLA.jetty_jersey.JettyMain" | grep -Ev '(^\[|Download\w+:)'
