import { Navigate, useNavigate } from '@solidjs/router';
import { Component, JSX, createSignal } from "solid-js";
import {useAuth} from '../AuthProvider';

const Login: Component = () => {
  const {updateAuth} = useAuth();
  const navigate = useNavigate();

  const LForm: Component<{ titulo: string, nome: string, type?: string; }> = (props) => (
    <div>
      <h3 class="text-xl">{props.titulo}</h3>
      <input class="w-96 outline-none bg-transparent text-lg border-b border-gray-700 mt-1" id={`${props.nome}-form`} type={props.type} />
    </div>
  );

  const [statusc, setStatus] = createSignal<JSX.Element>();
  const sendCadastrar = async () => {

    const body = {
      email: (document.getElementById("email-form") as HTMLInputElement).value,
      senha: (document.getElementById("senha-form") as HTMLInputElement).value,
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
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possíve fazer login: {msg}</span>);
    };
  };

  return (
    <div class="mx-8 my-10 md:mx-20 flex flex-col items-start gap-5">
      <h1 class="text-4xl">Faça Login</h1>
      <LForm titulo="E-Mail" nome="email" type="email" />
      <LForm titulo="Senha" nome="senha" type="password" />
      <button onClick={sendCadastrar} class="bg-fundo1 text-lg flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto">
        <span>Entrar</span>
        {/* <span class="material-sybols-outlined">{props.icone}</span> */}
      </button>
      {statusc()}
    </div>
  );
};

export default Login;
