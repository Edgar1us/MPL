package edbeca.simarro.proyectoandroid.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edbeca.simarro.proyectoandroid.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker inicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        inicial = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.16383410103335, -0.42702762185105886))
                .title("Cine Punt")
                .snippet("Telefono: 677589378")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.15208335697701, -0.4387584656024248))
                .title("Cine Colon")
                .snippet("Telefono: 619155956")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(39.14947310332277, -0.439540730682632))
                .title("Cine Marbella")
                .snippet("Telefono: 962414379")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.43027351474765, -3.7009197459985934))
                .title("Cine Yelmo Luxury Palafox")
                .snippet("Telefono: 912414379")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.40419876739652, 2.156859740529252))
                .title("Cinemes Verdi")
                .snippet("Telefono: +34932387990")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.83737753700317, 2.382499998375657))
                .title("Cinemateca Francesa")
                .snippet("Telefono: +33171193333")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.90155518746156, 12.491881598211444))
                .title("Cinema Quattro Fontane")
                .snippet("Telefono: +33171193333")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.52063122950294, 13.42285079847223))
                .title("Kino International")
                .snippet("Telefono: +4930322931322")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-33.8747339498262, 151.20509818108897))
                .title("Cinema Centre Park")
                .snippet("Telefono: +61292645867")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.762090316465454, -73.96606394414194))
                .title("Cinema 123 by Angelika")
                .snippet("Telefono: +12127536022")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.81559316593178, 15.969397184808102))
                .title("Cinema Tuskanac")
                .snippet("Telefono: +38514834039")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(59.932453781875395, 10.71244823361465))
                .title("Cinema Neuf")
                .snippet("Telefono: +38514834039")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.353717710482044, -75.80481890170987))
                .title("Cinéma")
                .snippet("Telefono: +38514834039")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(38.72047836476193, -9.14625335767747))
                .title("Cinema Sao Jorge")
                .snippet("Telefono: +351213103400")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(49.48401198150939, 16.66222365606346))
                .title("кинотеатр ")
                .snippet("Telefono: +351213103400")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cine))
                .draggable(true)
        );




        mMap.moveCamera(CameraUpdateFactory.newLatLng(inicial.getPosition()));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMapClickListener(this);
        //mMap.setOnMapLongClickListener(this);

        mMap.setOnMarkerClickListener(this);
        //mMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Toast.makeText(this, "Has hecho click en "+latLng.latitude+", "+latLng.longitude, Toast.LENGTH_SHORT).show();
        inicial.setPosition(latLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }
}