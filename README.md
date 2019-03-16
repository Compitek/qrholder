# qrholder
https://github.com/Compitek/qrholder
https://app.swaggerhub.com/apis/n6163957/qrHolder/1.0.0

<h2>Initial:</h2>
<ul>
<li>Create qrHolder directory
<li> With CLI in /qrHolder set command:
(<a href="https://docs.gradle.org/current/userguide/build_init_plugin.html#sec:kotlinapplication_">according to docs.gradle.org docs</a>)
<br>
<i>
gradle init --type kotlin-application
</i>
<li> Add swagger api from <a href="https://app.swaggerhub.com/apis/n6163957/qrHolder/1.0.0">swaggerhub</a> to <i>\resources\swagger</i> directory
<li> Test it with CLI:<br>
<b><i>
gradle run
</i></b>
<br>
hope you see "hello world" result.
</ul>
<h2>step1: build.gradle</h2>
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
Add <a href="https://spring.io/guides/tutorials/spring-boot-kotlin/#_compiler_options">compiler options</a>.
<li> remove App.kt and it test. Add <a href="https://kotlinlang.org/docs/tutorials/spring-boot-restful.html#creating-the-application-class">Application class</a> instead. 
<br>Add simple controller.

<li>Build and start your project:
 <i><b> 
  gradlew bootRun
 </b></i>
 