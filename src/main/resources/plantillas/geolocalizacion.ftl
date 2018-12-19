<!DOCTYPE html>
<html>
<head>
    <title>Geolocalización</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>

        #map {
            height: 100%;
        }

        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="map"></div>
<div class="form-group">
    <input type="hidden" placeholder="Latitud" class="form-control" value="${encuestado.getLatitud()}" name="latitude" id="latitude" aria-describedby="sizing-addon1"
           size="10" pattern=".{4,}">
</div>

<div class="form-group">
    <input type="hidden" placeholder="Longitud" class="form-control" value="${encuestado.getLongitud()}" name="longitud" id="longitud" aria-describedby="sizing-addon1"
           size="10" pattern=".{4,}">
</div>

<#--<button type="button" class="btn btn-success" onclick="initMap()">Mostrar geolocalizacion</button>-->


<script>
    // Note: This example requires that you consent to location sharing when
    // prompted by your browser. If you see the error "The Geolocation service
    // failed.", it means you probably did not give permission for the browser to
    // locate you.

    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 6
        });
        var infoWindow = new google.maps.InfoWindow({map: map});


        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
//                var pos = {
//                    lat: position.coords.latitude,
//                    lng: position.coords.longitude
//                };
                var pos = {
                    lat: Number(document.getElementById("latitude").value),
                    lng: Number(document.getElementById("longitud").value)
                };

                infoWindow.setPosition(pos);
                infoWindow.setContent('Location found.');
                map.setCenter(pos);
            }, function() {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {

            handleLocationError(false, infoWindow, map.getCenter());
        }
    }

    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDegN-C8ZkR-TyDaWPVE0_Rb-RgMeGceFU&callback=initMap">
</script>
</body>
</html>