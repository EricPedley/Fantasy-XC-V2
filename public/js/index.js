'use strict';

const e = React.createElement;

function makeRequest() {
    var request = new XMLHttpRequest();
    var data = {message:"hi server!!"};
    request.addEventListener("load", function() {
        console.log(request.responseText);
    });
    request.open('GET', 'http://localhost:6969/endpoint', true);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    request.send(JSON.stringify(data));
}

class Button extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {

        return e(
            'button',
            { onClick: () => makeRequest() },
            'do stuff'
        );
    }
}
const domContainer = document.querySelector('#button');
ReactDOM.render(e(Button), domContainer);