package com.eam.mybatis.service;

import com.eam.context.EamContextUtil;
import org.springframework.context.ApplicationContext;

/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月4日 下午5:37:24
 * @version 1.0
 */

public class GetService {
	
	private MybatisPickupReturnService mybatisPickupReturnService = null;
	private MybatisReceiptService mybatisReceiptService = null;
	private MybatisCodeDescService mybatisCodeDescService = null;
	private MybatisJobService mybatisJobService = null;
	private MybatisDataSyncService mybatisDataSyncService = null;
	private MybatisScrapDisposeService mybatisScrapDisposeService = null;
	private MybatisTransferAllocateService mybatisTransferAllocateService = null;
	private MybatisInventoryService mybatisInventoryService = null;

	public MybatisPickupReturnService getMybatisPickupReturnService(){
		if ( mybatisPickupReturnService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisPickupReturnService = appContext.getBean(MybatisPickupReturnService.class);
		}
		return mybatisPickupReturnService;
	}

	public MybatisScrapDisposeService getMybatisScrapDisposeService(){
		if ( mybatisScrapDisposeService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisScrapDisposeService = appContext.getBean(MybatisScrapDisposeService.class);
		}
		return mybatisScrapDisposeService;
	}

	public MybatisTransferAllocateService getMybatisTransferAllocateService(){
		if ( mybatisTransferAllocateService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisTransferAllocateService = appContext.getBean(MybatisTransferAllocateService.class);
		}
		return mybatisTransferAllocateService;
	}

	public MybatisReceiptService getMybatisReceiptService(){
		if ( mybatisReceiptService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisReceiptService = appContext.getBean(MybatisReceiptService.class);
		}
		return mybatisReceiptService;
	}
	
	public MybatisCodeDescService getMybatisCodeDescService(){
		if ( mybatisCodeDescService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisCodeDescService = appContext.getBean(MybatisCodeDescService.class);
		}
		return mybatisCodeDescService;
	}

	public MybatisDataSyncService getMybatisDataSyncService(){
		if ( mybatisDataSyncService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisDataSyncService = appContext.getBean(MybatisDataSyncService.class);
		}
		return mybatisDataSyncService;
	}
	
	public MybatisJobService getMybatisJobService(){
		if ( mybatisJobService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisJobService = appContext.getBean(MybatisJobService.class);
		}
		return mybatisJobService;
	}

	public MybatisInventoryService getMybatisInventoryService(){
		if ( mybatisInventoryService==null ) {
			ApplicationContext appContext = EamContextUtil.getApplicationContext();
			mybatisInventoryService = appContext.getBean(MybatisInventoryService.class);
		}
		return mybatisInventoryService;
	}



	static class GetServiceHolder {
		static GetService instance = new GetService();
	}
	
	public static GetService getInstance(){
		return GetServiceHolder.instance;
	}
}
