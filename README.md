
<p align="center">
  <img src="asset/brick.svg" height="50"> <img src="asset/foundation_title.svg" height="50">
</p>

# Overview

`Foundation` is part of infrastructure component for Java.

| | branch | travis ci | JitPack |
|:---:|:---|:---|:---|
| release | master | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=master)](https://travis-ci.org/mickey305/Foundation) | [![](https://jitpack.io/v/mickey305/Foundation.svg)](https://jitpack.io/#mickey305/Foundation) |
| - | develop | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=develop)](https://travis-ci.org/mickey305/Foundation) |  |

# Features

![Library Version](https://img.shields.io/badge/Foundation%20library-v0.5.0-green.svg?style=flat)
![Java Version](https://img.shields.io/badge/JDK-1.7-yellowgreen.svg?style=flat)
[![Apache License](http://img.shields.io/badge/license-Apache2.0-blue.svg?style=flat)](LICENSE)

- [x] source compatibility `java7`
- [x] easily usable
- [x] some design patterns
- [x] some utility classes
- [x] multi-thread API
- [x] downcast API(unsafe)
<!--
- [x] benchmark API
- [x] java source builder annotations
-->
- [x] and more...

# Contents
- [Usage](#usage)
- [Requirements](#requirements)
- [Installation Pattern#1](#installation1)
- [Installation Pattern#2](#installation2)
- [History](#history)

# <a name="usage"> Usage

# <a name="requirements"> Requirements
- JDK `>= 1.7`
- Gradle `>= 3.5-rc-2`

<!--

# <a name="installation1"> Installation(Pattern 1)
## 1 - Register repository in local library

```
repositories {
  maven { url 'http://mickey305.github.io/Foundation/repository/' }
  ...
}
```

## 2 - Compile library

```
dependencies {
  // newest version
  compile 'com.mickey305:Foundation:+@jar'
  ...
}
```

```
dependencies {
  // target version - e.g. version 0.0.1-SNAPSHOT
  compile 'com.mickey305:Foundation:0.0.1-SNAPSHOT'
  ...
}
```
-->

# <a name="installation2"> Installation(Pattern 2) - how to use the JitPack service
## 1 - Register repository in local library

```
repositories {
  maven { url 'https://jitpack.io' }
  ...
}
```

## 2 - Compile library

```
dependencies {
  // target version - e.g. version 0.0.1-SNAPSHOT
  compile 'com.github.mickey305:Foundation:0.0.1-SNAPSHOT'
  ...
}
```

# <a name="history"> History
 * version 0.5.0 deploy - 2017-11-11
 * version 0.0.1-SNAPSHOT deploy(Test version) - 2017-10-09
