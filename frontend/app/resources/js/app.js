require('./bootstrap');

let collapse_buttons = document.getElementsByClassName("collapse_button");

for (let i = 0; i < collapse_buttons.length; i++) {
  collapse_buttons[i].addEventListener("click", function () {
    this.classList.toggle("rotate-180");
    let content = this.nextElementSibling;

    if (!content.style.maxHeight) {
      content.style.maxHeight = content.scrollHeight + "px";
    }

    if (content.style.maxHeight !== "0px") {
      content.style.maxHeight = "0px";
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    }
  });
}

let copy_uri_button = document.getElementById('copy-uri');
copy_uri_button?.addEventListener('click', () => {
  let text = document.getElementById('uri').innerText;
  navigator.clipboard.writeText(text);
  document.getElementById('tooltip-uri').style.visibility = "visible";
  setTimeout(() => {
    document.getElementById('tooltip-uri').style.visibility = "hidden";
  }, 1000);
})

let nav_buttons = document.querySelectorAll(".nav-buttons button");

for (let index = 0; index < nav_buttons.length; index++) {
  nav_buttons[index].addEventListener("click", function () {
    let toggle = this.parentNode.attributes.toggle.value;

    let buttons = document.getElementsByClassName("nav-buttons");
    let tabs = document.getElementsByClassName("tab");

    for (let index = 0; index < buttons.length; index++) {
      const element = buttons[index];
      if ((element.attributes.toggle.value === toggle || element.classList.contains("text-white")) && !(element.attributes.toggle.value == toggle && element.classList.contains("text-white"))) {
        element.classList.toggle("bg-gray-dark");
        element.classList.toggle("text-white");
        element.classList.toggle("hover:bg-gray-light");
        element.classList.toggle("hover:text-black");
      }
    }

    for (let index = 0; index < tabs.length; index++) {
      const element = tabs[index];
      if ((element.attributes.toggle.value === toggle || !element.classList.contains("hidden")) && !(element.attributes.toggle.value == toggle && !element.classList.contains("hidden"))) element.classList.toggle('hidden')
    }

  })

}
