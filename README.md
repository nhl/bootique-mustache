[![Build Status](https://travis-ci.org/bootique/bootique-mvc.svg)](https://travis-ci.org/bootique/bootique-mvc)

A simple MVC web framework for [Bootique](http://bootique.io). Uses JAX-RS (on top of 
[bootique-jersey](https://github.com/bootique/bootique-jersey)) for processing requests and responding with 
template-generated views. Provides a framework for pluggable templating engines. Out of the box contains integration 
with [Mustache](https://mustache.github.io/) engine. This framework is suitable for simple HTML UIs, where server-side 
rendering is minimal (e.g. when most of the UI work is done on the client with JavaScript). For advanced server-side 
rendering, you may consider JSF, Apache Tapestry (there's [bootique-tapestry](https://github.com/bootique/bootique-tapestry)
module available) and such.
