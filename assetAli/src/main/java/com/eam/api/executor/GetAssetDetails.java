package com.eam.api.executor;
/*
 * @author  Jason.Zhao
 * @date 创建时间：2018年7月23日 下午3:26:09
 * @version 1.0
 */

import com.eam.context.Constants;
import com.eam.context.MapKey;
import com.eam.mybatis.model.AliResponseData;
import com.eam.mybatis.model.R5ActChecklists;
import com.eam.mybatis.model.U5AssetDetails;
import com.eam.mybatis.service.GetService;
import com.eam.utils.JsonUtils;
import com.eam.utils.MyUtils;
import com.eam.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.Objects.isNull;

@SuppressWarnings(value = { "rawtypes" })
public class GetAssetDetails implements Callable {
	
	
	HashMap <String, String> map = new HashMap <>();
	private String receiptNo;
	HttpServletResponse response;
	private Logger logger = LoggerFactory.getLogger(getClass());

	DecimalFormat df = new DecimalFormat("#.00");
//    GetAssetDetails(acceptanceLineId, poNumber, response);
	
	//增加字段 增加入参 盒马行行ID
	public GetAssetDetails(String acceptanceLineId,String corp,String poNumber,String language,String receiptNo,String acceptanceLineOfHEMA,String assetCode, HttpServletResponse response){
		map.put(MapKey.ACCEPTANCE_LINE_ID,acceptanceLineId);
		map.put(MapKey.PO_NUMBER,poNumber);
		map.put(MapKey.ORG_CODE,corp);
		map.put(MapKey.RECEIPT_NO,receiptNo);
		map.put(MapKey.ASSET_CODE,assetCode);
		map.put(MapKey.LANGUAGE,MyUtils.getRealLanguage(language));
		//增加字段
		map.put(MapKey.ACCEPTANCE_LINE_ID_HEMA,isNull(acceptanceLineOfHEMA) ? "" : acceptanceLineOfHEMA);
		this.response = response;
		this.receiptNo = receiptNo;
	}
	
	@Override
	public Object call(){
		logger.info(receiptNo + "," + map.get(MapKey.RECEIPT_NO));
		List <U5AssetDetails> list = GetService.getInstance().getMybatisReceiptService().getAssetDetails(map);

//		for(int i=0; i<list.size(); i++){
//			if(list.get(i).getUnitPrice()!=null&&!"".equals(list.get(i).getUnitPrice())) {
//				String formatUnitPrice = df.format(Double.parseDouble(list.get(i).getUnitPrice()));
//				list.get(i).setUnitPrice(formatUnitPrice);
//			}
//		}
		
		if ( StringUtils.isEmpty(receiptNo) )
			receiptNo = GetService.getInstance().getMybatisReceiptService().getReceiptNoByLineId(map.get(MapKey.ACCEPTANCE_LINE_ID)
					,map.get(MapKey.ACCEPTANCE_LINE_ID_HEMA)
					,map.get(MapKey.ORG_CODE)
																								);
		map.put(MapKey.OBJ_CODE,Constants.GENERAL);
		map.put(MapKey.EVT_CODE,receiptNo);
		List <R5ActChecklists> checklists = GetService.getInstance().getMybatisReceiptService().getChecklists(map);
		HashMap <String, Object> resMap = new HashMap <>();
		resMap.put(MapKey.CHECK_LIST,checklists);
		resMap.put(MapKey.RECEIPT_NO,receiptNo);
		resMap.put(MapKey.ASSET_DETAILS,list);
		ResponseUtils.writeResponse(response,
									JsonUtils.getJsonFromObject(
											new AliResponseData(Constants.INTERFACE_SUCCESS,
																null,resMap)
															   ));
		return null;
	}
	
}
