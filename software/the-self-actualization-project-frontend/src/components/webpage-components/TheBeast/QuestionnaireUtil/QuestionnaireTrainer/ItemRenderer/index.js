import ItemEntry from "./ItemEntry";

function ItemRenderer(props) {

    var type = props.itemEntryType;
    var value = props.itemEntryValue
    var text = props.itemEntryText


    return (
        <div className="item-renderer">
            <p>Question?</p>
            <div>
                <ul className="list-group">
                    <ItemEntry itemEntryData={{"type": "checkbox", "value": "", "text": "First checkbox"}}/>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="checkbox"
                               value="" aria-label="..."/>
                        First checkbox
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="checkbox"
                               value="" aria-label="..."/>
                        Second checkbox
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="checkbox"
                               value="" aria-label="..."/>
                        Third checkbox
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="checkbox"
                               value="" aria-label="..."/>
                        Fourth checkbox
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="checkbox"
                               value="" aria-label="..."/>
                        Fifth checkbox
                    </li>
                </ul>
            </div>
            <br />
            <div>
                <ul className="list-group">
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="radio"
                               value="" aria-label="..." name="test-radio"/>
                        First radio
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="radio"
                               value="" aria-label="..." name="test-radio"/>
                        Second radio
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="radio"
                               value="" aria-label="..." name="test-radio"/>
                        Third radio
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="radio"
                               value="" aria-label="..." name="test-radio"/>
                        Fourth radio
                    </li>
                    <li className="list-group-item">
                        <input className="form-check-input me-1" type="radio"
                               value="" aria-label="..." name="test-radio"/>
                        Fifth radio
                    </li>
                </ul>
            </div>
        </div>
    )
}

export default ItemRenderer;