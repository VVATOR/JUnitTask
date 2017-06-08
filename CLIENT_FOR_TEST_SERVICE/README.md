# client-avg

<details>
  <summary>description structure of solution</summary>
<pre>
  
  This application contain console application wrapped on executable jar file.
  
  This application run test for SOAP service.

</pre>
</details>

## Build client-avg with maven
```
mvn clean package install
```

## Run client-avg
After build in folder **target** will be created ***client-avg-1.0.jar*** file (this file is a executable jar).

Add path to java in system variable PATH.
For run client-avg execute next command:
``` 
java -jar client-avg-1.0.jar
```