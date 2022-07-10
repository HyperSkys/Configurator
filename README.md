# Configurator Library
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/github/languages/code-size/HyperSkys/Configurator?color=cyan&label=Size&labelColor=000000&logo=GitHub&style=for-the-badge)
![GitHub](https://img.shields.io/github/license/HyperSkys/Configurator?color=violet&logo=GitHub&labelColor=000000&style=for-the-badge)
![Discord](https://img.shields.io/discord/898154272636678196?color=5865F2&label=Discord&logo=Discord&labelColor=23272a&style=for-the-badge)

**Configurator** is a universal configuration library.

### Download Instructions

You can download Configurator and use it on your projects by heading to the releases tab and downloading the latest release available, if you download a beta releasing you agree that any bugs that occur are expected.

### API Introduction

Here is an example of making a configuration file for your plugin, it is really this simple.

```java
Configuration configFile = new Configuration("config.yml");
Configurator.setupConfigurator(this);
```

### Project Intergration

There are two major different types of ways of integrating this API into your project, below will list the way of integrating while using Maven and Gradle.


#### Maven
For maven please add these to your repositories and dependencies.
```
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
``` 
```
 <dependency>
    <groupId>com.github.HyperSkys</groupId>
    <artifactId>Configurator</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```

-----------------------

#### Gradle
For gradle add this to your repositories and dependencies.
```
maven { url 'https://jitpack.io' }
```
```
implementation 'com.github.HyperSkys:Configurator:1.0.0-RELEASE'
```



### Discord

You can join my discord using this invite https://discord.gg/Y59DddqZZR please join the server if you have any issues or suggestions that you would like to make do not make random issues when you could just use the discord.

## License
This project is licensed under [Eclipse Public License](https://github.com/HyperSkys/Configurator/blob/main/LICENSE)