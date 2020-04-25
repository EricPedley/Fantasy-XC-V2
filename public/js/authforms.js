class Login extends React.Component {
    render() {
        return (
            <div style={{ backgroundColor: "#FF0000" }} className="row">
                <center className="col-md-12">
                    <h3>Login:</h3>
                    <form name="login" method="POST" target="_self" className="formOverride">
                        <input type="text" name="type" style={{ display: "none" }} value={0} readOnly={true}></input>
                        <input type="text" name="user"></input><br></br>
                        <input type="text" name="pass"></input><br></br>
                        <input type="submit"></input><br></br>
                    </form>
                </center>
            </div>
        );
    }
}

class Signup extends React.Component {
    render() {
        return (
            <div style={{ backgroundColor: "#00FF00" }} className="row">
                <center className="col-md-12">
                    <h3>Signup:</h3>
                    <form name="signup" method="POST" target="_self" className="formOverride">
                        <input type="text" name="type" style={{ display: "none" }} value={1} readOnly={true}></input>
                        <input type="text" name="user"></input><br></br>
                        <input type="text" name="pass"></input><br></br>
                        <input type="submit"></input><br></br>
                    </form>
                </center>
            </div>
        );
    }
}
