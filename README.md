# Overview

My own JPA EntityManager request scope implementation. May be used in non JEE containers (such as Tomcat).</br>
Original article: http://javanotepad.blogspot.com/2007/05/jpa-entitymanagerfactory-in-web.html

#Usage

Just add in your **web.xml**:

```xml
<context-param>
  <param-name>by.creepid.persistence.PERSISTENCE_UNIT</param-name>
  <param-value>MyPersistenceUnit</param-value>
</context-param>

<listener>
  <description>ServletContext and Request Listener for managing EntityManager with request scope</description>
  <listener-class>by.creepid.persistence.PersistenceAppRequestListener</listener-class>   
</listener>
```

or set in code:

```java
PersistenceManager.setPersistenceUnit("MyPersistenceUnit");
```
