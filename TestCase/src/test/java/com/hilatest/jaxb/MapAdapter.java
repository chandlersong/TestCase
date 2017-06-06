package com.hilatest.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<AdaptedMap, Map<String, String>> {
    public MapAdapter() {
    }

    @Override
    public AdaptedMap marshal(Map<String, String> arg0) throws Exception {
        AdaptedMap mapElements = new AdaptedMap();
        int i = 0;
        for (Map.Entry<String, String> entry : arg0.entrySet())
            mapElements.elements.add(new MapElements(entry.getKey(), entry.getValue()));

        return mapElements;
    }

    @Override
    public Map<String, String> unmarshal(AdaptedMap arg0) throws Exception {
        Map<String, String> r = new TreeMap<String, String>();
        for (MapElements mapelement : arg0.elements)
            r.put(mapelement.key, mapelement.value);
        return r;
    }
}

class AdaptedMap {
    @XmlAnyElement
    public List<MapElements> elements = new ArrayList<MapElements>();
}

@XmlRootElement(name = "abc")
class MapElements {
    @XmlAttribute
    public String key;
    @XmlAttribute
    public String value;

    private MapElements() {
    } // Required by JAXB

    public MapElements(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
