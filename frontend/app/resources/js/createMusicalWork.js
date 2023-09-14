import {makeRequest} from './makeRequest.js';

let saveButton = document.getElementById("save_button");

saveButton.addEventListener("click", function () {
  this.disabled = true;
  let musicalWorkURI = document.getElementById("musical-work-uri-input").value;
  let musicalWorkTitle = document.getElementById("musical-work-title-input").value;

  const urlSplit = location.href.split("/");
  const musicalWorkBaseURL = (urlSplit.splice(0, urlSplit.length - 1)).join("/");

  makeRequest(
    "POST",
    musicalWorkBaseURL + "/create",
    (response) => onSaveButtonOk(response, saveButton),
    (response) => onSaveButtonError(response, saveButton),
    JSON.stringify({
      URI: musicalWorkURI,
      title: musicalWorkTitle
    })
  );
});

function onSaveButtonOk(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  const uuid = response?.uuid;
  if (uuid === undefined) {
    console.error(response);
    return;
  }
  location.href =
    location.href.split("/musicalWork/create")[0] + "/musicalWork/" + uuid;
}

function onSaveButtonError(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  console.error(response);
}
