import {State} from "../../state-machine";

function ItemEntry(props) {

    let checked = props.selectedOptions.includes(props.optionData.identifier);

    let classNames = "list-group-item" + ([State.INITIAL_STATE, State.ANSWER_CHOSEN].includes(props.state) ? '' : ' no-pointer-events');
    if ([State.SUBMITTED, State.CONTINUED].includes(props.state)) {
        let correctOptionChosen = props.correctResponses.includes(props.optionData.identifier);
        if (checked && correctOptionChosen) {
            classNames += " list-group-item-success";
        } else if (checked && !correctOptionChosen) {
            classNames += " list-group-item-danger";
        } else if (!checked && correctOptionChosen) {
            classNames += " list-group-item-warning";
        }
    }

    return (
        <li className={classNames}>
            <input id={props.optionData.id} className="form-check-input me-1"
                   type={props.cardinality === 'SINGLE' ? 'radio' : 'checkbox'}
                   value={props.optionData.identifier} aria-label="..."
                   name={props.id}
                   onChange={props.handleChange}
                   checked={checked}
            />
            <label
                htmlFor={props.optionData.id}>{props.optionData.label}</label>
        </li>
    )
}

export default ItemEntry;