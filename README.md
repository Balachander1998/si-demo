# si-demo

Sample Application primarily for testing/demoying integration between Spring and Apache NiFi

Contains 4 pre-configured applications in ```src/main/resources```

- _fromNiFiOny.xml_ - receives messages form NiFi but doesn't send anything back
- _toNiFiOnly.xml_ - sends generated messages to NiFi, but doesn't expect anything from NiFi
- _biDirectionalWithAggregator.xml_ - send and receives messages from/to NiFi with Spring performing some aggregation.
- _requestReply.xml_ - a simple echo based setup with message going in and out of spring with slight modification.

More details inside XML files

To run follow these steps:

* clone the project
* ```mvn clean install```

Once done you'll have target/deps directory which contains all the dependenies requied for this app to run in NiFi. Basically class path.

Start NiFi
Drag Spring processor onto the canvas and configure it. The only two required properties are:

* _Application Context config path_ - (e.g., requestReply.xml). Use one of the config files mentioned above as they are already part of the JAR.

* _Application Context class path_ - (e.g., /foo/bar/target/deps). Basically use absolute path to the directory with all the JARs

Core features to NOT be missed:
* Once NiFi is started you can have as many SpringContext processors as you want. Even though they all derive from the same NAR, the instance of Spring Application Context is completely isolated and created/desroyed during start/stop processor. This means that any changes to the Application Context configuration and/or classpath will be picked up upon the next start of the processor. 

