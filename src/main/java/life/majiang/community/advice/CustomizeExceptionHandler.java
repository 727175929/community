package life.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpServer;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustiomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response){
        //HttpStatus status = getStaus(request);

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO ;
            //返回Json
            if(ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO  = ResultDTO.errorOf(CustiomizeErrorCode.SYS_ERROR);
            }
            try{
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException ioe){

            }
            return  null;
        }
        else {
            if(ex instanceof CustomizeException){
                model.addAttribute("message", ex.getMessage());
            }else {
                model.addAttribute("message",CustiomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

//    private HttpStatus getStaus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if(statusCode == null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
