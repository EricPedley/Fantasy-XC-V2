function loadTeam() {
    let id = -1;
    if ((id = localStorage.getItem('id'))) {
        getRoster(id);
    }
    console.log("load team event, id is " + id);
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(TeamView), content);
}
class TeamView extends React.Component {
    render() {
        return (
            <div>
                <center id = "cards" ><h1>My Team</h1></center>
                <div className="row" >
                    <div className="banana">Col 1</div>
                    <div className="banana">Col 2</div>
                    <div className="banana">Col 3</div>
                    <div className="banana">Col 4</div>
                    <div className="banana">Col 5</div>
                    <div className="banana">Col 6</div>
                    <div className="banana">Col 7</div>
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
    let request = new XMLHttpRequest();
    let target = window.location.href.substring(0, window.location.href.indexOf("#")) + `Rosters?id=${id}&meetID=${1}`;//TODO make meet ID dynamic
    request.open("GET", target);
    request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            console.log(request.responseText);
            return request.responseText;//this return statement never runs b/c of async stuff, need to use a promise or something
        }
    }
    request.setRequestHeader("Content-Type", "text/plain");
    request.send("it won't work without body text for some reason");
}


function testGET() {
    console.log("you pressed the test button, it does nothing right now");
}