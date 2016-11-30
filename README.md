[![CircleCI](https://circleci.com/gh/SekiguchiKai/Extendable_WebServer/tree/master.svg?style=svg&circle-token=984c4cf521041699102c93148672dd127a31ef28)](https://circleci.com/gh/SekiguchiKai/Extendable_WebServer/tree/master)

## Overview
Simple WebServer.

## Description
You can use this application in two ways as following.<br>
### 1.WebSever
* You can access your resource at localhost server through your browers.<br>
* At this time, you can access following extension's files.<br>

#### Text type Data
1. ~.html
2. ~.css
3. ~.js

#### Binary type Data
1. ~.png
2. ~.jpeg
3. ~.gif

### 2.Bulletin board
* You can write your comment.<br>



## Requirement
* java version "1.8.0_92"


## Usage

### run
1. clone
2. Hit the command ```./gradlew run```at Application root directory.

### 1.WebSever
After run <br>

1. Start your browser.
2. Visit ```http://localhost:8080/{resource name which you want to access}```
(```{resource name which you want to access}``` is relative path of resource which you want to access from ```src/main/resources``` directory .)


### 　2.Bulletin board**
After run <br>

1. Start your browser.
2. Visit ```http://localhost:8080/program/board/```.

#### To post your comment
1. Enter textbox of "ユーザーネーム" and "コメント".
2. Click "送信する" button.

#### To delete your comment
1. Click "削除" button under your comment posted.

#### To search particular user's comment
1. Enter user's name which you want to search into textbox of "ユーザーネーム".
2. Click "検索する" button under textbox of "ユーザーネーム".


### test
1. Hit the command ```./gradlew test``` at Application root directory.

### add new directories/files
1. add new directories/files under the ```src/main/resources```.

## Specifications of each classes.
Please read JavaDocument under ```doc``` directory.

## Licence
 [MIT](https://github.com/tcnksm/tool/blob/master/LICENCE)
