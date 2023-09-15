import logo from "/logo-branco.svg";
import {Component, For, createSignal, onMount} from 'solid-js';
import {A} from '@solidjs/router';
import {AnimalI} from '../models/Animal';
import {CategoriaI} from '../models/Categoria';
import {Menu, MenuItem, Popover, PopoverButton, PopoverPanel, Transition} from 'solid-headless';

const WPMenu: Component<{title: string, itens: {title: string, link: string;}[];}> = (props) => (
    <Popover class="relative" defaultOpen={false}>{({isOpen}) => (<>
        <PopoverButton class="bg-fundo2 flex justify-center gap-2 rounded py-1 px-2"
            classList={{['bg-fundo_escuro text-texto_claro']: isOpen()}}>
            <span>{props.title}</span>
            <span class="material-symbols-outlined">menu</span>
        </PopoverButton>
        <Transition show={isOpen()}
            enterFrom="opacity-0 pointer-events-none"
            enterTo="opacity-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0 pointer-events-none">
            <PopoverPanel unmount={false} class="absolute z-10 px-4 mt-3 sm:px-0 lg:max-w-3xl" >
                <Menu class="overflow-hidden w-64 rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 bg-white flex flex-col space-y-1 p-1">
                    <For each={props.itens}>{(item) =>
                        <MenuItem as={A} href={item.link} class="text-sm p-1 text-left rounded hover:bg-fundo_escuro hover:text-texto_claro">
                            {item.title}
                        </MenuItem>
                    }</For>
                </Menu>
            </PopoverPanel>
        </Transition>

    </>)}</Popover>
);

const Cabecalho: Component = () => {
    const [categorias, setCategoria] = createSignal<CategoriaI[]>();
    const [animais, setAnimais] = createSignal<AnimalI[]>();

    onMount(() => {
        fetch(`http://localhost:9000/categorias`, {mode: 'cors'}).then(async res => {
            setCategoria(await res.json().then(r => r.resultado));
        });
        fetch(`http://localhost:9000/animal`, {mode: 'cors'}).then(async res => {
            setAnimais(await res.json().then(r => r.resultado));
        });
    });
    return (
        <>
            <header id="cabecalho" class='flex justify-between sticky py-4 px-6 md:px-12 bg-fundo_escuro h-20 md:h-24'>
                <A href="/">
                    <img class="max-h-full" src={logo}></img>
                </A>
                <div class="flex w-1/2 my-2 bg-fundo2 items-center rounded px-3">
                    <input class="flex-1 bg-transparent outline-none min-w-0"/>
                    <span class="material-symbols-outlined">search</span>
                </div>
                <button class="bg-fundo_alt flex justify-center items-center gap-2 rounded max-md:aspect-square my-2 px-3">
                    <span class="material-symbols-outlined"> account_circle</span>
                    <span class="hidden md:block">Minha Conta</span>
                </button>
            </header>
            <nav class="flex items-center py-1 px-6 md:px-12 bg-fundo1 h-12 md:h-14 gap-3 mt-2">
                <WPMenu title="Animais" itens={(animais() ?? []).map(cat => ({title: cat.nome, link: `/animal/${cat.id}`}))}></WPMenu>
                <WPMenu title="Categorias" itens={(categorias() ?? []).map(cat => ({title: cat.nome, link: `/categoria/${cat.id}`}))}></WPMenu>
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
    );
};

export default Cabecalho;
