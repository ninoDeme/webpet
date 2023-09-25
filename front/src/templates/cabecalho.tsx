import logo from "/logo-branco.svg";
import {Component, For, createResource, createSignal, onMount} from 'solid-js';
import {A, useNavigate} from '@solidjs/router';
import {AnimalI} from '../models/Animal';
import {CategoriaI} from '../models/Categoria';
import {Menu, MenuItem, Popover, PopoverButton, PopoverPanel, Transition} from 'solid-headless';
import {useAuth} from '../AuthProvider';

const WPMenu: Component<{title: string, itens: {title: string, link: string;}[];}> = (props) => (
  <Popover class="relative" defaultOpen={false}>{({isOpen, setState}) => (<>
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
            <MenuItem as={A} href={item.link} onClick={() => setState(false)} class="text-sm p-1 text-left rounded hover:bg-fundo_escuro hover:text-texto_claro">
              {item.title}
            </MenuItem>
          }</For>
        </Menu>
      </PopoverPanel>
    </Transition>

  </>)}</Popover>
);

function getResource(nome: string) {
  return async () => {
    const res = await fetch(`http://localhost:3000/api/${nome}`, {mode: 'cors'});
    const r = await res.json();
    return r.resultado;
  };
}

const Cabecalho: Component = () => {
  const navigate = useNavigate();
  const [categorias] = createResource<CategoriaI[]>(getResource('categorias'));
  const [animais] = createResource<AnimalI[]>(getResource('animal'));
  const {auth, logout} = useAuth();

  return (
    <>
      <header id="cabecalho" class="flex justify-between sticky py-4 px-6 md:px-12 bg-fundo_escuro h-20 md:h-24">
        <A href="/">
          <img class="max-h-full" src={logo}></img>
        </A>
        <div class="flex w-1/2 my-2 bg-fundo2 items-center rounded px-3">
          <input class="flex-1 bg-transparent outline-none min-w-0" id="big-search" onKeyPress={key => key.key === 'Enter' && navigate(`/search?q=${encodeURI(document.querySelector<HTMLInputElement>("#big-search").value)}`)} />
          <span class="material-symbols-outlined">search</span>
        </div>
        {auth()?.id
          ?
          <button class="bg-fundo_alt flex justify-center items-center gap-2 rounded max-md:aspect-square my-2 px-3" onClick={() => logout()}>
            <span class="material-symbols-outlined">logout</span>
            <span class="hidden md:block">Sair</span>
          </button>
          :
          <div class="flex gap-3">
            <A href="/login" class="bg-fundo_alt flex justify-center items-center gap-2 rounded my-2 px-3">
              <span>Entrar</span>
            </A>
            <A href="/cadastrar" class="bg-fundo_alt flex justify-center items-center gap-2 rounded my-2 px-3">
              <span>Cadastrar</span>
            </A>
          </div>
        }
      </header>
      <nav class="flex items-center py-1 px-6 md:px-12 bg-fundo1 h-12 md:h-14 gap-3 mt-2">
        <WPMenu title="Animais" itens={(animais() ?? []).map(cat => ({title: cat.nome, link: `/search?animal=${cat.id}`}))}></WPMenu>
        <WPMenu title="Categorias" itens={(categorias() ?? []).map(cat => ({title: cat.nome, link: `/search?categoria=${cat.id}`}))}></WPMenu>
        <div class="flex-1"></div>
        <A href="/favoritos">
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
