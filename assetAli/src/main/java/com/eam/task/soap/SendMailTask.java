package com.eam.task.soap;

import com.eam.context.Constants;
import com.eam.mybatis.model.R5MailEvents;
import com.eam.mybatis.model.R5MailTemplate;
import com.eam.utils.MyUtils;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月5日 上午11:27:39
 * @version 1.0
 */

public class SendMailTask implements Callable <String> {


	private final Logger logger = LoggerFactory.getLogger(getClass());
	private List <String> workNos;
	private String title;
	private String content;
	private int rule;
	
	public SendMailTask(R5MailEvents mailEvents,R5MailTemplate mailTemplate){
		workNos = new ArrayList <>();
		String[] workNoStr = mailFormat(mailTemplate.getMat_mail(),mailEvents).split(";");


		for(int i=0; i<workNoStr.length; i++) {
			this.workNos.add(String.format("\"%s\"",workNoStr[i]));
		}
		this.title = mailFormat(mailTemplate.getMat_subject(),mailEvents);
		this.content = mailFormat(mailTemplate.getMat_text(),mailEvents);
		this.rule = mailEvents.getMer_rulecode().getMailRuleCode();
	}
	
	private String mailFormat(String str,R5MailEvents mailEvents){
		AtomicReference <String> newStr = new AtomicReference <>(str);
		//return str
		//			   .replaceAll("%15",checkParamEmpty(mailEvents.getMAE_PARAM15()))
		//			   .replaceAll("%14",checkParamEmpty(mailEvents.getMAE_PARAM14()))
		//			   .replaceAll("%13",checkParamEmpty(mailEvents.getMAE_PARAM13()))
		//			   .replaceAll("%12",checkParamEmpty(mailEvents.getMAE_PARAM12()))
		//			   .replaceAll("%11",checkParamEmpty(mailEvents.getMAE_PARAM11()))
		//			   .replaceAll("%10",checkParamEmpty(mailEvents.getMAE_PARAM10()))
		//			   .replaceAll("%9",checkParamEmpty(mailEvents.getMAE_PARAM9()))
		//			   .replaceAll("%8",checkParamEmpty(mailEvents.getMAE_PARAM8()))
		//			   .replaceAll("%7",checkParamEmpty(mailEvents.getMAE_PARAM7()))
		//			   .replaceAll("%6",checkParamEmpty(mailEvents.getMAE_PARAM6()))
		//			   .replaceAll("%5",checkParamEmpty(mailEvents.getMAE_PARAM5()))
		//			   .replaceAll("%4",checkParamEmpty(mailEvents.getMAE_PARAM4()))
		//			   .replaceAll("%3",checkParamEmpty(mailEvents.getMAE_PARAM3()))
		//			   .replaceAll("%2",checkParamEmpty(mailEvents.getMAE_PARAM2()))
		//			   .replaceAll("%1",checkParamEmpty(mailEvents.getMAE_PARAM1()));
		AtomicReference <BeanMap> map
				= new AtomicReference <>(new BeanMap(mailEvents));
		
		// 各Lambda表达式详情
		// 1.只处理MAE_PARAM开头的值
		// 2.将处理顺序倒序排列。(需要先replace %15 再 replace %1 ,否则 就错误了)
		// 3.执行replace操作，将%k 替换为 MAE_PARAM k  对应的 值。(如果为null,会报错，处理为 "")
		map.get().entrySet().stream()
				.filter((s) -> s.getKey().toString().startsWith(Constants.SENDMAIL_PARAM))
				.sorted((p1,p2) -> ( p2.getKey().toString().compareTo(p1.getKey().toString()) ))
				.forEach(e -> newStr.set(
						newStr.get().replace(
								e.getKey().toString().replace(Constants.SENDMAIL_PARAM,Constants.SENDMAIL_REPLACE),
								StringUtils.isEmpty(e.getValue()) ? Constants.EMPTY_STRING : e.getValue().toString()))
						);
		return newStr.get();
	}
	
	@Override
	public String call(){
		HashMap <String, Object> map = new HashMap <>();
		map.put("workNos",workNos);
		map.put("title",title);
		map.put("content",StringToMail(content));
		map.put("recipientsRule", rule);
		logger.info(map.toString());
		return SendMailClient.getInstance().sendMailClient(map);
		
	}
	
	private String StringToMail(String s){
		return s.replace("\n","</br>");
	}
}
