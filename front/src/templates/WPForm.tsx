import {Component} from 'solid-js';

const WPForm: Component<{titulo: string, nome: string, type?: string, ref?: HTMLInputElement;}> = (props) => (
    <div>
        <h3 class="text-xl">{props.titulo}</h3>
        <input class="w-96 outline-none bg-transparent text-lg border-b border-gray-700 mt-1" name={props.nome} id={`${props.nome}-form`} type={props.type} ref={props.ref} />
    </div>
);

export default WPForm;