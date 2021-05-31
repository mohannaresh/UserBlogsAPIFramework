# UserBlogsAPIFramework

  Main objective of this project is to validate the email id present in comment section of blog posts created by a specific user.

**SoftwareRequirements**

  JDK1.8 and above
  Maven 4.0.0

**Testcases**

  1. Validate displayed email id  is proper format present in the comment section of each blog post created by a specific user
  2. Enter empty value in the user name and validate it fails with message showing "Username is empty or Username doesn't exist"
  3. Enter wrong username and validate it fails with message showing "Username is empty or Username doesn't exist"

**How to run the project: **
  1. Open the project in any one of the IDE
  2. Select "Maven build" ---> type "clean install" in Goals textfield
  3. Go to src/test/java/com/bdd/test/cucumber/Options/
  4. Check tags = "@EndToEndTest" in TestRunner.java file is matching with userBlogs.feature file Scenario tag
  5. Right click on TestRunner.java
  6. RunAs ---> JUnitTest




