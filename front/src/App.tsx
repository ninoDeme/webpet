import type {Component} from 'solid-js';
import Cabecalho from './templates/cabecalho';
import './App.css';
import {Outlet, Route, Router, Routes} from '@solidjs/router';
import Home from './pages/home';
import ProdutoPage from './pages/produto';
import PesquisaProdutos from './pages/pesquisa-produtos';
import Login from './pages/login';
import Cadastrar from './pages/cadastrar';
import CadastroSucesso from './pages/cadastro-sucesso';

const App: Component = () => {
  return (
      <Router>
        <Routes>
          <Route element={<> <Cabecalho /> <Outlet /></>} path={"/*"}>
            <Route path="/home" component={Home} />
            <Route path="/cadastrar" component={Cadastrar} />
            <Route path="/cadastro-sucesso" component={CadastroSucesso} />
            <Route path="/login" component={Login} />
            <Route path="/item/:id" component={ProdutoPage} />
            <Route path="/animal/:animal" component={PesquisaProdutos} />
            <Route path="/categoria/:categoria" component={PesquisaProdutos} />
            <Route path="/favoritos/" component={PesquisaProdutos} />
            <Route path="/" component={Home} />
          </Route>
        </Routes>
      </Router>
  );
};

export default App;
