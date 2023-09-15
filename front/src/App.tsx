import type { Component } from 'solid-js';
import Cabecalho from './templates/cabecalho'
import './App.css'
import { Route, Router, Routes } from '@solidjs/router';
import Home from './pages/home';
import ProdutoPage from './pages/produto';
import PesquisaProdutos from './pages/pesquisa-produtos';

const App: Component = () => {
  return (
    <>
      <Cabecalho></Cabecalho>
        <Routes>
          <Route path="/home" component={Home} /> 
          <Route path="/item/:id" component={ProdutoPage}/> 
          <Route path="/animal/:animal" component={PesquisaProdutos}/> 
          <Route path="/categoria/:categoria" component={PesquisaProdutos}/> 
          <Route path="/" component={Home} /> 
        </Routes>
    </>
  );
};

export default App;
