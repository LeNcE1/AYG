package com.example.android.normalnotdagger.models.new_model.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

//    {
//        "type": "Feature",
//            "geometry": {
//        "type": "Point",
//                "coordinates": [125.6, 10.1]
//    },
//        "properties": {
//        "name": "Dinagat Islands"
//    }
//    }
    public String getGeoGson(){
        String geo = "{\"type\":"+"\""+this.type+"\","+"\"properties\":{\"color\":"+"\""+this.properties.getColor()+"\","+"\"fillColor\":"+"\""+this.properties.getFillColor()+"\""
                +",\"opacity\":"+"\""+this.properties.getOpacity()+"\","+"\"fillOpacity\":"+"\""+this.properties.getFillOpacity()+"\","+"\"backend_layer_id\":"+"\""+this.properties.getBackendLayerId()+"\","
                +"\"text\":"+"\""+this.properties.getText()+"\""
                +"\"geometry\":{\"type\""+"\""+this.geometry.getType()+"\","+"\"coordinates\":"+this.geometry.getCoordinates().toString()+"}}";
        return geo;
    }
}