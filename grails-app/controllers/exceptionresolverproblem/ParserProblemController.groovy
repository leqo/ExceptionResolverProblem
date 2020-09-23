package exceptionresolverproblem

import groovy.json.JsonSlurper

class ParserProblemController {

    def index() {
        String s = getClass().getResource('/resources/sample.json').text
        JsonSlurper js = new JsonSlurper()
        js.parseText(s)
    }
}
