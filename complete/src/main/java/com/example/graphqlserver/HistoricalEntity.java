package com.example.graphqlserver;

import java.util.List;
import java.util.Map;

public class HistoricalEntity {
    private Map<String, Map<String, Object>> _historical;

    public Map<String, Map<String, Object>> get_historical() {
        return _historical;
    }

    public void set_historical(Map<String, Map<String, Object>> _historical) {
        this._historical = _historical;
    }
}
