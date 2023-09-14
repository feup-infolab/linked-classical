export function makeRequest(method, url, onOk, onError, input) {
  let xmlhttp = new XMLHttpRequest();
  xmlhttp.open(method, url, true);
  xmlhttp.onload = function (event) {
    event.preventDefault();
    try {
      const res = JSON.parse(xmlhttp.responseText);
      if (xmlhttp.status === 200) {
        onOk(res)
      } else onError(res)
    } catch (e) {
      onError(e)
    }
  };
  xmlhttp.setRequestHeader(
    "Content-Type",
    "application/json;charset=UTF-8"
  );
  xmlhttp.setRequestHeader(
    "X-CSRF-TOKEN",
    document.querySelector('meta[name="csrf-token"]').content
  );
  xmlhttp.send(input);
}
