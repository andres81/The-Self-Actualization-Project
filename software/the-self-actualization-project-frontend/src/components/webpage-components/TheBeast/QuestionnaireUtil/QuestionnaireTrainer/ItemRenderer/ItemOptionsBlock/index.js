import ItemEntry from "./ItemEntry";

function ItemOptionsBlock(props) {

    const handleChange = (event) => {
        if (props.cardinality === 'SINGLE') {
            props.optionsChangeCallback([event.target.value]);
        } else {
            let newOptions = [...props.chosenOptions];
            event.target.checked ?
                newOptions.push(event.target.value) :
                newOptions.splice(newOptions.indexOf(event.target.value), 1);
            props.optionsChangeCallback(newOptions);
        }
    }

    const options = props.options.map((option) => {
        return <ItemEntry key={option.id}
                          handleChange={(event) => handleChange(event)}
                          optionData={option} id={props.id}
                          cardinality={props.cardinality}
                          selectedOptions={props.chosenOptions}
                          correctResponses={props.correctResponses}
                          state={props.state}/>
    });

    return (
        <div className="item-renderer-options">
            <ul className="list-group">
                {options}
            </ul>
        </div>
    )
}

export default ItemOptionsBlock;