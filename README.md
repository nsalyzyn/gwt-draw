Goals
-----

* Provide basic drawing functionality leveraging features of canvas
* Develop this in unison with gwt-canvas-shapes to create a reasonably framework
* Most Critical: The gwt-canvas-shapes library needs a good user interface to create the code to define and build shapes

Not Goals
---------

* Provide a useful drawing tool

Running
-------

I've tested this with Eclipse 3.5. Later versions will likely
also work, earlier versions may not.

You will need to install the following plugin components:
* Google Plugin for Eclipse including AppEngine (instructions at http://code.google.com/eclipse/)
* m2eclipse Core
* Maven Integration for WTP (in m2eclipse extras)
  Instructions for installing the maven plugins can be found here:
  http://m2eclipse.sonatype.org/installing-m2eclipse.html
* An installed version of the canvas shape library: git@github.com:nsalyzyn/gwt-canvas-shapes.git

Ensure Eclipse is configured to use Java 1.6 as this uses
AppEngine.

In Eclipse, go to the File menu and choose:

1. File -> Import... -> Existing Maven Projects into Workspace
2. Browse to the directory containing this file and click OK.
3. Click Finish with the available pom.xml selected.

You can now browse the project in Eclipse.

To launch your web app in GWT development mode

1. Go to the Run menu item and select Run -> Run as -> Web Application, which
should default to index.jsp.
2. If prompted for which directory to run from, simply select the war directory
that Eclipse may defaults to.

Deploying
---------

1. Right click on the project -> Google -> Deploy to App Engine
2. Choose a site to deploy to (I'll have to pull that setting out of the repository into a setting file sometime).
