
<p align="center">
  <img src="asset/logo/brick.svg" height="100">
  <img src="asset/logo/foundation_title.png" height="100">
  <img src="asset/logo/brick.svg" height="100">
</p>

# Overview

`Foundation` is part of infrastructure component for Java. You can easily extend the basic functions. This makes it easier to implement event driven processing and design patterns which are not provided by the standard API.

The following table shows status status of external CI service.

| | branch | travis ci | AppVeyor |
|:---:|:---|:---|:---|
| release | master | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=master)](https://travis-ci.org/mickey305/Foundation) | [![Build status](https://ci.appveyor.com/api/projects/status/kw8u113ot8x8by9i/branch/master?svg=true)](https://ci.appveyor.com/project/mickey305/foundation/branch/master) |
| - | develop | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=develop)](https://travis-ci.org/mickey305/Foundation) | [![Build status](https://ci.appveyor.com/api/projects/status/kw8u113ot8x8by9i/branch/develop?svg=true)](https://ci.appveyor.com/project/mickey305/foundation/branch/develop) |

### Repository hosting services
| Service Name | Link |
|:---|:---|
| JitPack | [![](https://jitpack.io/v/mickey305/Foundation.svg)](https://jitpack.io/#mickey305/Foundation) |
| Bintray | [![Download](https://api.bintray.com/packages/mickey305/maven/foundation/images/download.svg)](https://bintray.com/mickey305/maven/foundation/_latestVersion) |

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
- Gradle `>= 3.5-rc-2` (in the case of gradle project)

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

# <a name="installation2"> Installation(Pattern 2)

how to use the JitPack service
[![](https://jitpack.io/v/mickey305/Foundation.svg)](https://jitpack.io/#mickey305/Foundation)

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
