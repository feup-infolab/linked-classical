import { makeRequest } from './makeRequest.js';

const linkedClassicalEntityURI = document.getElementById("uri");
const createLinkButton = document.getElementById("create-link-button");
const externalEntityURI = document.getElementById("external-entity-uri");
const datasourceSelector = document.getElementById("datasource-selector");
const errorMessage = document.getElementById("external-entity-error-message");
const successMessage = document.getElementById("external-entity-success-message");
const removeButtons = document.getElementsByClassName("remove-entity-link");
const removeErrorMessage = document.getElementById("remove-error-message");

// --- Get External Datasets ---

const urlSplit = location.href.split("/")
const baseURL = (urlSplit.splice(0, urlSplit.length - 2)).join("/") + "/resource"

makeRequest(
  "GET", baseURL + "/external-datasets",
  (response) => onExternalDatasetsOk(response),
  (response) => onExternalDatasetsError(response)
);

function onExternalDatasetsOk(response) {
  console.log(response)
  response.forEach((datasource) => {
    const optionElement = document.createElement("option");
    optionElement.value = datasource.dataset;
    optionElement.text = datasource.title;
    datasourceSelector.appendChild(optionElement);
  });
}

function onExternalDatasetsError(response) {
  console.error(response);
}

// --- Create external entity link ---

createLinkButton.addEventListener("click", function () {
  errorMessage.innerText = "";

  if (externalEntityURI.value === "") {
    errorMessage.innerText = "URI is required"
    successMessage.innerText = "";
  } else if (!/^https?:\/\/(.*)$/.test(externalEntityURI.value)) {
    errorMessage.innerText = "Invalid URI";
    successMessage.innerText = "";
  } else if (datasourceSelector.value === "") {
    errorMessage.innerText = "Please select a datasource";
    successMessage.innerText = "";
  } else {
    const input = {
      "linkedClassicalEntityURI": linkedClassicalEntityURI.innerText,
      "externalEntityURI": externalEntityURI.value,
      "externalDatasetURI": datasourceSelector.value,
    };
    makeRequest(
      "POST", baseURL + "/link-external-entity",
      (response) => onButtonOk(response, createLinkButton),
      (response) => onSaveButtonError(response, createLinkButton),
      JSON.stringify(input)
    );
  }
});

function onButtonOk(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  successMessage.innerText = "Link created successfully";
  errorMessage.innerText = "";

  localStorage.setItem("openTab", "external-data");
  location.reload();
}

function onSaveButtonError(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  errorMessage.innerText = "Something went wrong, try again later";
  successMessage.innerText = "";
  console.error("Error: ", response);
}

// --- Delete external entity link ---

function onDeleteButtonError(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  removeErrorMessage.innerText = "Something went wrong, try again later";
  console.error("Error: ", response);
}

function onDeleteButtonOk(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  errorMessage.innerText = "";

  localStorage.setItem("openTab", "external-data");
  location.reload();
}


Array.from(removeButtons).forEach((el) => {
  el.addEventListener("click", () => {
    const input = {
      "linkedClassicalEntityURI": linkedClassicalEntityURI.innerText,
      "externalEntityURI": el.getAttribute("data-uri"),
    };
    makeRequest(
      "DELETE",
      baseURL + "/unlink-external-entity",
      (response) => onDeleteButtonOk(response, el),
      (response) => onDeleteButtonError(response, el),
      JSON.stringify(input)
    );
  });
});

// --- Coordinate Location ---

const coordinatesDiv = document.getElementById("gps-coordinates");

if (coordinatesDiv != null) {
  const coordinates = coordinatesDiv.innerText.split("(")[1].split(")")[0].split(" ");
  coordinates.forEach((coordinateString) => parseInt(coordinateString));

  var map = L.map("entity-map").setView([coordinates[1], coordinates[0]], 8);

  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  L.marker([coordinates[1], coordinates[0]]).addTo(map);

  const externalDataTabButton = document.getElementById("external-data");

  externalDataTabButton.addEventListener("click", () => {
    // https://stackoverflow.com/questions/21405189/leaflet-map-shows-up-grey
    window.dispatchEvent(new Event('resize'));
  });

  document.getElementById("coordinate-location-title").hidden = false;
  document.getElementById("entity-map").hidden = false;
}

// --- Wikidata Property Search ---

const propertySearchInput = document.getElementById("propertySearch");

propertySearchInput.addEventListener("keyup", () => {
  const searchInput = propertySearchInput.value.toLowerCase();
  const properties = document.querySelectorAll("#wikidata-table tr td.property");

  for (let i = 0; i < properties.length; i++) {
    const propertyValue = properties[i].innerText.toLowerCase();

    if (propertyValue.indexOf(searchInput) > -1) {
      properties[i].parentElement.style.display = "";
    } else {
      properties[i].parentElement.style.display = "none";
    }
  }
});

