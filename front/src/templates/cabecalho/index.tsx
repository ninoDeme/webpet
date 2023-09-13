import logo from "/logo-branco.svg"
import './cabecalho.scss'

export function Cabecalho() {
    return (
        <header id="cabecalho">
            <img className="logo-branco" src={logo}></img>
        </header>
    )
}

export default Cabecalho