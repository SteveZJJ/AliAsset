package com.eam.api.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import com.eam.context.Constants;
import com.eam.mybatis.model.AliResponse;
import com.eam.mybatis.model.AliResultProcedure;
import com.eam.mybatis.model.R5ActChecklists;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.ResponseUtils;

public class NewReceiptDetails implements Callable <Object> {
	
	//R5ActChecklists checklists;
	List <R5ActChecklists> checklist_L = new ArrayList <R5ActChecklists>();
	HttpServletResponse response;
	
	public NewReceiptDetails( R5ActChecklists object , HttpServletResponse response ) {
		//this.checklists = object;
		this.checklist_L.add( object );
		this.response = response;
	}
	
	public NewReceiptDetails( List <R5ActChecklists> object , HttpServletResponse response ) {
		this.checklist_L = object;
		this.response = response;
	}
	
	@Override
	public Object call() throws Exception {
		boolean isSuccess=true;
		for (R5ActChecklists checklists : checklist_L) {
			System.out.println(checklists.toString());
			String errorMsg="";
			AliResultProcedure res1 = GetService.getInstance().getMybatisReceiptService().newReceiptDetails( checklists );
			System.out.println(res1.toString());
			if ( res1.getFlag().equals( Constants.INTERFACE_ERROR ) ) {
				errorMsg+=res1.getMessage();
				ResponseUtils.writeResponse( response ,
											 JsonUtils.getJsonFromObject( new AliResponse( res1.getFlag() , res1.getMessage() ) ) );
				isSuccess=false;
				//break;
			}
		}
		if ( isSuccess )
		ResponseUtils.writeResponse( response ,
									 JsonUtils.getJsonFromObject( new AliResponse( Constants.INTERFACE_SUCCESS , null ) ) );
		return null;
	}
	
}
