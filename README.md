This project is based on the [cmakeant](http://code.google.com/p/cmakeant/) project, hosted on Google Code and originally created by Iain Hull. 

Provides a simple ant task to integrate cmake projects into larger projects built with ant. Supports multiplatform cmake builds easily from a single ant file. Allows ant properties to be passed to cmake as variables and and cmake variables to passed back to ant as properties.

This task calls cmake to generate the platform specific build files (ide projects or makefiles). It then uses the generated files to build the project, reading the CMakeCache.txt to resolve the platform's appropriate build command. This enables a single ant task to call cmake on any platform, hiding the platform specific code required to launch the build.

You can get some usage documentation [here](http://code.google.com/p/cmakeant/wiki/CmakeDoc). 