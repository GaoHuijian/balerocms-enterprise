![Balero CMS](http://demo.balerocms.com/site/apps/admin/panel/images/logo.png)


The first Java CMS fully optimized for mobiles and tablets and desktops.

Introduction
=============

Balero CMS is mobile first Enterprise Content Management System.

Requirements
============

* JDK 7
* MySQL 5.1+

Building From Source Code
========================

Making the .WAR and executable .JAR file ;-).

Install Maven

    # Ubuntu
    sudo apt-get install maven
    
    # Fedora
    sudo yum install maven

1. cd balerocms-enterprise
2. mvn package

If success search for 'standalone.jar' on 'target' folder

Installation
============

Create an empty database

1. cd balerocms-enterprise
2. cp dist target
3. Configure 'target/conf/database.properties'

    * MySQL Database
    * MySQL Username
    * MySQL Password
    
4. cd target
5. Deploy:

    sudo chmod 777 *.sh
    ./balero-start.sh

Open [http://localhost:8080/](http://localhost:8080/)

Enjoy!

Development
===========

Debugging/Deploying App in Tomcat

Copy 'dist' to 'bin' folder

    cp dist ${CATALINA_HOME/bin/
    
Done!

Technologies under Balero CMS
=============================

Balero CMS is an Enterprise Application developed
under technologies:

* Spring MVC
* Hibernate
* Bootstrap

Upcoming Technologies

* Bower
* NodeJS
* Grunt Or GNU Make?
* Yeoman

Under development

* balerocms.com

Under development with ntelliJ IDE Ultimate
===========================================

We recommend this wonderful IDE ;-)

API Reference Documentation
===========================

Read: docs/index.html

About and comments
==================

* Website: balerocms.com
* Contact: anibalgomez@icloud.com

