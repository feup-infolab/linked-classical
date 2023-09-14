const isComposerPage =
  document.body.innerHTML.search("http://xmlns.com/foaf/0.1/name") !== -1;
const isMusicalWorkPage =
  document.body.innerHTML.search("http://purl.org/ontology/mo/MusicalWork") !==
  -1;

window.addEventListener("load", () => {
  let newURL = window.location.href;

  if (document.referrer.includes("composer") || document.referrer.includes("musicalWork"))
    return;

  if (isComposerPage) newURL = newURL.replace("/resource/", "/composer/");
  else if (isMusicalWorkPage)
    newURL = newURL.replace("/resource/", "/musicalWork/");
  else return;

  window.location.replace(newURL);
});
 