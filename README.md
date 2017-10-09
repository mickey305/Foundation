# Foundation(infrastructure component for Java)

| | branch | travis ci | JitPack |
|:---:|:---|:---|:---|
| release | master | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=master)](https://travis-ci.org/mickey305/Foundation) | [![](https://jitpack.io/v/mickey305/Foundation.svg)](https://jitpack.io/#mickey305/Foundation) |
| - | develop | [![Build Status](https://travis-ci.org/mickey305/Foundation.svg?branch=develop)](https://travis-ci.org/mickey305/Foundation) |  |

<!--

# Installation(Pattern 1)
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

# Installation(Pattern 2) - how to use the JitPack service
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

# History
 * version 0.0.1-SNAPSHOT deploy(Test version) - 2017-10-09
