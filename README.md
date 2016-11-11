[![CircleCI](https://circleci.com/gh/SekiguchiKai/Extendable_WebServer/tree/master.svg?style=svg&circle-token=984c4cf521041699102c93148672dd127a31ef28)](https://circleci.com/gh/SekiguchiKai/Extendable_WebServer/tree/master)

## Overview
Simple WebServer.

## Description
You can access your resource at localhost server through your browers.
At this time, you can access following extension's files.
### Text type Data
1. ~.html
2. ~.css
3. ~.js

### Binary type Data
1. ~.png
2. ~.jpeg
3. ~.gif

## Requirement
* java version "1.8.0_92"


## Usage 

### run
1. clone
2. Hit the command ```gradle run```at Application root directory.
3. Start your browser.
4. Visit ```http://localhost:8080/{resource name which you want to access}```
(```{resource name which you want to access}``` is relative path of resource which you want to access.)

### test
1. Hit the command ```gradle test``` at Application root directory.

### add new directories/files
1. add new directories/files under the ```src/main/resources/jp/co/topgate/sekiguchi/kai```.

## Specifications of each classes.
Please read JavaDocument under ```doc``` directory.

## Licence
 [MIT](https://github.com/tcnksm/tool/blob/master/LICENCE)
