import _ from "lodash";

export class ItemChooserAlgorithm {

    constructor(items) {
        this.items = items;
        this.init();
    }

    init() {
        this.addMetaData();
        this.setActiveItem();
        this.score = 0;
    }

    addMetaData() {
        this.preparedItems = this.items.map(item => {
            return {
                id: item.id,
                item: item,
                score: 0,
                timesAnswered: 0
            }
        })
    }

    setActiveItem() {
        this.preparedItems = _.sortBy(_.shuffle(this.preparedItems), item => item.score);
        if (!!!this.activeItem) {
            this.activeItem = this.preparedItems[0];
        } else if (this.activeItem.id === this.preparedItems[0].id) {
            this.activeItem = this.preparedItems[1];
        } else {
            this.activeItem = this.preparedItems[0];
        }
    }

    scoreOutcome(isCorrectAnswerGiven) {
        if (isCorrectAnswerGiven) {
            this.activeItem.score += 1;
            if (this.activeItem.score > 1) {
                this.activeItem.score = 1;
            }
        } else {
            this.activeItem.score -= 1;
            if (this.activeItem.score < -1) {
                this.activeItem.score = -1;
            }
        }
        this.updateScore();
        this.setActiveItem();
    }

    getScore() {
        return this.score;
    }

    updateScore() {
        let totalScore = this.preparedItems.filter(item => item.score > 0).reduce((sum, item) => sum + item.score, 0);
        this.score = Math.floor(totalScore / this.preparedItems.length * 100);
    }

    getCurrentItem() {
        return this.activeItem.item;
    }

    itemSubmitted(chosenOptions) {
        this.activeItem.timesAnswered += 1;
        let isCorrectAnswerGiven = _.isEqual(chosenOptions, this.activeItem.item.correctResponses);
        this.scoreOutcome(isCorrectAnswerGiven);
    }
}

