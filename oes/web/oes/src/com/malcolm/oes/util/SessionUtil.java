package com.malcolm.oes.util;

import java.lang.reflect.Method;

import com.malcolm.oes.AppContext;
import com.malcolm.oes.Constants;

public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getContext().getObject(Constants.APP_CONTEXT_SESSION);
        return session;
    }

    public static void addSession(String key, Object object) {
        Object session = getSessionInThread();
        if (null == session) {
            return;
        }
        try {
            Class<?> param[] = new Class[2];
            param[0] = String.class;
            param[1] = Object.class;

            Method method = session.getClass().getMethod("setAttribute", param);

            Object objects[] = new Object[2];
            objects[0] = key;
            objects[1] = object;

            method.invoke(session, objects);
        } catch (Exception e) {
            new RuntimeException();
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();
        if (null == session) {
            return null;
        }
        try {
            Class<?> param[] = new Class[1];
            param[0] = String.class;

            Method method = session.getClass().getMethod("getAttribute", param);

            Object objects[] = new Object[1];
            objects[0] = key;

            return method.invoke(session, objects);
        } catch (Exception e) {
            new RuntimeException();
        }
        return null;
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        if (null == session) {
            return;
        }
        try {
            Class<?> param[] = new Class [1];
            param[0] = String.class;

            Method method = session.getClass().getMethod("removeAttribute", param);

            Object objects[] = new Object[1];
            objects[0] = key;
            method.invoke(session, objects);
        } catch (Exception e) {
            new RuntimeException();
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (null == session) {
            return;
        }
        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);
        } catch (Exception e) {
            new RuntimeException();
        }
    }
}
