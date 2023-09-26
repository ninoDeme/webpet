import { useNavigate } from '@solidjs/router';
import { Component, JSX, createSignal } from "solid-js";
import WPForm from '../templates/WPForm';

const Cadastrar: Component = () => {
  const navigate = useNavigate();


  let form: {
    nome?: HTMLInputElement,
    email?: HTMLInputElement,
    senha?: HTMLInputElement,
    confSenha?: HTMLInputElement,
    telefone?: HTMLInputElement,
    termos?: HTMLInputElement
  } = {}

  const [statusc, setStatus] = createSignal<JSX.Element>();
  const sendCadastrar = async () => {
    try {
      if (!form.termos?.checked) {
        throw new Error("Aceite os termos de consentimento")
      }
      if (form.senha?.value !== form.confSenha?.value) {
        throw new Error("As senhas não são iguais")
      }
    } catch (e) {
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possível cadastrar-se: {e.message}</span>);
      console.error(e);
      return;
    }

    const res = await fetch("http://localhost:3000/api/cadastrar", {
      method: 'POST', body: JSON.stringify({
        nome: form.nome?.value,
        email: form.email?.value,
        senha: form.senha?.value,
        telefone: form.telefone?.value,
      })
    });
    if (res.status === 200) {
      navigate('/cadastro-sucesso');
    } else {
      let msg = await res.text();
      setStatus(<span class="text-red-600 font-bold text-lg">Não foi possível cadastrar-se: {msg}</span>);
    };
  };

  return (
    <form onSubmit={(form) => {sendCadastrar(); form.preventDefault()}} class="mx-8 my-10 md:mx-20 flex flex-col items-start gap-5">
      <h1 class="text-4xl">Cadastre-se agora mesmo no WebPets</h1>
      <WPForm titulo="Nome Completo" nome="nome" ref={form.nome}/>
      <WPForm titulo="E-Mail" nome="email" type="email" ref={form.email}/>
      <WPForm titulo="Senha" nome="senha" type="password" ref={form.senha}/>
      <WPForm titulo="Confirmar Senha" nome="conf-senha" type="password" ref={form.confSenha}/>
      <WPForm titulo="Telefone" nome="telefone" type="tel" ref={form.telefone}/>
      <div class="flex items-center gap-2">
        <input type="checkbox" class="w-6 h-6" id="term-form" ref={form.termos}/>
        {/* @ts-ignore */}
        <h3 class="text-lg cursor-pointer select-none" onClick={() => document.getElementById("term-form").checked = !document.getElementById("term-form").checked}>Eu concordo com os termos de consentimento</h3>
      </div>

      <button type="submit" class="bg-fundo1 text-lg flex justify-center gap-2 rounded py-1 px-4 hover:bg-fundo_alt hover:text-texto">
        <span>Cadastrar</span>
        {/* <span class="material-symbols-outlined">{props.icone}</span> */}
      </button>
      {statusc()}
    </form>
  );
};

export default Cadastrar;
