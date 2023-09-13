import logo from "/logo-branco.svg"
import './cabecalho.css'

export function Cabecalho() {
    return (
        <header id="cabecalho" className='flex justify-between sticky py-4 px-12 bg-fundo_escuro h-20 md:h-24'>
            <img className="" src={logo}></img>
            <div className="flex w-1/2 my-2 bg-fundo2 items-center rounded px-3">
                <input className='flex-1 bg-transparent outline-none'/>
                <span className="material-symbols-outlined">search</span>
            </div>
            <button className="bg-fundo_alt flex justify-center items-center gap-2 rounded max-md:aspect-square my-2 px-3">
                <span className="material-symbols-outlined"> account_circle</span>
                <span className="hidden md:block">Minha Conta</span>
            </button>
        </header>
    )
}

export default Cabecalho