class Login extends React.Component {
    render() {
        return (
            <center style = {{backgroundColor:"FF0000"}}>
                <h3>Login:</h3>
                <form name = "login" method = "POST" target = "_self" className = "formOverride">
                    <input type = "text" name = "type" style = {{display:"none"}} value = {0}></input>
                    <input type = "text" name = "user"></input><br></br>
                    <input type = "text" name = "pass"></input><br></br>
                    <input type = "submit"></input><br></br>
                </form>
            </center>
        );
    }
}

class Signup extends React.Component {
    render() {
        return (
            <center style = {{backgroundColor:"00FF00"}}>
                <h3>Signup:</h3>
                <form id = "signup" method = "POST" target = "_self" className = "formOverride">
                <input type = "text" name = "type" style = {{display:"none"}} value = {1}></input>
                    <input type = "text" name = "user"></input><br></br>
                    <input type = "text" name = "pass"></input><br></br>
                    <input type = "submit"></input><br></br>
                </form>
            </center>
        );
    }
}
