package com.malcolm.oes.common;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private Map<String, Object> sessions = new HashMap<String, Object>();
    private Map<String, Object> requests = new HashMap<String, Object>();

    private String view;
    private boolean isRedirect = false;

    public ModelAndView() {
        super();
    }
    public ModelAndView(Map<String, Object> requests, Map<String, Object> sessions) {
        this.sessions = sessions;
        this.requests = requests;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public boolean isRedirect() {
        return isRedirect;
    }
    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }
    public Map<String, Object> getSessions() {
        return sessions;
    }
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    public Map<String, Object> getRequests() {
        return requests;
    }

    public void addSessionAttribute(String key, Object object) {
        sessions.put(key, object);
    }
    public void removeSessionAttribute(String key) {
        sessions.remove(key);
    }
    public void addObject(String key, Object object) {
        requests.put(key, object);
    }
}
