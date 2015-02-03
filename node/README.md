# Node tier :bowtie:

## Structure :v:
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
  * README.md (:point_left: you are here)
* resources/
  * exemple.ttl (some example triples)
  * wisecat.ttl (ontology)


## Run :runner:
> npm install node/app/ (first time only)  
> node node/app/server.js  
> firefox localhost:3000/  

## Build :sparkles:
You will need Jade, Stylus & CoffeeScript  
See build.sh  
*Creates a .thrash folder*  
Also runs with firefox (& Chrome)

:smile_cat:
