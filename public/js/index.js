

const content = document.querySelector('#content');
const e = React.createElement;
if(!localStorage.getItem('id')) {
    localStorage.setItem('id',-1);
}


loadNavbar();
loadTeam();









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