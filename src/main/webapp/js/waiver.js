
function loadWaivers() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Waivers), content);
}
class Waivers extends React.Component{
    render() {
        return (
            <div className="row">
                Waivers page
            </div>
        );
    }
}