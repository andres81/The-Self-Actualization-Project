import {NavLink, Outlet} from "react-router";

function QuestionnaireUtil() {

    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <NavLink relative="route" to={"./"}
                             className="navbar-brand">Questionnaire
                        Util</NavLink>
                    <button className="navbar-toggler" type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#navbarNav"
                            aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="/"
                                   id="navbarDropdownMenuLink" role="button"
                                   data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Use Cases
                                </a>
                                <ul className="dropdown-menu"
                                    aria-labelledby="navbarDropdownMenuLink">
                                    <li className="nav-item">
                                        <NavLink to="./trainer"
                                                 className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                            UC1: Questionnaire Trainer
                                        </NavLink>
                                    </li>
                                    <li className="nav-item">
                                        <NavLink to="./trainer"
                                                 className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                            UC2: Attitude Test Questionnaire
                                        </NavLink>
                                    </li>
                                </ul>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="/"
                                   id="navbarDropdownMenuLink" role="button"
                                   data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Tools
                                </a>
                                <ul className="dropdown-menu"
                                    aria-labelledby="navbarDropdownMenuLink">
                                    <li className="nav-item">
                                        <NavLink to="./trainer"
                                                 className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                            Questionnaire Trainer
                                        </NavLink>
                                    </li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <NavLink to={"./software-design"} className="nav-link"
                                         href="#">Software Design</NavLink>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <Outlet/>
        </>
    )
}

export default QuestionnaireUtil;