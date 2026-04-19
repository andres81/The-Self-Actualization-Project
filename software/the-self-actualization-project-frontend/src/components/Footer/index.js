import './style.scss'
import {Link} from "react-router";

function Footer() {
    return <div className="container footer-container">
        <footer className="py-3 my-4">
            <ul className="nav justify-content-center border-bottom pb-3 mb-3">
                <li className="nav-item">
                    <Link to={'/'}
                          className="nav-link px-2 text-muted">
                        Home</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/features'}
                          className="nav-link px-2 text-muted">
                        Features</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/faqs'}
                          className="nav-link px-2 text-muted">
                        FAQs</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/about'}
                          className="nav-link px-2 text-muted">
                        About</Link>
                </li>
                <li className="nav-item">
                    <a href={'/disclaimer.html'}
                       className="nav-link px-2 text-muted">
                        Disclaimer</a>
                </li>

            </ul>
            <p className="text-center text-muted">© 2026 André Schepers</p>
        </footer>
    </div>
}

export default Footer;