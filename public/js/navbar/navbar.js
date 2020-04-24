class Navbar extends React.Component  {
    render() {
        return (
            <div className = "navbar" style = {{backgroundColor:"cyan"}}>
                <div className = "nav-item"><h3>Fantasy XC</h3></div>
                <a className = "nav-item">My Team</a>
                <a className = "nav-item">Rules</a>
                <a className = "nav-item" style={{backgroundColor:"pink"}}>League</a>
                <a className = "nav-item">Trades</a>
                <a className = "nav-item">Waivers</a>
            </div>
        )
    }
}