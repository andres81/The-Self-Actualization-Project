import ItemRenderer from "./ItemRenderer";
import {useState} from "react";
import {ItemChooserAlgorithm} from "./item-chooser-algorithm";
import _ from "lodash";

function QuestionnaireTrainer(props) {

    const [showEndScreen, setShowEndScreen] = useState(false);
    const [itemChooserAlgorithm, setItemChooserAlgorithm] = useState(() => new ItemChooserAlgorithm(props.items));

    let continueCallback = (chosenOptions) => {
        itemChooserAlgorithm.itemSubmitted(chosenOptions);
        setItemChooserAlgorithm(_.cloneDeep(itemChooserAlgorithm));

        if (itemChooserAlgorithm.getScore() === 100 && !showEndScreen) {
            setShowEndScreen(true);
        } else {
        }
    }

    let resetTrainer = () => {
        setShowEndScreen(false);
        setItemChooserAlgorithm(new ItemChooserAlgorithm(props.items));
    }

    return <div className={'container'}>

        <h1>{props.title}</h1>

        <br/>

        <div className="progress">
            <div
                className="progress-bar progress-bar-striped progress-bar-animated bg-success"
                role="progressbar" aria-valuenow="75" aria-valuemin="0"
                aria-valuemax="100"
                style={{"width": itemChooserAlgorithm.getScore() + "%"}}>{itemChooserAlgorithm.getScore()}%
            </div>
        </div>

        <br/><br/>

        {showEndScreen && <>
            <h1>Success!</h1>
            <p>You have completed the training, well done!</p>
            <br/><br/>

            <button type="button" className="btn btn-info" onClick={resetTrainer}>Do it again!</button>
        </>
        }
        {!showEndScreen &&
            <ItemRenderer
                key={itemChooserAlgorithm.getCurrentItem().id} {...itemChooserAlgorithm.getCurrentItem()}
                continueCallback={continueCallback}/>
        }

    </div>
}

export default QuestionnaireTrainer;
