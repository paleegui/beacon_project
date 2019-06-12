# MDD.RIDDEN APLLICATION

MDD.RIDDEN APLLICATION is 

## Documentation
Altbeacon library:
The [project website](http://altbeacon.github.io/android-beacon-library/) has [full documentation](http://altbeacon.github.io/android-beacon-library/documentation.html) including [Javadocs.](http://altbeacon.github.io/android-beacon-library/javadoc/)

## Changes from the 0.x library version

This library has changed significantly from the 0.x library version and is now designed to work with
open AltBeacons which fully support Android without any intellectual property restrictions.  For
more information on how to migrate projects using the 0.x APIs to the 2.x APIs, see
[API migration.](api-migrate.md)

## Downloads

### Binary

You may [download binary releases here.](http://altbeacon.github.io/android-beacon-library/download.html)

### JCenter

Add JCenter to your build file's list of repositories.

```groovy
repositories {
    jcenter()
}
```

to use the JCenter Repository

```groovy
dependencies {
    ...
    //Altbeacon library
    implementation 'org.altbeacon:android-beacon-library:${altbeacon.version}'
    
    //goson convert object to json
    implementation 'com.google.code.gson:gson:2.8.5'
    
    //Tag 
    implementation 'co.lujun:androidtagview:1.1.7'
    implementation 'androidx.appcompat:appcompat:1.0.1'

    //Firebase
    implementation 'com.google.firebase:firebase-core:16.0.7'
    implementation 'com.google.firebase:firebase-firestore:18.0.1'
    ...
}
```
