/*
 * Copyright 2024 - 2026 André Schepers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import React from 'react';

class AccordionEntry extends React.Component {

    render() {
        return (
            <div className="accordion-item">
                <h2 className="accordion-header"
                    id={"panelsStayOpen-heading" + this.props.id}>
                    <button className="accordion-button collapsed" type="button"
                            data-bs-toggle="collapse"
                            data-bs-target={"#panelsStayOpen-collapse" + this.props.id}
                            aria-expanded="true"
                            aria-controls="panelsStayOpen-collapseOne">
                        {this.props.headerText}
                    </button>
                </h2>
                <div id={"panelsStayOpen-collapse" + this.props.id}
                     className="accordion-collapse collapse"
                     aria-labelledby={"panelsStayOpen-heading" + this.props.id}>
                    <div className="accordion-body">
                        {this.props.child}
                    </div>
                </div>
            </div>
        )
    }
}

export default AccordionEntry;