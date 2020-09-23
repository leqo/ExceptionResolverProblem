package org.grails.web.errors

import org.grails.web.util.WebUtils
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class InterruptibleGrailsExceptionResolver extends GrailsExceptionResolver {
    @Override
    protected void setStatus(HttpServletRequest request, HttpServletResponse response, ModelAndView mv, Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        final InterruptibleGrailsWrappedRuntimeException gwre = new InterruptibleGrailsWrappedRuntimeException(servletContext, e)
        mv.addObject(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, gwre)
        mv.addObject(WebUtils.EXCEPTION_ATTRIBUTE, gwre)
    }
}
