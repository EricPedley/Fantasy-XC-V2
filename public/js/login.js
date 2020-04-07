class Login extends React.Component {
    render() {
        console.log("login rendering");
        return (
            <center style = {{backgroundColor:"FF0000"}}>
                <h3>Login:</h3>
                <input type ="text" id = "username"></input><br></br>
                <input type ="text" id = "password"></input><br></br>
                <button id = "submitLogin">Login</button>
            </center>
        );
    }
}