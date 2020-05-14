package com.eam.mybatis.model;

import lombok.Data;

/**************************************
 *@ClassName U5EventsApproveList
 *@Description   //EAM任务单状态历史
 *@Author jason
 *@Date 2018/9/18 - 15:40
 *@Version 1.0
 **************************************/
@Data
public class U5EventsApproveList {
	private String evtStatus;// 状态
	private String statusType;// 状态的时效性
	private String logName;// 节点名称
	private String logCode; // 节点代码
	private String logWorkNo;// 节点处理人
	private String logResult;//  处理结果
	private String logOpinion;// 意见
	private String logTime;// 处理时间
	private String expressNo;// 快递单号
	private String expressComp;// 快递公司

	private String fromStatus;//从状态
	private String toStatus;//到状态
}
