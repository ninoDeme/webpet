import { Component, For, onMount } from "solid-js";
import Swiper from "swiper";
// import 'swiper/css';

import { register } from 'swiper/element/bundle';
import { ProdutoSimplesI } from "../models/ProdutoSimples";
register();

const Home: Component = (props: {produtos: ProdutoSimplesI[]}) => {
  const id = Math.floor(Math.random() * 200 + 1) 

  onMount(() => {
    new Swiper('.' + id, {
      spaceBetween: 20
    });
  })
  return (
    <>
    <div class="w-full flex justify-stretch h-72 mt-2">
      <div id={id.toString()} class="swiper">
        <div class="swiper-wrapper">
            <For each={props.produtos}>{(produto, i) => (
              <div class="swiper-slide">
                <img src={produto.img}/>
              </div>
            )}</For>
        </div>
      </div>
    </div>
    </>
    )
}

export default Home
