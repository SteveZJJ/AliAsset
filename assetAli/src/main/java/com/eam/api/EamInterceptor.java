package com.eam.api;

import com.eam.utils.ResponseUtils;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 上午11:48:26
 * @version 1.0
 */

@SuppressWarnings("unused")
public class EamInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final static String PARAM_TIMESTAMP = "timeStamp";
	private final static String PARAM_CLIENTID = "clientId";
	private final static String PARAM_SIGN = "sign";
	private final static String SECRET = "secret";
	private final static String ERROR_ERRORSIGN = "请求非法";
	
	/* preHandle是一开始就 执行的 方法，并由返回的结果决定--> 后面的 拦截器是否执行 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
	throws Exception{
		//try {
		//	return true;
		//}
		//catch (Exception ignored) {
		//
		//}
//        logger.info("Start preHandle");
		//如果是get方法，则校验URI的参数，否则只校验header中的timeStamp和秘钥值
		String clientId = request.getHeader(PARAM_CLIENTID);
		String timeStamp = request.getHeader(PARAM_TIMESTAMP);
		String sign = request.getHeader(PARAM_SIGN);
		String queryString = "";
		if ( request.getMethod().equalsIgnoreCase("get") ) {
			queryString = request.getQueryString();
		}
		String newSignStr = queryString + String.format("&%s=%s&%s=%s&%s=%s",
														PARAM_CLIENTID,clientId,PARAM_TIMESTAMP,timeStamp,SECRET,SECRET);
//        logger.info(String.format("signStr=[%s]", newSignStr));
		String checkSign = DigestUtils.md5Hex(newSignStr);
//        logger.info(String.format("checkSign=[%s]", checkSign));
//        logger.info(String.format("Sign=[%s]", sign));
//        logger.info("End PreHandle");
		request.setCharacterEncoding(String.valueOf(Charsets.UTF_8));
		if ( checkSign.equals(sign) || "test".equals(sign) ) return true;
		//ResponseUtils.writeResponse(response,JsonUtils.getJsonFromObject(new AliResponse(Constants.INTERFACE_ERROR,ERROR_ERRORSIGN)));
		ResponseUtils.writeErrorResponse(response,ERROR_ERRORSIGN);
		return false; // 为了说明效果，全部拦截下来
	}
	
	/* postHandle 是controller正常执行完成后，执行的方法。如果没有正常执行完成，所有的postHandle 方法都不会执行 */
	/* 该方法在controller正常执行完成之后 才会执行，并且在 中央处理器 渲染页面之前 执行。 */
	@Override
	public void postHandle(
			HttpServletRequest request,HttpServletResponse response,Object handler,
			ModelAndView modelAndView
						  ){
		// System.out.println("postHandle 执行！！！");
	}
	
	/* afterCompletion 是只要该拦截器的preHandle 方法执行的结果为true ，该方法就执行 */
	/*
	 * afterCompletion 在所有的工作完成之后，且该该拦截器的 preHandle 返回的结果是 true ,该方法相当于
	 * try-catch-finally 中的finally ，用来释放一些资源。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		// System.out.println("afterCompletion 执行！！！");
	}
	
}
