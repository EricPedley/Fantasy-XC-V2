function loadLogin() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Login), content);
    overrideLoginForms(true);
}
class Login extends React.Component {
    render() {
        return (
            <div style={{ backgroundColor: "#FF0000" }} className="row">
                <center className="col-md-12">
                    <h3>Login:</h3>
                    <form name="login" method="POST" target="_self" className="formOverride">
                        <input type="text" name="type" style={{ display: "none" }} value={0} readOnly={true}></input>
                        <label>Username:</label><input type="text" name="user"></input><br></br>
                        <label>Password:</label><input type="password" name="pass"></input><br></br>
                        <input type="submit"></input><br></br>
                    </form>
                </center>
            </div>
        );
    }
}
function loadSignup() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Signup), content);
    overrideLoginForms(false);
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
                        <input type="password" name="pass"></input><br></br>
                        <input type="submit"></input><br></br>
                    </form>
                </center>
            </div>
        );
    }
}
function overrideLoginForms(login) {//login is a boolean for whether the form is a login for or not
    //TODO: make server send a state besides done if there's an error, because rn it just returns a response but says it's done
    if (login) {//login is provided in the overrideForms method that calls this method. js is strange.
        overrideForms(function (request,formData) { onLogin(request.responseText, formData.get("user")); });
    } else
        console.log("response for signup:" + request.responseText);
}

function overrideForms(callback) {//login is a boolean for whether the form is a login for or not
    console.log("overriding form sumbits");
    let form = document.querySelector('.formOverride');
    form.addEventListener("submit", function (event) {
        customSubmit(event, callback);
    });
}

function customSubmit(event, callback) {
    let form = document.querySelector('.formOverride');
    let formData = new FormData(form);
    let request = new XMLHttpRequest();
    event.preventDefault();
    console.log(event);
    request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            callback(request,formData);
        } else {
            console.log("request state: "+request.readyState);
        }
    };
    request.open(form.method, window.location.href);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    let entries = [];
    for (let pair of formData.entries()) {
        entries.push(`${pair[0]}=${pair[1]}`);
    }
    let data = entries.join('&').replace(/ /g, '+');
    request.send(data);
}

function onLogin(id, user) {//runs on successful login
    localStorage.setItem('id', id);
    localStorage.setItem('user', user);
    loadNavSignout();
    loadTeam();
}