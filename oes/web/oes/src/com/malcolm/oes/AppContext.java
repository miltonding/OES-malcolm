package com.malcolm.oes;

import java.util.HashMap;
import java.util.Map;

import com.malcolm.oes.model.User;

public class AppContext {

    private static ThreadLocal<AppContext> appConetxtMap = new ThreadLocal<AppContext>();

    private Map<String, Object> objects = new HashMap<String, Object>();

    private static String contextPath;

    private AppContext() {
    }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        if (null == objects) {
            objects = new HashMap<String, Object>();
        }
        this.objects = objects;
    }

    public void addObject(String key, Object object) {
        this.objects.put(key, object);
    }

    public void removeObject(String key) {
        this.objects.remove(key);
    }

    public Object getObject(String key) {
        return this.objects.get(key);
    }

    public static AppContext getContext() {
        AppContext appContext = appConetxtMap.get();
        if (null == appContext) {
            appContext = new AppContext();
            appConetxtMap.set(appContext);
        }
        return appContext;
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public User getUser() {
        return (User) objects.get(Constants.APP_CONTEXT_USER);
    }

    public String getUserName() {
        User user = (User) objects.get(Constants.APP_CONTEXT_USER);
        if (user != null) {
            return user.getUserName();
        }
        return "";
    }

    public int getUserId() {
        User user = (User) objects.get(Constants.APP_CONTEXT_USER);
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    public void clear() {
        objects.clear();
    }
}
