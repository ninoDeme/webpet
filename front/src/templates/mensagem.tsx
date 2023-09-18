import { Component } from "solid-js";

const Mensagem: Component<{ text: string, onClick: () => void, button: string }> = (props) => {
  return <div class="my-20 mx-auto w-7/12 bg-fundo2 flex flex-col items-center min-h-[20rem] p-10">
    <h1 class="text-2xl font-bold">{props.text}</h1>
    <div class="flex-1"></div>
    <button onClick={props.onClick} class="bg-fundo_escuro text-texto_claro text-lg flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto">
      {props.button}
    </button>
  </div>
}

export default Mensagem;
