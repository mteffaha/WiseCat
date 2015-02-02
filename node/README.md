# Node tier

## Structure

* node/
  * app/
    * modules/ (app modules)
    * node_modules/ (node dependencies)
    * static/ (files to be serve to the clients)
      * package.json (see npm install)
      * server.js (server's entry point !)
  * src/
    * coffee/ (coffee source files)
    * jade/ (jade source files)
    * stylus/ (stylus source files)
  * build.sh (build script)
  * README.md (you are here)
* resources/
  * exemple.ttl (some exemple triples)
  * wisecat.ttl (ontology)


## Run
> npm install node/app/  
> node node/app/server.js

## Build

You will need Jade, Stylus & CoffeeScript  
See build.sh  
*Creates a .thrash folder*  
Also runs with firefox (& Chrome)
