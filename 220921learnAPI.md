### Create and use API via NetBeans

MyAPI.java
``` java
package myapi;

public class MyAPI {

    public static void hello() {
        System.out.println("Hello World!");
    }

    public static int return50() {
        return 50;
    }

    public static int timesTwo(int i) {
        return (i * 2);
    }
}
```

TestAPI.java
``` java
package testapi;

import myapi.MyAPI;

public class TestAPI {
    public static void main(String[] args) {
        MyAPI.hello();
        int i = MyAPI.return50();
        System.out.println(MyAPI.timesTwo(i));
    }
}

/*
How to add a jar in NetBeans?
Right click on project opened in netbeans editor 2. select properties 
3. choose libraries 4. add jar 5. click ok You can also use this (when the 
libraries are not in "dist/lib"), tested with NetBeans and ant-contrib: <target 
name="-post-jar"> <taskdef resource="net/sf/antcontrib/antlib.xml"> 
<classpath> <!--
*/
```
