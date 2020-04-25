function loadNavbar() {
    const navContainer = document.querySelector('#navbar');
    ReactDOM.render(e(Navbar), navContainer);

}
class Navbar extends React.Component {
    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light row" style={{ backgroundColor: "cyan" }}>
                <div className="navbar-brand" href="#">Fantasy XC</div>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <div className="navbar-nav mr-auto">
                        <a className="nav-item nav-link" href="#Team" onClick={loadTeam}>My Team</a>
                        <a className="nav-item nav-link" href="#Waivers" onClick={loadWaivers}>Waivers</a>
                        <a className="nav-item nav-link mr-auto" href="#Trades" onClick={loadTrades}>Trades</a>
                    </div>
                    <div className = "navbar-nav" id = "navAuth">
                    <a className="nav-item nav-link" href="#" onClick={loadLogin}>Login</a>
                    <a className="nav-item nav-link" href="#" onClick={loadSignup}>Sign Up</a>
                    </div>
                </div>
            </nav>
        )
    }
}

class navLogin extends React.Component{

}

class navLogout extends React.Component{
    
}






//dropdown menu maybe I'll use it later
{/* <div className="nav-item dropdown">
<a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">League</a>
<div className="dropdown-menu" aria-labelledby="navbarDropdown">
    <a className="dropdown-item" href="#">My League</a>
    <a className="dropdown-item" href="#">New League</a>
</div>
</div> */}