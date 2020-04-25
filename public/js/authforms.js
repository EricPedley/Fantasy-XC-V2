function loadLogin() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Login), content);
    overrideForms(true);
  }
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
function loadSignup() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Signup), content);
    overrideForms(false);
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
function overrideForms(login) {
    console.log("overriding form sumbits");
    let form = document.querySelector('.formOverride');
    form.addEventListener("submit", function (event) {
      let formData = new FormData(form);
      let request = new XMLHttpRequest();
      request.setRequestHeader
      event.preventDefault();
      console.log(event);
      request.onreadystatechange = function () {
        if (request.readyState == XMLHttpRequest.DONE) {
            if(login)
                localStorage.setItem('id',request.responseText);
            else
                console.log("response for signup:" + request.responseText);
        }
      }
      request.open("POST", window.location.href);
      request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      let entries = [];
      for (let pair of formData.entries()) {
        entries.push(`${pair[0]}=${pair[1]}`);
      }
      let data = entries.join('&').replace(/%20/g, '+');
      request.send(data);
    });
  }