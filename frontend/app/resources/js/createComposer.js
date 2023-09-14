import {makeRequest} from './makeRequest.js';

let saveButton = document.getElementById("save_button");

saveButton.addEventListener("click", function () {
  this.disabled = true;
  let composerURI = document.getElementById("composer-uri-input").value;
  let composerName = document.getElementById("composer-name-input").value;

  const urlSplit = location.href.split("/");
  const composerBaseURL = (urlSplit.splice(0, urlSplit.length - 1)).join("/");

  makeRequest(
    "POST",
    composerBaseURL + "/create",
    (response) => onSaveButtonOk(response, saveButton),
    (response) => onSaveButtonError(response, saveButton),
    JSON.stringify({
      URI: composerURI,
      name: composerName
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
    location.href.split("/composer/create")[0] + "/composer/" + uuid;
}

function onSaveButtonError(response, disabledElement) {
  if (disabledElement)
    disabledElement.disabled = false;
  console.error(response);
}
