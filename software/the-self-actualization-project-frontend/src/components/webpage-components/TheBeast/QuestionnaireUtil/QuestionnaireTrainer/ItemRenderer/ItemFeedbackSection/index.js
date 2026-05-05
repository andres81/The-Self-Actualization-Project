import {State} from "../state-machine";


function ItemFeedbackSection(props) {

    const calcFeedbackClassnames = () => {
        return "item-renderer-feedback" + ([State.SUBMITTED, State.CONTINUED].includes(props.state) ? "" : " d-none");
    }

    return (
        <p className={calcFeedbackClassnames()}>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit.
        </p>
    )
}

export default ItemFeedbackSection;