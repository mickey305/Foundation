
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
- [Requirements](#requirements)
- [References](#references)
- [History](#history)

# <a name="usage"> Usage

# <a name="requirements"> Requirements

### User settings
- Install JRE (version`>= 1.7`)
- Add settings to the build tool for projects that use this library
  - write build script (via [JitPack](https://jitpack.io/#mickey305/Foundation))
  - download jar (via [release list](https://github.com/mickey305/Foundation/releases/)) and set `lib` directory

### Developer settings
- Install JDK (version`>= 10`)
- clone this repository
- run script in this repository: shell-`init.sh` or batch-`init.bat`
- import IDE([Eclipse](https://www.eclipse.org/downloads/), [IntelliJ](https://www.jetbrains.com/idea/) etc.) via [Gradle](https://gradle.org/)

# <a name="references"> References
- [Windows JDK環境切替バッチファイル](http://www.torutk.com/projects/swe/wiki/Windows_JDK%E7%92%B0%E5%A2%83%E5%88%87%E6%9B%BF%E3%83%90%E3%83%83%E3%83%81%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB)
- [install-jdk.sh on GitHub](https://github.com/sormuras/bach)

# <a name="history"> History
 * version [0.6.51](https://github.com/mickey305/Foundation/releases/tag/0.6.51) deploy - 2020-02-10
 * version [0.6.0](https://github.com/mickey305/Foundation/releases/tag/0.6.0) deploy - 2018-12-08
 * version [0.5.0](https://github.com/mickey305/Foundation/releases/tag/0.5.0) deploy - 2017-11-11
 * version [0.0.1-SNAPSHOT](https://github.com/mickey305/Foundation/releases/tag/0.0.1-SNAPSHOT) deploy(Test version) - 2017-10-09
