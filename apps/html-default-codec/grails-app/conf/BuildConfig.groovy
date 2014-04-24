grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.work.dir="target/work"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.fork.run = true
grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://repo.grails.org/grails/core"

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
        test 'net.sourceforge.nekohtml:nekohtml:1.9.18'
        test 'net.sourceforge.htmlunit:htmlunit:2.12'
        test 'net.sourceforge.htmlunit:htmlunit-core-js:2.12'
    }

    plugins {
        runtime "${System.getProperty('hibernatePluginVersion',':hibernate:3.6.10.13')}"
        build ":tomcat:7.0.52.1"
        compile ":jquery:1.11.0.2"
        compile ':scaffolding:2.1.0'

        runtime ":database-migration:1.3.8"
        
        test ':functional-test:2.0.0', {
            excludes 'htmlunit'
        }

    }
}
