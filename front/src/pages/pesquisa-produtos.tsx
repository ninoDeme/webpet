import {Component, For, createEffect, createMemo, createResource} from "solid-js";
import {A, useLocation, useParams} from '@solidjs/router';
import {ProdutoSimplesI} from '../models/Produto';

async function fetchProdutos(query: {animal?: string, categoria?: string, fav?: string, query?: string}): Promise<fetchResults<ProdutoSimplesI>> {
  const currQuery = new URLSearchParams(query.query);
  const finalQuery = new URLSearchParams({limit: '20', pagina: currQuery.get('pagina') ?? '1'});
  let categoria = query.categoria || currQuery.get('categoria')
  if (categoria) {
    finalQuery.set('categoria', categoria)
  }
  let animal = query.animal || currQuery.get('animal')
  if (animal) {
    finalQuery.set('animal', animal)
  }
  let fav = query.fav || currQuery.get('fav')
  if (fav) {
    finalQuery.set('fav', 'true')
  }
  if (currQuery.get('q')) {
    finalQuery.set('q', currQuery.get('q'));
  }
  
  
  const res = await fetch(`http://localhost:3000/api/produtos?${finalQuery}`, {mode: 'cors'});
  return res.json();
}

const PesquisaProdutos: Component = () => {

  const params = useParams();
  const location = useLocation();

  const derivedState = createMemo(() => ({animal: params['animal'], categoria: params['categoria'], fav: params['fav'], query: location.search}));

  const [produtos] = createResource(derivedState, fetchProdutos);

  return (
    <>
      {/* <div class="w-full flex justify-stretch overflow-clip h-72 mt-2 mb-4">
        <img class="flex-1 object-cover" src={banner1} />
        <img class="flex-1 object-cover" src={banner2} />
        <img class="flex-1 object-cover" src={banner3} />
        <img class="flex-1 object-cover" src={banner4} />
      </div> */}
      <div>
        <h1 class="text-5xl ml-20 my-8">{'Resultados'}</h1>
      </div>
      <div class="mx-20">
        <For each={produtos()?.resultado}>{(prod, i) =>
          <div class="bg-fundo2 rounded overflow-hidden h-44 flex my-8" classList={{['bg-fundo_alt']: (i() % 2 === 0)}}>
            <img class="object-contain bg-white h-full w-56 p-2" src={`/produtos/${prod.id}.webp`} />
            <div class="flex flex-col flex-1 m-4">
              <h2 class="text-xl">{prod.nome}</h2>
              <h2 class="text-2xl font-bold mt-2 ml-2">R$ {prod.preco.toLocaleString(undefined, {minimumFractionDigits: 2, maximumFractionDigits: 2})}</h2>
              <div class="flex-1"></div>
              <div class="self-start flex gap-3">
                <A href={`/item/${prod.id}`}>
                  <button class="hover:bg-opacity-10 hover:bg-gray-950 text-blue-600 font-bold flex justify-center gap-2 rounded max-md:aspect-square py-1 px-2">
                    Comprar Agora
                  </button>
                </A>
                <A href={`/item/${prod.id}`}>
                <button class="hover:bg-opacity-10 hover:bg-gray-950 text-blue-600 font-bold flex justify-center gap-2 rounded max-md:aspect-square py-1 px-2">
                  Abrir Página do Produto
                </button>
                </A>
              </div>
            </div>
          </div>
        }</For>
        <div class="flex gap-5 m-4">
        <For each={Array.from({length: Math.ceil((produtos()?.total ?? 0) / 20)}, (_, i) => i+1)}>{(val) => 
          <button class={"h-12 w-12 bg-fundo1 flex justify-center items-center text-texto font-medium text-2xl" + (produtos().pagina === val ? ' bg-fundo_escuro text-texto_claro' : '')}
                  onClick={() => {throw new Error()}}>
            {val}
          </button>
        }</For>
        </div>
      </div>
    </>
  );
};

export default PesquisaProdutos;

interface fetchResults<T> {
  resultado: T[],
  total: number,
  pagina: number
}