function Resources() {

    return (
        <div className={'container'}>
            <h1>Resources</h1>
            <p>Resources one can use for self-actualization by subject</p>

            <h2>Information Technology</h2>
            <ul>
                <li>
                    <div className="accordion"
                         id="professionals-accordion">
                        <div className="accordion-item">
                            <h2 className="accordion-header"
                                id="professionals-accordion-header">
                                <button className="accordion-button"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#Professionals"
                                        aria-expanded="true"
                                        aria-controls="Professionals">
                                    <h5>Professionals</h5>
                                </button>
                            </h2>
                            <div id="Professionals"
                                 className="accordion-collapse collapse collapsed"
                                 aria-labelledby="test">
                                <div className="accordion-body">
                                    <div className="list-group">
                                        <a href={"https://www.youtube.com/watch?v=dQw4w9WgXcQ"}
                                           className="list-group-item list-group-item-action"
                                           aria-current="true"
                                           target={"_blank"}
                                           rel="noreferrer">
                                            <div
                                                className="d-flex w-100 justify-content-between">
                                                <h5 className="mb-1">Anonymous</h5>
                                                <small>Senior</small>
                                            </div>
                                            <p className="mb-1">Allrounder</p>
                                            <small>*</small>
                                        </a>
                                        <a href={"https://mortitech.com/"}
                                           className="list-group-item list-group-item-action"
                                           aria-current="true"
                                           target={"_blank"}
                                           rel="noreferrer">
                                            <div
                                                className="d-flex w-100 justify-content-between">
                                                <h5 className="mb-1">Morteza
                                                    Taghdisi</h5>
                                                <small>Senior
                                                    Software
                                                    Engineer /
                                                    Architect</small>
                                            </div>
                                            <p className="mb-1">Architect
                                                /
                                                Software
                                                Engineer,
                                                Backend / Frontend /
                                                Android / Software
                                                Design,
                                                IT
                                                professional</p>
                                            <small>Web (Frontend /
                                                Backend) / Android /
                                                Software
                                                design</small>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    )

}

export default Resources;