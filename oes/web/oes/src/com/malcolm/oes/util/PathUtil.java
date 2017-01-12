package com.malcolm.oes.util;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;

public class PathUtil {

    public static String getFullPath(String path) {
        if (null == path) {
            path = "";
        }
        String urlPrefix = Constants.APP_URL_PREFIX;
        if (!StringUtil.isEmpty(urlPrefix)) {
            urlPrefix += "/";
        }
        return AppContext.getContextPath() + "/" + urlPrefix + path;
    }
}
