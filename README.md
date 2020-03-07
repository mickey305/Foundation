
<p align="center">
  <img src="asset/logo/brick.svg" height="100">
  <img src="asset/logo/foundation_title.png" height="100">
  <img src="asset/logo/brick.svg" height="100">
</p>

# Overview

`Foundation` is part of infrastructure component and personal-library for Java. You can easily extend the basic functions. This makes it easier to implement event driven processing, design patterns and more which are not provided by the standard API.

The following table shows status of external CI service.

| | **Branch** | **TravisCI** | **AppVeyor** |
|:---:|:---|:---|:---|
| release | master | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=master)](https://travis-ci.org/mickey305/Foundation) | [![Build status](https://ci.appveyor.com/api/projects/status/kw8u113ot8x8by9i/branch/master?svg=true)](https://ci.appveyor.com/project/mickey305/foundation/branch/master) |
| - | develop | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=develop)](https://travis-ci.org/mickey305/Foundation) | [![Build status](https://ci.appveyor.com/api/projects/status/kw8u113ot8x8by9i/branch/develop?svg=true)](https://ci.appveyor.com/project/mickey305/foundation/branch/develop) |

### Repository hosting services
| **ServiceName** | **Link** |
|:---|:---|
| JitPack | [![](https://jitpack.io/v/mickey305/Foundation.svg)](https://jitpack.io/#mickey305/Foundation) |
| Bintray | [![Download](https://api.bintray.com/packages/mickey305/maven/foundation/images/download.svg)](https://bintray.com/mickey305/maven/foundation/_latestVersion) |

# Features

![Library Version](https://img.shields.io/badge/Foundation%20library-v0.6.51-green.svg?style=flat)
![Java Version](https://img.shields.io/badge/JRE-1.7-yellowgreen.svg?style=flat)
[![Apache License](http://img.shields.io/badge/license-Apache2.0-blue.svg?style=flat)](LICENSE)
[![Maintainability](https://api.codeclimate.com/v1/badges/97b4705656277130b8f3/maintainability)](https://codeclimate.com/github/mickey305/Foundation/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/97b4705656277130b8f3/test_coverage)](https://codeclimate.com/github/mickey305/Foundation/test_coverage)

- [x] source compatibility `java7`
- [x] easily usable
- [x] some design patterns
- [x] some utility classes
- [x] math matrix API
- [x] multi-thread API
- [x] downcast API(unsafe)
- [x] and more...

# Contents
- [Usage](#usage)
  - [Matrix calculation](#usage-matrix)
  - [String utilities](#usage-string)
  - [Date/Time covert utilities](#usage-datetime)
  - [Read/Write lock utilities](#usage-rw-lock)
  - [Collection utilities](#usage-collection)
  - [SystemLog capture](#usage-syslog)
- [Requirements](#requirements)
  - [User settings](#requirements-user)
  - [Developer settings](#requirements-dev)
- [References](#references)
- [License information of dependent library](#license-info-deplib)
- [History](#history)

# <a name="usage"> Usage

### <a name="usage-matrix"> Matrix calculation
- sample1-title
  
  sentence1
  ```java
  final JavaSample1 sample = new JavaSample1();
  ```
  long - sentence1: This is a sample sentence. As soon as the actual description is completed, this part of the description will be replaced.

- sample2-title
  
  sentence2
  ```java
  final JavaSample2 sample = new JavaSample2();
  final int rt = sample.execute();
  System.out.println("exection result=" + rt + ".");
  ```
  long - sentence1: This is a sample sentence. As soon as the actual description is completed, this part of the description will be replaced.
   - sample pattern1
   - sample pattern2
   - sample pattern3
   - sample pattern4
   - sample pattern5
  
  long - sentence1: This is a sample sentence. As soon as the actual description is completed, this part of the description will be replaced.

- sample3-title
  
  sentence3
  ```java
  final JavaSample3 sample = new JavaSample3();
  ```
  long - sentence1: This is a sample sentence. As soon as the actual description is completed, this part of the description will be replaced.
  
  | **col1** | **col2** | **col3** | **col4** | **col5** |
  |:---:|:---:|:---:|:---:|:---:|
  | -      | data12 | data13 | data14 | data15 |
  | data21 | -      | data23 | data24 | data25 |
  | data31 | data32 | -      | data34 | data35 |
  | data41 | data42 | data43 | -      | data45 |
  | data51 | data52 | data53 | data54 | -      |
  | data61 | data62 | data63 | data64 | data65 |

  long - sentence2: This is a sample sentence. As soon as the actual description is completed, this part of the description will be replaced.

### <a name="usage-string"> String utilities
- sample1-title
- sample2-title

### <a name="usage-datetime"> Date/Time covert utilities
- sample1-title
- sample2-title

### <a name="usage-rw-lock"> Read/Write lock utilities
- sample1-title
- sample2-title

### <a name="usage-collection"> Collection utilities
#### sample1-theme
- sample11-title
- sample12-title
#### sample2-theme
- sample21-title
- sample22-title

### <a name="usage-syslog"> SystemLog capture
- sample1-title
- sample2-title

# <a name="requirements"> Requirements

### <a name="requirements-user"> User settings
- Install JRE (version`>= 1.7`)
- Add settings to the build tool for projects that use this library
  - write build script (via [JitPack](https://jitpack.io/#mickey305/Foundation))
  - download jar (via [release list](https://github.com/mickey305/Foundation/releases/)) and set `lib` directory

### <a name="requirements-dev"> Developer settings
- Install JDK (version`>= 10`)
- clone this repository
- run script in this repository: shell-`init.sh` or batch-`init.bat`
- import IDE([Eclipse](https://www.eclipse.org/downloads/), [IntelliJ](https://www.jetbrains.com/idea/) etc.) via [Gradle](https://gradle.org/)

# <a name="references"> References
- [Windows JDK環境切替バッチファイル](http://www.torutk.com/projects/swe/wiki/Windows_JDK%E7%92%B0%E5%A2%83%E5%88%87%E6%9B%BF%E3%83%90%E3%83%83%E3%83%81%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB)
- [install-jdk.sh on GitHub](https://github.com/sormuras/bach)

# <a name="#license-info-deplib"> License information of dependent library
- module `foundation`

  | **library name** | **license** | **link** |
  |:---|:---|:---|
  | junit                        | Eclipse Public License 1.0 | https://junit.org/junit4/license.html |
  | apache commons-lang3         | Apache License Version 2.0 | https://github.com/apache/commons-lang/blob/master/LICENSE.txt |
  | apache commons-math3         | Apache License Version 2.0 | https://github.com/apache/commons-math/blob/master/LICENSE.txt |
  | apache commons-collections4  | Apache License Version 2.0 | https://github.com/apache/commons-collections/blob/master/LICENSE.txt |
  | apache commons-codec         | Apache License Version 2.0 | https://mvnrepository.com/artifact/commons-codec/commons-codec |
  | hibernate validator          | Apache License Version 2.0 | https://github.com/hibernate/hibernate-validator/blob/master/license.txt |
  | google.code findbugs-jsr305  | Apache License Version 2.0 | https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305/1.3.9 |
  | robust-it cloning            | Apache License Version 2.0 | https://mvnrepository.com/artifact/uk.com.robust-it/cloning/1.9.2 |
  | glassfish javax.el           | CDDL, GPL, GPL2.0          | https://mvnrepository.com/artifact/org.glassfish/javax.el/3.0.0 |
  
- module `maintenance`

  | **library name** | **license** | **link** |
  |:---|:---|:---|
  | junit                | same module `foundation`   | - |
  | apache commons-lang3 | same module `foundation`   | - |
  | reflections          | WTFPL                      | https://github.com/ronmamo/reflections |
  | squareup javapoet    | Apache License Version 2.0 | https://github.com/square/javapoet/blob/master/LICENSE.txt
  
- module `sample`

  | **library name** | **license** | **link** |
  |:---|:---|:---|
  | junit | same module `foundation` | - |

# <a name="history"> History
 * version [0.6.51](https://github.com/mickey305/Foundation/releases/tag/0.6.51) deploy - 2020-02-10
 * version [0.6.0](https://github.com/mickey305/Foundation/releases/tag/0.6.0) deploy - 2018-12-08
 * version [0.5.0](https://github.com/mickey305/Foundation/releases/tag/0.5.0) deploy - 2017-11-11
 * version [0.0.1-SNAPSHOT](https://github.com/mickey305/Foundation/releases/tag/0.0.1-SNAPSHOT) deploy(Test version) - 2017-10-09
