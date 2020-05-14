
/*
 * Config.java
 *
 */
package com.eam.context;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * For EHR configuration
 *
 * @Author uthor
 */
@Component
@Data
public class Config {
	@Value("#{configuring['jdbcUrl']}")
	String jdbcUrl = null;
	@Value("#{configuring['AmpUrl']}")
	String AmpUrl = null;
	@Value("#{configuring['sendMailApi']}")
	String sendMailApi = null;
	@Value("#{configuring['sendWorkFlowApi']}")
	String sendWorkFlowApi = null;
	@Value("#{configuring['sendAssetStatusChangeApi']}")
	String sendAssetStatusChangeApi = null;
	@Value("#{configuring['sendReceiptStatusApi']}")
	String sendReceiptStatusApi = null;
	@Value("#{configuring['sendDelaySyncFAApi']}")
	String sendDelaySyncFAApi = null;
	@Value("#{configuring['driverClass']}")
	String driverClass = null;
	@Value("#{configuring['username']}")
	String username = null;
	@Value("#{configuring['password']}")
	String password = null;
	@Value("#{configuring['initialPoolSize']}")
	String initialPoolSize = null;
	@Value("#{configuring['minPoolSize']}")
	String minPoolSize = null;
	@Value("#{configuring['maxPoolSize']}")
	String maxPoolSize = null;
	@Value("#{configuring['acquireIncrement']}")
	String acquireIncrement = null;
	@Value("#{configuring['maxIdleTime']}")
	String maxIdleTime = null;
	@Value("#{configuring['maxStatements']}")
	String maxStatements = null;

}
