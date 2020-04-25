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
            </div>
        )
    }
}