import {Navigate, useNavigate} from '@solidjs/router';
import {Component, JSX, createSignal} from "solid-js";

const Cadastrar: Component = () => {
  const navigate = useNavigate();

  const LForm: Component<{titulo: string, nome: string, type?: string;}> = (props) => (
    <div>
      <h3 class="text-xl">{props.titulo}</h3>
      <input class="w-96 outline-none bg-transparent text-lg border-b border-gray-700 mt-1" id={`${props.nome}-form`} type={props.type} />
    </div>
  );

  const [statusc, setStatus] = createSignal<JSX.Element>();
  const sendCadastrar = async () => {
    if ((document.getElementById("senha-form") as HTMLInputElement).value !== (document.getElementById("conf-senha-form") as HTMLInputElement).value) {
      console.error("As senhas não são iguais");
      return;
    }

    const res = await fetch("http://localhost:3000/api/cadastrar", {
      method: 'POST', body: JSON.stringify({
        nome: (document.getElementById("nome-form") as HTMLInputElement).value,
        email: (document.getElementById("email-form") as HTMLInputElement).value,
        senha: (document.getElementById("senha-form") as HTMLInputElement).value,
        telefone: (document.getElementById("telefone-form") as HTMLInputElement).value,
      })
    });
    if (res.status === 200) {
      navigate('/');
    } else {
      let msg = await res.text();
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possível cadastrar-se: {msg}</span>);
    };
  };

  return (
    <div class="mx-8 my-10 md:mx-20 flex flex-col items-start gap-5">
      <h1 class="text-4xl">Cadastre-se agora mesmo no WebPets</h1>
      <LForm titulo="Nome Completo" nome="nome" />
      <LForm titulo="E-Mail" nome="email" type="email" />
      <LForm titulo="Senha" nome="senha" type="password" />
      <LForm titulo="Confirmar Senha" nome="conf-senha" type="password" />
      <LForm titulo="Telefone" nome="telefone" type="tel" />
      <div class="flex items-center gap-2">
        <input type="checkbox" class="w-6 h-6" id="term-form" />
        {/* @ts-ignore */}
        <h3 class="text-lg cursor-pointer select-none" onClick={() => document.getElementById("term-form").checked = !document.getElementById("term-form").checked}>Eu concordo com os termos de consentimento</h3>
      </div>

      <button onClick={sendCadastrar} class="bg-fundo1 text-lg flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto">
        <span>Cadastrar</span>
        {/* <span class="material-symbols-outlined">{props.icone}</span> */}
      </button>
      {statusc()}
    </div>
  );
};

export default Cadastrar;