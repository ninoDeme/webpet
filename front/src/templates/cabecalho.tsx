import logo from "/logo-branco.svg"
import {Component} from 'solid-js';
import {A} from '@solidjs/router';

const Cabecalho: Component = () => {
    return (
        <>
        <header id="cabecalho" class='flex justify-between sticky py-4 px-12 bg-fundo_escuro h-20 md:h-24'>
            <A href="/">
                <img class="max-h-full" src={logo}></img>
            </A>
            <div class="flex w-1/2 my-2 bg-fundo2 items-center rounded px-3">
                <input class='flex-1 bg-transparent outline-none'/>
                <span class="material-symbols-outlined">search</span>
            </div>
            <button class="bg-fundo_alt flex justify-center items-center gap-2 rounded max-md:aspect-square my-2 px-3">
                <span class="material-symbols-outlined"> account_circle</span>
                <span class="hidden md:block">Minha Conta</span>
            </button>
        </header>
        <nav class="flex items-center py-1 px-12 bg-fundo1 h-12 md:h-14 gap-3 mt-2">
            <A href="/">
              <button class="bg-fundo2 flex justify-center gap-2 rounded max-md:aspect-square py-1 px-3">
                  <span class="hidden md:block">Categorias</span>
              </button>
            </A>
            <A href="/">
              <button class="bg-fundo2 flex justify-center gap-2 rounded max-md:aspect-square py-1 px-3">
                  <span class="hidden md:block">Pets</span>
              </button>
            </A>
            <div class="flex-1"></div>
            <A href="/">
              <button class="bg-fundo2 flex justify-center gap-2 rounded max-md:aspect-square py-1 px-1">
                  <span class="material-symbols-outlined">favorite</span>
              </button>
            </A>
            <A href="/">
              <button class="bg-fundo2 flex justify-center gap-2 rounded max-md:aspect-square py-1 px-1">
                  <span class="material-symbols-outlined">shopping_cart</span>
              </button>
            </A>
            <A href="/">
              <button class="bg-fundo2 flex justify-center gap-2 rounded max-md:aspect-square py-1 px-1">
                  <span class="material-symbols-outlined">settings</span>
              </button>
            </A>
        </nav>
        </>
    )
}

export default Cabecalho
