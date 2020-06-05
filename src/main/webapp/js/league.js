var runnersList;

function loadLeague() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(LeagueView), content);
    loadAllRunners();
    overrideForms(function (request, formData) {
        console.log(request.responseText);
        loadAllRunners();
    });
}

class LeagueView extends React.Component {
    render() {
        return (
            <div>
                <center><h1>League Stuff:</h1></center>
                <center><h2>All runners:</h2></center>
                <div id="runnerList"></div>
                <center>Update Runner Name:</center>
                <form method="POST" target="_self" className="formOverride">
                    <input type="text" name="type" style={{ display: "none" }} value={2} readOnly={true}></input>
                    <label>Athlete ID:</label><input type="text" name="athleteID"></input><br></br>
                    <label>New Name:</label><input type="text" name="athletename"></input>
                    <input type="submit"></input>
                </form>
            </div>
        );
    }
}

function loadAllRunners() {
    let container = document.querySelector("#runnerList");
    container.innerHTML = "";
    let request = new XMLHttpRequest();
    request.open("GET", `${removeFragment(window.location.href)}AllRunners`);
    request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            console.log("raw response text for allrunners: " + request.responseText);
            runnersList = request.responseText.split("], [");
            runnersList.forEach(function (idAndName) {
                let idAndNameArray = idAndName.split(", ");
                let id = idAndNameArray[0];
                let name = idAndNameArray[1];
                container.innerHTML += `id: ${id}, name: ${name}<br>`;
            })
        }
    };
    request.send();
}

/*
The fragment is the # part of the URL
For example, if you give this function the string "https://example.com/blahblah/#something",
it will return "https://example.com/blahblah/"
*/
function removeFragment(href) {
    if ((""+href).indexOf("#")==-1)
        return href;
    else {
        return href.substring(0,href.indexOf("#"));
    }
}
