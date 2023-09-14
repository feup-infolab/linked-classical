/******/ (() => { // webpackBootstrap
var __webpack_exports__ = {};
/*!***********************************!*\
  !*** ./resources/js/_resource.js ***!
  \***********************************/
var isComposerPage = document.body.innerHTML.search("http://xmlns.com/foaf/0.1/name");
var isMusicalWorkPage = document.body.innerHTML.search("http://purl.org/dc/elements/1.1/title");
document.addEventListener("load", function () {
  var newURL = window.location.href;
  if (isComposerPage) newURL.replace("/resource/", "/composer/");else if (isMusicalWorkPage) newURL.replace("/resource/", "/musicalWork/");
  window.location.replace(newURL);
});
console.log(isComposerPage);
console.log(isMusicalWorkPage);
/******/ })()
;