import { Component, createSignal } from "solid-js";
import banner1 from "/banner1.webp";
import banner2 from "/banner2.webp";
import banner3 from "/banner3.webp";
import banner4 from "/banner4.webp";
import { ProdutoSimplesI } from "../models/Produto";
import ListaProdutos from "../templates/lista-produtos";

const Home: Component = () => {
  const [produtos1, setProdutos1] = createSignal<ProdutoSimplesI[]>([]);
  const [produtos2, setProdutos2] = createSignal<ProdutoSimplesI[]>([]);
  const [produtos3, setProdutos3] = createSignal<ProdutoSimplesI[]>([]);

  fetch("http://localhost:3000/api/produtos?limit=10&random=true", {mode: 'cors'}).then(async res => {
    setProdutos1(await res.json().then(r => r.resultado));
  })
  fetch("http://localhost:3000/api/produtos?limit=10&random=true", {mode: 'cors'}).then(async res => {
    setProdutos2(await res.json().then(r => r.resultado));
  })
  fetch("http://localhost:3000/api/produtos?limit=10&random=true", {mode: 'cors'}).then(async res => {
    setProdutos3(await res.json().then(r => r.resultado));
  })

  return (
    <>
      <div class="w-full flex justify-stretch overflow-clip h-72 mt-2 mb-4">
        <img class="flex-1 object-cover" src={banner1} />
        <img class="flex-1 object-cover" src={banner2} />
        <img class="flex-1 object-cover" src={banner3} />
        <img class="flex-1 object-cover" src={banner4} />
      </div>

      <ListaProdutos produtos={produtos1()} titulo="Ofertas Do Dia"/>
      <ListaProdutos produtos={produtos2()} titulo="Mais Vendidos"/>
      <ListaProdutos produtos={produtos3()} titulo="Novidades"/>
    </>
  )
}

export default Home
