What is it
========================
This is a very basic crawler program, which takes a starting url, and outputs all the links,
crawled from that url. You can also specify the number of max-urls you want to crawl

Dependency List
=======================
Jsoup - Its the java variant of beautiful soup. Its used to get html and parts of html 
from a given url

How to run the crawler
=========================

javac -cp '.:../deps/jsoup-1.8.2.jar' WebCrawlerTester.java WebCrawler.java

java -cp '.:../deps/jsoup-1.8.2.jar' WebCrawlerTester WebCrawler
