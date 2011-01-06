class DemoController {
    
    def index = {
        forward action: 'doit'
    }
    
    def doit = {
        render "action name in filter: ${params.actionNameInFilter}"
    }
    
    def showLoggerNames = {
        render "Alpha Logger Name: ${params.alphaFilterLoggerName} <br/> Beta Logger Name: ${params.betaFilterLoggerName}"
    }
}