function loadTeam() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(TeamView), content);
    let id = -1;
    if ((id = localStorage.getItem('id'))) {
        getRoster(id);
    }
    console.log("load team event, id is " + id);
}
class TeamView extends React.Component {
    render() {
        return (
            <div>
                <center id="cards" ><h1>My Team</h1></center>
                <div className="row" >
                    <div className="col" id="r1">Col 1</div>
                    <div className="col" id="r2">Col 2</div>
                    <div className="col" id="r3">Col 3</div>
                    <div className="col" id="r4">Col 4</div>
                    <div className="col" id="r5">Col 5</div>
                    <div className="col" id="r6">Col 6</div>
                    <div className="col" id="r7">Col 7</div>
                </div>
                <center><h2>Next Meet</h2></center>
                <div className="row" style={{ height: "50vh" }}>
                    <div className="col-md-6" style={{ backgroundColor: "#00FF00" }}>Roster</div>
                    <div className="col-md-6" style={{ backgroundColor: "#FF0000" }}>Bench</div>
                </div>
                <center><h2>Previous Meets</h2></center>
                <button onClick={testGET}>Press for testing</button>
            </div>
        )
    }
}


function getRoster(id) {
    console.log("getting roster");
    //the removeFragment function is in league.js, so make sure that loads before this does
    let target = removeFragment(window.location.href) + `Rosters?id=${id}&meetID=${1}`;//TODO make meet ID dynamic
    getReq(target, [["Content-Type", "text/plain"]],
        function (request) {
            console.log(request.responseText);
            var runners = request.responseText.split(",");
            let counter = 1;
            let r;

            for (r of runners) {//r is the runner's id
                getReq(removeFragment(window.location.href) + `Runner?id=${r}`, [["Content-Type", "text/plain"]],
                    function (request) {
                        let card = document.querySelector(`#r${counter}`);
                        card.innerHTML = request.responseText;
                        counter++;
                    }
                );
            }
            return request.responseText;//this return statement never runs b/c of async stuff, need to use a promise or something
        }
    );
}


function testGET() {
    console.log("you pressed the test button, it does nothing right now");
}

function getReq(target, headers, callback) {
    let request = new XMLHttpRequest();
    request.open("GET", target);
    request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            callback(request);
        }
    }
    if (headers) {
        for (let header of headers) {
            request.setRequestHeader(header[0], header[1]);
        }
    }
    request.send("it won't work without body text for some reason");
}