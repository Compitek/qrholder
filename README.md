# qrholder
https://github.com/Compitek/qrholder
https://app.swaggerhub.com/apis/n6163957/qrHolder/1.0.0


"How to" start an api-first project with gradle, Springboot, kotlin  and swagger codegen


<h2>Initial:</h2>
<ul>
<li>Create qrHolder directory
<li> With CLI in /qrHolder set command
(<a href="https://docs.gradle.org/current/userguide/build_init_plugin.html#sec:kotlinapplication_">according to docs.gradle.org docs</a>):
<br><b><i>
gradle init --type kotlin-application
</b></i>
<li> Add swagger api from <a href="https://app.swaggerhub.com/apis/n6163957/qrHolder/1.0.0">swaggerhub</a> to <i>\resources\swagger</i> directory
<li> Test it with CLI:<br>
<b><i>
gradle run
</i></b>
<br>
hope you see "hello world" result.
</ul>


<h2>Step1: Kotlin / Spring Boot application with simple controller</h2>
<ul>
<li>Across <a href="https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#using-boot-gradle">Spring-boot reference</a>, 
we are going to 
<a href="https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/gradle-plugin/reference/html/">
Spring Boot Gradle Plugin Reference</a>.
Add org.springframework.boot, java, io.spring.dependency-management plugins, 
and spring-boot-starter-web, dependencies.<br>
Add spring-boot-starter-data-jpa  dependency, but comment it for now. 
(According to <a href="https://docs.spring.io/dependency-management-plugin/docs/current-SNAPSHOT/reference/html/">Dependency Management Plugin</a> either.)
<br>Add spring-boot-starter-web and spring-boot-starter-web dependencies.
<li>Add "repositories" task within "jcenter()" 
<br>
<li>As per <a href="https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/gradle-plugin/reference/html/#packaging-executable">packaging executable archives chapter</a>
 and with <a href="https://docs.gradle.org/current/userguide/war_plugin.html">war plugin</a>, add 'war' plugn and bootWar task within setting: 
 <br><i><b> 
 mainClassName ='net.compitek.qrholder.Application' 
</b></i>
 
<li>According to <a href="https://kotlinlang.org/docs/tutorials/spring-boot-restful.html">kotlin tutorial for springboot</a> and 
<a href="https://spring.io/guides/tutorials/spring-boot-kotlin/">spring-boot kotlin tutorial</a>  add kotlin.jvm plugin, kotlin.plugin.jpa and 
<a href="https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support">kotlin.plugin.spring  (instead of 'all-open')</a> plugin.<br>
Add kotlin-stdlib-jdk8, kotlin-reflect, jackson-module-kotlin dependencies.<br>
Add <a href="https://spring.io/guides/tutorials/spring-boot-kotlin/#_compiler_options">
compiler options</a>. Add "targetCompatibility" and "version" parameters. 

<li> Remove App.kt and it test. Add <a href="https://kotlinlang.org/docs/tutorials/spring-boot-restful.html#creating-the-application-class">Application class</a> instead. 
<br>Add simple controller.

<li>Build and start your project in CLI:
<i><b> 
gradlew bootRun
</b></i>
Check your controller on <a href="http://localhost:8080/test">http://localhost:8080/test</a> after start.<br>
Shutdown application with "Stop" button or Ctrl+f2.
  
<li> Configure your project in Intellij Idea;
check gradle version: menu Run > Settings > 
"Build, execution, deployment" > "Build tools" > Gradle.
Check "use local gradle distribution" and set correct gradle path.
<li> Build and start your project in Intellij Idea; 
open Gradle panel   (menu View > "Tool windows" > Gradle) 
and double click "clean" (Tasks > build > clean) 
and "bootRun" (Tasks > application > bootRun).<br>
Check your controller on <a href="http://localhost:8080/test">http://localhost:8080/test</a> after start.<br>
Shutdown application with "Stop" button or Ctrl+f2.
</ul>
 
<h2>Step2: Add swagger-codegen and apply it to openapi.yaml</h2>
<ul>
<li>From google "swagger codegen" search - through <a href="https://github.com/swagger-api/swagger-codegen#gradle-integration"> 
swagger-codegen gitHub page, gradle integration</a> we are going to 
official <a href="https://github.com/int128/gradle-swagger-generator-plugin">Gradle Swagger Generator Plugin</a>.
So, add org.hidetake.swagger.generator plugin and one of their dependencies
(swaggerCodegen 'io.swagger.codegen.v3:swagger-codegen-cli:3.0.0') to the build.gradle.
<br> Add swaggerSources task either. Edit this task: change project name, path to yaml file  <i>openapi.yaml</i>, add "<a href="https://github.com/int128/gradle-swagger-generator-plugin#use-configuration-file">configFile</a>"  option .
<br> Add config file<i>codegen.json</i> to the root folder and set standard settings (libraries, packages...).
Set <i>"interfaceOnly": true</i>, <br>because we don't need default java realisation, but only interfaces.
<li> Run "generateSwaggerCode" gradle task and look /build/swagger-code-qrholder/src/main/java/ folder.
Codegen generate api interface and models from openapi.yaml; 
<br>Mark  /build/swagger-code-qrholder/src/main/java folder as "source root" in IDEA 
(R.Click on directory > "Mark Directory as" > "Generated sources root").
<li> Delete "TestController.kt". 
<br>Create QrApiImpl kotlin class, implement QrApi interface. Kotlin doesn't see java interface? 
According <a href="https://kotlinlang.org/docs/reference/using-gradle.html#targeting-the-jvm">
Kotlin "Using Gradle" tutorial</a> we should add "sourceSets" task, 
with "main.java.srcDirs" property. We don't need "main.kotlin.srcDirs" property.
<br><a href="https://github.com/int128/gradle-swagger-generator-plugin#build-generated-code">
We can use</a> ${swaggerSources.qrholder.code.outputDir} variable (use double quotes!).
<li> QrApi can't get io.swagger.annotations import. So, we have to add swagger-annotations dependency in build.gradle.
<li>Create QrApiImpl.generateTheReport implementation  (just for example).
<br> Write:
<br><b><i>
var row = CommonReportRow(); row.count=0; row.namespace="/test";<br>
var rows = mutableListOf<CommonReportRow>(); rows.add(row);<br>
return ResponseEntity(rows, HttpStatus.OK)
</b></i>
         
<li>Build and start your project in CLI or IDEA (bootRun task).     
Check your controller on <a href="http://localhost:8080/qr/report/common">http://localhost:8080/qr/report/common</a> after start.<br>
And get http status 200 and empty json array:
<br><b><i>
[{"count":0,"namespace":"/test"}]
</b></i>
 
