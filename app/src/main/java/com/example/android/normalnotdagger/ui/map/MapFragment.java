package com.example.android.normalnotdagger.ui.map;



import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.android.normalnotdagger.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Layer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;




public class MapFragment extends Fragment implements OnMapReadyCallback, MapMVP{
    private GoogleMap mMap;
    MapPresentr presentr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presentr = new MapPresentr(this);
        presentr.loadMap();
        return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(51, 107);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("Click", "MAP");
            }
        });
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                String text = polygon.getId().replace("pg","");
                Toast.makeText(getActivity(), texts.get(Integer.valueOf(text)), Toast.LENGTH_LONG).show();
                Log.e("Click", "Polygon in map "+ polygon.getId()+ " " + texts.get(Integer.valueOf(text)));
            }
        });
    }
    List<String> texts = new ArrayList<>();
    List<LatLng> getListPoint(JSONArray c){
        List<LatLng> ret = new ArrayList<>();
        for(int i = 0; i < c.length(); i++){
            try {
                double x = Double.valueOf(c.getString(i).substring(1,c.getString(i).length()-1).split(",")[1].substring(1,c.getString(i).substring(1,c.getString(i).length()-1).split(",")[1].length()-1));
                double y = Double.valueOf(c.getString(i).substring(1,c.getString(i).length()-1).split(",")[0].substring(1,c.getString(i).substring(1,c.getString(i).length()-1).split(",")[0].length()-1));
                LatLng latLng = new LatLng(x,y);
                ret.add(latLng);
            } catch (JSONException e) {
                Log.e("error", "not string: " + e);
                e.printStackTrace();
            }
        }

        return ret;
    }
    @Override
    public void showMap(JSONArray maps) {
        for(int i = 0; i < maps.length(); i++) {
            JSONObject json = null;
            try {
                json = new JSONObject(maps.get(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject geometry = null;
            try {
                geometry = new JSONObject(json.get("geometry").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray coordinates = null;
            try {
                String cm = geometry.getString("coordinates");
                coordinates = new JSONArray(cm);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray coordinates1 = null;
            try {
                coordinates1 = new JSONArray(coordinates.getString(0));
            } catch (JSONException e) {
                Log.e("Coordinates", "2 geometry: "+ e);
                e.printStackTrace();
            }
            List<LatLng> g = new ArrayList<>();
            g = getListPoint(coordinates1);
            PolygonOptions rectOptions = new PolygonOptions()
                    .addAll(g);
            double a = 0.4 * 255;
            int b = (int) a;
            JSONObject properties = null;
            try {
                properties = new JSONObject(json.get("properties").toString());
            } catch (JSONException e) {
                Log.e("ErrorProperties", "not Properties: "+ e);
                e.printStackTrace();
            }
            StringBuffer color = new StringBuffer("");
            try {
                if(properties.get("fillColor").toString().isEmpty()){
                    color =  color.insert(0 , properties.get("color").toString());
                    color = color.insert(1,"55");
                }
                else{
                    color = color.insert(0 ,properties.get("fillColor").toString());
                    color = color.insert(1,"99");
                }
                Log.e("Color", color.toString());
            mMap.addPolygon(new PolygonOptions().addAll(g).fillColor(Color.parseColor(color.toString())).strokeWidth(2).strokeColor(Color.RED)).setClickable(true);
            } catch (JSONException e) {
                Log.e("ErrorColor", "not Color: "+ e);
                e.printStackTrace();
            }
            String text = null;
            try {
                text = properties.get("text").toString();
                texts.add(text);
            } catch (JSONException e) {
                Log.e("ErrorColor", "not Color: "+ e);
                e.printStackTrace();
            }
        }
    }
    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG);
    }
    @Override
    public void startProgresBar() {

    }
    @Override
    public void stopProgresBar() {

    }
}


