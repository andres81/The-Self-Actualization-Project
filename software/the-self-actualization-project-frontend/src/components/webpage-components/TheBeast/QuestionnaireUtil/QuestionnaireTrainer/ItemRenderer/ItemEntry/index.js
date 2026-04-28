function ItemEntry(props) {

    var type = props.itemEntryData.type;
    var value = props.itemEntryData.value
    var text = props.itemEntryData.text

    console.log(props.itemEntryData);

    return (
        <li className="list-group-item">
            <input className="form-check-input me-1" type={type}
                   value={value} aria-label="..."/>
            {text}
        </li>
    )
}

export default ItemEntry;