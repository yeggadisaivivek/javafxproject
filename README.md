# javafxproject

Steps to run this project:
1) Download the project from the [github](https://github.com/yeggadisaivivek/javafxproject)https://github.com/yeggadisaivivek/javafxproject
2) Follow these steps to create a JavaFX non-modular project and use the IDE tools to build it and run it. Alternatively, you can download a similar project from here.

Download the appropriate JavaFX SDK for your operating system and unzip it to a desired location, for instance /Users/your-user/Downloads/javafx-sdk-21.0.1.


Now in eclipse,
File -> Import -> General -> Import existing project -> Browse the downloaded project -> Click on Next

**_You can include the JavaFX21.0.1 library into the classpath.Add JavaFX library. This is the same library which we have created in the above step_**


Warning: If you now run the project it will compile but you will get this error:

Error: JavaFX runtime components are missing, and are required to run this application
This error is shown since the Java 21 launcher checks if the main class extends javafx.application.Application. If that is the case, it is required to have the javafx.graphics module on the module-path.
3. Add VM arguments
To solve the issue, click on Run -> Run Configurations...  -> Java Application, create a new launch configuration for your project named `hellofx` and add these VM arguments:

Linux/Mac
Windows

--module-path /path/to/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml
Warning: Make sure the checkbox "Use the -XstartOnFirstThread argument when launching with SWT" is not checked.
VM argumentsClick apply and close the dialog.

Run the project
Click Run -> Run As -> Java Application -> Main - hellofx to run the project.

Create a new User Library under Eclipse -> Window -> Preferences -> Java -> Build Path -> User Libraries -> New.Add User LibraryName it JavaFX21 and include the jars under the lib folder from JavaFX 21.Add JavaFX jars
