
function loadTrades() {
    ReactDOM.unmountComponentAtNode(content);
    ReactDOM.render(e(Trades), content);
}
class Trades extends React.Component{
    render() {
        return (
            <div className="row">
                trades page
            </div>
        );
    }
}