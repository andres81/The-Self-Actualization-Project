import {State} from "../state-machine";

function ItemSubmitButton(props) {

    const continueButtonClassNames = () => {
        let classes = "btn btn-primary";
        if (props.state === State.CONTINUED) {
            return classes + " disabled";
        } else if (props.state !== State.SUBMITTED) {
            return classes + " d-none";
        }
        return "btn btn-primary" + (props.state === State.CONTINUED ? " disabled" : "");
    }

    return (
        <div className="item-renderer-continue-button">
            <button className={continueButtonClassNames()}
                    onClick={props.handleContinue}>
                Continue
            </button>
        </div>
    )
}

export default ItemSubmitButton;