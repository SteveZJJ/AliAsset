
/*
 * ConfigHandler.java
 *
 */
package com.eam.context;

import org.springframework.context.ApplicationContext;

/**
 * Class to retrieve the configuration information from configinfo.properties
 *
 * @Author
 */
public class ConfigService {
    //private final Logger logger = LoggerFactory.getLogger(getClass());

    //private static String seperator = ",";
    private Config config = null;

    //
    //public List<String> getRequiredFields(String className) {
    //    Config config = getConfig();
    //    try {
    //        Field field = config.getClass().getDeclaredField(className);
    //        String value = (String) field.get(config);
    //        logger.debug("required fields are: " + value);
    //        return split(value, seperator);
    //    } catch (Exception e) {
    //        logger.info("Failed to get validation fields for " + className, e);
    //    }
    //    return new ArrayList<String>();
    //}
    //
    //private List<String> split(String inS, String inDelim) {
    //    List<String> res = new ArrayList<String>();
    //    if ((inS == null) || (inDelim == null))
    //        return res;
    //    else
    //        try {
    //            int iSlen = inS.length();
    //            int iCurPos = 0;
    //            int iDelLen = inDelim.length();
    //            while ((iCurPos != -1) && (iCurPos < iSlen)) {
    //                iCurPos = inS.indexOf(inDelim, iCurPos);
    //                if ((iCurPos == 0) && (iCurPos + iDelLen >= iSlen))
    //                    break;
    //                else if (iCurPos > 0) {
    //                    String temp = inS.substring(0, iCurPos);
    //                    res.add(temp);
    //                }
    //                if ((iCurPos != -1) && (iCurPos + iDelLen <= iSlen)) {
    //                    inS = inS.substring(iCurPos + iDelLen);
    //                    iSlen = inS.length();
    //                    iCurPos = 0;
    //                }
    //            }
    //
    //            if ((iCurPos == -1) && (inS.length() > 0))
    //                res.add(inS);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    return res;
    //}

    public Config getConfig() {
        if (config == null) {
            ApplicationContext appContext = EamContextUtil.getApplicationContext();
            config = appContext.getBean(Config.class);
        }
        return config;
    }

    static class ConfigServiceHolder {
        static ConfigService instance = new ConfigService();
    }

    private ConfigService() {
    }

    public static ConfigService getInstance() {
        return ConfigServiceHolder.instance;
    }

    public static void main(String[] args) {
        String str = "1";
        System.out.println(Boolean.parseBoolean(str));
        
    }
}
