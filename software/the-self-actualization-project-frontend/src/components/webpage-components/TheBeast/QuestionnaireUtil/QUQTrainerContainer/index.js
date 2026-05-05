import QuestionnaireTrainer from "../QuestionnaireTrainer";
import {JSON} from './test-questionnaire'

function QUQTrainerContainer() {

    return (
        <div>
            <QuestionnaireTrainer title={JSON.title} items={JSON.items}/>
        </div>
    )
}

export default QUQTrainerContainer;