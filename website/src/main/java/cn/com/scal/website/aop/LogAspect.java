package cn.com.scal.website.aop;

//import cn.com.scal.components.annotations.LogAnnotation;
//import cn.com.scal.components.domain.CurrentUser;
//import cn.com.scal.components.domain.LogsEntity;
//import cn.com.scal.components.dto.Api;
//import cn.com.scal.components.dto.LogsEntityDTO;
//import cn.com.scal.components.enums.LogsStatus;
//import cn.com.scal.components.service.ICommonService;
//import cn.com.scal.components.utils.Consts;
//import cn.com.scal.components.utils.DTFormatUtil;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.lang.reflect.Method;
//import java.util.Arrays;

/**
 * Created by vslimit on 15/8/26.
 */
//@Aspect
//@Component
public class LogAspect {

//    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class.getSimpleName());
//
//    private static final String methods = "GET,PUT,DELETE,POST";
//    private static final String POST = "POST";
//    private static final String GET = "GET";
//    private static final String PUT = "PUT";
//    private static final String DELETE = "DELETE";
//    private static final String LOGIN = "login";
//    @Resource(name = "commonServiceImpl")
//    protected ICommonService<LogsEntity, LogsEntityDTO, Integer> commonService;
//
//
////    @Pointcut("execution(public * cn.com.scal.website.action.controller..*(..))")
//    private void aspect() {
//    }
//
////    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
//    public void afterThrow(JoinPoint joinPoint, Exception ex) {
//        LogsEntity logsEntity = initLogs(joinPoint);
//        logsEntity.setStatus(LogsStatus.ERROR);
//        logsEntity.setResult(ex.getClass().getName());
//        commonService.create(logsEntity);
//        logger.error(ex.getMessage());
//    }
//
//
////    @Around("aspect()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        LogsEntity logsEntity = initLogs(joinPoint);
//
//        try {
//            Object result = joinPoint.proceed();
//            if (null != logsEntity) {
////                Api api = (Api) result;
////                logsEntity.setCode(api.getCode());
////                logsEntity.setResult(api.getTip());
////                logsEntity.setStatus(Api.SUCCESS_CODE == api.getCode().intValue() ? LogsStatus.SUCCESS : LogsStatus.FAILURE);
//                commonService.create(logsEntity);
//            }
//            return result;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw e;
//        }
//    }
//
//    private LogsEntity initLogs(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        String requestURI = request.getRequestURI();
//        String login;
//        if (requestURI.endsWith(LOGIN)) {
//            login = request.getMethod().equals(POST) ? request.getParameter("login") : "";
//        } else {
//            //读取request中的用户
//            CurrentUser user = (CurrentUser) session.getAttribute(Consts.CURRENT_USER);
//            login = user.getEmpNo();
//        }
//        //请求的IP
//        String ip = request.getRemoteAddr();
//        Class targetClass = joinPoint.getTarget().getClass();
//        String targetName = targetClass.getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        logger.info(methodName);
//        Method[] methods = targetClass.getMethods();
//        LogsEntity logsEntity = new LogsEntity();
//        logsEntity.setAction(methodName);
//        logsEntity.setIp(methodName);
//        logsEntity.setLogin(login);
//        logger.info(Arrays.toString(arguments));
//        System.out.println(Arrays.toString(arguments));
////        logsEntity.setParams();
//        logsEntity.setIp(ip);
//        logsEntity.setTarget(targetName);
//        logsEntity.setCreateDt(DTFormatUtil.getCurrentTime());
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
//                    LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
//                    if (null != annotation) {
//                        logsEntity.setClassify(annotation.value());
//                        logsEntity.setDescription(annotation.name());
//                    }
//                    break;
//                }
//            }
//        }
//
//        return logsEntity;
//    }

}
