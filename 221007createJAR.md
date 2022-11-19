
Create Maven and run JAR in Netbean for Windows10
---
ref: https://techglimpse.com/create-maven-jar-file-netbeans-tutorial/

### Creating JAR file in NetBeans
1. From the ProjectsFIles, example `wk3testjar`, rightClick for `Properties`. 
  Categories panel, select `Run`, 
  and key in at the Main Class: (example)`com.zero1.wk3testjar.Main` and `OK`
2. Open `pom.xml` from the project file, `wk3testjar`. Add the following just before `</project>`. 
    The exmaple `com.zero1.wk3testjar.Main` replace with the main class of project.
    ``` js
    <build>
    <plugins>
    <plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>2.4</version>
    <configuration>
    <descriptorRefs>
    <descriptorRef>jar-with-dependencies</descriptorRef>
    </descriptorRefs>
    <archive>
    <manifest>
    <mainClass>com.zero1.wk3testjar.Main</mainClass>
    </manifest>
    </archive>
    </configuration>
    <executions>
    <execution>
    <id>make-assembly</id>
    <phase>package</phase>
    <goals>
    <goal>single</goal>
    </goals>
    </execution>
    </executions>
    </plugin>
    </plugins>
    </build>
    ```
3. Using Command Prompt, go to `target` directory, run the jar file. **`java -jar <jarfile>.jar`**.
    ``` console
    C:\Users\AlvinNg\wk3TestJar\target>dir
    ...
    07/10/2022  03:36 pm         2,017,611 wk3TestJar-1.0-SNAPSHOT-jar-with-dependencies.jar
    07/10/2022  03:36 pm             3,544 wk3TestJar-1.0-SNAPSHOT.jar

    C:\Users\AlvinNg\wk3TestJar\target>java -jar wk3TestJar-1.0-SNAPSHOT-jar-with-dependencies.jar
    Hello World!
    >>>> response >>> {"data" ...
    ```

---
