package com.mytest.springsecuritydemo2.common.exception;

import com.mytest.springsecuritydemo2.common.base.BaseErrorEnum;
import com.mytest.springsecuritydemo2.common.base.BaseException;
import com.mytest.springsecuritydemo2.common.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResponse bizExceptionHandler(HttpServletRequest req, BaseException e) {
        logger.error("请求url:[{}],发生业务异常！原因是：{}", req.getRequestURI(), e.getErrMsg());
        return BaseResponse.error(e.getErrCode(), e.getErrMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("请求url:[{}],发生空指针异常！原因是:[{}]", req.getRequestURI(), e);
        return BaseResponse.error(BaseErrorEnum.BODY_NOT_MATCH);
    }

    /**
     * 参数验证统一异常处理
     * 当使用@RequestBody注解时会抛出次异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleArgumentInValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        List<String> errorMsgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return BaseResponse.error(BaseErrorEnum.PARAM_ERROR.getCode(), String.join("|", errorMsgList));
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public BaseResponse handleArgumentInValidException(BindException e) {
        List<String> errorMsgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return BaseResponse.error(BaseErrorEnum.PARAM_ERROR.getCode(), String.join("|", errorMsgList));
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("请求url:[{}],未知异常！原因是:[{}]", req.getRequestURI(), e);
        return BaseResponse.error(BaseErrorEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
