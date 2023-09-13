/* @refresh reload */
import './index.css';
import { render } from 'solid-js/web';

import App from './App';
import {Route, Router, Routes} from '@solidjs/router';
import Cabecalho from './templates/cabecalho';

const root = document.getElementById('root');

if (import.meta.env.DEV && !(root instanceof HTMLElement)) {
  throw new Error(
    'Root element not found. Did you forget to add it to your index.html? Or maybe the id attribute got misspelled?',
  );
}

render(() => (
  <Router>
      <Routes>
        {/* <Route path="/*" component={} />  */}
        <Route path="/" component={App} /> 
      </Routes>
  </Router>
),
  root!
);
