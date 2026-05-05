import {State} from "../state-machine";

function ItemSubmitButton(props) {

    const submitButtonClassNames = () => {
        return "btn btn-primary" + (props.state !== State.ANSWER_CHOSEN ? " disabled" : "");
    }

    return (
        <div className="item-renderer-submitbutton">
            <button className={submitButtonClassNames()}
                    onClick={props.handleSubmit}>Submit
            </button>
        </div>
    )
}

export default ItemSubmitButton;