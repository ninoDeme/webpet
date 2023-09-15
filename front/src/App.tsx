import type { Component } from 'solid-js';
import Cabecalho from './templates/cabecalho'
import './App.css'
import { Route, Router, Routes } from '@solidjs/router';
import Home from './pages/home';

const App: Component = () => {
  return (
    <>
      <Cabecalho></Cabecalho>
      <Router>
          <Routes>
            <Route path="/" component={Home} /> 
            <Route path="/home" component={Home} /> 
          </Routes>
      </Router>
    </>
  );
};

export default App;
