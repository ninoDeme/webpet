import logo from "/logo-branco.svg"
import './cabecalho.css'
import {Component} from 'solid-js';

const Cabecalho: Component = () => {
    return (
        <header id="cabecalho" class='flex justify-between sticky py-4 px-12 bg-fundo_escuro h-20 md:h-24'>
            <img class="" src={logo}></img>
            <div class="flex w-1/2 my-2 bg-fundo2 items-center rounded px-3">
                <input class='flex-1 bg-transparent outline-none'/>
                <span class="material-symbols-outlined">search</span>
            </div>
            <button class="bg-fundo_alt flex justify-center items-center gap-2 rounded max-md:aspect-square my-2 px-3">
                <span class="material-symbols-outlined"> account_circle</span>
                <span class="hidden md:block">Minha Conta</span>
            </button>
        </header>
    )
}

export default Cabecalho