

const content = document.querySelector('#content');
const e = React.createElement;

function loadSignup() {
  ReactDOM.unmountComponentAtNode(content);
  ReactDOM.render(e(Signup), content);
}

function overrideForms() {
  console.log("overriding form sumbits");
  let form = document.querySelector('.formOverride');
  form.addEventListener("submit", function (event) {
    let formData = new FormData(form);
    let request = new XMLHttpRequest();
    request.setRequestHeader
    event.preventDefault();
    console.log(event);
    request.onreadystatechange = function () {
      if (request.readyState == XMLHttpRequest.DONE) {
        console.log(request.responseText);
      }
    }
    request.open("POST", window.location.href);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    let entries = [];
    for (let pair of formData.entries()) {
      entries.push(`${pair[0]}=${pair[1]}`);
    }
    let data = entries.join('&').replace(/%20/g, '+');
    request.send(data);
  });
}

window.onload = function () {
  overrideForms();
}

const navContainer = document.querySelector('#navbar');
ReactDOM.render(e(Login), content);
ReactDOM.render(e(Navbar), navContainer);













// function POST(data) {
//   var request = new XMLHttpRequest();
//   request.upload.timeout = 5000;
//   request.upload.onprogress = function (event) {
//     console.log(`Uploaded ${event.loaded} of ${event.total} bytes`);
//   };

//   request.upload.onload = function () {
//     console.log(`Upload finished successfully.`);
//   };

//   request.upload.onerror = function () {
//     console.log(`Error during the upload: ${request.status}`);
//   };
//   request.timeout = 500;
//   request.addEventListener("load", function () {
//     console.log(request.response);
//   });

//   request.open('POST', 'http://localhost:6969/endpoint', true);

//   request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
//   request.send(JSON.stringify(data));

// }