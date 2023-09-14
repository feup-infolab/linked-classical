
function configureLocationsMap() {
  const loadingMapDiv = document.getElementById("loading-map-div");
  const mapDiv = document.getElementById("locations-map");
  const locationsInfoUl = document.getElementById("locations-info");
  const locationsInfo = [];

  Array.from(locationsInfoUl.children).forEach((locationInfoLi) => {
    locationsInfo.push({
      uri: locationInfoLi.querySelector(".uri").innerHTML,
      latitude: locationInfoLi.querySelector(".latitude").innerHTML,
      longitude: locationInfoLi.querySelector(".longitude").innerHTML,
      name: locationInfoLi.querySelector(".name").innerHTML,
      population: locationInfoLi.querySelector(".population").innerHTML
    });
  });

  if (locationsInfo.length === 0) {
    loadingMapDiv.innerText = "No locations.";
    return;
  }

  const markers = [];
  const map = L.map("locations-map").setView([0, 0], 1);

  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);


  locationsInfo.forEach((locationInfo) => {

    const coordinates = [locationInfo.latitude, locationInfo.longitude];
    coordinates.forEach((coordinateString) => parseInt(coordinateString));

    const marker = L.marker([coordinates[0], coordinates[1]]).addTo(map);
    markers.push(marker);

    marker.bindPopup(`
        <div class="font-bold">${locationInfo.name}</div>
        <br>
        <div>
            <a target="_blank" class="underline italic" href='${locationInfo.uri}'>${locationInfo.uri}</a>
        </div>
        <br>
        <div><span class="font-bold">Population:</span> ${new Intl.NumberFormat().format(locationInfo.population)}</div>
    `);
  });

  if (markers.length === 1) {
    map.setView(markers[0].getLatLng(), 5);
  } else {
    let latlngs = markers.map(marker => marker.getLatLng());
    let latlngBounds = L.latLngBounds(latlngs);
    map.flyToBounds(latlngBounds);
  }

  window.dispatchEvent(new Event('resize'));

  loadingMapDiv.hidden = true;
  mapDiv.hidden = false;
}

configureLocationsMap();
