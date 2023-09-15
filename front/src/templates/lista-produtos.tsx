import {Component, For, createEffect, onMount} from "solid-js";
import Swiper from "swiper";
import {Navigation} from "swiper/modules";
import 'swiper/css';
import 'swiper/css/navigation';

import {register} from 'swiper/element/bundle';
import {ProdutoSimplesI} from "../models/Produto";
import {A} from '@solidjs/router';
register();

const ListaProdutos: Component<{produtos: ProdutoSimplesI[], titulo: string;}> = (props) => {
  const id = Math.floor(Math.random() * 200 + 1);

  createEffect((swiper: Swiper) => {
    props.produtos;
    if (swiper) {
      swiper.update();
    } else {
      swiper = new Swiper('#swiper-' + id, {
        spaceBetween: 40,
        slidesPerView: 4,
        slidesOffsetBefore: 80,
        slidesOffsetAfter: 80,
        navigation: {
          prevEl: ".swiper-button-prev",
          nextEl: ".swiper-button-next",
        },
        modules: [Navigation]
      });

    }
    return swiper;
  });

  return (
    <>
      <div>
        <h1 class="text-5xl ml-20 my-8">{props.titulo}</h1>
      </div>
      <div class="w-full flex justify-stretch h-72 mt-2">
        <div id={'swiper-' + id.toString()} class="swiper w-full h-full">
          <div class="swiper-wrapper">
            <For each={props.produtos}>{(produto, i) => (
              <div class="swiper-slide">
                <A href={`/item/${produto.id}`}
                   class="flex flex-col items-center h-full gap-1 overflow-hidden">
                  <div class="object-cover bg-white mx-4 h-4/6 self-stretch rounded overflow-hidden">
                    <img src={`/produtos/${produto.id}.webp`} class="h-full py-2 mx-auto rounded" loading="lazy" />
                  </div>
                  <div class="text-xl">{`R$${produto.preco.toLocaleString()}`}</div>
                  <div class="text-center text-ellipsis overflow-hidden h-12">{produto.nome}</div>
                </A>
              </div>
            )}</For>
          </div>
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
      </div>
    </>
  );
};

export default ListaProdutos;
