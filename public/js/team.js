function loadTeam() {
    let id=-1;
    if((id=localStorage.getItem('id'))) {
        
    }
    console.log("load team event, id is "+id);
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(TeamView), content);
}
class TeamView extends React.Component {
    render() {
        return (
            <div>
                <center><h2>Next Meet</h2></center>
                <div className="row" style={{height:"50vh"}}>
                    <div className="col-md-6" style={{ backgroundColor: "#00FF00" }}>Roster</div>
                    <div className="col-md-6" style={{ backgroundColor: "#FF0000" }}>Bench</div>
                </div>
                <center><h2>Previous Meets</h2></center>
                <button onClick={testGET}>Press for testing</button>
            </div>
        )
    }
}

function testGET() {
    let request = new XMLHttpRequest();
    let target = window.location.href.substring(0,window.location.href.indexOf("#"))+"Rosters?foo=bar&id=3";
    request.open("GET",target);
    console.log("target: " + target);
    request.setRequestHeader("Content-Type","text/plain");
    request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            console.log(request.responseText);
        }
    }
    request.send("an empty body breaks this somehow");
    
}