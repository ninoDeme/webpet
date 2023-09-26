import { useNavigate } from '@solidjs/router';
import { Component, JSX, createSignal } from "solid-js";
import {useAuth} from '../AuthProvider';
import WPForm from '../templates/WPForm';

const Login: Component = () => {
  const {updateAuth} = useAuth();
  const navigate = useNavigate();

  let form: {email?: HTMLInputElement, senha?: HTMLInputElement} = {}

  const [statusc, setStatus] = createSignal<JSX.Element>();
  const sendCadastrar = async () => {

    const body = {
      email: form.email?.value,
      senha: form.senha?.value,
    };
    try {
      if (!body.senha) {
        throw new Error("Senha é requerida")
      }
      if (!body.email) {
        throw new Error("E-Mail é requerida")
      }
    } catch (e) {
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possível cadastrar-se: {e.message}</span>);
    }
    const res = await fetch("http://localhost:3000/api/login", {
      method: 'POST', body: JSON.stringify(body)
    });
    if (res.status === 200) {
      updateAuth(await res.text())
      navigate('/');
    } else {
      let msg = await res.text();
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possível fazer login: {msg}</span>);
    };
  };

  return (
    <form onSubmit={(form) => {sendCadastrar(); form.preventDefault()}} novalidate class="mx-8 my-10 md:mx-20 flex flex-col items-start gap-5">
      <h1 class="text-4xl">Faça Login</h1>
      <WPForm titulo="E-Mail" nome="email" type="email" ref={form.email}/>
      <WPForm titulo="Senha" nome="senha" type="password" ref={form.senha}/>
      <button type="submit" class="bg-fundo1 text-lg flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto">
        <span>Entrar</span>
        {/* <span class="material-sybols-outlined">{props.icone}</span> */}
      </button>
      {statusc()}
    </form>
  );
};

export default Login;
