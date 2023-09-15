import {Component, JSX, Show} from 'solid-js';

const WPBotao: Component<{children?: JSX.Element, icone?: string, isSelected?: boolean, fundo?: string, class?: string}> = (props) => {
    return (
        <button class={props.fundo ?? "bg-fundo1" + " flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto" + props.class}
            classList={{['bg-fundo_escuro text-texto_claro']: props.isSelected}}>
            <Show when={props.children}>
            <span>{props.children}</span>
            </Show>
            <Show when={props.icone}>
                <span class="material-symbols-outlined">{props.icone}</span>
            </Show>
        </button>
    );
};

export default WPBotao;