# PlanificateurTache
container


===Test the mini app===
1 Without IDE firstable(for people who hate eclipse) : 
-Install mvn ,(thanks to sudo apt ...) it is a command line to work with maven.
-Then to test the mini app : 
mvn org.codehaus.mojo:exec-maven-plugin:1.5.0:java -Dexec.mainClass="com.example.jetty_jersey.JettyMain" | grep -Ev '(^\[|Download\w+:)'

2 With Eclipse (you can hate it but it is so simpler) :
- On Eclispe, go to File/Import, then Maven/Existing Maven Project, then set "Root directory" as the Jetty-Jersey folder.
- If you can't build the project: 
	- Right-click on the project then Properties then Java Build Path then the tab called "Libraries".
	- Here, click "Add Library" then select JRE System Library and click Next.
	- Then check "Alternate JREs" and choose for example the Java 1.8 JDK.
- The program is compiled automatically as you code; to run the app, click the "Run" button.

Then open a browser with url : localhost:8080 and see the result.


===Some stuffs with jetty jersey(just some tests)====
Login test (very basic)

a user example is ->  
(mail, password) ->(pseudo,code).

 When connected , we can test one webservice (only one for the moment),
 on the URL : 
 https://localhost/8080/ws/planned_task/12 
 
 it will print on a array all planned task ,with a search engine and number of line we want.

