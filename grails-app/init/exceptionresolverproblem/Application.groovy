package exceptionresolverproblem

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.grails.web.errors.InterruptibleGrailsExceptionResolver

class Application extends GrailsAutoConfiguration {

    @Override
    Closure doWithSpring() {
        def beans = {
            exceptionResolver(InterruptibleGrailsExceptionResolver) {
                exceptionMappings = ['java.lang.Exception': '/error']
            }
        }
        return beans
    }

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}