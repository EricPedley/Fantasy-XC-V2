class Login extends React.Component {
    render() {
        console.log("login rendering");
        return (
            <center style = {{backgroundColor:"FF0000"}}>
                <h3>Login:</h3>
                <form id = "login" method = "POST" target = "_self">
                    <input type = "text" name = "user"></input><br></br>
                    <input type = "text" name = "pass"></input><br></br>
                    <input type = "submit"></input><br></br>
                </form>
            </center>
        );
    }
}
