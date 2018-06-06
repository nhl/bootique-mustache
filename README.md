<!--
     Licensed to the ObjectStyle LLC under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ObjectStyle LLC licenses
   this file to you under the Apache License, Version 2.0 (the
   “License”); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.
  -->

[![Build Status](https://travis-ci.org/bootique/bootique-mvc.svg)](https://travis-ci.org/bootique/bootique-mvc)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bootique.mvc/bootique-mvc/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bootique.mvc/bootique-mvc/)

A simple MVC web framework for [Bootique](http://bootique.io). Uses JAX-RS (on top of 
[bootique-jersey](https://github.com/bootique/bootique-jersey)) for processing requests and responding with 
template-generated views. Provides a framework for pluggable templating engines. Out of the box contains integration 
with [Mustache](https://mustache.github.io/) engine. This framework is suitable for simple HTML UIs, where server-side 
rendering is minimal (e.g. when most of the UI work is done on the client with JavaScript). For advanced server-side 
rendering, you may consider JSF, Apache Tapestry (there's [bootique-tapestry](https://github.com/bootique/bootique-tapestry)
module available) and such.
