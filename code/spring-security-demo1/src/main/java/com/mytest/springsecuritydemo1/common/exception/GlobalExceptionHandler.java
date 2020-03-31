package com.mytest.springsecuritydemo1.common.exception;

import com.mytest.springsecuritydemo1.common.base.BaseErrorEnum;
import com.mytest.springsecuritydemo1.common.base.BaseException;
import com.mytest.springsecuritydemo1.common.base.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public BaseResp bizExceptionHandler(HttpServletRequest req, BaseException e) {
        logger.error("请求url:[{}],发生业务异常！原因是：{}", req.getRequestURI(), e.getErrMsg());
        return BaseResp.error(e.getErrCode(), e.getErrMsg());
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
    public BaseResp exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("请求url:[{}],发生空指针异常！原因是:[{}]", req.getRequestURI(), e);
        return BaseResp.error(BaseErrorEnum.BODY_NOT_MATCH);
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
    public BaseResp handleArgumentInValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        List<String> errorMsgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return BaseResp.error(BaseErrorEnum.PARAM_ERROR.getCode(), String.join("|", errorMsgList));
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public BaseResp handleArgumentInValidException(BindException e) {
        List<String> errorMsgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return BaseResp.error(BaseErrorEnum.PARAM_ERROR.getCode(), String.join("|", errorMsgList));
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResp handleNoPermissionException(HttpServletRequest req, AccessDeniedException e) {
        logger.error("请求url:[{}], 没有权限访问", req.getRequestURI());
        return BaseResp.error(BaseErrorEnum.NOT_PERMISSION.getCode(), e.getMessage());
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResp exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("请求url:[{}],未知异常！原因是:[{}]", req.getRequestURI(), e);
        return BaseResp.error(BaseErrorEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
