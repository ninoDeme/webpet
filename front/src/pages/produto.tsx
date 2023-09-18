import {Component, Show, createSignal, onMount} from 'solid-js';
import {ProdutoI} from '../models/Produto';
import {A, useParams} from '@solidjs/router';
import {BuscaCepResultado} from '../models/BuscaCepResultado';

const ProdutoPage: Component = () => {
    const [produto, setProduto] = createSignal<ProdutoI>();
    const [cepResult, setCepRes] = createSignal<BuscaCepResultado>();
    const params = useParams();

    const emptyCepIf = function () {
        if (typeof cepResult() === 'string') {
            setCepRes(undefined);
        }
    };
    const buscarCep = async function () {
        try {
            setCepRes(undefined);
            let cep: string = (document.getElementById('busca-cep') as HTMLInputElement).value;
            cep = cep.replace(/\D/g, '');
            if (cep.length !== 8) {
                throw new Error('CEP Inválido');
            }
            const res = await fetch(`http://localhost:3000/api/cep?cep=${cep}`, {mode: 'cors'});
            if (res.status === 400) {
                throw new Error('CEP Inválido');
            }
            if (res.status === 404) {
                throw new Error('CEP Não Encontrado');
            }
            if (res.status === 500) {
                throw new Error('Ocorreu um erro, tente novamente mais tarde');
            }
            const decoder = new TextDecoder('iso-8859-1');
            const text = decoder.decode(await res.arrayBuffer());
            setCepRes(JSON.parse(text));
        } catch (e) {
            console.error(e);
            setCepRes(e.message);
        }
    };
    onMount(() => {
        fetch(`http://localhost:3000/api/produto?id=${params.id}`, {mode: 'cors'}).then(async res => {
            setProduto(await res.json().then(r => r.resultado));
        });
    });
    return (
        <div class="grid md:grid-cols-3 gap-10 mx-8 md:mx-20 my-20">
            <div class="bg-white w-full px-4 py-8 h-auto rounded overflow-hidden mb-5 border border-fundo_alt">
                {produto()
                    ? <img class="aspect-square object-contain w-full" src={`/produtos/${produto().id}.webp`} />
                    : <div class="w-full bg-fundo1"></div>
                }
            </div>
            <div class="flex flex-col items-start gap-3">
                <h1 class="text-2xl mb-4 font-bold">{produto()?.nome}</h1>
                <h1 class="text-xl font-bold">Categoria: {produto()?.categoria.nome}</h1>
                <h1 class="text-xl font-bold">Animal: {produto()?.animal.nome}</h1>
                <h1 class="text-xl font-bold">Peso: {produto()?.peso}</h1>
                <div class="py-1 my-auto"> {produto()?.descricao} </div>
            </div>
            <div class="bg-fundo1 rounded p-4 flex flex-col">
                <div class="flex justify-end gap-4">
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
                </div>
                <div class="my-5 mx-10 flex flex-col items-start flex-1">
                    <span class="text-3xl font-bold mb-1">R$ {produto()?.preco.toLocaleString(undefined, {maximumFractionDigits: 2, minimumFractionDigits: 2})}</span>
                    <span class="text-lg">À vista no cartão ou pix ou até 12x R$ {(produto()?.preco / 12 * 1.1).toLocaleString(undefined, {maximumFractionDigits: 2, minimumFractionDigits: 2})} no cartão</span>

                    <div class="p-3 bg-fundo2 rounded flex mt-5 mx-auto items-center w-10/12" classList={{['shake']: typeof cepResult() === "string"}}>
                        <input class="outline-none bg-transparent placeholder:text-texto flex-1 min-w-0" placeholder="Digite Seu CEP..." id="busca-cep" onKeyPress={(e) => e.key === "Enter" && buscarCep()} onInput={emptyCepIf} />
                        <button class="material-symbols-outlined" onClick={buscarCep}>
                            keyboard_arrow_right
                        </button>
                    </div>
                    <Show when={cepResult() != null && typeof cepResult() === 'object'} fallback={(<span class="text-red-600 font-bold">{cepResult() as string || ''}</span>)}>
                        {/*  @ts-ignore */}
                        <span>{cepResult()?.state} - {cepResult()?.city} - {cepResult()?.district}</span>
                    </Show>

                    <div class="flex-1"></div>
                    <button class="py-3 px-8 mb-5 bg-fundo2 rounded text-center mt-3 mx-auto">Comprar</button>
                </div>
            </div>
        </div>
    );
};

export default ProdutoPage;