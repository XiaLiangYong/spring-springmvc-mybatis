package com.spring.domain;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {
    private List<String> list;
    private Set<Integer> set;
    private Map<String, Integer> map;
    private Properties properties;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
