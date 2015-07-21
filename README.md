```
                                  
              _             _   _           
     /\      | |           | | (_)          
    /  \   __| | __ _ _ __ | |_ ___   _____ 
   / /\ \ / _` |/ _` | '_ \| __| \ \ / / _ \
  / ____ \ (_| | (_| | |_) | |_| |\ V /  __/
 /_/    \_\__,_|\__,_| .__/ \__|_| \_/ \___|
                     | |                    
                     |_|   
                                      
```

Adaptive command line interface V2
=================================


Documentation for the CLI can be found on http://docs.adaptive.me/cli

CLI Syntax documentation: http://docs.adaptive.me/cli/cli-syntax/


# Quick Start for the developer
------------------------------

## Building
1. Checkout of the project: git clone https://github.com/adaptive/cli
2. Build: mvn clean install

## Running
1. Launch from the root directory of the project:

  ./adaptive
  
  ./adaptive command-name
  
(if no command is entered (like help) cli will use interactive mode)


## Developing a new command
Apache Karaf is used for providing the Adaptive CLI.

### New command
When adding a new builtin command, the following files need to be modified :
  * command/src/main/resources/OSGI-INF/blueprint/shell-log.xml
  * command/src/main/resources/META-INF/services/org/apache/karaf/shell/commands

The first one is for providing the command to Interactive/OSGi mode and the next one is for providing the command to the default CLI shell in non-interactive mode.


### Advanced help 
The guide to develop karaf command is available at http://karaf.apache.org/manual/latest/developers-guide/extending.html

There is also `adaptive` command `stack-traces-print` to enable printing stacktraces.

#### Timeouts

With system properties you can increase timeout, else it's default timeout of the JVM

http://docs.oracle.com/javase/8/docs/technotes/guides/net/properties.html
`sun.net.client.defaultConnectTimeout`
`sun.net.client.defaultReadTimeout`
